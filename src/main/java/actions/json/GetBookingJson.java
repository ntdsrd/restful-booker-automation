package actions.json;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GetBookingJson {
    public void GetRequest() {
        HttpResponse<String> response = Unirest.get(GlobalConstants.loadProd("url").concat("/" + CreateBookingJson.bookingId))
                .accept(GlobalConstants.JSON)
                .asString();
        System.out.println("GET: " + response.getBody());
    }
}