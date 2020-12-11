package com.project.thread.controllers;

import com.project.thread.exceptions.RedditHttpException;
import com.project.thread.services.RedditService;
import com.project.thread.models.RedditModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reddit")
public class RedditController extends Controller {

    private static final RedditService reddit = RedditService.builder().build();

    @PostMapping("/login")
    public ResponseEntity<> login(@RequestBody RedditModel body) {
        System.out.println("Reddit Login");

        boolean authenticated = false;

        try {
            authenticated = reddit.login(body);
        }
        catch (RedditHttpException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "ERROR: Reddit Service API is down",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(authenticated, HttpStatus.OK);
    }

    @Override
    ResponseEntity<?> search(String q) {
        return null;
    }
}
