package ua.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/*
TODO: for all tests - add ability to use system variables (if user wants to use UI data for verifies)
TODO: return ability to select group when modify group
*/

@Entity
@Table (name = "addressbook")
@XStreamAlias("user")
public class UserData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "middlename")
    private String middlename;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Expose
    @Column(name = "nickname")
    private String nickname;
    @Expose
    @Column(name = "title")
    private String title;
    @Column(name = "company")
    private String company;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "home")
    @Type(type = "text")
    private String home;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Column(name = "work")
    @Type(type = "text")
    private String work;

    @Column(name = "fax")
    @Type(type = "text")
    private String fax;
    @Column(name = "email")
    @Type(type = "text")
    private String email;
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String allPhones;
    @Transient
    private String allEmails;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

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

    public Groups getGroups() {
        return new Groups(groups);
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

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public File getPhoto() {
        return new File(photo);
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

    public UserData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public UserData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }


    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public UserData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public UserData withPhoto(File photo) {
        this.photo = photo.getPath();
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
        if (middlename != null ? !middlename.equals(userData.middlename) : userData.middlename != null) return false;
        if (lastname != null ? !lastname.equals(userData.lastname) : userData.lastname != null) return false;
        if (nickname != null ? !nickname.equals(userData.nickname) : userData.nickname != null) return false;
        if (title != null ? !title.equals(userData.title) : userData.title != null) return false;
        if (work != null ? !work.equals(userData.work) : userData.work != null) return false;
        return email != null ? email.equals(userData.email) : userData.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public UserData inGroup(GroupData group) {
        groups.add(group);
        return this;

    }
}
