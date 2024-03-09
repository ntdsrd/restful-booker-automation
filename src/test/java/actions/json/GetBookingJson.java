package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

public class GetBookingJson {
    JSONObject jsonObject;

    public void sendGetRequest() {
        HttpResponse<String> response = Unirest.get(GlobalConstants.loadProperties("Prod", "url").concat("/" + CreateBookingJson.bookingId))
                .accept(GlobalConstants.JSON_HEADER)
                .asString();
        jsonObject = new JSONObject(response.getBody());
        System.out.println("Get: " + response.getBody());
    }

    public void validateFirstName(String firstName) {
        String firstNameResponse = jsonObject.getString("firstname");
        System.out.println("First name: " + firstNameResponse);
        GlobalConstants.softAssert.assertEquals(firstNameResponse, firstName);
        GlobalConstants.softAssert.assertAll("Validate first name fail");
    }

    public void validateLastName(String lastName) {
        String lastNameResponse = jsonObject.getString("lastname");
        System.out.println("Last name: " + lastNameResponse);
        GlobalConstants.softAssert.assertEquals(lastNameResponse, lastName);
        GlobalConstants.softAssert.assertAll("Validate last name fail");
    }

    public void validateForApiSchema() {
        try {
            String jsonSchema = GlobalConstants.jsonSchema("json");
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