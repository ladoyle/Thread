package com.thread.Thread.Controllers;

import com.thread.Thread.Models.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Facebook")
public class FacebookController implements Controller {

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
