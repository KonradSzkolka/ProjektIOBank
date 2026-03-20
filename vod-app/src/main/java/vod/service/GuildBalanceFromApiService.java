package vod.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import vod.model.GuildBalance;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class GuildBalanceFromApiService implements GuildBalanceService {

    private final GuildBankOnTheFlyService onTheFlyService;

    // ten sam guildId co w REST
    private final String guildId = "2B1FBD6B-7F6C-F011-81AA-F91D67309373";
    // Twój klucz API na sztywno (na labach OK)
    private final String apiKey = "CDE25C6A-B2C2-C44D-B9AB-1CE85D8E79E8AF439A12-1048-4D7B-A0BA-BB33EC933C09";

    public GuildBalanceFromApiService(GuildBankOnTheFlyService onTheFlyService) {
        this.onTheFlyService = onTheFlyService;
    }

    @Override
    public List<GuildBalance> getAllBalances() {
        return onTheFlyService.getBalancesForApiKey(guildId, apiKey);
    }

    @Override
    public Optional<GuildBalance> getBalanceForMember(String accountName) {
        return getAllBalances().stream()
                .filter(b -> b.member.accountName.equals(accountName))
                .findFirst();
    }
}
