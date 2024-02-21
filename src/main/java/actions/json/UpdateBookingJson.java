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
        HttpResponse<String> response = Unirest.put(GlobalConstants.loadProd("url").concat("/" + CreateBookingJson.bookingId))
                .contentType(GlobalConstants.JSON)
                .accept(GlobalConstants.JSON)
                .basicAuth(GlobalConstants.loadProd("username"), GlobalConstants.loadProd("password"))
                .body(GlobalConstants.JSON(firstname,
                        lastname,
                        totalprice,
                        depositpaid,
                        checkin,
                        checkout,
                        additionalneeds))
                .asString();
        System.out.println("PUT: " + response.getBody());
    }
}