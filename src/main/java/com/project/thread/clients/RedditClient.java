package com.project.thread.clients;

import com.project.thread.models.reddit.RedditAccessToken;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

public class RedditClient {
    private static final String accessTokenUrl = "https://www.reddit.com/api/v1/access_token";
    private WebClient httpClient = WebClient.create();

    public RedditAccessToken login(String basicAuthorization, String refreshToken) {

        WebClient.RequestBodySpec request = httpClient.post()
                .uri(
                        uriBuilder -> uriBuilder
                            .path(accessTokenUrl)
                            .queryParam("Authorization", basicAuthorization)
                            .build()
                )
                .header("grant_type", "refresh_token")
                .header("refresh_token", refreshToken);
        return Objects.requireNonNull(request.exchange().block()).bodyToMono(RedditAccessToken.class).block();
    }
}
