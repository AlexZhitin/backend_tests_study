package Tests;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class ParsingJsonToExctractValues {

    @Test

    public void Test() {

        // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&key=AIzaSyD8CTAbN0kl2a-mpJoVVk1j1drJg6N5g0U

        //Base URL or host
        RestAssured.baseURI = "https://maps.googleapis.com";

        Response res = RestAssured.given().//parameters, request headers, request cookies, body
                queryParam("location", "-33.8670522,151.1957362").
                queryParam("radius", "500").
                queryParam("key", "AIzaSyD8CTAbN0kl2a-mpJoVVk1j1drJg6N5g0U").log().all(). //Shows all queryParams logs

                when().get("/maps/api/place/nearbysearch/json").//resourse is passed here
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                /*body("results[0].name", equalTo("Sydney")).and().
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).*/
                        extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);
        int count = js.get("results.size()");

        for (int i = 0; i < count; i++) {
            String name = js.get("results[" + i + "].name");
            System.out.println(name);
        }
    }
}
