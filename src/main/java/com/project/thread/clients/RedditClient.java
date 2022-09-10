package com.project.thread.clients;

import com.project.thread.core.ApplicationConstants;
import com.project.thread.core.ApplicationConstants.*;
import com.project.thread.core.headers.RedditHeaders;
import com.project.thread.core.httpparameters.RedditParams;
import com.project.thread.models.reddit.RedditAccessToken;
import com.project.thread.models.reddit.RedditBasicAuthToken;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Data
@Component
public class RedditClient {
    private String accessToken;
    private WebClient httpClient;
    private boolean authenticated;

    RedditClient() {
        this.httpClient = WebClient.builder().build();
    }

    public RedditBasicAuthToken authorize(String basicAuth) {
        return null;
    }

    public RedditAccessToken login(RedditBasicAuthToken basicAuthToken, String token) {

        if(!authenticated) {
            this.setAccessToken(token);

            WebClient.RequestBodySpec request = httpClient.post()
                    .uri(
                            uriBuilder -> uriBuilder
                                    .scheme(ApplicationConstants.DEFAULT_SCHEME)
                                    .host(Reddit.ACCESS_TOKEN_HOST)
                                    .path(Reddit.ACCESS_TOKEN_URL)
                                    .queryParam(RedditParams.GRANT_TYPE.getKey(), RedditParams.GRANT_TYPE.getValue())
                                    .queryParam(RedditParams.REFRESH_TOKEN.getKey(), accessToken)
                                    .build()
                    )
                    .header(RedditHeaders.USER_AGENT.getKey(), RedditHeaders.USER_AGENT.getValue())
                    .header(RedditHeaders.AUTHORIZATION.getKey(), basicAuthToken.getToken());
            return Objects.requireNonNull(request.exchange().block()).bodyToMono(RedditAccessToken.class).block();
        } else {
            return RedditAccessToken.builder()
                    .accessToken(accessToken)
                    .build();
        }
    }
}
