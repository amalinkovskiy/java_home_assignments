package ua.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.UserData;

/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class UserDeletionTests extends TestBase {

    @Test
    public void testUserDeletion() {

        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, "old", "[none]"));
        }
        app.getNavigationHelper().gotoUsersPage();
        if (! app.getUserHelper().isThereAUser()){
            app.getUserHelper().createUser(new UserData("Alexander", "B", "Malinkovskiy", "amalinkovskiy", "title", "company", "address", "home", "(777)777-88-99", null, "fax", "a@a.a", "test1"), true);
        }
        int before = app.getUserHelper().getUserCount();
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteSelectedUsers();
        app.getUserHelper().returnToUsersPage();

        int after = app.getUserHelper().getUserCount();

        Assert.assertEquals(after, before - 1);

    }
}
