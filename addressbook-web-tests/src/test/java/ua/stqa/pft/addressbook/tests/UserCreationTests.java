package ua.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ua.stqa.pft.addressbook.model.GroupData;
import ua.stqa.pft.addressbook.model.UserData;
import ua.stqa.pft.addressbook.model.Users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserCreationTests extends TestBase {



    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            app.group().create(new GroupData().withName("test1").withFooter("old").withGroup("[none]"));
        }
        app.goTo().usersPage();

    }

    @DataProvider
    public Iterator<Object[]> validUsersFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/users.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }

            Gson gson = new Gson();
            List<UserData> users = gson.fromJson(json, new TypeToken<List<UserData>>() {
            }.getType());

            return users.stream().map((u) -> new Object[]{u}).collect(Collectors.toList()).iterator();
        }
    }

        @Test (dataProvider = "validUsersFromJson")
        public void testUserCreation(UserData user) {

        Users before = app.user().all();
//        File photo = new File("src/test/resources/300px.png");
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size() + 1));
        Users after = app.user().all();
        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test (enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/300px.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }


    @Test (enabled = false)
    public void testBadUserCreation() {

        Users before = app.user().all();
        UserData user = new UserData().withFirstname("Alexander'").withMiddlename("B").withLastname("Malinkovskiy")
                .withNickname("amalinkovskiy").withTitle("title").withAddress("add").withGroup("test1");
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size()));
        Users after = app.user().all();
        assertThat(after, equalTo(before));
    }

}
