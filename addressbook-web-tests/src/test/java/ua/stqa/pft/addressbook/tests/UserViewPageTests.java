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
public class UserViewPageTests extends TestBase{

    @Test
    public void testUserViewPage(){
        app.goTo().usersPage();
        UserData user = app.user().all().iterator().next();
        String userInfoFromViewForm = app.user().infoFromViewForm(user);
        app.goTo().usersPage();
        UserData userFromEditForm = app.user().infoFromEditForm(user);
        String userInfoFromEditForm = mergeFields(userFromEditForm);

        assertThat(userInfoFromViewForm, equalTo(userInfoFromEditForm));

    }

    private String mergeFields(UserData user) {
        String title = mergeTitle(user.getFirstname(), user.getMiddlename(), user.getLastname());
        String details = mergeDetails(user.getNickname(), user.getTitle(), user.getCompany(), user.getAddress());
        String phones = mergePhones("H: " + user.getHome(), "M: "
                + user.getMobile(), "W: " + user.getWork(), "F: " + user.getFax());
        String emails = mergeEmails(user.getEmail(),user.getEmail2(),user.getEmail3());

        return mergeResult(title,details,phones,emails);
    }

    private String mergeTitle (String firstname, String middlename, String lastname) {
        return Arrays.asList(firstname, middlename, lastname)
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining(" "));    }

    private String mergeDetails (String nickname, String title, String company, String address) {
        return Arrays.asList(nickname, title, company, address)
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones (String home, String moblie, String work, String fax) {
        return Arrays.asList(home, moblie, work, fax)
                .stream().filter((s) -> ! s.equals("") && ! s.equals("H: ") && ! s.equals("W: ") && ! s.equals("M: ") && ! s.equals("F: "))
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails (String email, String email2, String email3) {
        return Arrays.asList(email, email2, email3)
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }


    private String mergeResult (String title, String details, String phones, String emails) {

        if (!title.equals("")){
        return title + "\n" + Arrays.asList(details, phones, emails)
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n\n"));
        } else {
            return Arrays.asList(details, phones, emails)
                    .stream().filter((s) -> ! s.equals(""))
                    .collect(Collectors.joining("\n\n"));
        }
    }


}
