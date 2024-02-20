package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class CreateBookingJson {
    public void PostRequest(String firstname,
                            String lastname,
                            String totalprice,
                            String depositpaid,
                            String checkin,
                            String checkout,
                            String additionalneeds) {
        HttpResponse<String> response = Unirest.post(GlobalConstants.load("url"))
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
        System.out.println(response.getBody());
    }
}