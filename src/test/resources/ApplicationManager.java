package severotek.appmanager;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import severotek.model.FormData;

public class ApplicationManager {

    ChromeDriver wd;

    static Faker faker = new Faker();
    public static String title = faker.name().title();
    public static String slug = faker.internet().slug();
    public static String markdown = faker.number().digits(15);
    public static String text = faker.backToTheFuture().toString();

    public void init() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
    }

    public void deleteFirstArticle() {
        wd.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/td[1]/input")).click();
        wd.findElement(By.xpath("//select")).click();
        wd.findElement(By.xpath("//option[@value='delete_selected']")).click();
        wd.findElement(By.xpath("//button[(text()='Выполнить')]")).click();
        wd.findElement(By.xpath("//input[@value='Да, я уверен']")).click();
    }

    public void fillFormData(FormData formData) {
        wd.findElement(By.xpath("//input[@id='id_title']")).sendKeys(formData.getTitle());
        wd.findElement(By.xpath("//input[@id='id_slug']")).sendKeys(formData.getSlug());
        wd.findElement(By.xpath("//textarea[@id='id_text_markdown']")).sendKeys(formData.getMarkdown());
        wd.findElement(By.xpath("//textarea[@id='id_text']")).sendKeys(formData.getText());
    }

    public void goToLoginPage() {
        wd.get("https://igorakintev.ru/admin/");
    }

    public void login(String username, String password) {
        wd.findElement(By.xpath("//*[@id='id_username']")).sendKeys(username);
        wd.findElement(By.xpath("//*[@id='id_password']")).sendKeys(password);
        wd.findElement(By.xpath("//input[@type='submit']")).click();
    }

    public void assertDashboardText() {
        WebElement dashboard = wd.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(dashboard.getText(), "Панель управления");
    }

    public void pressBtnAddEntry() {
        wd.findElement(By.xpath("//a[@href='/admin/blog/entry/add/']//span[contains(text(),'Добавить')]")).click();
    }

    public void assertHeadlineText() {
        WebElement headline = wd.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(headline.getText(), "Добавить entry");
    }

    public void pressBtnSave() {
        wd.findElement(By.xpath("//input[@name='_save']")).click();
    }

    public void goToBlogPage() {
        wd.get("https://igorakintev.ru/blog/");
    }

    public void assertArticleIsPresent() {
        WebElement article = wd.findElement(By.xpath("//*[@id='entries']/h2[1]/a"));
        Assert.assertEquals(article.getText(), title);
    }

    public void goToAdminEntryPage() {
        wd.get("https://igorakintev.ru/admin/blog/entry/");
    }

    public void stop() {
        wd.close();
    }

}
