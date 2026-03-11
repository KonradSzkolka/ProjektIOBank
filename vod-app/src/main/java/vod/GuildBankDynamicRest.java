package vod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vod.model.GuildBalance;
import vod.service.GuildBankOnTheFlyService;

import java.util.List;

@RestController
@RequestMapping("/webapi")
public class GuildBankDynamicRest {

    private final GuildBankOnTheFlyService service;
    private final String guildId = "2B1FBD6B-7F6C-F011-81AA-F91D67309373";

    public GuildBankDynamicRest(GuildBankOnTheFlyService service) {
        this.service = service;
    }

    @GetMapping("/balances-live")
    public List<GuildBalance> getBalancesLive(
            @RequestParam("apiKey") String apiKey) {

        return service.getBalancesForApiKey(guildId, apiKey);
    }
}
