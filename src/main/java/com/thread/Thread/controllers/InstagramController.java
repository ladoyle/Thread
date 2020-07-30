package com.thread.Thread.controllers;

import com.thread.Thread.models.Model;
import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.brunocvcunha.instagram4j.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/instagram")
public class InstagramController extends Controller {
    String tempKey = "EAAlbeYvpA10BACL9jbckq0RLFC3gSwfFACPNLFmtiZCyWDFrOTeRmEeyNJaWq6VkLGuvmxkg2LlxigZCi4LZCBZA1Mj8gXnxrfZBptfof7aKI8XZAbofNJWOEVJeRnkDAHPEnim9NmVMjcTCEkMHDRHiTxxHHIGTem78eFmE6z7bEXWaIxCAMoJSM7mC6us28UR1vPd0vgoZCFiyYIpgkXUPTAbpiRpYoZBY01OIhExOJwZDZD";
    Instagram4j instagram = null;

    @GetMapping("/login")
    public void login() {
        //Hard code your Instagram credentials here, then uncomment
//        instagram = Instagram4j.builder().username("").password("").build();
        instagram.setup();

        try {
            instagram.login();
        }
        catch (ClientProtocolException loginClientProtocolException)
        {
            System.out.println("ClientProtocolException caught logging in");
        }
        catch (IOException loginIOException){
            System.out.println("IOException caught logging in");
        }
    }

    @Override
    public ResponseEntity<List<InstagramFeedItem>> search(String q) {
        InstagramFeedResult tagFeed = null;
        InstagramSearchUsernameResult userResult = null;

        try {
            tagFeed = instagram.sendRequest(new InstagramTagFeedRequest(q));
        }
        catch (ClientProtocolException feedClientProtocolException)
        {
            System.out.println("ClientProtocolException caught getting hashtag feed");
        }
        catch (IOException feedIOException){
            System.out.println("IOException caught getting hashtag feed");
        }

//        try {
//            userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(q));
//        }
//        catch (ClientProtocolException feedClientProtocolException)
//        {
//            System.out.println("ClientProtocolException caught getting username result");
//        }
//        catch (IOException feedIOException){
//            System.out.println("IOException caught getting username result");
//        }

//        if (tagFeed == null && userResult == null) {
//            System.out.println("No results");
//            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//        }

        if (tagFeed != null) {
            for (InstagramFeedItem feedResult : tagFeed.getItems()) {
                System.out.println("Post ID: " + feedResult.getPk());
            }
            System.out.println("Total feed count: " + tagFeed.getItems().size());
            return new ResponseEntity<>(tagFeed.getItems(), HttpStatus.OK);
        }

        else {
            System.out.println("No tagFeed");
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

//        if (userResult != null) {
//            System.out.println("User ID: " + userResult.getUser().getPk());
//            System.out.println("Full Name: " + userResult.getUser().getFull_name());
//
////            return new ResponseEntity<InstagramSearchUsernameResult>(userResult, HttpStatus.OK);
//        }
//
//        else {
//            System.out.println("No userResult");
//            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

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

    @Override
    public ResponseEntity unshared(Model body) { return null; }

    @Override
    public ResponseEntity unreact(Model body) { return null; }
}
