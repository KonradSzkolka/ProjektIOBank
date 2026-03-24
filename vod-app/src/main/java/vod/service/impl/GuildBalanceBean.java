package vod.service.impl;

import org.springframework.stereotype.Service;
import vod.model.GuildBalance;
import vod.repository.GuildBalanceDao;
import vod.service.GuildBalanceService;

import java.util.List;
import java.util.Optional;

@Service
public class GuildBalanceBean implements GuildBalanceService {

    private final GuildBalanceDao balanceDao;

    public GuildBalanceBean(GuildBalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    @Override
    public List<GuildBalance> getAllBalances() {
        return balanceDao.findAll();
    }

    @Override
    public Optional<GuildBalance> getBalanceForMember(String accountName) {
        return balanceDao.findByMember(accountName);
    }
}
