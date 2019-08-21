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

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream(propertiesPath);
        prop.load(fis);
    }

    @Test
    public void JiraAPI() {

        //Creating issue
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("content-type", "application/json").
                header("Cookie", "JSESSIONID=" +ReusableMethods.getSessionKey()).
                body("{	\"fields\": {        \"project\":        {        	" +
                        "\"key\": \"RES\"        },        " +
                        "\"summary\":\"REST summary\",           " +
                        "\"description\": \"Creating the third report\",          " +
                        "\"issuetype\": {          	" +
                        "\"name\":\"Bug\"          }        }	}").
                when().
                post("/rest/api/2/issue").
                then().statusCode(201).extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);
        String issue_id = js.get("id");
        System.out.println(issue_id);

    }
}
