package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;

public class CreateBookingJson {
    public static String bookingId;
    HttpResponse<String> response;
    JSONObject jsonObject;

    public void PostRequest(String firstname,
                            String lastname,
                            String totalprice,
                            String depositpaid,
                            String checkin,
                            String checkout,
                            String additionalneeds) {
        try {
            response = Unirest.post(GlobalConstants.loadProd("url"))
                    .contentType(GlobalConstants.JSON)
                    .accept(GlobalConstants.JSON)
                    .body(GlobalConstants.JSON(firstname,
                            lastname,
                            totalprice,
                            depositpaid,
                            checkin,
                            checkout,
                            additionalneeds))
                    .asString();
            jsonObject = new JSONObject(response.getBody());
            System.out.println("POST: " + response.getBody());
        } catch (Exception exception) {
            throw new RuntimeException("Send POST request fail");
        }
    }

    public void GetBookingId() {
        try {
            bookingId = jsonObject.get("bookingid").toString();
            System.out.println("Booking ID: " + bookingId);
        } catch (Exception exception) {
            throw new RuntimeException("Get booking id fail");
        }

    }

    public void ValidateStatusCode() {
        int status;
        try {
            status = response.getStatus();
            System.out.println("CODE: " + status + " " + response.getStatusText());
        } catch (Exception exception) {
            throw new RuntimeException("Cannot get status code");
        }
        GlobalConstants.softAssert.assertEquals(status, 200);
        GlobalConstants.softAssert.assertAll("Validate status code fail");
    }
}