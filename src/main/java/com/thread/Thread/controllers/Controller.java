package com.thread.Thread.controllers;

import com.thread.Thread.models.TwitterModel;
import org.springframework.http.ResponseEntity;
import twitter4j.TwitterException;

public interface Controller {

    ResponseEntity search(String q);

    ResponseEntity share(TwitterModel body);

    ResponseEntity like(TwitterModel body);

    ResponseEntity comment(TwitterModel body);
}
