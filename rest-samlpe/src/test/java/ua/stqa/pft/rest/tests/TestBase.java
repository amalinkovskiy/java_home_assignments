package ua.stqa.pft.rest.tests;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ua.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;


/**
 * Created by amalinkovskiy on 4/19/2017.
 */
public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager();


    public boolean isIssuePresent(int issueId) throws IOException {

        try {
            String json = app.rest().getExec()
                    .execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))
                    .returnContent().asString();
            return true;
        } catch (HttpResponseException ex){
            return false;
        }

    }


    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssuePresent(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}

