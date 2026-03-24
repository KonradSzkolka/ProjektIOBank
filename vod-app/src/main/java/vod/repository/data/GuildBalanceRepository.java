package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.GuildBalance;

import java.util.Optional;

public interface GuildBalanceRepository extends JpaRepository<GuildBalance, Long> {

    Optional<GuildBalance> findByMember_AccountName(String accountName);
}
