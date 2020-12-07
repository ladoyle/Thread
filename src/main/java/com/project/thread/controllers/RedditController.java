package com.project.thread.controllers;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.oauth.Credentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reddit")
public class RedditController extends Controller {
    @Value("${reddit.credentials.clientId}")
    private static String clientId;

    @Value("${reddit.credentials.clientSecret}")
    private static String clientSecret;

    @Value("${reddit.redirect_uri}")
    private static String redirectUri;

    private static final UserAgent userAgent =
            new UserAgent("web", "com.project.thread", "v1.0", "healfein");
    private static final Credentials credentials =
            Credentials.webapp(clientId, clientSecret, redirectUri);

    @Override
    ResponseEntity<?> search(String q) {
        return null;
    }
}
