package vod.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
@Table(name = "guild_balance")
public class GuildBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")   // FK do GuildMember.member_id
    public GuildMember member;

    @Column(name = "total_coins")     // dokładnie jedna kolumna
    public long totalCoins;

    @Transient                        // na razie nie mapujemy do tabeli
    public Map<String, Integer> itemCounts;

    public GuildBalance() {
    }

    public GuildBalance(GuildMember member, long totalCoins, Map<String, Integer> itemCounts) {
        this.member = member;
        this.totalCoins = totalCoins;
        this.itemCounts = itemCounts;
    }
}
