package com.project.thread.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import net.dean.jraw.*;

@RestController
@RequestMapping("/reddit")
public class RedditController extends Controller {
//    private static final UserAgent userAgent =
//            new UserAgent("");

    @Override
    ResponseEntity<?> search(String q) {
        return null;
    }
}
