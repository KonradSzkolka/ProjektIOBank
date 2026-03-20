package vod.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vod.model.GuildBalance;
import vod.service.GuildBalanceService;

import java.util.List;

@Controller
public class GuildBankViewController {

    private final GuildBalanceService guildBalanceService;

    public GuildBankViewController(GuildBalanceService guildBalanceService) {
        this.guildBalanceService = guildBalanceService;
    }

    @GetMapping("/guild-bank")
    public String showGuildBank(Model model) {
        List<GuildBalance> balances = guildBalanceService.getAllBalances();
        model.addAttribute("title", "Stan banku gildii");
        model.addAttribute("balances", balances);
        return "guildBankView"; // templates/guildBankView.html
    }
}
