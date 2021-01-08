package com.project.thread.controllers;

import com.project.thread.services.RedditService;
import com.project.thread.models.reddit.RedditModel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/reddit")
public class RedditController extends Controller {

    private final RedditService reddit;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RedditModel body) {
        System.out.println("Reddit Login");
        return reddit.login(body);
    }

    @Override
    ResponseEntity<?> search(String q) {
        return null;
    }
}
