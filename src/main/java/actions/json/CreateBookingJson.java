package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;

public class CreateBookingJson {
    JSONObject jsonObject;

    public void PostRequest(String firstname,
                            String lastname,
                            String totalprice,
                            String depositpaid,
                            String checkin,
                            String checkout,
                            String additionalneeds) {
        HttpResponse<String> response = Unirest.post(GlobalConstants.loadProd("url"))
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
    }

    public void GetBookingId() {
        String bookingId = jsonObject.get("bookingid").toString();
        System.out.println("Booking ID: " + bookingId);
    }
}