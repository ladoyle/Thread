package com.thread.Thread.controllers;

import com.thread.Thread.models.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Facebook")
public class FacebookController extends Controller {

    @Override
    public ResponseEntity search(String q) {
        return null;
    }

    @Override
    public ResponseEntity share(Model body) {
        return null;
    }

    @Override
    public ResponseEntity react(Model body) {
        return null;
    }

    @Override
    public ResponseEntity comment(Model body) {
        return null;
    }
}
