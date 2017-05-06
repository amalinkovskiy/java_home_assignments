package ua.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;

import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() {

        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, "old", "[none]"));
        }
        app.getUserHelper().initNewUserCreation();
        app.getUserHelper().fillFormWithData(new UserData("Alexander", "B", "Malinkovskiy", "amalinkovskiy", "title", "company", "address", "home", "(777)777-88-99", null, "fax", "a@a.a", "test1"), true);
        app.getUserHelper().submitUserCreation();
        app.getUserHelper().returnToUsersPage();
    }

}
