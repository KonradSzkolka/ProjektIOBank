package vod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vod.dto.WeeklyContributionRow;
import vod.dto.ContributionEntry;   // ← DODAJ TEN IMPORT
import vod.service.GuildContributionService;

import java.util.List;

@RestController
public class GuildContributionRest {

    private final GuildContributionService service;
    private final String guildId = "2B1FBD6B-7F6C-F011-81AA-F91D67309373";

    public GuildContributionRest(GuildContributionService service) {
        this.service = service;
    }

    // już istniejący endpoint tygodniowy
    @GetMapping("/weekly-contributions")
    public List<WeeklyContributionRow> contributions(
            @RequestParam("apiKey") String apiKey) {
        return service.getWeeklyContributions(guildId, apiKey);
    }

    // NOWY endpoint z surowymi wpisami
    @GetMapping("/contributions-raw")
    public List<ContributionEntry> raw(
            @RequestParam("apiKey") String apiKey) {
        return service.getRawContributions(guildId, apiKey);
    }
}
