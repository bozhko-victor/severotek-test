package severotek.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ApplicationManager {

    ChromeDriver wd;

    private FormHelper formHelper;

    public void init() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        formHelper = new FormHelper(wd);
    }

    public void goToLoginPage() {
        wd.get("https://igorakintev.ru/admin/");
    }

    public void login(String username, String password) {
        type(By.xpath("//*[@id='id_username']"), username);
        type(By.xpath("//*[@id='id_password']"), password);
        click(By.xpath("//input[@type='submit']"));
    }

    private void type(By locator, String text) {
        wd.findElement(locator).sendKeys(text);
    }

    public void assertDashboardText() {
        WebElement dashboard = wd.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(dashboard.getText(), "Панель управления");
    }

    public void pressBtnAddEntry() {
        click(By.xpath("//a[@href='/admin/blog/entry/add/']//span[contains(text(),'Добавить')]"));
    }

    public void assertHeadlineText() {
        WebElement headline = wd.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(headline.getText(), "Добавить entry");
    }

    public void pressBtnSave() {
        click(By.xpath("//input[@name='_save']"));

    }

    private void click(By locator) {
        wd.findElement(locator).click();
    }

    public void goToBlogPage() {
        wd.get("https://igorakintev.ru/blog/");
    }

    public void assertArticleIsPresent() {
        WebElement article = wd.findElement(By.xpath("//*[@id='entries']/h2[1]/a"));
        Assert.assertEquals(article.getText(), FormHelper.title);
    }

    public void goToAdminEntryPage() {
        wd.get("https://igorakintev.ru/admin/blog/entry/");
    }

    public void stop() {
        wd.close();
    }

    public FormHelper getFormHelper() {
        return formHelper;
    }
}
