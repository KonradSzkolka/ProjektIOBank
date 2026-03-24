package vod.model;

import jakarta.persistence.*;

@Entity
@Table(name = "guild_transaction")
public class GuildTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")   // FK do guild_member.member_id
    public GuildMember member;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public TransactionType type;

    @Column(name = "coins")
    public long coins;

    // jeśli masz timestamp/description – dodaj odpowiednie kolumny

    public GuildTransaction() {
    }

    public GuildTransaction(GuildMember member, TransactionType type, long coins) {
        this.member = member;
        this.type = type;
        this.coins = coins;
    }
}
