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
    public void PostRequestJsonTest() {
        //post request
        createBookingJson.PostRequest();
        //get booking id
        createBookingJson.GetBookingId();
        //validate status code
        createBookingJson.ValidateStatusCode(GlobalConstants.loadProperties("test-data", "statusCode"));
        System.out.println();
    }

    @Test(description = "Get: GetBookingJson", dependsOnMethods = {"PostRequestJsonTest"})
    public void GetRequestJsonTest() {
        //get request
        getBookingJson.GetRequest();
        //validate first name
        getBookingJson.ValidateFirstName(GlobalConstants.loadProperties("test-data", "firstname"));
        //validate last name
        getBookingJson.ValidateLastName(GlobalConstants.loadProperties("test-data", "lastname"));
        System.out.println();
    }

    @Test(description = "Put: UpdateBookingJson", dependsOnMethods = {"PostRequestJsonTest"})
    public void PutRequestJsonTest() {
        //put request
        updateBookingJson.PutRequest();
    }
}