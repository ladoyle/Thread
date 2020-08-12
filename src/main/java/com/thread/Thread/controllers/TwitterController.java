package com.thread.Thread.controllers;

import com.thread.Thread.models.TwitterModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/twitter")
public class TwitterController extends Controller {
    private Twitter twitter;


    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        twitter = TwitterFactory.getSingleton();
        request.getSession().setAttribute("twitter", twitter);

        try {
            StringBuffer callbackURL = request.getRequestURL();
            callbackURL.append("/callback");
            RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
            request.getSession().setAttribute("requestToken", requestToken);
            response.sendRedirect(requestToken.getAuthenticationURL());

        } catch (TwitterException e) {
            throw new ServletException(e);
        }
    }

    @GetMapping("/login/callback")
    public ResponseEntity<Map> callback(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);

            String screenName = accessToken.getScreenName();
            String userIdString = String.valueOf(accessToken.getUserId());
            String accessTokenString = accessToken.getToken();
            String accessTokenSecretString = accessToken.getTokenSecret();

            Map<String, String> obj = new HashMap<String, String>();
            obj.put("screenName", screenName);
            obj.put("userId", userIdString);
            obj.put("accessToken", accessTokenString);
            obj.put("accessTokenSecret", accessTokenSecretString);
            System.out.println(obj);

            twitter.setOAuthAccessToken(accessToken);
            request.getSession().removeAttribute("requestToken");
            return new ResponseEntity<>(obj, HttpStatus.OK);
        } catch (TwitterException e) {
            throw new ServletException(e);
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+ "/");
    }

    /*
    @GetMapping("/post")
    public ResponseEntity<Status> post(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Twitter4j Setup
        twitter = (Twitter)request.getSession().getAttribute("twitter");
        Status retweet = null;

        //Retweet status based on id given in request body
        try {
            retweet = twitter.updateStatus("Testing Twitter");
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if(retweet == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        response.sendRedirect(request.getContextPath()+ "/");
        return new ResponseEntity<>(retweet, HttpStatus.OK);
    }
    */

    @Override
    public ResponseEntity<List<Status>> search(@RequestParam String q) {
        //Set up decoder to decode encoded query
        byte[] encodedBytes = Base64.getEncoder().encode(q.getBytes());
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        String decodedString = new String(decodedBytes);

        //Twitter4j Setup
        twitter = TwitterFactory.getSingleton();
        Query query = new Query(decodedString);

        //Search tweets related to the query
        QueryResult result = null;
        try {
            result = twitter.search(query);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        //Print tweets related to query
        List<Status> tweets = result.getTweets();
        System.out.println("Total number of tweets: "+ tweets.size());
        for (Status status : tweets) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }

        if(result == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tweets, HttpStatus.OK);
    }

    @PostMapping("/share")
    public ResponseEntity<Status> share(@RequestBody TwitterModel body) {
        //Twitter4j Setup
        twitter = TwitterFactory.getSingleton();
        Status retweet = null;

        //Retweet status based on id given in request body
        try {
            retweet = twitter.retweetStatus(body.getId());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if(retweet == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(retweet, HttpStatus.OK);
    }

    @PostMapping("/react")
    public ResponseEntity<Status> react(@RequestBody TwitterModel body) {
        //Twitter4j Setup
        twitter = TwitterFactory.getSingleton();
        Status reaction = null;

        //Favorite status based on id given in request body
        try {
            reaction = twitter.createFavorite(body.getId());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if(reaction == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reaction, HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<Status> comment(@RequestBody TwitterModel body) {
        //Twitter4j Setup
        twitter = TwitterFactory.getSingleton();
        Status comment = null;

        //Comment status based on id given in request body
        try {
            //Get status based on id given in request body
            Status status = twitter.showStatus(body.getId());
            //Reply to that status with a comment
            comment = twitter.updateStatus(
                    new StatusUpdate(" @" + status.getUser().getScreenName() + " " + body.getMessage()).inReplyToStatusId(body.getId())
            );
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if(comment == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/share")
    public ResponseEntity<Status> unshared(@RequestBody TwitterModel body) {
        //Twitter4j Setup
        twitter = TwitterFactory.getSingleton();
        Status retweet = null;

        //Retweet status based on id given in request body
        try {
            retweet = twitter.destroyStatus(body.getId());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if(retweet == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(retweet, HttpStatus.OK);
    }

    @DeleteMapping("/react")
    public ResponseEntity<Status> unreact (@RequestBody TwitterModel body) {
        //Twitter4j Setup
        twitter = TwitterFactory.getSingleton();
        Status reaction = null;

        //Retweet status based on id given in request body
        try {
            reaction = twitter.destroyFavorite(body.getId());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        if(reaction == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reaction, HttpStatus.OK);
    }
}
