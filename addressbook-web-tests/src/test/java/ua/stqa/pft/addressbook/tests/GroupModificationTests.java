package ua.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().GroupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        } else {
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }

    }

    @Test
    public void testGroupModification() {

        List<GroupData> before = app.group().list();
        int index = before.size()-1;

        GroupData group = new GroupData().
                withId(before.get(index).getId()).
                withName("new1").
                withHeader("new2").
                withFooter("test3").
                withGroup("test1");

        app.group().modify(index, group);


        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

        GroupData groupFromList = new GroupData().
                withId(before.get(before.size()-1).getId()).
                withName("new1" + " " + "(" + group.getGroup() + ")").
                withHeader("new2").
                withFooter("test3").
                withGroup("test1");

        before.remove(index);
        before.add(groupFromList);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
