package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import static test.TestData.*;

public class AdminPage {

    WebDriver driver = new ChromeDriver();

    public AdminPage openAdminPage() {
        driver.get("https://igorakintev.ru/admin/");
        return this;
    }

    public AdminPage setLogin() {
        driver.findElement(By.xpath("//*[@id='id_username']")).sendKeys(credentials.login());
        return this;
    }

    public AdminPage setPassword() {
        driver.findElement(By.xpath("//*[@id='id_password']")).sendKeys(credentials.password());
        return this;
    }

    public AdminPage pressEnter() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        return this;
    }

    public AdminPage checkTitleIsVisible() {
        WebElement dashboard = driver.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(dashboard.getText(), "Панель управления");
        return this;
    }

    public AdminPage pressBtnPlusEntries() {
        driver.findElement(By.xpath("//a[@href='/admin/blog/entry/add/']//span[contains(text(),'Добавить')]")).click();
        return this;
    }

    public AdminPage checkTextBlockEntryIsPresent() {
        WebElement headline = driver.findElement(By.xpath("//h1[contains(text(),'Добавить entry')]"));
        Assert.assertEquals(headline.getText(), "Добавить entry");
        return this;
    }

    public AdminPage setTitle() {
        driver.findElement(By.xpath("//input[@id='id_title']")).sendKeys(title);
        return this;
    }

    public AdminPage setSlug() {
        driver.findElement(By.xpath("//input[@id='id_slug']")).sendKeys(slug);
        return this;
    }

    public AdminPage setMarkdownArea() {
        driver.findElement(By.xpath("//textarea[@id='id_text_markdown']")).sendKeys(markdown);
        return this;
    }

    public AdminPage setTextArea() {
        driver.findElement(By.xpath("//textarea[@id='id_text']")).sendKeys(text);
        return this;
    }

    public AdminPage pressBtnSave() {
        driver.findElement(By.xpath("//input[@name='_save']")).click();
        return this;
    }

    public AdminPage openBlogPage() {
        driver.get("https://igorakintev.ru/blog/");
        return this;
    }

    public AdminPage checkNewArticleIsPresent() {
        WebElement article = driver.findElement(By.xpath("//*[@id='entries']/h2[1]/a"));
        Assert.assertEquals(article.getText(), title);
        return this;
    }

    public void deleteNewArticle() {
        driver.get("https://igorakintev.ru/admin/blog/entry/");
        driver.findElement(By.xpath("//*[@id='result_list']/tbody/tr[1]/td[1]/input")).click();
        driver.findElement(By.xpath("//select")).click();
        driver.findElement(By.xpath("//option[@value='delete_selected']")).click();
        driver.findElement(By.xpath("//button[(text()='Выполнить')]")).click();
        driver.findElement(By.xpath("//input[@value='Да, я уверен']")).click();
        driver.close();

    }
}
