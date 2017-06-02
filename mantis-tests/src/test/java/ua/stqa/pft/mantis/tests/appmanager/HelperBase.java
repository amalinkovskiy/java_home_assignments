package ua.stqa.pft.mantis.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class HelperBase {
    protected WebDriver wd;
    protected ApplicationManager app;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null){
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)){
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void login(String operation){

        String page = "login_page.php";
        if(operation.equals("ModifyUser")){
            page = "/manage_user_page.php";
        }
        wd.get(app.getProperty("web.baseURL") + page);
        if(operation.equals("ModifyUser")){
            type(By.name("username"), app.getProperty("admin"));
            type(By.name("password"), app.getProperty("adminPassword"));
            click(By.cssSelector("input[value='Login']"));
        }
    }

}
