package ua.stqa.pft.mantis.tests.appmanager;

import org.openqa.selenium.By;
import ua.stqa.pft.mantis.tests.model.User;

/**
 * Created by amalinkovskiy on 6/1/2017.
 */
public class ModificationHelper extends HelperBase{


    public ModificationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(User user) {
        login("ModifyUser");
        wd.findElement(By.cssSelector(
                String.format("a[href='manage_user_edit_page.php?user_id=%s']", user.getId()))).click();
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));

    }
}
