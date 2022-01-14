package com.severotek.tests;

import com.severotek.appmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.openqa.selenium.remote.BrowserType.*;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(CHROME);


    @BeforeMethod
    public void setUp() {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

}
