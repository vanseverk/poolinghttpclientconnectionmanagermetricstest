package be.vanseverk.demo.poolinghttpclientconnectionmanagermetrics;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class HttpConfiguration {
    private static final int MAX_TOTAL_CONNECTIONS = 20;
    private static final int MAX_PER_ROUTE_CONNECTIONS = 5;
    private static final int IDLE_CONNECTION_TTL_SECS = 30;

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.custom()
                .setMaxConnTotal(MAX_TOTAL_CONNECTIONS)
                .setMaxConnPerRoute(MAX_PER_ROUTE_CONNECTIONS)
                .setConnectionTimeToLive(IDLE_CONNECTION_TTL_SECS, SECONDS)
                .build();
    }

    @Bean
    public RestTemplate restTemplate(HttpClient client) {
        RestTemplate template = new RestTemplate();
        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
        return template;
    }

}