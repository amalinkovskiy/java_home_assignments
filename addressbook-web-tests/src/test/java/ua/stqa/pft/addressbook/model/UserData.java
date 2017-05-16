package ua.stqa.pft.addressbook.model;

public class UserData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String title;
    private String company;
    private String address;
    private String home;
    private String mobile;
    private String work;
    private String fax;
    private String email;
    private String group;

    public String getAllPhones() {
        return allPhones;
    }

    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    private String allPhones;

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public UserData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public UserData withTitle(String title) {
        this.title = title;
        return this;
    }

    public UserData withCompany(String company) {
        this.company = company;
        return this;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserData withHome(String home) {
        this.home = home;
        return this;
    }

    public UserData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public UserData withWork(String work) {
        this.work = work;
        return this;
    }

    public UserData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withGroup(String group) {
        this.group = group;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (id != userData.id) return false;
        if (firstname != null ? !firstname.equals(userData.firstname) : userData.firstname != null) return false;
        if (lastname != null ? !lastname.equals(userData.lastname) : userData.lastname != null) return false;
        return address != null ? address.equals(userData.address) : userData.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
