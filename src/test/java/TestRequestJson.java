import actions.commons.GlobalConstants;
import actions.json.CreateBookingJson;
import actions.json.GetBookingJson;
import actions.json.UpdateBookingJson;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TestRequestJson {
    CreateBookingJson createBookingJson = new CreateBookingJson();
    GetBookingJson getBookingJson = new GetBookingJson();
    UpdateBookingJson updateBookingJson = new UpdateBookingJson();

    @Test(description = "POST: CreateBookingJson")
    public void PostRequestJsonTest() {
        //post request
        createBookingJson.PostRequest(GlobalConstants.loadProd("firstname"),
                GlobalConstants.loadProd("lastname"),
                GlobalConstants.loadProd("totalprice"),
                GlobalConstants.loadProd("depositpaid"),
                GlobalConstants.loadProd("checkin"),
                GlobalConstants.loadProd("checkout"),
                GlobalConstants.loadProd("additionalneeds"));
        //get booking id
        createBookingJson.GetBookingId();
        //validate status code
        createBookingJson.ValidateStatusCode();
        System.out.println();
    }

    @Test(description = "GET: GetBookingJson", dependsOnMethods = {"PostRequestJsonTest"})
    public void GetRequestJsonTest() {
        //get request
        getBookingJson.GetRequest();
        System.out.println();
    }

    @Test(description = "PUT: UpdateBookingJson", dependsOnMethods = {"PostRequestJsonTest"})
    public void PutRequestJsonTest() {
        //put request
        updateBookingJson.PutRequest(GlobalConstants.loadProd("firstname"),
                GlobalConstants.loadProd("lastname"),
                GlobalConstants.loadProd("totalprice"),
                GlobalConstants.loadProd("depositpaid"),
                GlobalConstants.loadProd("checkin"),
                GlobalConstants.loadProd("checkout"),
                GlobalConstants.loadProd("additionalneedsUpdate"));
    }
}