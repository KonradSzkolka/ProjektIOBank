package vod.repositroy.mem;

import org.springframework.stereotype.Repository;
import vod.model.*;
import vod.repositroy.GuildBalanceDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemGuildBalanceDao implements GuildBalanceDao {

    private final List<GuildBalance> balances = new ArrayList<>();

    public MemGuildBalanceDao() {
        List<GuildMember> members = SampleData.sampleMembers();
        List<GuildTransaction> transactions = SampleData.sampleTransactions(members);

        for (GuildMember member : members) {
            long netCoins = transactions.stream()
                    .filter(t -> t.member.accountName.equals(member.accountName))
                    .mapToLong(t -> t.type == TransactionType.DEPOSIT ? t.coins : -t.coins)
                    .sum();

            balances.add(new GuildBalance(member, netCoins, null));
        }
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
