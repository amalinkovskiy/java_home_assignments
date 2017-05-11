package ua.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.UserData;

import java.util.*;

/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class UserModificationTests extends TestBase {

    @Test (enabled = false)
    public void testUserModification() {
        app.goTo().GroupPage();
        if (! app.group().isThereAGroup()){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }

        app.goTo().gotoUsersPage();
        if (! app.getUserHelper().isThereAUser()){
            app.getUserHelper().createUser(new UserData("Alexander", "B", "Malinkovskiy", "amalinkovskiy", "title", "company", "address", "home", "(777)777-88-99", null, "fax", "a@a.a", "test1"), true);
        }

        List<UserData> before = app.getUserHelper().getUserList();

        //app.getUserHelper().selectUser(before - 1);
        app.getUserHelper().editParticularUser(before.size() - 1);
        UserData user = new UserData(before.get(before.size() - 1).getId(),"Alexandem", "B", "Malinkovskiy", "amalinkovskiy", "title", "company", "address", "home", "(777)777-88-99", "work", null, "a@a.a", null);
        app.getUserHelper().fillFormWithData(user, false);
        app.getUserHelper().submitUserModification();
        app.getUserHelper().returnToUsersPage();

        List<UserData> after = app.getUserHelper().getUserList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(user);
        Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
