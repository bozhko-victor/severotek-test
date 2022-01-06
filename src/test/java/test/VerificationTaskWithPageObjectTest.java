package test;

import org.testng.annotations.Test;
import page.AdminPage;

public class VerificationTaskWithPageObjectTest extends TestBase {

    AdminPage adminPage = new AdminPage();

    @Test
    public void addingArticleToBlogTest() {
        adminPage.openAdminPage()
                 .setLogin()
                 .setPassword()
                 .pressEnter()
                 .checkTitleIsVisible()
                 .pressBtnPlusEntries()
                 .checkTextBlockEntryIsPresent()
                 .setTitle()
                 .setSlug()
                 .setMarkdownArea()
                 .setTextArea()
                 .pressBtnSave()
                 .openBlogPage()
                 .checkNewArticleIsPresent()
                 .deleteNewArticle();
    }

}
