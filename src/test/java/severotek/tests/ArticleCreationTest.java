package severotek.tests;

import org.testng.annotations.Test;
import severotek.appmanager.FormHelper;
import severotek.model.FormData;

public class ArticleCreationTest extends TestBase {

    @Test
    public void simpleTest() {
        app.goToLoginPage();
        app.login("selenium", "super_password");
        app.assertDashboardText();
        app.pressBtnAddEntry();
        app.assertHeadlineText();
        app.getFormHelper().fillFormData(new FormData(FormHelper.title,
                FormHelper.slug,
                FormHelper.markdown,
                FormHelper.text));
        app.pressBtnSave();
        app.goToBlogPage();
        app.assertArticleIsPresent();
        app.goToAdminEntryPage();
        app.getFormHelper().deleteFirstArticle();
    }

}
