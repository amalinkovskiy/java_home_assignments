package ua.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;



import static org.testng.Assert.assertEquals;

/**
 * Created by amalinkovskiy on 6/5/2017.
 */
public class RestTests extends TestBase{
    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed( 9);

        Set<Issue> oldIssues = app.rest().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New Issue");
        int issueId = app.rest().createIssue(newIssue);
        Set<Issue> newIssues = app.rest().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

}
