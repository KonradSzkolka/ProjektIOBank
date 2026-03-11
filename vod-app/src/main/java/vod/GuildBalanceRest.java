package vod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vod.model.GuildBalance;
import vod.service.GuildBalanceService;

import java.util.List;

@RestController
@RequestMapping("/webapi")
public class GuildBalanceRest {

    private final GuildBalanceService balanceService;

    public GuildBalanceRest(GuildBalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/balances")
    public List<GuildBalance> getBalances() {
        return balanceService.getAllBalances();
    }
}
