package actions.xml;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class GetBookingXml {
    public void sendGetRequest() {
        HttpResponse<String> response = Unirest.get(GlobalConstants.loadProperties("Prod", "url").concat("/" + CreateBookingXml.bookingId))
                .accept(GlobalConstants.XML_ACCEPT)
                .asString();
        System.out.println("Get: " + response.getBody());
    }
}