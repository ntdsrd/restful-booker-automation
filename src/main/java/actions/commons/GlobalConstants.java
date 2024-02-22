package actions.commons;

import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalConstants {
    public static SoftAssert softAssert = new SoftAssert();
    public static final String JSON = "application/json";

    public static String loadProperties(String fileName, String value) {
        Properties properties;
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/".concat(fileName).concat(".properties"));
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(value);
    }

    public static String JSON(String firstname,
                              String lastname,
                              String totalprice,
                              String depositpaid,
                              String checkin,
                              String checkout,
                              String additionalneeds) {
        return "{\n" +
                "  \"firstname\": \"" + firstname + "\",\n" +
                "  \"lastname\": \"" + lastname + "\",\n" +
                "  \"totalprice\": \"" + totalprice + "\",\n" +
                "  \"depositpaid\": " + depositpaid + ",\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \"" + checkin + "\",\n" +
                "    \"checkout\": \"" + checkout + "\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \"" + additionalneeds + "\"\n" +
                "}";
    }
}