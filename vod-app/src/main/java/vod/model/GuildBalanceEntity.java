package vod.model;

import jakarta.persistence.*;

@Entity
@Table(name = "guild_balance")
public class GuildBalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // np. "Korcyk.1234"
    @Column(name = "account_name", nullable = false)
    private String accountName;

    // ilość golda (w coinach, jak u Ciebie totalCoins)
    @Column(name = "total_coins", nullable = false)
    private long totalCoins;

    public GuildBalanceEntity() {
    }

    public GuildBalanceEntity(String accountName, long totalCoins) {
        this.accountName = accountName;
        this.totalCoins = totalCoins;
    }

    public Long getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getTotalCoins() {
        return totalCoins;
    }

    public void setTotalCoins(long totalCoins) {
        this.totalCoins = totalCoins;
    }
}
