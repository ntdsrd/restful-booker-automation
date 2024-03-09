package testcases;

import actions.commons.GlobalConstants;
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
        //send post request
        createBookingXml.sendPostRequest();
        //get booking id
        createBookingXml.getBookingId();
        //validate status code
        createBookingXml.validateStatusCode(GlobalConstants.loadProperties("TestData", "statusCode"));
        System.out.println("--------------------------------------------------");
    }

    @Test(description = "Get: GetBookingXml", dependsOnMethods = {"testPostRequestXml"})
    public void testGetRequestXml() {
        //send get request
        getBookingXml.sendGetRequest();
        //validate first name
        getBookingXml.validateFirstName(GlobalConstants.loadProperties("TestData", "firstname"));
        System.out.println("--------------------------------------------------");
    }

    @Test(description = "Put: UpdateBookingXml", dependsOnMethods = {"testPostRequestXml"})
    public void testPutRequestXml() {
        //send put request
        updateBookingXml.sendPutRequest();
        System.out.println("--------------------------------------------------");
    }
}