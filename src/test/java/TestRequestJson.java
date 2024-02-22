import actions.commons.GlobalConstants;
import actions.json.CreateBookingJson;
import actions.json.GetBookingJson;
import actions.json.UpdateBookingJson;
import org.testng.annotations.Test;

public class TestRequestJson {
    CreateBookingJson createBookingJson = new CreateBookingJson();
    GetBookingJson getBookingJson = new GetBookingJson();
    UpdateBookingJson updateBookingJson = new UpdateBookingJson();

    @Test(description = "POST: CreateBookingJson")
    public void PostRequestJsonTest() {
        //post request
        createBookingJson.PostRequest(GlobalConstants.loadProperties("prod", "firstname"),
                GlobalConstants.loadProperties("prod", "lastname"),
                GlobalConstants.loadProperties("prod", "totalprice"),
                GlobalConstants.loadProperties("prod", "depositpaid"),
                GlobalConstants.loadProperties("prod", "checkin"),
                GlobalConstants.loadProperties("prod", "checkout"),
                GlobalConstants.loadProperties("prod", "additionalneeds"));
        //get booking id
        createBookingJson.GetBookingId();
        //validate status code
        createBookingJson.ValidateStatusCode(GlobalConstants.loadProperties("test-data", "statusCode"));
        System.out.println();
    }

    @Test(description = "GET: GetBookingJson", dependsOnMethods = {"PostRequestJsonTest"})
    public void GetRequestJsonTest() {
        //get request
        getBookingJson.GetRequest();
        //validate first name
        getBookingJson.ValidateFirstName(GlobalConstants.loadProperties("test-data", "firstname"));
        System.out.println();
    }

    @Test(description = "PUT: UpdateBookingJson", dependsOnMethods = {"PostRequestJsonTest"})
    public void PutRequestJsonTest() {
        //put request
        updateBookingJson.PutRequest(GlobalConstants.loadProperties("prod", "firstname"),
                GlobalConstants.loadProperties("prod", "lastname"),
                GlobalConstants.loadProperties("prod", "totalprice"),
                GlobalConstants.loadProperties("prod", "depositpaid"),
                GlobalConstants.loadProperties("prod", "checkin"),
                GlobalConstants.loadProperties("prod", "checkout"),
                GlobalConstants.loadProperties("prod", "additionalneeds"));
    }
}