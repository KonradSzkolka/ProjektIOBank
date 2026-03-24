package vod.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import vod.gw2.Gw2ClientDynamic;
import vod.model.*;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GuildBankOnTheFlyService {

    private final Gw2ClientDynamic client;
    private final ObjectMapper mapper = new ObjectMapper();
    //private final String guildId = "TWOJ_GUILD_ID"; // albo parametr

    public GuildBankOnTheFlyService(Gw2ClientDynamic client) {
        this.client = client;
    }

    public List<GuildBalance> getBalancesForApiKey(String guildId, String apiKey) {
        try {
            String json = client.getGuildLogRaw(guildId, apiKey);

            List<Map<String, Object>> entries =
                    mapper.readValue(json, new TypeReference<>() {});

            // mapowanie logów GW2 → transakcje
            List<GuildTransaction> transactions = entries.stream()
                    .filter(e -> "stash".equals(e.get("type")))
                    .map(e -> {
                        String user = (String) e.get("user");
                        String operation = (String) e.get("operation");
                        long coins = e.get("coins") == null ? 0L
                                : ((Number) e.get("coins")).longValue();
                        String time = (String) e.get("time"); // na razie nie używamy

                        GuildMember member = new GuildMember(user, "");
                        TransactionType type = "deposit".equals(operation)
                                ? TransactionType.DEPOSIT
                                : TransactionType.WITHDRAW;

                        return new GuildTransaction(member, type, coins);
                    })
                    .toList();

            // agregacja do GuildBalance
            Map<String, Long> byUser = new HashMap<>();
            for (GuildTransaction t : transactions) {
                long delta = t.type == TransactionType.DEPOSIT ? t.coins : -t.coins;
                byUser.merge(t.member.accountName, delta, Long::sum);
            }

            return byUser.entrySet().stream()
                    .map(e -> new GuildBalance(
                            new GuildMember(e.getKey(), ""),
                            e.getValue(),
                            null
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to load GW2 data", e);
        }
    }
}
