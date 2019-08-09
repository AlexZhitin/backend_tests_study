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

public class basics7_jira_Test {

    Properties prop = new Properties();
    private static String propertiesPath = System.getProperty("user.dir") + "/src/test/java/files/env.properties";

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream(propertiesPath);
        prop.load(fis);
    }

    @Test
    public void JiraAPI() {

        //Creating comment

        String session_id = ReusableMethods.getSessionKey();
        RestAssured.baseURI = "http://localhost:8080";
        given().header("content-type", "application/json").
                header("Cookie", "JSESSIONID=" + session_id).
                body("{\"body\":\"Inserting comment from automation code\", " +
                        "\"visibility\": " +
                        "{    \"type\": \"role\",    " +
                        "\"value\": \"Administrators\"  }}").
                when().
                post("/rest/api/2/issue/" + ReusableMethods.getIssueID() + "/comment").
                then().statusCode(201).extract().response();

    }
}
