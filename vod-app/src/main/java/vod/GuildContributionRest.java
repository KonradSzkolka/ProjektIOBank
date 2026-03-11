package vod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vod.dto.WeeklyContributionRow;
import vod.dto.ContributionEntry;
import vod.service.GuildContributionService;

import java.util.List;

@RestController
@RequestMapping("/webapi")
public class GuildContributionRest {

    private final GuildContributionService service;
    private final String guildId = "2B1FBD6B-7F6C-F011-81AA-F91D67309373";

    public GuildContributionRest(GuildContributionService service) {
        this.service = service;
    }

    @GetMapping("/weekly-contributions")
    public List<WeeklyContributionRow> contributions(
            @RequestParam("apiKey") String apiKey) {
        return service.getWeeklyContributions(guildId, apiKey);
    }

    @GetMapping("/contributions-raw")
    public List<ContributionEntry> raw(
            @RequestParam("apiKey") String apiKey) {
        return service.getRawContributions(guildId, apiKey);
    }
    @GetMapping("/weekly-contributions-filtered")
    public List<WeeklyContributionRow> contributionsFiltered(
            @RequestParam("apiKey") String apiKey,
            @RequestParam(name = "minGold", required = false) Integer minGold
    ) {
        List<WeeklyContributionRow> all = service.getWeeklyContributions(guildId, apiKey);

        if (minGold == null) {
            return all;
        }

        return all.stream()
                .filter(row -> row.getTotal() >= minGold)
                .toList();
    }
}
