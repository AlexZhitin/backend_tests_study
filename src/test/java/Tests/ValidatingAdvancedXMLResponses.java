package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import files.ReusableMethods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class ValidatingAdvancedXMLResponses {

    @Test

    public void PostData() throws IOException {
        String postdataPath = System.getProperty("user.dir") + "/src/test/java/files/postdata.xml";
        String postdata = GenerateStringFromResource(postdataPath);
        RestAssured.baseURI = "http://216.10.245.166";

        Response response = given().
                queryParam("key", "qaclick123").
                header("Content-Type", "application/xml;charset=UTF-8").
                body(postdata).
                when().post("/maps/api/place/add/xml").
                then().assertThat().statusCode(200).and().contentType(ContentType.XML).
                extract().response();

        XmlPath  x = ReusableMethods.rawToXML(response);
        String string = x.get("response.place_id");
        System.out.println(string);
    }

    public static String GenerateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }
}