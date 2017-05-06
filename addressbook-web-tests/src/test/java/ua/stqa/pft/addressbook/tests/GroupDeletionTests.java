package ua.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    

    
    @Test
    public void testGroupDeletion() {

        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("testfordel", null, "old", "[none]"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }


}
