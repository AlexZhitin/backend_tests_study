package Tests.POJO;

import com.fasterxml.jackson.core.JsonProcessingException;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class createJiraTicket {

    Properties prop = new Properties();
    private static String propertiesPath = System.getProperty("user.dir") + "/src/test/java/files/env.properties";

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream(propertiesPath);
        prop.load(fis);
    }

    @Test(priority = 1)
    public void createJiraIssue() throws JsonProcessingException {



        //Creating issue
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", ReusableMethods.getSessionKey())
                .body(FinalPayload.FinalBody()).log().body()
                .post("/rest/api/2/issue")
                .then().log().body().statusCode(201).extract().response();


        String issue_id = res.jsonPath().get("id");
        System.out.println(issue_id);
        System.out.println(res.getBody().jsonPath().prettify());
    }
}
