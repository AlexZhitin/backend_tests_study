package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

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
}
