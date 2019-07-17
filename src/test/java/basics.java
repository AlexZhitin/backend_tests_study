import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class basics {

    public static void main(String[] args) {

        // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&key=AIzaSyD8CTAbN0kl2a-mpJoVVk1j1drJg6N5g0U

        //Base URL or host
        RestAssured.baseURI = "https://maps.googleapis.com";

        RestAssured.given().//parameters, request headers, request cookies, body
                param("location, -33.8670522,151.1957362").
                param("radius, 500").
                param("key, AIzaSyD8CTAbN0kl2a-mpJoVVk1j1drJg6N5g0U").

                when().get("/maps/api/place/nearbysearch/json").//resourse is passed here
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].geometry.name",equalTo("Sydney"));
                /*header("edede,  frfrfrf").
                cookie("deded, dedede").
                body()*/

    }
}
