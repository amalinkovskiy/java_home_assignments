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

    public void viewParticularUserById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }


    public void submitUserModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void create(UserData user, boolean b) {

        initNewUserCreation();
        fillFormWithData(user, b);
        submitUserCreation();
        userCache = null;
        returnToUsersPage();
    }

    public void modify(UserData user) {
        editParticularUserById(user.getId());
        fillFormWithData(user, false);
        submitUserModification();
        userCache = null;
        returnToUsersPage();
    }

    public void delete(UserData user) {
        selectUserById(user.getId());
        deleteSelectedUsers();
        userCache = null;
        returnToUsersPage();
    }

    public void create(UserData user) {
        initNewUserCreation();
        fillFormWithData(user, true);
        submitUserCreation();
        userCache = null;
        returnToUsersPage();
    }

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Users userCache = null;

    public Users all() {
        if (userCache != null){
            return new Users(userCache);
        }

        userCache = new Users();
        List<WebElement> rows = wd.findElements(By.name("entry"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String LastName = cells.get(1).getText();
            String FirstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            UserData user = new UserData().withId(id).withFirstname(FirstName).withLastname(LastName).
                    withAddress(address).withGroup("[none]").withAllEmails(allEmails).withAllPhones(allPhones);
            userCache.add(user);        }

        return new Users(userCache);
    }

    public UserData infoFromEditForm(UserData user) {
        editParticularUserById(user.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
        String company = wd.findElement(By.name("company")).getAttribute("value");
        String title = wd.findElement(By.name("title")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String fax = wd.findElement(By.name("fax")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();

        return new UserData().withId(user.getId()).withFirstname(firstname).withMiddlename(middlename)
                .withLastname(lastname).withNickname(nickname).withCompany(company).withTitle(title)
                .withAddress(address).withHome(home).withMobile(mobile).withWork(work).withEmail(email)
                .withEmail2(email2).withEmail3(email3).withFax(fax);

    }

    public String infoFromViewForm(UserData user){
        viewParticularUserById(user.getId());
        String content = wd.findElement(By.id("content")).getText();
        return content;
    }
}
