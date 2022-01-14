package com.severotek.tests;

import com.severotek.model.FormData;
import org.testng.annotations.Test;

public class AddedAndDeletedArticleTest extends TestBase {

    @Test
    public void simpleTest() {
        app.assertDashboardText();
        app.getControlPanelHelper().initAddingEntry();
        app.assertHeadlineText();
        app.getControlPanelHelper().fillEntryForm(new FormData("title", "slug", "markdown", "text"));
        app.getControlPanelHelper().pressBtnSave();
        app.getNavigationHelper().goToBlogPage();
        app.assertEntryCreation();
        app.getNavigationHelper().goToAdminEntryPage();
        app.getControlPanelHelper().selectCheckBoxFirstEntry();
        app.getControlPanelHelper().actionChoice();
        app.getControlPanelHelper().chooseToDeleteEntries();
        app.getControlPanelHelper().pressBtnExecute();
        app.getControlPanelHelper().confirmSelection();
    }

}
