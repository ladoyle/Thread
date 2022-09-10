package com.project.thread.services;

import com.project.thread.clients.RedditClient;
import com.project.thread.models.reddit.RedditBasicAuthToken;
import com.project.thread.models.reddit.RedditStrand;
import com.project.thread.models.reddit.RedditAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class RedditService {

    private final RedditClient reddit;
    private RedditAccessToken accessToken;

    public ResponseEntity<?> login(RedditStrand strand) {
        String authCreds = strand.getId() + ":" + strand.getKey();
        String basicAuth = Base64.getEncoder().encodeToString(
                authCreds.getBytes()
        );

        RedditBasicAuthToken authToken;
        try {
            authToken = reddit.authorize(basicAuth);
            accessToken = reddit.login(authToken, strand.getToken());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "ERROR: Reddit Service API is down",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println(accessToken.getAccessToken());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
