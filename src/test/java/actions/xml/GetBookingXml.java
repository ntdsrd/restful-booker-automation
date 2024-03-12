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

public class GetBookingXml {
    HttpResponse<String> response;
    JSONObject jsonObject;

    public void sendGetRequest() {
        response = Unirest.get(GlobalConstants.loadProperties("Prod", "url").concat("/" + CreateBookingXml.bookingId))
                .accept(GlobalConstants.XML_ACCEPT)
                .asString();
        jsonObject = XML.toJSONObject(response.getBody());
        System.out.println("Get: " + response.getBody());
    }

    public void validateFirstName(String firstName) {
        String firstNameResponse = jsonObject.getJSONObject("booking").getString("firstname");
        System.out.println("First name: " + firstNameResponse);
        GlobalConstants.softAssert.assertEquals(firstNameResponse, firstName);
        GlobalConstants.softAssert.assertAll("Validate first name fail");
    }

    public void validateLastName(String lastName) {
        String lastNameResponse = jsonObject.getJSONObject("booking").getString("lastname");
        System.out.println("Last name: " + lastNameResponse);
        GlobalConstants.softAssert.assertEquals(lastNameResponse, lastName);
        GlobalConstants.softAssert.assertAll("Validate last name fail");
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