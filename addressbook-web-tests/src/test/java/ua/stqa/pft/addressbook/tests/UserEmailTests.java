package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by amalinkovskiy on 5/16/2017.
 */
public class UserEmailTests extends TestBase{

    @Test
    public void testUserEmails(){
        app.goTo().usersPage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);
        assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoFromEditForm)));

    }

    private String mergeEmails(UserData user) {
        return Arrays.asList(user.getEmail(), user.getEmail2(), user.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
