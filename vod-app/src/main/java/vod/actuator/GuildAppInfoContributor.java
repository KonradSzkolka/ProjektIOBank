package vod.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GuildAppInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app",
                Map.of(
                        "name", "Guild Bank VOD",
                        "description", "Demo bank gildii z integracją GW2 API",
                        "author", "Korcyk"
                )
        );
    }
}
