package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

public class CreateBookingJson {
    public static String bookingId;
    HttpResponse<String> response;
    JSONObject jsonObject;

    public void sendPostRequest() {
        response = Unirest.post(GlobalConstants.loadProperties("Prod", "url"))
                .contentType(GlobalConstants.JSON_HEADER)
                .accept(GlobalConstants.JSON_HEADER)
                .body(GlobalConstants.jsonFormat(GlobalConstants.loadProperties("Prod", "firstname"),
                        GlobalConstants.loadProperties("Prod", "lastname"),
                        GlobalConstants.loadProperties("Prod", "totalprice"),
                        GlobalConstants.loadProperties("Prod", "depositpaid"),
                        GlobalConstants.loadProperties("Prod", "checkin"),
                        GlobalConstants.loadProperties("Prod", "checkout"),
                        GlobalConstants.loadProperties("Prod", "additionalneeds")))
                .asString();
        jsonObject = new JSONObject(response.getBody());
        System.out.println("Post: " + response.getBody());
    }

    public void getBookingId() {
        bookingId = jsonObject.get("bookingid").toString();
        System.out.println("Booking id: " + bookingId);
    }

    public void validateStatusCode(String statusCode) {
        String statusCodeResponse = String.valueOf(response.getStatus());
        System.out.println("Status code: " + statusCodeResponse + " " + response.getStatusText());
        GlobalConstants.softAssert.assertEquals(statusCodeResponse, statusCode);
        GlobalConstants.softAssert.assertAll("Validate status code fail");
    }

    public void validateForApiSchema() {
        try {
            String jsonSchema = GlobalConstants.jsonSchema("json");
            JSONObject rawSchema = new JSONObject(new JSONTokener(jsonSchema));
            Schema schema = SchemaLoader.load(rawSchema);
            try {
                schema.validate(new JSONObject(jsonObject.get("booking").toString()));
                System.out.println("Schema validated");
            } catch (Exception exception) {
                throw new RuntimeException("Validate for api schema fail");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}