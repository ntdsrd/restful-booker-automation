package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

public class UpdateBookingJson {
    JSONObject jsonObject;

    public void putRequest() {
        HttpResponse<String> response = Unirest.put(GlobalConstants.loadProperties("Prod", "url").concat("/" + CreateBookingJson.bookingId))
                .contentType(GlobalConstants.JSON_HEADER)
                .accept(GlobalConstants.JSON_HEADER)
                .basicAuth(GlobalConstants.loadProperties("Prod", "username"), GlobalConstants.loadProperties("Prod", "password"))
                .body(GlobalConstants.jsonFormat(GlobalConstants.loadProperties("Prod", "firstname"),
                        GlobalConstants.loadProperties("Prod", "lastname"),
                        GlobalConstants.loadProperties("Prod", "totalprice"),
                        GlobalConstants.loadProperties("Prod", "depositpaid"),
                        GlobalConstants.loadProperties("Prod", "checkin"),
                        GlobalConstants.loadProperties("Prod", "checkout"),
                        GlobalConstants.loadProperties("Prod", "additionalneedsUpdate")))
                .asString();
        jsonObject = new JSONObject(response.getBody());
        System.out.println("Put: " + response.getBody());
    }

    public void validateInformationAsNeeded(String additionalNeeds) {
        String additionalNeedsResponse = jsonObject.getString("additionalneeds");
        System.out.println("Information as needed: " + additionalNeedsResponse);
        GlobalConstants.softAssert.assertEquals(additionalNeedsResponse, additionalNeeds);
        GlobalConstants.softAssert.assertAll("Validate information as needed fail");
    }

    public void validateForApiSchema() {
        try {
            String jsonSchema = GlobalConstants.readSchemas("json");
            JSONObject rawSchema = new JSONObject(new JSONTokener(jsonSchema));
            Schema schema = SchemaLoader.load(rawSchema);
            try {
                schema.validate(new JSONObject(jsonObject.toString()));
                System.out.println("Schema validated");
            } catch (Exception exception) {
                throw new RuntimeException("Validate for api schema fail");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}