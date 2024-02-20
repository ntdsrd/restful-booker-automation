import actions.commons.GlobalConstants;
import actions.json.CreateBookingJson;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TestRequestJson {
    CreateBookingJson createBookingJson = new CreateBookingJson();

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
    }
}