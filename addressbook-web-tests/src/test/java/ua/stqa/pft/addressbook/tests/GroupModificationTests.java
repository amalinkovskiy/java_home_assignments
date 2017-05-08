package ua.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, "old", "[none]"));
            app.getGroupHelper().createGroup(new GroupData("test1", null, "new", "[none]"));
        } else {
            app.getGroupHelper().createGroup(new GroupData("test1", null, "new", "[none]"));
        }
        List<GroupData> before = app.getGroupHelper().getGrouplist();

        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().initGroupModification();

        GroupData group = new GroupData(before.get(before.size()-1).getId(),"new1", "new2", "test3", "test1");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGrouplist();
        Assert.assertEquals(after.size(), before.size());



        GroupData groupFromList = new GroupData(before.get(before.size()-1).getId(),"new1" + " " + "(" + group.getGroup() + ")", "new2", "test3", "test1");
        before.remove(before.size() - 1);
        before.add(groupFromList);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    }
}
