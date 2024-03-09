package actions.xml;

import actions.commons.GlobalConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;
import org.json.XML;

public class GetBookingXml {
    JSONObject jsonObject;

    public void sendGetRequest() {
        HttpResponse<String> response = Unirest.get(GlobalConstants.loadProperties("Prod", "url").concat("/" + CreateBookingXml.bookingId))
                .accept(GlobalConstants.XML_ACCEPT)
                .asString();
        jsonObject = XML.toJSONObject(response.getBody());
        System.out.println("Get: " + response.getBody());
    }

    public void validateFirstName(String firstName) {
        String firstNameResponse = jsonObject.getJSONObject("booking").getString("firstname");
        System.out.println("First name: " + firstNameResponse);
        GlobalConstants.softAssert.assertEquals(firstNameResponse, firstName);
        GlobalConstants.softAssert.assertAll("Validate first name fail");
    }
}