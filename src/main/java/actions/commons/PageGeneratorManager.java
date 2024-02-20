package actions.commons;

import actions.json.CreateBookingJson;

public class PageGeneratorManager {
    public static CreateBookingJson createBookingJson() {
        return new CreateBookingJson();
    }
}