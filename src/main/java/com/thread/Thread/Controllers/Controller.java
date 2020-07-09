package com.thread.Thread.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface  Controller {
    enum socialPlatform {
        FACEBOOK,
        TWITTER,
        INSTAGRAM
    }

    enum  reactionType{
        FACELIKE,
        TWITLIKE,
        INSTLIKE
    }

    @GetMapping("/search")
    void search(String queryText, socialPlatform platform);
    @PostMapping("/reaction")
    void reaction(reactionType reaction);
    @PostMapping("/comment")
    void comment(String commentText);
    @PostMapping("/share")
    void share();
}


