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

public class UpdateBookingXml {
    HttpResponse<String> response;
    JSONObject jsonObject;

    public void sendPutRequest() {
        response = Unirest.put(GlobalConstants.loadProperties("Prod", "url").concat("/" + CreateBookingXml.bookingId))
                .contentType(GlobalConstants.XML_CONTENT_TYPE)
                .accept(GlobalConstants.XML_ACCEPT)
                .basicAuth(GlobalConstants.loadProperties("Prod", "username"), GlobalConstants.loadProperties("Prod", "password"))
                .body(GlobalConstants.xmlFormat(GlobalConstants.loadProperties("Prod", "firstname"),
                        GlobalConstants.loadProperties("Prod", "lastname"),
                        GlobalConstants.loadProperties("Prod", "totalprice"),
                        GlobalConstants.loadProperties("Prod", "depositpaid"),
                        GlobalConstants.loadProperties("Prod", "checkin"),
                        GlobalConstants.loadProperties("Prod", "checkout"),
                        GlobalConstants.loadProperties("Prod", "additionalneedsUpdate")))
                .asString();
        jsonObject = XML.toJSONObject(response.getBody());
        System.out.println("Put: " + response.getBody());
    }

    public void validateInformationAsNeeded(String additionalNeeds) {
        String additionalNeedsResponse = jsonObject.getJSONObject("booking").getString("additionalneeds");
        System.out.println("Information as needed: " + additionalNeedsResponse);
        GlobalConstants.softAssert.assertEquals(additionalNeedsResponse, additionalNeeds);
        GlobalConstants.softAssert.assertAll("Validate information as needed fail");
    }

    public void validateForApiSchema() {
        Path path = Paths.get(GlobalConstants.XML_RESPONSE);
        try {
            if (Files.exists(path)) {
                Files.delete(path);
            } else {
                Files.createFile(path);
            }
            Files.write(path, response.getBody().getBytes());
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(GlobalConstants.xmlSchema("GetAndPut")));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(GlobalConstants.XML_RESPONSE));
            System.out.println("Schema validated");
            Files.delete(path);
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}