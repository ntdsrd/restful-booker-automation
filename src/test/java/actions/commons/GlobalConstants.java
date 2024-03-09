package actions.commons;

import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class GlobalConstants {
    public static SoftAssert softAssert = new SoftAssert();
    public static final String JSON_HEADER = "application/json";
    public static final String XML_CONTENT_TYPE = "text/xml";
    public static final String XML_ACCEPT = "application/xml";
    public static final String XML_RESPONSE = "src/test/java/schemas/Response.xml";

    public static String loadProperties(String fileName, String value) {
        Properties properties;
        try {
            InputStream inputStream = new FileInputStream("src/test/java/properties/".concat(fileName).concat(".properties"));
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(value);
    }

    public static String jsonSchema(String fileExtension) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/java/schemas/Schema.".concat(fileExtension))));
    }

    public static String xmlSchema(String fileName) {
        return "src/test/java/schemas/".concat(fileName + "Schema.xsd");
    }

    public static String jsonFormat(String firstname,
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

    public static String xmlFormat(String firstname,
                                   String lastname,
                                   String totalprice,
                                   String depositpaid,
                                   String checkin,
                                   String checkout,
                                   String additionalneeds) {
        return "<booking>\n" +
                "    <firstname>" + firstname + "</firstname>\n" +
                "    <lastname>" + lastname + "</lastname>\n" +
                "    <totalprice>" + totalprice + "</totalprice>\n" +
                "    <depositpaid>" + depositpaid + "</depositpaid>\n" +
                "    <bookingdates>\n" +
                "        <checkin>" + checkin + "</checkin>\n" +
                "        <checkout>" + checkout + "</checkout>\n" +
                "    </bookingdates>\n" +
                "    <additionalneeds>" + additionalneeds + "</additionalneeds>\n" +
                "</booking>";
    }
}