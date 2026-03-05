package vod.model;

import java.util.Map;

public class GuildBalance {
    public GuildMember member;
    public long totalCoins;
    public Map<String, Integer> itemCounts;

    public GuildBalance(GuildMember member, long totalCoins, Map<String, Integer> itemCounts) {
        this.member = member;
        this.totalCoins = totalCoins;
        this.itemCounts = itemCounts;
    }

    // jeśli itemCounts na razie nie używasz:
    public GuildBalance(GuildMember member, long totalCoins) {
        this(member, totalCoins, null);
    }
}
