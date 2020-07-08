package Tests;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StaticJson {

    private static String path = System.getProperty("user.dir") + "/src/test/java/files/Addbookdetails.json";

    @Test

    public void addBook() throws IOException {


        RestAssured.baseURI = "http://216.10.245.166";

        Response resp = given().
                header("Content-Type", "application/json;charset=UTF-8").
                body(GenerateStringFromResource(path)).
                when().post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response();

        JsonPath js = ReusableMethods.rawToJson(resp);
        String id = js.get("ID");
        System.out.println(id);


    }


    public static String GenerateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }


    @Test

    public void addBookHashMap() throws IOException {

        HashMap<String, Object> map = new HashMap<>();

        map.put("name","REST");
        map.put("isbn","qwert");
        map.put("aisle","234");
        map.put("author","Alex");

        /*HashMap<String, Object> mapLocation = new HashMap<>(); //if there's json in json (nested json)
        mapLocation.put("city","ssss");
        mapLocation.put("street","sddd");
        map.put("location", mapLocation);*/


        RestAssured.baseURI = "http://216.10.245.166";

        Response resp = given()
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(map) //HashMap of key/value pairs
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response();

        JsonPath js = ReusableMethods.rawToJson(resp);
        String id = js.get("ID");
        System.out.println(id);


    }

    @Test

    public void addBookHashMapExcel() throws IOException {

        ArrayList data = dataDriven.getData("RestAddBook", "RestAssured");
        HashMap<String, Object> map = new HashMap<>();
       /* JSONObject creds = new JSONObject(); //alternative to JSONObject
        creds.put("username", "aszhitin");
        creds.put("password", "lenovos820");*/

        map.put("name",data.get(0));
        map.put("isbn",data.get(1));
        map.put("aisle",data.get(2));
        map.put("author",data.get(3));

        /*HashMap<String, Object> mapLocation = new HashMap<>(); //if there's json in json (nested json)
        mapLocation.put("city","ssss");
        mapLocation.put("street","sddd");
        map.put("location", mapLocation);*/


        RestAssured.baseURI = "http://216.10.245.166";

        Response resp = given()
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(map) //HashMap of key/value pairs
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response();

        JsonPath js = ReusableMethods.rawToJson(resp);
        String id = js.get("ID");
        System.out.println(id);


    }
}

