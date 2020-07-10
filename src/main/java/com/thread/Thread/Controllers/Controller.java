package com.thread.Thread.Controllers;

import com.thread.Thread.Models.Model;
import org.springframework.http.ResponseEntity;

public interface Controller {

    ResponseEntity search(String q);

    ResponseEntity share(Model body);

    ResponseEntity react(Model body);

    ResponseEntity comment(Model body);
}
