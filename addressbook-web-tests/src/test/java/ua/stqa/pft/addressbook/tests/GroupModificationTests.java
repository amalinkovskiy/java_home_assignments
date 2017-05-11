package ua.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, "old", "[none]"));
            app.getGroupHelper().createGroup(new GroupData("test1", null, "new", "[none]"));
        } else {
            app.getGroupHelper().createGroup(new GroupData("test1", null, "new", "[none]"));
        }

    }

    @Test
    public void testGroupModification() {

        List<GroupData> before = app.getGroupHelper().getGrouplist();
        int index = before.size()-1;

        GroupData group = new GroupData(before.get(index).getId(),"new1", "new2", "test3", "test1");

        app.getGroupHelper().modifyGroup(index, group);


        List<GroupData> after = app.getGroupHelper().getGrouplist();
        Assert.assertEquals(after.size(), before.size());



        GroupData groupFromList = new GroupData(before.get(before.size()-1).getId(),"new1" + " " + "(" + group.getGroup() + ")", "new2", "test3", "test1");
        before.remove(index);
        before.add(groupFromList);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
