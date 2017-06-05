package ua.stqa.pft.mantis.tests.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.mantis.tests.model.Issue;
import ua.stqa.pft.mantis.tests.model.Project;

import javax.xml.rpc.ServiceException;


import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by amalinkovskiy on 6/3/2017.
 */
public class SoapTests extends TestBase{

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {

        Set<Project> projects = app.soap().getProjects();

        System.out.println(projects.size());
        for (Project project : projects){
            System.out.println("name = " + project.getName() + ", id = " + project.getId());

        }
    }

    @Test
    public void createIssue() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(3);
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue").withDesccription("Test issue description")
                .withProject(projects.iterator().next());

        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());

    }
}
