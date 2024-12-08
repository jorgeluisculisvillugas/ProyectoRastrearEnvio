package RastreoDeEnvio.Controlador;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/track")
public class TrackingController {

    @Value("${aftership.api.key}")
    private String apiKey;

    @GetMapping("/{slug}/{trackingNumber}")
    public ResponseEntity<String> trackPackage(
            @PathVariable String slug,
            @PathVariable String trackingNumber) {
        String url = "https://api.aftership.com/v4/trackings/" + slug + "/" + trackingNumber;

        RestTemplate restTemplate = new RestTemplate();
        var headers = new org.springframework.http.HttpHeaders();
        headers.add("aftership-api-key", apiKey);

        var entity = new org.springframework.http.HttpEntity<>(headers);
        return restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, String.class);
    }
}