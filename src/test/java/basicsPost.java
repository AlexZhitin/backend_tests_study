import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class basicsPost {

    @Test

    public void PostData() {

        // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&key=AIzaSyD8CTAbN0kl2a-mpJoVVk1j1drJg6N5g0U

        //Base URL or host
        RestAssured.baseURI = "http://216.10.245.166";

       Response res = given().//parameters, request headers, request cookies, body
                queryParam("key", "AIzaSyD8CTAbN0kl2a-mpJoVVk1j1drJg6N5g0U").
                body("{    \"location\":{\"lat\" : -38.383494,        \"lng\" : 33.427362    },    \"accuracy\":50,    \"name\":\"Frontline house\",    \"phone_number\":\"(+91) 983 893 3937\",    \"address\" : \"29, side layout, cohen 09\",    \"types\": [\"shoe park\",\"shop\"],    \"website\" : \"http://google.com\",    \"language\" : \"French-IN\"}").

                when().post("/maps/api/place/add/json").//resourse is passed here
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK")).extract().response();

        String responseString = res.asString();
        System.out.println(responseString);
                /*header("edede,  frfrfrf").
                cookie("deded, dedede").
                body()*/

    }
}