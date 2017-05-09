package ua.stqa.pft.addressbook.model;

public class GroupData {
    private int id;
    private final String name;
    private final String header;
    private final String footer;
    private final String group;

    public GroupData(int id, String name, String header, String footer, String group) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
        this.group = group;
    }

    public GroupData(String name, String header, String footer, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.header = header;
        this.footer = footer;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        return name != null ? name.equals(groupData.name) : groupData.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getGroup() {
        return group;
    }
}
