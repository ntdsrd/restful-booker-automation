package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;

public class GetBookingJson {
    JSONObject jsonObject;

    public void GetRequest() {
        try {
            HttpResponse<String> response = Unirest.get(GlobalConstants.loadProperties("prod", "url").concat("/" + CreateBookingJson.bookingId))
                    .accept(GlobalConstants.JSON)
                    .asString();
            jsonObject = new JSONObject(response.getBody());
            System.out.println("GET: " + response.getBody());
        } catch (Exception exception) {
            throw new RuntimeException("SEND GET REQUEST FAIL");
        }
    }

    public void ValidateFirstName(String firstName) {
        String firstNameResponse;
        try {
            firstNameResponse = jsonObject.getString("firstname");
            System.out.println("FIRST NAME: " + firstNameResponse);
        } catch (Exception exception) {
            throw new RuntimeException("GET FIRST NAME FAIL");
        }
        GlobalConstants.softAssert.assertEquals(firstNameResponse, firstName);
        GlobalConstants.softAssert.assertAll("Validate first name fail");
    }

    public void ValidateLastName(String lastName) {
        String lastNameResponse;
        try {
            lastNameResponse = jsonObject.getString("lastname");
            System.out.println("LAST NAME: " + lastNameResponse);
        } catch (Exception exception) {
            throw new RuntimeException("GET LAST NAME FAIL");
        }
        GlobalConstants.softAssert.assertEquals(lastNameResponse, lastName);
        GlobalConstants.softAssert.assertAll("Validate last name fail");
    }
}