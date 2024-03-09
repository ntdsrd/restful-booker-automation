package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;

public class GetBookingJson {
    JSONObject jsonObject;

    public void getRequest() {
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
}