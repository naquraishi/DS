package test.dstest;

import test.dspages.AllUsersPage;
import test.dspages.MainPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;

public class T2PageSwitchTest extends FluentLeniumTest {

    @Page
    private AllUsersPage allUsersPage;

    @Page
    private MainPage mainPage;

    @Test
    public void pageSwitchTest() throws InterruptedException {
        mainPage.go();
        mainPage.allUserButtonClick();
        Thread.sleep(1000);
        takeScreenshot("target/screenshots/pageswitchtest/allusersscreen.png");
        allUsersPage.newUserButtonClick();
        Thread.sleep(1000);
        takeScreenshot("target/screenshots/pageswitchtest/newuserscreen.png");
    }
}
