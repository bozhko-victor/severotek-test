package com.severotek.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.*;

public class ApplicationManager {
    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private ControlPanelHelper controlPanelHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (Objects.equals(browser, CHROME)) {
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (Objects.equals(browser, OPERA)) {
            wd = new OperaDriver();
        }

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        controlPanelHelper = new ControlPanelHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("selenium", "super_password");
    }

    public void assertEntryCreation() {
        WebElement article = wd.findElement(By.xpath("//*[@id='entries']/h2[1]/a"));
        Assert.assertEquals(article.getText(), "title");
    }

    public void assertHeadlineText() {
        WebElement headline = wd.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(headline.getText(), "Добавить entry");
    }

    public void assertDashboardText() {
        WebElement dashboard = wd.findElement(By.xpath("//*[@id='content']/h1"));
        Assert.assertEquals(dashboard.getText(), "Панель управления");
    }

    public void stop() {
        wd.close();
    }

    public ControlPanelHelper getControlPanelHelper() {
        return controlPanelHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
