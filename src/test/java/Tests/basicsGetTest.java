package Tests;

import Log4j.Log4j_packageA.Demo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class basicsGetTest {

    @Test

    public void Test() {


        // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&key=AIzaSyD8CTAbN0kl2a-mpJoVVk1j1drJg6N5g0U

        //Base URL or host
        RestAssured.baseURI = "https://maps.googleapis.com";
        RestAssured.given().//parameters, request headers, request cookies, body
                queryParam("location", "-33.8670522,151.1957362").
                queryParam("radius", "500").
                queryParam("key", "AIzaSyD8CTAbN0kl2a-mpJoVVk1j1drJg6N5g0U").

                when().get("/maps/api/place/nearbysearch/json").//resourse is passed here
                then().log().all().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].name",equalTo("Sydney")).and().
                body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"));
                /*header("edede,  frfrfrf").
                cookie("deded, dedede").
                body()*/

    }
}
