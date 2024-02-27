package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;

public class CreateBookingJson {
    public static String bookingId;
    HttpResponse<String> response;
    JSONObject jsonObject;

    public void PostRequest() {
        response = Unirest.post(GlobalConstants.loadProperties("prod", "url"))
                .contentType(GlobalConstants.JSON)
                .accept(GlobalConstants.JSON)
                .body(GlobalConstants.JSON(GlobalConstants.loadProperties("prod", "firstname"),
                        GlobalConstants.loadProperties("prod", "lastname"),
                        GlobalConstants.loadProperties("prod", "totalprice"),
                        GlobalConstants.loadProperties("prod", "depositpaid"),
                        GlobalConstants.loadProperties("prod", "checkin"),
                        GlobalConstants.loadProperties("prod", "checkout"),
                        GlobalConstants.loadProperties("prod", "additionalneeds")))
                .asString();
        jsonObject = new JSONObject(response.getBody());
        System.out.println("Post: " + response.getBody());
    }

    public void GetBookingId() {
        bookingId = jsonObject.get("bookingid").toString();
        System.out.println("Booking id: " + bookingId);

    }

    public void ValidateStatusCode(String statusCode) {
        String statusCodeResponse = String.valueOf(response.getStatus());
        System.out.println("Status code: " + statusCodeResponse + " " + response.getStatusText());
        GlobalConstants.softAssert.assertEquals(statusCodeResponse, statusCode);
        GlobalConstants.softAssert.assertAll("Validate status code fail");
    }
}