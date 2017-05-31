package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.Groups;
import ua.stqa.pft.addressbook.model.UserData;
import ua.stqa.pft.addressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by amalinkovskiy on 5/29/2017.
 */
public class UserGroupAddRemoveTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        File photo = new File("src/test/resources/300px.png");
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }

        app.goTo().usersPage();
        if (app.db().users().size() == 0){
            app.user().create(new UserData().withFirstname("Alexander").withMiddlename("B").withLastname("Malinkovskiy").
                    withNickname("amalinkovskiy").withAddress("address").
                    withHome("home").withFax("fax").withEmail("a@a.a").withPhoto(photo), true);
        }
    }



    @Test
    public void testAddUserToGroup(){

        Users users = app.db().users();
        Groups groups = app.db().groups();
        UserData freeGroupUser = app.user().userWithFreeGroup(users, groups);

        if (freeGroupUser == null){

            app.group().create(
                    new GroupData().withName("newgroup").withFooter("old").withGroup("[none]"));
            groups = app.db().groups();
            freeGroupUser = users.iterator().next();
            Groups before = freeGroupUser.getGroups();
            GroupData availGroup = app.user().getAvailGroup(freeGroupUser, groups);
            app.user().toGroup(freeGroupUser, availGroup);

            Users afterUsers = app.db().users();
            Groups after = app.user().getGroups(afterUsers, freeGroupUser);
            assertThat(after, equalTo(before.withAdded(availGroup)));

        } else {
            Groups before = freeGroupUser.getGroups();

            GroupData availGroup = app.user().getAvailGroup(freeGroupUser, groups);
            app.user().toGroup(freeGroupUser, availGroup);

            Users afterUsers = app.db().users();
            Groups after = app.user().getGroups(afterUsers, freeGroupUser);
            assertThat(after, equalTo(before.withAdded(availGroup)));
        }
    }



    @Test
    public void testRemoveUserFromGroup(){

        Users users = app.db().users();
        Groups groups = app.db().groups();
        UserData userInGroups = app.user().userInGroups(users, groups);


        if (userInGroups == null){
            GroupData availGroup = groups.iterator().next();
            UserData availUser = users.iterator().next();

            app.user().toGroup(availUser, availGroup);

            Users beforeUsers = app.db().users();
            Groups before = app.user().getGroups(beforeUsers, availUser);

            app.user().removeFromGroup(availUser, availGroup);

            Users afterUsers = app.db().users();
            Groups after = app.user().getGroups(afterUsers, availUser);
            assertThat(after, equalTo(before.without(availGroup)));

        } else {
            Groups before = userInGroups.getGroups();

            GroupData groupToRemove = app.user().getGroupToRemove(userInGroups);
            app.user().removeFromGroup(userInGroups, groupToRemove);

            Users afterUsers = app.db().users();
            Groups after = app.user().getGroups(afterUsers, userInGroups);
            assertThat(after, equalTo(before.without(groupToRemove)));

        }
    }
}
