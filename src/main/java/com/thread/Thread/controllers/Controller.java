package com.thread.Thread.controllers;

import com.thread.Thread.models.Model;
import org.springframework.http.ResponseEntity;

public interface Controller {

    ResponseEntity search(String q);

    ResponseEntity share(Model body);

    ResponseEntity react(Model body);

    ResponseEntity comment(Model body);
}
