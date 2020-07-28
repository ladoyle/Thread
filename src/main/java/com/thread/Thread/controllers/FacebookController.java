package com.thread.Thread.controllers;
import com.thread.Thread.models.FaceBookModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/Facebook")
public class FacebookController extends Controller {

    private final String appID = "287242839181671";
    private final String appSecret = "ab1eb7052493f7fe024f9dbc70593cc0";
    private Facebook facebook;
    private AccessGrant accessToken;

    private final FacebookConnectionFactory connectionFactory =
            new FacebookConnectionFactory(appID, appSecret);

    @GetMapping("/login")
    public String login() {
        OAuth2Operations operations = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();

        params.setRedirectUri("http://localhost:8080/Facebook/login/auth");
        params.setScope("email,public_profile");

        String url = operations.buildAuthenticateUrl(params);
        System.out.println("The URL is" + url);
        return "redirect:" + url;
    }

    @RequestMapping("/login/auth")
    public ModelAndView loginAuthenticate(@RequestParam("code") String authorizationCode) {
        OAuth2Operations operations = connectionFactory.getOAuthOperations();
        accessToken = operations.exchangeForAccess(
                authorizationCode,
                "http://localhost:8080/Facebook/login/auth",
                null);

        Connection<Facebook> connection = connectionFactory.createConnection(accessToken);
        facebook = connection.getApi();
        String[] fields = { "id", "email", "first_name", "last_name" };
        User userProfile = facebook.fetchObject("me", User.class, fields);
        ModelAndView model = new ModelAndView("details");
        model.addObject("user", userProfile);
        return model;
    }

    @Override
    public ResponseEntity search(@RequestParam String q) {
        PagedList<Post> posts = facebook.feedOperations().getFeed();

        /*try {
            posts = userFB.feedOperations().getHomeFeed();
        }
        catch (org.springframework.social.ApiException ignored){
            System.out.println(ignored.getMessage());
          return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }*/

        System.out.println(">>> "+ posts);
        // search through posts and find the relevant ones
        return new ResponseEntity<> (posts, HttpStatus.OK);

    }


    public ResponseEntity share(FaceBookModel body) {
        assert facebook != null: "unset userFB";

        return null;
    }

    @PostMapping("/react")
    public ResponseEntity react(@RequestBody FaceBookModel body) {
        //like, love, care, haha, wow, sad, angry
        assert facebook != null: "unset userFB";
        try {
            facebook.likeOperations().like(String.valueOf(body.getId()));
        }
        catch (org.springframework.social.ApiException ignore) {
            System.out.println(ignore.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

/*    temporary decision: no commenting
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
*/

    public ResponseEntity unshared(@RequestBody FaceBookModel body) {
        assert facebook != null: "unset userFB";

        return null;
    }

    @DeleteMapping("/react")
    public ResponseEntity unreact(@RequestBody FaceBookModel body) {
        assert facebook != null: "unset userFB";
        try {
            facebook.likeOperations().unlike(String.valueOf(body.getId()));
        }
        catch (org.springframework.social.ApiException ignore) {
            System.out.println(ignore.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
