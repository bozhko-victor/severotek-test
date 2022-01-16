package severotek;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ArticleCreationTest {

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
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void simpleTest() {
        wd.get("https://igorakintev.ru/admin/");
        wd.findElement(By.xpath("//*[@id='id_username']")).sendKeys("selenium");
        wd.findElement(By.xpath("//*[@id='id_password']")).sendKeys("super_password");
        wd.findElement(By.xpath("//input[@type='submit']")).click();

        WebElement dashboard = wd.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(dashboard.getText(), "Панель управления");

        wd.findElement(By.xpath("//a[@href='/admin/blog/entry/add/']//span[contains(text(),'Добавить')]")).click();
        WebElement headline = wd.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(headline.getText(), "Добавить entry");

        wd.findElement(By.xpath("//input[@id='id_title']")).sendKeys(title);
        wd.findElement(By.xpath("//input[@id='id_slug']")).sendKeys(slug);
        wd.findElement(By.xpath("//textarea[@id='id_text_markdown']")).sendKeys(markdown);
        wd.findElement(By.xpath("//textarea[@id='id_text']")).sendKeys(text);

        wd.findElement(By.xpath("//input[@name='_save']")).click();

        wd.get("https://igorakintev.ru/blog/");

        WebElement article = wd.findElement(By.xpath("//*[@id='entries']/h2[1]/a"));
        Assert.assertEquals(article.getText(), title);

        wd.get("https://igorakintev.ru/admin/blog/entry/");
        wd.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/td[1]/input")).click();
        wd.findElement(By.xpath("//select")).click();
        wd.findElement(By.xpath("//option[@value='delete_selected']")).click();
        wd.findElement(By.xpath("//button[(text()='Выполнить')]")).click();
        wd.findElement(By.xpath("//input[@value='Да, я уверен']")).click();
    }

    @AfterMethod
    public void tearDown() {
        wd.close();
    }
}
