package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.GuildTransaction;

import java.util.List;

public interface GuildTransactionRepository extends JpaRepository<GuildTransaction, Long> {

    List<GuildTransaction> findByMember_AccountName(String accountName);
}
