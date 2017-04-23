package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class UserDeletionTests extends TestBase {

    @Test
    public void testUserDeletion() {

        app.getNavigationHelper().gotoUsersPage();
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteSelectedUsers();
        app.getUserHelper().returnToUsersPage();

    }
}
