package severotek.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import severotek.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

}
