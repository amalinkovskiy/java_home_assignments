package ua.stqa.pft.mantis.tests.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ua.stqa.pft.mantis.tests.model.MailMessage;
import ua.stqa.pft.mantis.tests.model.User;
import ua.stqa.pft.mantis.tests.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by amalinkovskiy on 6/1/2017.
 */
public class ModifyUserTests extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }


    @Test
    public void changePasswordTest() throws IOException, MessagingException {

        Users users = app.db().users();
        String userNameToModify = "usertomodify";
        User user = users.stream().filter((u) -> u.getUsername().equals(userNameToModify))
                .findFirst().get().withPassword("usertomodify");

        app.modification().start(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());


        app.modification().finish(confirmationLink, user.getPassword());
        assertTrue(app.newSession().login(user.getUsername(),user.getPassword()));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
