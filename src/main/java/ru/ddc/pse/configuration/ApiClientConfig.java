package ru.ddc.pse.configuration;

import com.manticoresearch.client.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiClientConfig {
    @Value("${manticore.server.url}")
    private String manticoreServerUrl;

    @Bean
    public ApiClient getApiClient() {
        ApiClient client = com.manticoresearch.client.Configuration.getDefaultApiClient();
        client.setBasePath(manticoreServerUrl);
        return client;
    }
}
