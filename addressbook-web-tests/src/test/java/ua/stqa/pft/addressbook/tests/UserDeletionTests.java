package ua.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.UserData;

import java.util.List;

/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class UserDeletionTests extends TestBase {

    @Test (enabled =  false)
    public void testUserDeletion() {

        app.goTo().GroupPage();
        if (! app.group().isThereAGroup()){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }
        app.goTo().gotoUsersPage();
        if (! app.getUserHelper().isThereAUser()){
            app.getUserHelper().createUser(new UserData("Alexander", "B", "Malinkovskiy", "amalinkovskiy", "title", "company", "address", "home", "(777)777-88-99", null, "fax", "a@a.a", "test1"), true);
        }
        List<UserData> before = app.getUserHelper().getUserList();

        app.getUserHelper().selectUser(before.size() - 1);
        app.getUserHelper().deleteSelectedUsers();
        app.getUserHelper().returnToUsersPage();

        List<UserData> after = app.getUserHelper().getUserList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);

        Assert.assertEquals(before, after);


    }
}
