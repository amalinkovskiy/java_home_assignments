package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.UserData;
import ua.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }
        app.goTo().usersPage();

    }

        @Test
        public void testUserCreation() {

        Users before = app.user().all();
        UserData user = new UserData().withFirstname("Alexander").withMiddlename("B").withLastname("Malinkovskiy")
                .withNickname("amalinkovskiy").withTitle("title").withAddress("add").withGroup("test1");
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size() + 1));
        Users after = app.user().all();
        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadUserCreation() {

        Users before = app.user().all();
        UserData user = new UserData().withFirstname("Alexander'").withMiddlename("B").withLastname("Malinkovskiy")
                .withNickname("amalinkovskiy").withTitle("title").withAddress("add").withGroup("test1");
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size()));
        Users after = app.user().all();
        assertThat(after, equalTo(before));
    }

}
