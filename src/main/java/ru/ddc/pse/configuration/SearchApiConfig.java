package ru.ddc.pse.configuration;

import com.manticoresearch.client.ApiClient;
import com.manticoresearch.client.api.SearchApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchApiConfig {
    private final ApiClient client;

    @Autowired
    public SearchApiConfig(ApiClient client) {
        this.client = client;
    }

    @Bean
    public SearchApi getSearchApi() {
        return new SearchApi(client);
    }
}
