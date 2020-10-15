package com.project.thread.controllers;

import com.project.thread.models.Model;
import facebook4j.FacebookException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public abstract class Controller {
    @GetMapping("/search")
    abstract ResponseEntity<?> search(String q) throws FacebookException;

    ResponseEntity<?> share(Model body) { return null; }
    ResponseEntity<?> react(Model body) { return null; }
    ResponseEntity<?> comment(Model body) { return null; }
    ResponseEntity<?> unshared(Model body) { return null; }
    ResponseEntity<?> unreact(Model body) { return null; }
}
