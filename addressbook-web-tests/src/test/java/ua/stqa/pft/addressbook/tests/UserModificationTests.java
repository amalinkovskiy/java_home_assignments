package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.UserData;

/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, "old", "[none]"));
        }

        app.getNavigationHelper().gotoUsersPage();
        if (! app.getUserHelper().isThereAUser()){
            app.getUserHelper().createUser(new UserData("Alexander", "B", "Malinkovskiy", "amalinkovskiy", "title", "company", "address", "home", "(777)777-88-99", null, "fax", "a@a.a", "test1"), true);
        }
        app.getUserHelper().editFirstUser();
        app.getUserHelper().fillFormWithData(new UserData("Alexandem", "B", "Malinkovskiy", "amalinkovskiy", "title", "company", "address", "home", "(777)777-88-99", "work", null, "a@a.a", null), false);
        app.getUserHelper().submitUserModification();
        app.getUserHelper().returnToUsersPage();

    }

}
