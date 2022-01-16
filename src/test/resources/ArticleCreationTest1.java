package severotek;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArticleCreationTest1 {

    ChromeDriver wd;

    static Faker faker = new Faker();
    public static String title = faker.name().title();
    public static String slug = faker.internet().slug();
    public static String markdown = faker.number().digits(15);
    public static String text = faker.backToTheFuture().toString();

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
    }

    @Test
    public void simpleTest() {
        goToLoginPage();
        login();
        assertDashboardText();
        pressBtnAddEntry();
        assertHeadlineText();
        fillFormData();
        pressBtnSave();
        goToBlogPage();
        assertArticleIsPresent();
        goToAdminEntryPage();
        deleteFirstArticle();
    }

    private void assertArticleIsPresent() {
        WebElement article = wd.findElement(By.xpath("//*[@id='entries']/h2[1]/a"));
        Assert.assertEquals(article.getText(), title);
    }

    private void pressBtnSave() {
        wd.findElement(By.xpath("//input[@name='_save']")).click();
    }

    private void assertHeadlineText() {
        WebElement headline = wd.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(headline.getText(), "Добавить entry");
    }

    private void pressBtnAddEntry() {
        wd.findElement(By.xpath("//a[@href='/admin/blog/entry/add/']//span[contains(text(),'Добавить')]")).click();
    }

    private void assertDashboardText() {
        WebElement dashboard = wd.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(dashboard.getText(), "Панель управления");
    }

    private void goToAdminEntryPage() {
        wd.get("https://igorakintev.ru/admin/blog/entry/");
    }

    private void goToBlogPage() {
        wd.get("https://igorakintev.ru/blog/");
    }

    private void goToLoginPage() {
        wd.get("https://igorakintev.ru/admin/");
    }

    private void deleteFirstArticle() {
        wd.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/td[1]/input")).click();
        wd.findElement(By.xpath("//select")).click();
        wd.findElement(By.xpath("//option[@value='delete_selected']")).click();
        wd.findElement(By.xpath("//button[(text()='Выполнить')]")).click();
        wd.findElement(By.xpath("//input[@value='Да, я уверен']")).click();
    }

    private void fillFormData() {
        wd.findElement(By.xpath("//input[@id='id_title']")).sendKeys(title);
        wd.findElement(By.xpath("//input[@id='id_slug']")).sendKeys(slug);
        wd.findElement(By.xpath("//textarea[@id='id_text_markdown']")).sendKeys(markdown);
        wd.findElement(By.xpath("//textarea[@id='id_text']")).sendKeys(text);
    }

    private void login() {
        wd.findElement(By.xpath("//*[@id='id_username']")).sendKeys("selenium");
        wd.findElement(By.xpath("//*[@id='id_password']")).sendKeys("super_password");
        wd.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @AfterMethod
    public void tearDown() {
        wd.close();
    }
}
