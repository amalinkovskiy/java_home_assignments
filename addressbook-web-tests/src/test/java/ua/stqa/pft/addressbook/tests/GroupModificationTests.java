package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        } else {
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }

    }

    @Test (enabled =  false)
    public void testGroupModification() {

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();


        GroupData group = new GroupData().
                withId(modifiedGroup.getId()).withName("new1").withHeader("new2").withFooter("test3").withGroup("test1");
        app.group().modify(group);


        GroupData groupFromList = new GroupData().
                withId(modifiedGroup.getId()).withName("new1" + " " + "(" + group.getGroup() + ")").
                    withHeader("new2").withFooter("test3").withGroup("test1");
        Groups after = app.group().all();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(groupFromList)));

    }
}
