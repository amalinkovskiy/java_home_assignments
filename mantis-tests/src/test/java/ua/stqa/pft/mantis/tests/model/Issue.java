package ua.stqa.pft.mantis.tests.model;

/**
 * Created by amalinkovskiy on 6/3/2017.
 */
public class Issue {
    private int id;
    private String summary;
    private String desccription;
    private Project project;
    private String status;

    public String getName() {
        return name;
    }


    public Issue withName(String name) {
        this.name = name;
        return this;
    }

    private String name;

    public String getStatus() {
        return status;
    }

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

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", desccription='" + desccription + '\'' +
                ", project=" + project +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
