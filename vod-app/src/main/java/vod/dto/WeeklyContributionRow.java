package vod.dto;

import java.util.Map;

public class WeeklyContributionRow {
    public String accountName;
    public Map<String, Long> weeklyAmounts; // "YYYY-Www" -> coins
    public long total;
}