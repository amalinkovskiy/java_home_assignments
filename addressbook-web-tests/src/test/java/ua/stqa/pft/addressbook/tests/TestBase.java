package ua.stqa.pft.addressbook.tests;


import org.testng.annotations.AfterSuite;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeSuite;
import ua.stqa.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by amalinkovskiy on 4/19/2017.
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
