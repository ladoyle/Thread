package com.thread.Thread.controllers;

import com.thread.Thread.models.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public abstract class Controller {
    @GetMapping("/search")
    abstract ResponseEntity<?> search(String q);

    ResponseEntity<?> share(Model body) { return null; }
    ResponseEntity<?> react(Model body) { return null; }
    ResponseEntity<?> comment(Model body) { return null; }
}
