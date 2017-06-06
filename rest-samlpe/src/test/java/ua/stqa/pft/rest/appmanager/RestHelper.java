package ua.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ua.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * Created by amalinkovskiy on 6/6/2017.
 */
public class RestHelper {

    private final ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public Issue getIssue(int issueId) throws IOException {
        Set<Issue> issues = getIssues();

        Issue issue = issues.stream().filter((i) -> i.getId() == issueId)
                .findFirst().get();

        return issue;
    }


    public Set<Issue> getIssues() throws IOException {
        String json = getExec().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");


        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());

    }

    public Executor getExec() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==","");
    }

    public int createIssue(Issue newIssue) throws IOException {

        String json = getExec().execute(Request
                .Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject())
                        , new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
