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
    // This will be replaced with logged in  userAccessToken
    private String userAccessToken = "EAAlbeYvpA10BAAVYxsRNpa8K3ex5exZC4ZBqVA8GG6d4DtMCbk1k0Czb3lemckXVdToKGPQBUL16YZAOFgf25j0DoJAdXYfvqNokQx8xTLvCNv0t9k197piZBmWxQvGzLlZBtZC5TKqFlU8744nZCW7ZA4hTZAI8efzsZC9ZAvP4ZAbvNe6QzG7gwdhDNVhEUQF0wLbtTJOycMibEJGQN5gsNdZBdpwaZCJL1NWc8pej8BnQiI1gZDZD";


    //Facebook userFB = null;
    // replace this v with ^ + initializing it in the login
    Facebook userFB = new FacebookTemplate(userAccessToken);

    public ResponseEntity login() {
        // TODO
        // This will be the last thing after successful login
        userFB = new FacebookTemplate(userAccessToken, "Thread"); // is Thread right to pass
        System.out.println("FB Login");
        return null;
    }

    @Override
    public ResponseEntity search(@RequestParam String q) {
        assert userFB != null: "unset userFB";

        PagedList<Post> posts = null;

        try {
            posts = userFB.feedOperations().getHomeFeed();
        }
        catch (org.springframework.social.ApiException ignored){
            System.out.println(ignored.getMessage());
          return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
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
