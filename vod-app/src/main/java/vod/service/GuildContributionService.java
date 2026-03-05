package vod.service;

import vod.dto.WeeklyContributionRow;
import vod.dto.ContributionEntry;
import java.util.List;

public interface GuildContributionService {
    List<WeeklyContributionRow> getWeeklyContributions(String guildId, String apiKey);
    List<ContributionEntry> getRawContributions(String guildId, String apiKey);
}
