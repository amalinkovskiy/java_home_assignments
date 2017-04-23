package ua.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.stqa.pft.addressbook.model.UserData;

/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class UserHelper extends HelperBase {
    public UserHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitUserCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initNewUserCreation() {
        click(By.linkText("add new"));
    }

    public void gotoUsersPage() {
        click(By.linkText("home page"));
    }


    public void fillFormWithData(UserData userData) {

        type(By.name("firstname"), userData.getFirstname());
        type(By.name("middlename"), userData.getMiddlename());
        type(By.name("lastname"), userData.getLastname());
        type(By.name("nickname"), userData.getNickname());
        type(By.name("title"), userData.getTitle());
        type(By.name("company"), userData.getCompany());
        type(By.name("address"), userData.getAddress());
        type(By.name("home"), userData.getHome());
        type(By.name("mobile"), userData.getMobile());
        type(By.name("work"), userData.getWork());
        type(By.name("fax"), userData.getFax());
        type(By.name("email"), userData.getEmail());

    }
}
