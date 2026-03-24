package vod.model;

import jakarta.persistence.*;

@Entity
@Table(name = "guild_member")
public class GuildMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    public Long id;

    @Column(name = "account_name", nullable = false, unique = true)
    public String accountName;

    @Column(name = "role")
    public String role;

    public GuildMember() {
    }

    public GuildMember(String accountName, String role) {
        this.accountName = accountName;
        this.role = role;
    }
}
