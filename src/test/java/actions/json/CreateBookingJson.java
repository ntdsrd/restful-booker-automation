package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;

public class CreateBookingJson {
    public static String bookingId;
    HttpResponse<String> response;
    JSONObject jsonObject;

    public void postRequest() {
        response = Unirest.post(GlobalConstants.loadProperties("Prod", "url"))
                .contentType(GlobalConstants.JSON)
                .accept(GlobalConstants.JSON)
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
}