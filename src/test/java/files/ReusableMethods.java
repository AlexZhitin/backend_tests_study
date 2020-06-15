package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class ReusableMethods {

    public static XmlPath rawToXML(Response r) {
        String responseBody = r.asString();
        XmlPath x = new XmlPath(responseBody);
        return x;
    }

    public static JsonPath rawToJson(Response r) {
        String responseBody = r.asString();
        JsonPath x = new JsonPath(responseBody);
        return x;
    }


    public static String getSessionKey(){

        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("content-type", "application/json").
                body("{ \"username\": \"aszhitin\", \"password\": \"lenovos820\" }").
                when().
                post("/rest/auth/1/session").
                then().statusCode(200).
                extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);
        String session_id = js.get("session.value");
        /*System.out.println(session_id);*/
        return session_id;
    }

    public static String getIssueID(){ //create and return issue_id

        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("content-type", "application/json").
                header("Cookie", "JSESSIONID=" + getSessionKey()).
                body("{	\"fields\": {        \"project\":        {        	" +
                        "\"key\": \"RES\"        },        " +
                        "\"summary\":\"REST summary\",           " +
                        "\"description\": \"Creating the third report\",          " +
                        "\"issuetype\": {          	" +
                        "\"name\":\"Bug\"          }        }	}").
                when().
                post("/rest/api/2/issue").
                then().statusCode(201).extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);
        String issue_id = js.get("id");
        /*System.out.println(issue_id);*/
        return issue_id;
    }
}
