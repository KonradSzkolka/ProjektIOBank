package vod.repository;

import vod.model.GuildBalance;

import java.util.List;
import java.util.Optional;

public interface GuildBalanceDao {
    List<GuildBalance> findAll();
    Optional<GuildBalance> findByMember(String accountName);
}
