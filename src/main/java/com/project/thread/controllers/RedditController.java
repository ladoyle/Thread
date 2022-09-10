package com.project.thread.controllers;

import com.project.thread.services.RedditService;
import com.project.thread.models.reddit.RedditStrand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/reddit")
public class RedditController {

    private final RedditService redditService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RedditStrand strand) {
        System.out.println("Reddit Login");
        return redditService.login(strand);
    }

    @GetMapping("/search")
    ResponseEntity<?> search(String q) {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
