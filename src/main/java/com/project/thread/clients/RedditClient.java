package com.project.thread.clients;

import com.project.thread.models.reddit.RedditAccessToken;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Data
@Component
@RequiredArgsConstructor
public class RedditClient {
    private static final String ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token";
    private static final String USER_AGENT = "Thread Mobile App: android:com.project.thread:v1.0.0 (by /u/healfein)";
    private String accessToken;
    private WebClient httpClient;
    private boolean authenticated;

    public RedditAccessToken login(String basicAuthorization, String token) {

        if(!authenticated) {
            this.setAccessToken(token);

            WebClient.RequestBodySpec request = httpClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .path(ACCESS_TOKEN_URL)
                                    .queryParam("Authorization", basicAuthorization)
                                    .build()
                    )
                    .header("User-Agent", USER_AGENT)
                    .header("grant_type", "refresh_token")
                    .header("refresh_token", accessToken);
            return Objects.requireNonNull(request.exchange().block()).bodyToMono(RedditAccessToken.class).block();
        } else {
            return RedditAccessToken.builder()
                    .accessToken(accessToken)
                    .build();
        }
    }
}
