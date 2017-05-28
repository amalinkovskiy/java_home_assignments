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
public class UserDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        File photo = new File("src/test/resources/300px.png");
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }
        app.goTo().usersPage();
        if (app.user().all().size() == 0){
            app.user().create(new UserData().withFirstname("Alexander").withMiddlename("B").withLastname("Malinkovskiy").
                    withNickname("amalinkovskiy").withTitle("title").withCompany("company").withAddress("address").
                    withHome("home").withMobile("(777)777-88-99").withFax("fax").withEmail("a@a.a")/*.withGroup("test1")*/.withPhoto(photo), true);
        }

    }

    @Test
    public void testUserDeletion() {

        Users before = app.db().users();
        UserData deletedUser = before.iterator().next();
        app.user().delete(deletedUser);
        //assertEquals(app.user().count(), before.size() - 1);
        Users after = app.db().users();
        assertThat(after, equalTo(before.without(deletedUser)));
    }
}
