package ua.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by amalinkovskiy on 4/20/2017.
 */
public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private UserHelper userHelper;
    private String browser;
    private DbHelper dbHelper;

    public ApplicationManager (String browser) {
        this.browser = browser;
        properties = new Properties();

    }


    public void init() throws IOException {
        //String browser = BrowserType.FIREFOX;

        String target = System.getProperty("target", "local");

        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();

        if("".equals(properties.getProperty("selenium.server"))) {

            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();

            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseURL"));
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        userHelper = new UserHelper(wd);
        sessionHelper.login(properties.getProperty("adminLogin"), properties.getProperty("adminPassword"));


    }



    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public  UserHelper user() {
        return userHelper;
    }

    public DbHelper db(){
        return dbHelper;
    }
}
