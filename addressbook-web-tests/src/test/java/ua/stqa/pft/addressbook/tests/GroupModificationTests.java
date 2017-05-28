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
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        } else {
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }

    }

    @Test
    public void testGroupModification() {

        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("new1").withHeader("new2")
                .withFooter("test3").withGroup("[none]");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();

    }
}
