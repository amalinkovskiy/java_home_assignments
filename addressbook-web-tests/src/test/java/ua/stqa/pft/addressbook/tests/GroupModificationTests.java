package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;


/**
 * Created by amalinkovskiy on 4/23/2017.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

    }
}