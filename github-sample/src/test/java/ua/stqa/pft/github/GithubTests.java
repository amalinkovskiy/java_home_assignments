package ua.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by amalinkovskiy on 6/6/2017.
 */
public class GithubTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub(" ");
        RepoCommits commits = github.repos()
                .get(new Coordinates.Simple("amalinkovskiy", "java_home_assignments")).commits();
        for(RepoCommit commit :commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
