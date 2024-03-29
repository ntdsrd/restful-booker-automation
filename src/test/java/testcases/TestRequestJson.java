package testcases;

import actions.commons.GlobalConstants;
import actions.json.CreateBookingJson;
import actions.json.GetBookingJson;
import actions.json.UpdateBookingJson;
import org.testng.annotations.Test;

public class TestRequestJson {
    CreateBookingJson createBookingJson = new CreateBookingJson();
    GetBookingJson getBookingJson = new GetBookingJson();
    UpdateBookingJson updateBookingJson = new UpdateBookingJson();

    @Test(description = "Post: CreateBookingJson")
    public void testPostRequestJson() {
        //send post request
        createBookingJson.sendPostRequest();
        //get booking id
        createBookingJson.getBookingId();
        //validate status code
        createBookingJson.validateStatusCode(GlobalConstants.loadProperties("TestData", "statusCode"));
        //validate for api schema
        createBookingJson.validateForApiSchema();
        System.out.println("--------------------------------------------------");
    }

    @Test(description = "Get: GetBookingJson", dependsOnMethods = {"testPostRequestJson"})
    public void testGetRequestJson() {
        //send get request
        getBookingJson.sendGetRequest();
        //validate first name
        getBookingJson.validateFirstName(GlobalConstants.loadProperties("TestData", "firstname"));
        //validate last name
        getBookingJson.validateLastName(GlobalConstants.loadProperties("TestData", "lastname"));
        //validate for api schema
        getBookingJson.validateForApiSchema();
        System.out.println("--------------------------------------------------");
    }

    @Test(description = "Put: UpdateBookingJson", dependsOnMethods = {"testPostRequestJson"})
    public void testPutRequestJson() {
        //send put request
        updateBookingJson.sendPutRequest();
        //validate information as needed
        updateBookingJson.validateInformationAsNeeded(GlobalConstants.loadProperties("TestData", "additionalneeds"));
        //validate for api schema
        updateBookingJson.validateForApiSchema();
        System.out.println("--------------------------------------------------");
    }
}