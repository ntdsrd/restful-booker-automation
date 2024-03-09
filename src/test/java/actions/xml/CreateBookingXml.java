package actions.xml;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateBookingXml {
    public static String bookingId;
    HttpResponse<String> response;
    JSONObject jsonObject;

    public void sendPostRequest() {
        response = Unirest.post(GlobalConstants.loadProperties("Prod", "url"))
                .contentType(GlobalConstants.XML_CONTENT_TYPE)
                .accept(GlobalConstants.XML_ACCEPT)
                .body(GlobalConstants.xmlFormat(GlobalConstants.loadProperties("Prod", "firstname"),
                        GlobalConstants.loadProperties("Prod", "lastname"),
                        GlobalConstants.loadProperties("Prod", "totalprice"),
                        GlobalConstants.loadProperties("Prod", "depositpaid"),
                        GlobalConstants.loadProperties("Prod", "checkin"),
                        GlobalConstants.loadProperties("Prod", "checkout"),
                        GlobalConstants.loadProperties("Prod", "additionalneeds")))
                .asString();
        jsonObject = XML.toJSONObject(response.getBody());
        System.out.println("Post: " + response.getBody());
    }

    public void getBookingId() {
        bookingId = jsonObject.getJSONObject("created-booking").get("bookingid").toString();
        System.out.println("Booking id: " + bookingId);
    }

    public void validateStatusCode(String statusCode) {
        String statusCodeResponse = String.valueOf(response.getStatus());
        System.out.println("Status code: " + statusCodeResponse + " " + response.getStatusText());
        GlobalConstants.softAssert.assertEquals(statusCodeResponse, statusCode);
        GlobalConstants.softAssert.assertAll("Validate status code fail");
    }

    public void validateForApiSchema() {
        Path path = Paths.get(GlobalConstants.XML_RESPONSE);
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Files.createFile(path);
            Files.write(path, response.getBody().getBytes());
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(GlobalConstants.xmlSchema("Post")));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(GlobalConstants.XML_RESPONSE));
            System.out.println("Schema validated");
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}