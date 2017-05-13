package ua.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;

import java.util.Set;


/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().GroupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        } else {
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }

    }

    @Test
    public void testGroupModification() {

        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();


        GroupData group = new GroupData().
                withId(modifiedGroup.getId()).
                withName("new1").
                withHeader("new2").
                withFooter("test3").
                withGroup("test1");

        app.group().modify(group);


        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        GroupData groupFromList = new GroupData().
                withId(modifiedGroup.getId()).
                withName("new1" + " " + "(" + group.getGroup() + ")").
                withHeader("new2").
                withFooter("test3").
                withGroup("test1");

        before.remove(modifiedGroup);
        before.add(groupFromList);
        Assert.assertEquals(before, after);

    }
}
