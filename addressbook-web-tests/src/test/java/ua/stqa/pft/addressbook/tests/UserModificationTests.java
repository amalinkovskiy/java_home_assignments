package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.UserData;
import ua.stqa.pft.addressbook.model.Users;

import java.io.File;

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
        File photo = new File("src/test/resources/300px.png");
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }

        app.goTo().usersPage();
        if (app.user().all().size() == 0){
            app.user().create(new UserData().withFirstname("Alexander").withMiddlename("B").withLastname("Malinkovskiy").
                    withNickname("amalinkovskiy").withAddress("address").
                    withHome("home").withFax("fax").withEmail("a@a.a").withGroup("test1").withPhoto(photo), true);
        }
    }

    @Test
    public void testUserModification() {

        Users before = app.db().users();
        UserData modifiedUser = before.iterator().next();
        File photo = new File("src/test/resources/200px.png");
        UserData user = new UserData().withId(modifiedUser.getId()).withFirstname("Sherlock").withNickname("test")
                .withLastname("Holmes").withMiddlename("D").withTitle("title").withAddress("Baker street").withWork("911")
                .withEmail("me@google.com").withPhoto(photo);
        app.user().modify(user);
        assertEquals(app.user().count(), before.size());
        Users after = app.db().users();
        Users beforeUser = before.without(modifiedUser).withAdded(user);
        assertThat(after, equalTo(beforeUser));
//        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
        System.out.println("for debug");

    }

}
