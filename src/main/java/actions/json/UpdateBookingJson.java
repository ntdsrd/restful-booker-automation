package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class UpdateBookingJson {
    public void PutRequest(String firstname,
                           String lastname,
                           String totalprice,
                           String depositpaid,
                           String checkin,
                           String checkout,
                           String additionalneeds) {
        try {
            HttpResponse<String> response = Unirest.put(GlobalConstants.loadProperties("prod", "url").concat("/" + CreateBookingJson.bookingId))
                    .contentType(GlobalConstants.JSON)
                    .accept(GlobalConstants.JSON)
                    .basicAuth(GlobalConstants.loadProperties("prod", "username"), GlobalConstants.loadProperties("prod", "password"))
                    .body(GlobalConstants.JSON(firstname,
                            lastname,
                            totalprice,
                            depositpaid,
                            checkin,
                            checkout,
                            additionalneeds))
                    .asString();
            System.out.println("PUT: " + response.getBody());
        } catch (Exception exception) {
            throw new RuntimeException("Send PUT request fail");
        }
    }
}