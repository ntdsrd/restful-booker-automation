package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class UpdateBookingJson {
    public void PutRequest() {
        HttpResponse<String> response = Unirest.put(GlobalConstants.loadProperties("prod", "url").concat("/" + CreateBookingJson.bookingId))
                .contentType(GlobalConstants.JSON)
                .accept(GlobalConstants.JSON)
                .basicAuth(GlobalConstants.loadProperties("prod", "username"), GlobalConstants.loadProperties("prod", "password"))
                .body(GlobalConstants.JSON(GlobalConstants.loadProperties("prod", "firstname"),
                        GlobalConstants.loadProperties("prod", "lastname"),
                        GlobalConstants.loadProperties("prod", "totalprice"),
                        GlobalConstants.loadProperties("prod", "depositpaid"),
                        GlobalConstants.loadProperties("prod", "checkin"),
                        GlobalConstants.loadProperties("prod", "checkout"),
                        GlobalConstants.loadProperties("prod", "additionalneedsUpdate")))
                .asString();
        System.out.println("Put: " + response.getBody());
    }
}