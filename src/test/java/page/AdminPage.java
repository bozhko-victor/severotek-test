package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static test.TestData.*;

public class AdminPage {

    private final String DASHBOARD_TITLE = "Панель управления";
    private final String BLOCK_TITLE = "Добавить entry";

    private SelenideElement
            usernameField = $("#id_username"),
            passwordField = $("#id_password"),
            submitBtn = $("input[type=submit]"),
            btnPlusEntries =  $("li [href='/admin/blog/entry/add/'"),
            dashboardTitle = $(".dashboard-title"),
            textBlockEntry =  $("#content"),
            inputTitle = $("#id_title"),
            inputSlug =  $("#id_slug"),
            inputMarkdownArea = $("#id_text_markdown"),
            inputTextArea =  $("#id_text"),
            btnSave = $("input.default"),
            firstArticle = $("#entries");

    public AdminPage openAdminPage() {
        open("https://igorakintev.ru/admin/");
        return this;
    }

    public AdminPage setLogin() {
        usernameField.setValue(credentials.login());
        return this;
    }

    public AdminPage setPassword() {
        passwordField.setValue(credentials.password());
        return this;
    }

    public AdminPage pressEnter() {
        submitBtn.click();
        return this;
    }

    public AdminPage checkTitleIsVisible() {
        dashboardTitle.shouldHave(Condition.text(DASHBOARD_TITLE));
        return this;
    }

    public AdminPage pressBtnPlusEntries() {
        btnPlusEntries.click();
        return this;
    }

    public AdminPage checkTextBlockEntryIsPresent() {
        textBlockEntry.shouldHave(Condition.text(BLOCK_TITLE));
        return this;
    }

    public AdminPage setTitle() {
        inputTitle.setValue(title);
        return this;
    }

    public AdminPage setSlug() {
        inputSlug.setValue(slug);
        return this;
    }

    public AdminPage setMarkdownArea() {
        inputMarkdownArea.setValue(markdown);
        return this;
    }

    public AdminPage setTextArea() {
        inputTextArea.setValue(text);
        return this;
    }

    public AdminPage pressBtnSave() {
        btnSave.click();
        return this;
    }

    public AdminPage openBlogPage() {
        open("https://igorakintev.ru/blog/");
        return this;
    }

    public AdminPage checkNewArticleIsPresent() {
        firstArticle.shouldHave(Condition.text(title));
        return this;
    }

    public void deleteNewArticle() {
        open("https://igorakintev.ru/admin/blog/entry/");
        $(By.xpath("//*[@id='result_list']/tbody/tr[1]/td[1]/input")).click();
        $(By.xpath("//select")).click();
        $(By.xpath("//option[@value='delete_selected']")).click();
        $(By.xpath("//button[(text()='Выполнить')]")).click();
        $(By.xpath("//input[@value='Да, я уверен']")).click();
    }
}
