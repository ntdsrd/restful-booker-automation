package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;

public class UpdateBookingJson {
    JSONObject jsonObject;

    public void putRequest() {
        HttpResponse<String> response = Unirest.put(GlobalConstants.loadProperties("Prod", "url").concat("/" + CreateBookingJson.bookingId))
                .contentType(GlobalConstants.JSON)
                .accept(GlobalConstants.JSON)
                .basicAuth(GlobalConstants.loadProperties("Prod", "username"), GlobalConstants.loadProperties("Prod", "password"))
                .body(GlobalConstants.jsonFormat(GlobalConstants.loadProperties("prod", "firstname"),
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
}