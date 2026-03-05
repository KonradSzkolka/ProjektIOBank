package vod.service;

import vod.model.GuildBalance;

import java.util.List;
import java.util.Optional;

public interface GuildBalanceService {
    List<GuildBalance> getAllBalances();
    Optional<GuildBalance> getBalanceForMember(String accountName);
}
