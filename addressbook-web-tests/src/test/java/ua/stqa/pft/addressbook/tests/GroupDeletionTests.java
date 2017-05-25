package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().groupPage();

        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("testfordel").withFooter("old").withGroup("[none]"));
        }
    }

    @Test
    public void testGroupDeletion() {

        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(deletedGroup)));

    }

}
