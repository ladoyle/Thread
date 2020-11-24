package com.project.thread.controllers;

import com.project.thread.models.FacebookModel;
import facebook4j.*;
import facebook4j.auth.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/facebook")
public class FacebookController extends Controller {
    private Facebook facebook;
    private static boolean isAppAuthorizationSet = false;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody FacebookModel body) {
        System.out.println("FB Login");

        facebook = FacebookFactory.getSingleton();
        AccessToken extendedToken;

        try {
            if(!isAppAuthorizationSet) {
                facebook.setOAuthAppId(body.getKey(), body.getKeySecret());
                isAppAuthorizationSet = true;
            }

            extendedToken = facebook.extendTokenExpiration(body.getToken());
            facebook.setOAuthAccessToken(extendedToken);
        }
        catch (FacebookException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "ERROR: Facebook Service API is down",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println(extendedToken);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        facebook = null;
        isAppAuthorizationSet = false;
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> search(@RequestParam String query) {
        final String key = query.toLowerCase();
        List<Post> results;
        try {
             ResponseList<Post> posts = facebook.getFeed();
             results = posts
                     .stream()
                     .filter(post ->
                             post.getMessage() != null &&
                             post.getMessage().toLowerCase().contains(key))
                     .collect(Collectors.toList());
        }
        catch (FacebookException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "ERROR: Facebook Service failed search",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/share")
    public ResponseEntity<?> share(@RequestBody FacebookModel body) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/share")
    public ResponseEntity<?> unshare(@RequestBody FacebookModel body) {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/react")
    public ResponseEntity<?> react(@RequestBody FacebookModel body) {
        System.out.println("FB React");
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/react")
    public ResponseEntity<?> unreact(@RequestBody FacebookModel body) {
        System.out.println("FB Unreact");
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> comment(@RequestBody FacebookModel body) {
        System.out.println("FB Comment");
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}