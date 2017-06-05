package ua.stqa.pft.mantis.tests.tests;


import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ua.stqa.pft.mantis.tests.appmanager.ApplicationManager;
import ua.stqa.pft.mantis.tests.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


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

    boolean isIssueOpen(BigInteger issueId) throws RemoteException, ServiceException, MalformedURLException {
        Issue issue = app.soap().getIssue(issueId);
        if (issue.getStatus().equals("closed")){
            return false;
        } else {
            return true;
        }

    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(new BigInteger(String.valueOf(issueId)))) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}

