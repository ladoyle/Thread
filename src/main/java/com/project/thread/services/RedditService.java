package com.project.thread.services;

import com.project.thread.clients.RedditClient;
import com.project.thread.models.RedditModel;
import com.project.thread.models.reddit.RedditAccessToken;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Builder
@Component
public class RedditService {

    @Autowired
    private static RedditClient redditClient;
    private RedditAccessToken redditAccessToken;

    public boolean login(RedditModel body) {
        String authorizationCredentials = Base64.getEncoder().encodeToString(
                new byte[]{Byte.parseByte(
                        body.getId() +
                                ":" +
                                body.getKey()
                )}
        );

        redditAccessToken = redditClient.login(
                "Basic " + authorizationCredentials, body.getToken()
        );
        setAccessToken(redditAccessToken.getAccessToken());
        return true;
    }

    private void setAccessToken(String accessToken) {

    }
}
