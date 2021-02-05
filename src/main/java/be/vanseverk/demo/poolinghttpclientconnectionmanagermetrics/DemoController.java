package be.vanseverk.demo.poolinghttpclientconnectionmanagermetrics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

    private RestTemplate restTemplate;

    public DemoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String demoPage() {
        return restTemplate.getForObject("http://example.com/", String.class);
    }

}
