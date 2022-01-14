package com.severotek.appmanager;

import org.openqa.selenium.WebDriver;

public class NavigationHelper  extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToAdminEntryPage() {
        wd.get("https://igorakintev.ru/admin/blog/entry/");
    }

    public void goToBlogPage() {
        wd.get("https://igorakintev.ru/blog/");
    }
}
