package page;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static test.TestData.*;

public class AdminPage {

    public AdminPage openAdminPage() {
        open("https://igorakintev.ru/admin/");
        return this;
    }

    public AdminPage setLogin() {
        $("#id_username").setValue(credentials.login());
        return this;
    }

    public AdminPage setPassword() {
        $("#id_password").setValue(credentials.password());
        return this;
    }

    public AdminPage pressEnter() {
        $("input[type=submit]").click();
        return this;
    }

    public AdminPage checkTitleIsVisible() {
        $(".dashboard-title").shouldHave(Condition.text("Панель управления"));
        return this;
    }

    public AdminPage pressBtnPlusEntries() {
        $("li [href='/admin/blog/entry/add/'").click();
        return this;
    }

    public AdminPage checkTextBlockEntryIsPresent() {
        $("#content").shouldHave(Condition.text("Добавить entry"));
        return this;
    }

    public AdminPage setTitle() {
        $("#id_title").setValue(title);
        return this;
    }

    public AdminPage setSlug() {
        $("#id_slug").setValue(slug);
        return this;
    }

    public AdminPage setMarkdownArea() {
        $("#id_text_markdown").setValue(markdown);
        return this;
    }

    public AdminPage setTextArea() {
        $("#id_text").setValue(text);
        return this;
    }

    public AdminPage pressBtnSave() {
        $("input.default").click();
        return this;
    }

    public AdminPage openBlogPage() {
        open("https://igorakintev.ru/blog/");
        return this;
    }

    public AdminPage checkNewArticleIsPresent() {
        $("#entries").shouldHave(Condition.text(title));
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
