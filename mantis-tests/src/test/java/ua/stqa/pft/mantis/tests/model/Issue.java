package ua.stqa.pft.mantis.tests.model;

/**
 * Created by amalinkovskiy on 6/3/2017.
 */
public class Issue {
    private int id;
    private String summary;
    private String desccription;
    private Project project;

    public String getName() {
        return name;
    }

    public Issue withName(String name) {
        this.name = name;
        return this;
    }

    private String name;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDesccription() {
        return desccription;
    }

    public Issue withDesccription(String desccription) {
        this.desccription = desccription;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }
}
