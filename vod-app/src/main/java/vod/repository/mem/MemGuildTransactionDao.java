package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.model.GuildTransaction;
import vod.repository.GuildTransactionDao;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemGuildTransactionDao implements GuildTransactionDao {

    private final List<GuildTransaction> transactions;

    public MemGuildTransactionDao() {
        // bez SampleData – po prostu pusta lista
        this.transactions = Collections.emptyList();
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
