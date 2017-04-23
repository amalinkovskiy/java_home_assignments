package ua.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;

import ua.stqa.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() {

        app.getUserHelper().initNewUserCreation();
        app.getUserHelper().fillFormWithData(new UserData("Alexander", "B", "Malinkovskiy", "amalinkovskiy", "title", "company", "address", "home", "(777)777-88-99", "work", "fax", "a@a.a"));
        app.getUserHelper().submitUserCreation();
        app.getUserHelper().gotoUsersPage();
    }

}
