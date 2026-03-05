package vod.repositroy.mem;

import org.springframework.stereotype.Repository;
import vod.model.GuildMember;
import vod.model.GuildTransaction;
import vod.repositroy.GuildTransactionDao;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemGuildTransactionDao implements GuildTransactionDao {

    private final List<GuildTransaction> transactions;

    public MemGuildTransactionDao() {
        List<GuildMember> members = SampleData.sampleMembers();
        this.transactions = SampleData.sampleTransactions(members);
    }

    @Override
    public List<GuildTransaction> findAll() {
        return transactions;
    }

    @Override
    public List<GuildTransaction> findByMember(String accountName) {
        return transactions.stream()
                .filter(t -> t.member.accountName.equals(accountName))
                .collect(Collectors.toList());
    }
}

