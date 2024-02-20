import actions.commons.GlobalConstants;
import actions.json.CreateBookingJson;
import actions.json.GetBookingJson;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TestRequestJson {
    CreateBookingJson createBookingJson = new CreateBookingJson();
    GetBookingJson getBookingJson = new GetBookingJson();

    @AfterTest
    public void afterTest() {
        GlobalConstants.softAssert.assertAll();
    }

    @Test(description = "POST: CreateBookingJson")
    public void CreateBookingJsonTest() {
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
        System.out.println();
    }

    @Test(description = "GET: GetBookingJson")
    public void GetBookingJsonTest() {
        //get request
        getBookingJson.GetRequest();
    }
}