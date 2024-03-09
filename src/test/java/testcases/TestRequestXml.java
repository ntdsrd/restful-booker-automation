package testcases;

import actions.xml.CreateBookingXml;
import org.testng.annotations.Test;

public class TestRequestXml {
    CreateBookingXml createBookingXml = new CreateBookingXml();

    @Test(description = "Post: CreateBookingXml")
    public void testPostRequestXml() {
        //post request
        createBookingXml.sendPostRequest();
    }
}