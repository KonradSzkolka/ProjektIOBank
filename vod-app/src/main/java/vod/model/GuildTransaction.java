package vod.model;

import java.time.LocalDateTime;

public class GuildTransaction {
    public Long id;
    public GuildMember member;
    public String itemId;
    public int count;
    public long coins;
    public LocalDateTime time;
    public TransactionType type;

    public GuildTransaction(Long id,
                            GuildMember member,
                            String itemId,
                            int count,
                            long coins,
                            LocalDateTime time,
                            TransactionType type) {
        this.id = id;
        this.member = member;
        this.itemId = itemId;
        this.count = count;
        this.coins = coins;
        this.time = time;
        this.type = type;
    }
}
