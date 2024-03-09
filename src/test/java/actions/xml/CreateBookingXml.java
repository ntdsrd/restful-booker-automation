package actions.xml;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;
import org.json.XML;

public class CreateBookingXml {
    public static String bookingId;
    JSONObject jsonObject;

    public void sendPostRequest() {
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
        jsonObject = XML.toJSONObject(response.getBody());
        System.out.println("Post: " + response.getBody());
    }

    public void getBookingId() {
        bookingId = jsonObject.getJSONObject("created-booking").get("bookingid").toString();
        System.out.println("Booking id: " + bookingId);
    }
}