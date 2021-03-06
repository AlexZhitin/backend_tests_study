package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.resources;
import files.payload;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class basicsPostDeleteTest {
    private static Logger log = LogManager.getLogger(basicsPostDeleteTest.class.getName());
    Properties prop = new Properties();
    private static String propertiesPath = System.getProperty("user.dir") + "/src/test/java/files/env.properties";

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream(propertiesPath);
        prop.load(fis);
    }

    @Test

    public void AddDeletePlace() {

        //Grab the response

        // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&key=AIzaSyD8CTAbN0kl2a-mpJoVVk1j1drJg6N5g0U
        log.info("Host information" + prop.getProperty("HOST"));
        RestAssured.baseURI = prop.getProperty("HOST");

        Response res = given().
                queryParam("key", prop.getProperty("KEY")).
                body(payload.getPostData()).
                when().post(resources.placePostData()).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK")).extract().response();


        //Grab the place_id from response

        String responseString = res.asString();
        System.out.println(responseString);
        log.info(responseString);

        JsonPath js = new JsonPath(responseString);
        String place_id = js.get("place_id"); //Had to add JsonObject dependency to make it work
        System.out.println(place_id);


        //Place the "place_id" to delete request
        given().
                queryParam("key", prop.getProperty("KEY")).
                body(payload.getPlace_id(place_id)).
                when().post(resources.placeDeleteData()).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));

    }
}