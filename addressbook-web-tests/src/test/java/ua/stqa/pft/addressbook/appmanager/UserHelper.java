package ua.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ua.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
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

    public void selectUser(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedUsers() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void editParticularUser(int index ) {
        wd.findElements(By.xpath("//input[@name='selected[]']/../../td[8]/a")).get(index).click();

    }


    public void submitUserModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void createUser(UserData user, boolean b) {

        initNewUserCreation();
        fillFormWithData(user, b);
        submitUserCreation();
        returnToUsersPage();
    }

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getUserCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<UserData> getUserList() {

        List<UserData> users = new ArrayList<UserData>();
        List<WebElement> elements = wd.findElements(By.xpath("//input[@name='selected[]']/../.."));

        for (WebElement element : elements) {
            String LastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String FirstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
            int id = Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(1) > input")).getAttribute("value"));
            UserData user = new UserData(id, FirstName, null, LastName, null, null, null, address, null, null, null, null, null,"[none]");
            users.add(user);
        }

        return  users;
    }
}
