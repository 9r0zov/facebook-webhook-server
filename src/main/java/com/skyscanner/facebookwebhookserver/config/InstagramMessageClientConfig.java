package com.skyscanner.facebookwebhookserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class InstagramMessageClientConfig {

    @Value("${instagram.access-token:token}")
    private String accessToken;
    @Value("${instagram.page-id:pageId}")
    private String pageId;

    @Bean
    public RestClient instagramClient() {
        return RestClient.builder()
                .baseUrl("https://graph.facebook.com/v19.0/" + pageId + "/messages")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", "Bearer " + accessToken)
                .build();
    }

}
