package ua.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test2", null, "old", "[none]"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }

}
