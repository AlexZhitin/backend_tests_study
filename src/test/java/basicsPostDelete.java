import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.resources;
import files.payload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basicsPostDelete {

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

        JsonPath js = new JsonPath(responseString);
        String place_id = js.get("place_id"); //Had to add JsonObject dependency to make it work
        System.out.println(place_id);


        //Place the "place_id" to delete request
        given().
                queryParam("key", "qaclick123").
                body("{" +
                        "\"place_id\": \""+place_id+"\""+
    "}").
                when().post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));

    }
}