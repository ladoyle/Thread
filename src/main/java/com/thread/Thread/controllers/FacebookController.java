package com.thread.Thread.controllers;
import com.thread.Thread.models.FaceBookModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Facebook")
public class FacebookController extends Controller {

    private String accessToken = "287242839181671|De0nEwSFA0y9pvhRxiKLkdZGhGg";
    private String domain = "https://www.facebook.com/";
    private String appID = "287242839181671";
    private String permissions = "user_about_me";
    private String authURL = "https://287242839181671graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appID
            +"&redirect_uri="+domain+"&scope="+permissions;
    // This will be replaced with dynamic userAccessToken
    private String userAccessToken = "??? need a new one!!!";

    //Facebook userFB = null;
    // replace this v with ^ + initializing it in the login
    Facebook userFB = new FacebookTemplate(userAccessToken); // is Thread right to pass

    public ResponseEntity login() {


//        // This will be the last thing after successful login
//        userFB = new FacebookTemplate(userAccessToken, "Thread"); // is Thread right to pass
//        System.out.println("FB Login");
        return null;
    }

    @Override
    public ResponseEntity search(@RequestParam String q) {
        assert userFB != null: "unset userFB";

        PagedList<Post> posts = null;
        System.out.println("userFB is = " + userFB);
        if(userFB == null) {
            //some undefined behavior
            System.out.println("userFB is null");
            return null;
        }

        try {
            System.out.println("userFB is try = " + userFB);

            posts = userFB.feedOperations().getHomeFeed();
        }
        catch (org.springframework.social.ApiException ignored){
            System.out.println("here???");
            System.out.println(ignored.getMessage());
        }
        System.out.println(">>> "+ posts);
        // search through posts and find the relevant ones
        return new ResponseEntity<> (posts, HttpStatus.OK);

    }


    public ResponseEntity share(FaceBookModel body) {
        assert userFB != null: "unset userFB";

        return null;
    }

    @PostMapping("/react")
    public ResponseEntity react(@RequestBody FaceBookModel body) {
        //like, love, care, haha, wow, sad, angry
        assert userFB != null: "unset userFB";
        try {
            userFB.likeOperations().like(String.valueOf(body.getId()));
        }
        catch (org.springframework.social.ApiException ignore) {
            System.out.println(ignore.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity comment(@RequestBody FaceBookModel body) {
        assert userFB != null: "unset userFB";
        String commentID;
        try{
            commentID = userFB.commentOperations().addComment(
                    String.valueOf(body.getId()), body.getMessage());
        }
        catch (org.springframework.social.ApiException ignore) {
            System.out.println(ignore.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(commentID, HttpStatus.OK);
    }


    public ResponseEntity unshared(@RequestBody FaceBookModel body) {
        assert userFB != null: "unset userFB";

        return null;
    }

    @DeleteMapping("/react")
    public ResponseEntity unreact(@RequestBody FaceBookModel body) {
        assert userFB != null: "unset userFB";
        try {
            userFB.likeOperations().unlike(String.valueOf(body.getId()));
        }
        catch (org.springframework.social.ApiException ignore) {
            System.out.println(ignore.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
