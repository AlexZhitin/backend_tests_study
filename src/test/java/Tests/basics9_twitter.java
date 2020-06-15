package Tests;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class basics9_twitter {

    String ConsumerKey = "xicJ4bsgo8Sgaxo40B003ohny";
    String ConsumerSecret = "587NjzA2sRy810AdF2jsJAqnwtyMRp8HY7BycXnnUJBx8bhSDt";
    String Token = "1157248755634253827-4pPRsiwmPBzEhp1FKrRBAmIwcPbjoV";
    String TokenSecret = "Yaz5a1P7lF110KQhpbjvq2Hvl9LH7egrIA0yIMG8c4E1U";
    String id;

    @Test

    public void getLatestTweet() {

        RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
        Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
                .param("count", "1")
                .when().get("/home_timeline.json")
                .then().extract().response();

        String response = res.asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String text = js.get("text").toString();
        System.out.println(text);
        String id = js.get("id").toString();
        System.out.println(id);
    }

    @Test

    public void createTweet() {

        RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
        Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
                .param("status", "New tweet created 8")
                .when().post("/update.json")
                .then().extract().response();

        String response = res.asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        System.out.println("Below is the tweet added");
        id = js.get("id").toString();
        System.out.println(id);
    }

    @Test

    public void deleteTweet() {
        RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
        Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
                .when().post("/destroy/" + id + ".json")
                .then().extract().response();

        String response = res.asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);

        System.out.println("Tweet which got deleted with Automation");
        String text = js.get("text").toString();
        System.out.println(text);
        String truncated = js.get("truncated").toString();
        System.out.println(truncated);//false if the tweet was deleted
        id = js.get("id").toString();
        System.out.println(id);


    }
}
