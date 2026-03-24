package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.GuildTransaction;
import vod.repository.GuildTransactionDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataGuildTransactionDao implements GuildTransactionDao {

    private final vod.repository.data.GuildTransactionRepository repo;

    @Override
    public List<GuildTransaction> findAll() {
        return repo.findAll();
    }

    @Override
    public List<GuildTransaction> findByMember(String accountName) {
        return repo.findByMember_AccountName(accountName);
    }
}
