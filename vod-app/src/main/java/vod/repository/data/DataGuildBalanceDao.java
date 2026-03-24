package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.GuildBalance;
import vod.repository.GuildBalanceDao;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class DataGuildBalanceDao implements GuildBalanceDao {

    private final GuildBalanceRepository repo;

    @Override
    public List<GuildBalance> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<GuildBalance> findByMember(String accountName) {
        return repo.findByMember_AccountName(accountName);
    }
}
