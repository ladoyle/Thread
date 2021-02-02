package com.project.thread.services;

import com.project.thread.clients.RedditClient;
import com.project.thread.models.reddit.RedditModel;
import com.project.thread.models.reddit.RedditAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class RedditService {

    private final RedditClient redditClient;

    public ResponseEntity<?> login(RedditModel body) {
        String authorization = body.getId() + ":" + body.getKey();
        String authorizationCredentials = Base64.getEncoder().encodeToString(
                authorization.getBytes()
        );

        RedditAccessToken redditAccessToken;
        try {
            redditAccessToken = redditClient.login("Basic " + authorizationCredentials, body.getToken());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "ERROR: Reddit Service API is down",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        redditClient.setAccessToken(redditAccessToken.getAccessToken());
        System.out.println(redditAccessToken.toString());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
