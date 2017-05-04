package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.UserData;

/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() {

        app.getNavigationHelper().gotoUsersPage();
        app.getUserHelper().editFirstUser();
        app.getUserHelper().fillFormWithData(new UserData("Alexandem", "B", "Malinkovskiy", "amalinkovskiy", "title", "company", "address", "home", "(777)777-88-99", "work", null, "a@a.a", null), false);
        app.getUserHelper().submitUserModification();
        app.getUserHelper().returnToUsersPage();

    }

}
