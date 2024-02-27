package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;

public class GetBookingJson {
    JSONObject jsonObject;

    public void GetRequest() {
        HttpResponse<String> response = Unirest.get(GlobalConstants.loadProperties("prod", "url").concat("/" + CreateBookingJson.bookingId))
                .accept(GlobalConstants.JSON)
                .asString();
        jsonObject = new JSONObject(response.getBody());
        System.out.println("Get: " + response.getBody());
    }

    public void ValidateFirstName(String firstName) {
        String firstNameResponse = jsonObject.getString("firstname");
        System.out.println("First name: " + firstNameResponse);
        GlobalConstants.softAssert.assertEquals(firstNameResponse, firstName);
        GlobalConstants.softAssert.assertAll("Validate first name fail");
    }

    public void ValidateLastName(String lastName) {
        String lastNameResponse = jsonObject.getString("lastname");
        System.out.println("Last name: " + lastNameResponse);
        GlobalConstants.softAssert.assertEquals(lastNameResponse, lastName);
        GlobalConstants.softAssert.assertAll("Validate last name fail");
    }
}