package ua.stqa.pft.mantis.tests.appmanager;

import org.openqa.selenium.By;

/**
 * Created by amalinkovskiy on 6/1/2017.
 */
public class RegistrationHelper extends HelperBase{


    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String userName, String email) {
        wd.get(app.getProperty("web.baseURL") + "/signup_page.php");
        type(By.name("username"), userName);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
