package ua.stqa.pft.mantis.tests.tests;


import org.testng.annotations.Test;
import ua.stqa.pft.mantis.tests.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by amalinkovskiy on 5/31/2017.
 */
public class LoginTests extends TestBase{
    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));


    }
}
