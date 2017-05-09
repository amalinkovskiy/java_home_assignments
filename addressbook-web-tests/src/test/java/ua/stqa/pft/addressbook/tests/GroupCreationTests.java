package ua.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {


        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGrouplist();
        GroupData group = new GroupData("test3", null, "create", "[none]");
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGrouplist();
        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
