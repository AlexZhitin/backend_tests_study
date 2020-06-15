package Tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.ReusableMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class basics6_jira_Test {

    Properties prop = new Properties();
    private static String propertiesPath = System.getProperty("user.dir") + "/src/test/java/files/env.properties";

    String issue_id;
    String comment_id;

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream(propertiesPath);
        prop.load(fis);
    }

    @Test(priority = 1)
    public void createJiraIssue() {

        //Creating issue
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("content-type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey()).
                body("{	\"fields\": {        \"project\":        {        	" +
                        "\"key\": \"RES\"        },        " +
                        "\"summary\":\"REST test summary\",           " +
                        "\"description\": \"Creating the test report\",          " +
                        "\"issuetype\": {          	" +
                        "\"name\":\"Bug\"          }        }	}").
                when().
                post("/rest/api/2/issue").
                then().log().body().statusCode(201).extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);
        issue_id = js.get("id");
        System.out.println(issue_id);

    }

    /*@Test
         //Deleting issue
    public void deleteIssue() {
        RestAssured.baseURI = "http://localhost:8080";
        given().header("content-type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey()).
                param("deleteSubtasks", "true"). //deletes all subtasks linked to the issue
                when().
                delete("/rest/api/2/issue/" + issue_id).
                then().log().body().statusCode(204).extract().response();

        System.out.println(issue_id + " is deleted");
    }*/

    @Test(priority = 2)

    public void addCommentToIssue() {

        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("content-type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey())
                .body("{  \"visibility\": {    \"type\": \"role\",    \"value\": \"Administrators\"  },  \"body\": \"Some comment.\"}")
                .when()
                .post("/rest/api/2/issue/" + issue_id + "/comment")
                .then().log().body().statusCode(201).extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);
        comment_id = js.getString("id");
        System.out.println(comment_id);
        System.out.println("Comment added");

    }

    @Test(priority = 3)

    public void updateComment() {

        RestAssured.baseURI = "http://localhost:8080";
        given().header("content-type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey())
                .body("{  \"visibility\": {    \"type\": \"role\",    \"value\": \"Administrators\"  },  \"body\": \"Some comment (updated).\"}")
                .when()
                .put("/rest/api/2/issue/" + issue_id + "/comment/" + comment_id)
                .then().log().body().statusCode(200);

        System.out.println("Comment updated");
    }

}