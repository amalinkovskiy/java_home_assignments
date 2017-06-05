package ua.stqa.pft.mantis.tests.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ua.stqa.pft.mantis.tests.model.Issue;
import ua.stqa.pft.mantis.tests.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by amalinkovskiy on 6/3/2017.
 */
public class SoapHelper {
    private ApplicationManager app;

    public SoapHelper(ApplicationManager app){
        this.app = app;
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue())
                .withName(p.getName())).collect(Collectors.toSet());
    }

    public Issue getIssue(BigInteger issue_id) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueData = mc.mc_issue_get("administrator", "root", issue_id);
        return new Issue().withId(issueData.getId().intValue()).withSummary(issueData.getSummary()).withStatus(issueData.getStatus().getName());



    }



    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                    .getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories("administrator", "root",
                BigInteger.valueOf(issue.getProject().getId()));

        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDesccription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);

        BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
        IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);

        return new Issue().withId(createdIssueData.getId().intValue())
                .withDesccription(createdIssueData.getDescription()).withSummary(createdIssueData.getSummary())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue()))
                .withName(createdIssueData.getProject().getName());
    }
}
