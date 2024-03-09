package actions.xml;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class CreateBookingXml {
    public void postRequest() {
        HttpResponse<String> response = Unirest.post(GlobalConstants.loadProperties("Prod", "url"))
                .contentType(GlobalConstants.XML_CONTENT_TYPE)
                .accept(GlobalConstants.XML_ACCEPT)
                .body(GlobalConstants.xmlFormat(GlobalConstants.loadProperties("Prod", "firstname"),
                        GlobalConstants.loadProperties("Prod", "lastname"),
                        GlobalConstants.loadProperties("Prod", "totalprice"),
                        GlobalConstants.loadProperties("Prod", "depositpaid"),
                        GlobalConstants.loadProperties("Prod", "checkin"),
                        GlobalConstants.loadProperties("Prod", "checkout"),
                        GlobalConstants.loadProperties("Prod", "additionalneeds")))
                .asString();
        System.out.println("Post: " + response.getBody());
    }
}