package testcases;

import actions.xml.CreateBookingXml;
import actions.xml.GetBookingXml;
import actions.xml.UpdateBookingXml;
import org.testng.annotations.Test;

public class TestRequestXml {
    CreateBookingXml createBookingXml = new CreateBookingXml();
    GetBookingXml getBookingXml = new GetBookingXml();
    UpdateBookingXml updateBookingXml = new UpdateBookingXml();

    @Test(description = "Post: CreateBookingXml")
    public void testPostRequestXml() {
        //post request
        createBookingXml.sendPostRequest();
        //get booking id
        createBookingXml.getBookingId();
        System.out.println("--------------------------------------------------");
    }

    @Test(description = "Get: GetBookingXml", dependsOnMethods = {"testPostRequestXml"})
    public void testGetRequestXml() {
        //get request
        getBookingXml.sendGetRequest();
        System.out.println("--------------------------------------------------");
    }

    @Test(description = "Put: UpdateBookingXml", dependsOnMethods = {"testPostRequestXml"})
    public void testPutRequestXml() {
        //put request
        updateBookingXml.sendPutRequest();
        System.out.println("--------------------------------------------------");
    }
}