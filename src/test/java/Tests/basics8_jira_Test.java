package Tests;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class basics8_jira_Test {

    private static String issue_id = ReusableMethods.getIssueID(); //global static variable not to create extra issue

    Properties prop = new Properties();
    private static String propertiesPath = System.getProperty("user.dir") + "/src/test/java/files/env.properties";

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream(propertiesPath);
        prop.load(fis);
    }

    @Test
    public void JiraAPI() {

        //Updating the comment


        String session_id = ReusableMethods.getSessionKey();
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("content-type", "application/json").
                header("Cookie", "JSESSIONID=" + session_id).
                body("{\"body\":\"Inserting comment from automation code\", " +
                        "\"visibility\": " +
                        "{    \"type\": \"role\",    " +
                        "\"value\": \"Administrators\"  }}").
                when().
                post("/rest/api/2/issue/" + issue_id + "/comment").
                then().assertThat().statusCode(201).extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);
        String comment_id = js.get("id");
        System.out.println(issue_id);
        System.out.println(comment_id);


        RestAssured.baseURI = "http://localhost:8080";
        given().header("content-type", "application/json").
                header("Cookie", "JSESSIONID=" + session_id).
                pathParam("comment_id", comment_id).
                body("{\"body\":\"Updated comment\", " +
                        "\"visibility\": {    " +
                        "\"type\": \"role\",    " +
                        "\"value\": \"Administrators\"  }}").
                when().
                put("/rest/api/2/issue/" + issue_id + "/comment/{comment_id}"). //{comment_id} is a path parameter specified above
                then().assertThat().statusCode(200).extract().response();

    }
}
