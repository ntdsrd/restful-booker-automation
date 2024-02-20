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
        System.out.print("POST: ");
        createBookingJson.PostRequest(GlobalConstants.load("firstname"),
                GlobalConstants.load("lastname"),
                GlobalConstants.load("totalprice"),
                GlobalConstants.load("depositpaid"),
                GlobalConstants.load("checkin"),
                GlobalConstants.load("checkout"),
                GlobalConstants.load("additionalneeds"));
    }
}