package ua.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){

        app.goTo().GroupPage();

        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("testfordel").withFooter("old").withGroup("[none]"));
        }
    }

    @Test
    public void testGroupDeletion() {

        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() - 1));

        assertThat(after, equalTo(before.without(deletedGroup)));

    }

}
