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
            response = Unirest.post(GlobalConstants.loadProperties("prod", "url"))
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
            throw new RuntimeException("SEND POST REQUEST FAIL");
        }
    }

    public void GetBookingId() {
        try {
            bookingId = jsonObject.get("bookingid").toString();
            System.out.println("BOOKING ID: " + bookingId);
        } catch (Exception exception) {
            throw new RuntimeException("GET BOOKING ID FAIL");
        }

    }

    public void ValidateStatusCode(String statusCode) {
        String statusCodeResponse;
        try {
            statusCodeResponse = String.valueOf(response.getStatus());
            System.out.println("STATUS CODE: " + statusCodeResponse + " " + response.getStatusText());
        } catch (Exception exception) {
            throw new RuntimeException("GET STATUS CODE FAIL");
        }
        GlobalConstants.softAssert.assertEquals(statusCodeResponse, statusCode);
        GlobalConstants.softAssert.assertAll("Validate status code fail");
    }
}