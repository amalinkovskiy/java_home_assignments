package ua.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.stqa.pft.addressbook.model.UserData;

/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class UserHelper extends HelperBase {
    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void submitUserCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initNewUserCreation() {
        click(By.linkText("add new"));
    }

    public void returnToUsersPage() {
        click(By.linkText("home"));
    }


    public void     fillFormWithData(UserData userData, boolean creation) {

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

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectUser() {
        click(By.name("selected[]"));

    }

    public void deleteSelectedUsers() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void editFirstUser() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));

    }

    public void submitUserModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }
}
