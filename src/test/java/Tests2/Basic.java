package Tests2;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Basic {

    @Test

    public void get() {

        RestAssured.baseURI = "https://maps.googleapis.com";

        given().
                queryParam("location", "-33.8670520,151.1957370").
                queryParam("radius", "1000").
                queryParam("type", "restaurant").
                queryParam("key", "AIzaSyAHDZ_zMNZ-lyGtDvw6cwpqQNkS0CrmW_0").

                when().get("/maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200);
    }
}
