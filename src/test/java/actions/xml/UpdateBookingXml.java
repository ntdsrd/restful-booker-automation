package actions.xml;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;
import org.json.XML;

public class UpdateBookingXml {
    JSONObject jsonObject;

    public void sendPutRequest() {
        HttpResponse<String> response = Unirest.put(GlobalConstants.loadProperties("Prod", "url").concat("/" + CreateBookingXml.bookingId))
                .contentType(GlobalConstants.XML_CONTENT_TYPE)
                .accept(GlobalConstants.XML_ACCEPT)
                .basicAuth(GlobalConstants.loadProperties("Prod", "username"), GlobalConstants.loadProperties("Prod", "password"))
                .body(GlobalConstants.xmlFormat(GlobalConstants.loadProperties("Prod", "firstname"),
                        GlobalConstants.loadProperties("Prod", "lastname"),
                        GlobalConstants.loadProperties("Prod", "totalprice"),
                        GlobalConstants.loadProperties("Prod", "depositpaid"),
                        GlobalConstants.loadProperties("Prod", "checkin"),
                        GlobalConstants.loadProperties("Prod", "checkout"),
                        GlobalConstants.loadProperties("Prod", "additionalneedsUpdate")))
                .asString();
        jsonObject = XML.toJSONObject(response.getBody());
        System.out.println("Put: " + response.getBody());
    }

    public void validateInformationAsNeeded(String additionalNeeds) {
        String additionalNeedsResponse = jsonObject.getJSONObject("booking").getString("additionalneeds");
        System.out.println("Information as needed: " + additionalNeedsResponse);
        GlobalConstants.softAssert.assertEquals(additionalNeedsResponse, additionalNeeds);
        GlobalConstants.softAssert.assertAll("Validate information as needed fail");
    }
}