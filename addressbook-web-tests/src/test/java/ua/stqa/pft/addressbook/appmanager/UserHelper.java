package ua.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ua.stqa.pft.addressbook.model.UserData;
import ua.stqa.pft.addressbook.model.Users;

import java.util.List;

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


    public void fillFormWithData(UserData userData, boolean creation) {

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

    public void selectUser(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectUserById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedUsers() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void editParticularUserById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

    }


    public void submitUserModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void create(UserData user, boolean b) {

        initNewUserCreation();
        fillFormWithData(user, b);
        submitUserCreation();
        returnToUsersPage();
    }

    public void modify(UserData user) {
        editParticularUserById(user.getId());
        fillFormWithData(user, false);
        submitUserModification();
        returnToUsersPage();
    }

    public void delete(UserData user) {
        selectUserById(user.getId());
        deleteSelectedUsers();
        returnToUsersPage();
    }

    public void create(UserData user) {
        initNewUserCreation();
        fillFormWithData(user, true);
        submitUserCreation();
        returnToUsersPage();
    }

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getUserCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Users all() {

        Users users = new Users();
        List<WebElement> elements = wd.findElements(By.xpath("//input[@name='selected[]']/../.."));

        for (WebElement element : elements) {
            String LastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String FirstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
            int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) > input")).getAttribute("value"));
            UserData user = new UserData().withId(id).withFirstname(FirstName).withLastname(LastName).
                    withAddress(address).withGroup("[none]");
            users.add(user);        }

        return users;
    }
}
