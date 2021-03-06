package files;

public class payload {

    public static String getPostData() {

        String requestBody = "{    \"location\":{\"lat\" : -38.383494,        \"lng\" : 33.427362    },    " +
                "\"accuracy\":50,    " +
                "\"name\":\"Frontline house\",    " +
                "\"phone_number\":\"(+91) 983 893 3937\",    " +
                "\"address\" : \"29, side layout, cohen 09\",    " +
                "\"types\": [\"shoe park\",\"shop\"],    " +
                "\"website\" : \"http://google.com\",    " +
                "\"language\" : \"French-IN\"}";

        return requestBody;
    }

    public static String getPlace_id(String place_id) {

        String requestBody = ("{" +
                "\"place_id\": \"" + place_id + "\"" +
                "}");

        return requestBody;
    }

    public static String addBook(String isbn, String aisle) {

        String requestBody = ("{\"name\":\"Learn Appium Automation with Java\",\"isbn\":\""+isbn+"\",\"aisle\":\""+aisle+"\",\"author\":\"John foe\"}");

        return requestBody;
    }
}