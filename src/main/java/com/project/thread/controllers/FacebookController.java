package com.project.thread.controllers;

import com.project.thread.models.FacebookModel;
import facebook4j.*;
import facebook4j.auth.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/facebook")
public class FacebookController extends Controller{
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
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(extendedToken != null)
            System.out.println(extendedToken.toString());
        else
            System.out.println("empty token");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> search(@RequestParam String query) {
        System.out.println("FB Search");
        query = query.toLowerCase();
        ResponseList<Post> posts;
        ArrayList<Post> results = new ArrayList<>();
        try {
             posts = facebook.getFeed();
        }
        catch (FacebookException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(posts != null) {
            for (Post post : posts) {
                String message = post.getMessage();
                if (message != null && message.toLowerCase().contains(query) && !results.contains(post))
                    results.add(post);
            }
        }

        return results.isEmpty() ? new ResponseEntity<>(null, HttpStatus.OK)
                : new ResponseEntity<>(results, HttpStatus.OK);
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