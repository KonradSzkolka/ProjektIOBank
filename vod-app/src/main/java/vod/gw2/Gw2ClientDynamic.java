package vod.gw2;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Gw2ClientDynamic {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://api.guildwars2.com/v2";

    // stara metoda (zostawiamy, ale już jej nie używamy w serwisie)
    public String getGuildLogRaw(String guildId, String apiKey) {
        String url = BASE_URL + "/guild/" + guildId + "/log";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    // NOWA: pobiera log od najstarszego (id rosnąco) do najnowszego
    public String getGuildLogFullRaw(String guildId, String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = BASE_URL + "/guild/" + guildId + "/log";

        // 1. najpierw pobierz „najnowszą stronę”, żeby poznać największe id
        ResponseEntity<String> firstResponse =
                restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String firstBody = firstResponse.getBody();

        // 2. wyciągamy max id z tej pierwszej odpowiedzi (prosty string hack)
        //    (u Ciebie i tak potem parsujemy JSON mapperem)
        long maxId = 0;
        if (firstBody != null && firstBody.contains("\"id\"")) {
            // bardzo uproszczone: szukamy wszystkich "id": N i bierzemy największe
            String[] parts = firstBody.split("\\{\"id\":");
            for (int i = 1; i < parts.length; i++) {
                String p = parts[i].split(",")[0].trim();
                try {
                    long id = Long.parseLong(p);
                    if (id > maxId) maxId = id;
                } catch (NumberFormatException ignored) {}
            }
        }

        // jeśli nie udało się określić maxId, po prostu zwracamy to co mamy
        if (maxId == 0) {
            return firstBody;
        }

        // 3. iterujemy od „maxId - 2000” w górę, aż do maxId (limituj, żeby nie zabić API)
        // w razie potrzeby możesz zmienić 2000 na większą liczbę
        StringBuilder all = new StringBuilder();
        all.append("[").append(firstBody.substring(1, firstBody.length() - 1)); // bez [ ]

        long step = 2000; // ile wpisów w dół na jedno zapytanie
        for (long from = maxId - step; from > 0; from -= step) {
            String pageUrl = url + "?since=" + from;

            ResponseEntity<String> resp =
                    restTemplate.exchange(pageUrl, HttpMethod.GET, entity, String.class);
            String body = resp.getBody();
            if (body == null || body.length() <= 2) {
                continue;
            }
            // dorzucamy kolejne wpisy (bez nawiasów [])
            all.append(",")
                    .append(body.substring(1, body.length() - 1));
        }

        all.append("]");
        return all.toString();
    }
}
