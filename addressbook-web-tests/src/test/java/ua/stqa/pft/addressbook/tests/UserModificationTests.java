package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.UserData;
import ua.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class UserModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }

        app.goTo().usersPage();
        if (app.user().all().size() == 0){
            app.user().create(new UserData().withFirstname("Alexander").withMiddlename("B").withLastname("Malinkovskiy").
                    withNickname("amalinkovskiy").withAddress("address").
                    withHome("home").withFax("fax").withEmail("a@a.a").withGroup("test1"), true);
        }
    }

    @Test (enabled = false)
    public void testUserModification() {

        Users before = app.user().all();
        UserData modifiedUser = before.iterator().next();

        UserData user = new UserData().withId(modifiedUser.getId()).withFirstname("Sherlock").
                withLastname("Holmes").withAddress("Baker street");
        app.user().modify(user);
        Users after = app.user().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));

    }

}
