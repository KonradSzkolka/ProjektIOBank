package vod.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import vod.dto.WeeklyContributionRow;
import vod.gw2.Gw2ClientDynamic;
import vod.model.GuildMember;
import vod.model.GuildTransaction;
import vod.model.TransactionType;
import vod.service.GuildContributionService;
import vod.dto.ContributionEntry;

import java.time.OffsetDateTime;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GuildContributionServiceImpl implements GuildContributionService {

    private final Gw2ClientDynamic client;
    private final ObjectMapper mapper = new ObjectMapper();

    public GuildContributionServiceImpl(Gw2ClientDynamic client) {
        this.client = client;
    }
    @Override
    public List<ContributionEntry> getRawContributions(String guildId, String apiKey) {
        try {
            String json = client.getGuildLogFullRaw(guildId, apiKey);

            List<Map<String, Object>> entries =
                    mapper.readValue(json, new TypeReference<>() {});

            return entries.stream()
                    .filter(e -> "stash".equals(e.get("type")))
                    .map(e -> {
                        String user = (String) e.get("user");
                        String operation = (String) e.get("operation");
                        long coins = e.get("coins") == null ? 0L
                                : ((Number) e.get("coins")).longValue();
                        String time = (String) e.get("time");

                        if (!"deposit".equals(operation)) {
                            coins = -coins; // wypłaty na minus
                        }

                        ContributionEntry ce = new ContributionEntry();
                        ce.accountName = user;
                        ce.coins = coins;
                        ce.time = OffsetDateTime.parse(time).toLocalDateTime();
                        return ce;
                    })
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load GW2 data", e);
        }
    }

    @Override
    public List<WeeklyContributionRow> getWeeklyContributions(String guildId, String apiKey) {
        try {
            String json = client.getGuildLogRaw(guildId, apiKey);

            List<Map<String, Object>> entries =
                    mapper.readValue(json, new TypeReference<>() {});

            // 1. Zamiana logów na ContributionEntry (jak wyżej)
            List<ContributionEntry> contributions = entries.stream()
                    .filter(e -> "stash".equals(e.get("type")))
                    .map(e -> {
                        String user = (String) e.get("user");
                        String operation = (String) e.get("operation");
                        long coins = e.get("coins") == null ? 0L
                                : ((Number) e.get("coins")).longValue();
                        String time = (String) e.get("time");

                        if (!"deposit".equals(operation)) {
                            coins = -coins;
                        }

                        ContributionEntry ce = new ContributionEntry();
                        ce.accountName = user;
                        ce.coins = coins;
                        ce.time = OffsetDateTime.parse(time).toLocalDateTime();
                        return ce;
                    })
                    .toList();

            // 2. Agregacja po tygodniach
            WeekFields wf = WeekFields.ISO;
            Map<String, Map<String, Long>> perUserPerWeek = new HashMap<>();

            for (ContributionEntry c : contributions) {
                long delta = c.coins;

                var date = c.time.toLocalDate();
                var firstDayOfWeek = date.with(wf.dayOfWeek(), 1); // poniedziałek
                var lastDayOfWeek  = date.with(wf.dayOfWeek(), 7); // niedziela

                String weekKey = firstDayOfWeek + " - " + lastDayOfWeek;

                perUserPerWeek
                        .computeIfAbsent(c.accountName, k -> new HashMap<>())
                        .merge(weekKey, delta, Long::sum);
            }

            // 3. Zbieramy tygodnie
            Set<String> allWeeks = perUserPerWeek.values().stream()
                    .flatMap(m -> m.keySet().stream())
                    .collect(Collectors.toCollection(TreeSet::new));

            List<WeeklyContributionRow> rows = new ArrayList<>();

            for (var entry : perUserPerWeek.entrySet()) {
                String account = entry.getKey();
                Map<String, Long> weekMap = entry.getValue();

                long total = weekMap.values().stream().mapToLong(Long::longValue).sum();

                WeeklyContributionRow row = new WeeklyContributionRow();
                row.accountName = account;
                row.weeklyAmounts = new LinkedHashMap<>();
                for (String week : allWeeks) {
                    row.weeklyAmounts.put(week, weekMap.getOrDefault(week, 0L));
                }
                row.total = total;
                rows.add(row);
            }

            rows.sort(Comparator.comparing(r -> r.accountName.toLowerCase()));
            return rows;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load GW2 data", e);
        }
    }

}
