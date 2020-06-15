package Tests;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import files.payload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicJson {

    @Test(dataProvider = "BooksData")

    public void addBook(String isbn, String aisle) {


        RestAssured.baseURI = "http://216.10.245.166";

        Response resp = given().
                header("Content-Type", "application/json;charset=UTF-8").
                body(payload.addBook(isbn, aisle)).
                when().post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response();

        JsonPath js = ReusableMethods.rawToJson(resp);
        String id = js.get("ID");
        System.out.println(id);


    }

    @DataProvider(name="BooksData")
    public Object[][] getdata(){
        return new Object[][] {{"isbn758 ", "aisle947"},{"isbn567g ", "aisle743nf"}, {"isbn54ff ", "aislefcne"} };
    }

}
