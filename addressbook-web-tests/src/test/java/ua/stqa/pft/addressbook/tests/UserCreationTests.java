package ua.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() {

        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, "old", "[none]"));
        }
        app.getNavigationHelper().gotoUsersPage();
        List<UserData> before = app.getUserHelper().getUserList();

        app.getUserHelper().initNewUserCreation();
        UserData user = new UserData("Alexander", "B", "Malinkovskiy", "amalinkovskiy", "title", "company", "address", "home", "(777)777-88-99", null, "fax", "a@a.a", "test1");
        app.getUserHelper().fillFormWithData(user, true);
        app.getUserHelper().submitUserCreation();
        app.getUserHelper().returnToUsersPage();
        List<UserData> after = app.getUserHelper().getUserList();

        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(user);
        Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
