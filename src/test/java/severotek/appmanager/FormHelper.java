package severotek.appmanager;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import severotek.model.FormData;

public class FormHelper {
    static Faker faker = new Faker();
    public static String title = faker.name().title();
    public static String slug = faker.internet().slug();
    public static String markdown = faker.number().digits(15);
    public static String text = faker.backToTheFuture().toString();

    private ChromeDriver wd;

    public FormHelper(ChromeDriver wd) {
        this.wd =  wd;
    }

    public void deleteFirstArticle() {
        click(By.xpath("//*[@id='result_list']/tbody/tr[1]/td[1]/input"));
        click(By.xpath("//select"));
        click(By.xpath("//option[@value='delete_selected']"));
        click(By.xpath("//button[(text()='Выполнить')]"));
        click(By.xpath("//input[@value='Да, я уверен']"));
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void fillFormData(FormData formData) {
        type(By.xpath("//input[@id='id_title']"), formData.getTitle());
        type(By.xpath("//input[@id='id_slug']"), formData.getSlug());
        type(By.xpath("//textarea[@id='id_text_markdown']"), formData.getMarkdown());
        type(By.xpath("//textarea[@id='id_text']"), formData.getText());
    }

    public void type(By locator, String text) {
        wd.findElement(locator).sendKeys(text);
    }
}
