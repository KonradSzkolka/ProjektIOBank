package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.model.GuildBalance;
import vod.model.GuildMember;
import vod.repository.GuildBalanceDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemGuildBalanceDao implements GuildBalanceDao {

    private final List<GuildBalance> balances = new ArrayList<>();

    public MemGuildBalanceDao() {
        // proste dane demo, bez SampleData:
        GuildMember m1 = new GuildMember("Korcyk.1234", "Leader");
        GuildMember m2 = new GuildMember("GuildMate.5678", "Member");

        balances.add(new GuildBalance(m1, 1000L, null));
        balances.add(new GuildBalance(m2, 500L, null));
    }

    @Override
    public List<GuildBalance> findAll() {
        return balances;
    }

    @Override
    public Optional<GuildBalance> findByMember(String accountName) {
        return balances.stream()
                .filter(b -> b.member.accountName.equals(accountName))
                .findFirst();
    }
}
