package com.severotek.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        wd.get("https://igorakintev.ru/admin/");
        type(By.xpath("//*[@id='id_username']"), username);
        type(By.xpath("//*[@id='id_password']"), password);
        click(By.xpath("//input[@type='submit']"));
    }
}
