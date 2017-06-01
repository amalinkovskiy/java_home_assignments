package ua.stqa.pft.mantis.tests.tests;


import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ua.stqa.pft.mantis.tests.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;


/**
 * Created by amalinkovskiy on 4/19/2017.
 */
public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.stop();
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
    }
}

