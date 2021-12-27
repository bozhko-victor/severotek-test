package test;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class VerificationTaskTest extends TestBase {

    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    Faker faker = new Faker();
    String title = faker.name().title();
    String slug = faker.internet().slug();
    String markdown = faker.number().digits(15);
    String text = faker.backToTheFuture().toString();

    @Test
    public void addingArticleToBlogTest() {
        open("https://igorakintev.ru/admin/");
        $("#id_username").setValue(credentials.login());
        $("#id_password").setValue(credentials.password());
        $("input[type=submit]").click();
        $(".dashboard-title").shouldHave(Condition.text("Панель управления"));
        $("li [href='/admin/blog/entry/add/'").click();
        $("#content").shouldHave(Condition.text("Добавить entry"));
        $("#id_title").setValue(title);
        $("#id_slug").setValue(slug);
        $("#id_text_markdown").setValue(markdown);
        $("#id_text").setValue(text);
        $("input.default").click();
        open("https://igorakintev.ru/blog/");
        $("#entries").shouldHave(Condition.text(title));
        open("https://igorakintev.ru/admin/blog/entry/");
        $(By.xpath("//*[@id='result_list']/tbody/tr[1]/td[1]/input")).click();
        $(By.xpath("//select")).click();
        $(By.xpath("//option[@value='delete_selected']")).click();
        $(By.xpath("//button[(text()='Выполнить')]")).click();
        $(By.xpath("//input[@value='Да, я уверен']")).click();
    }

}
