package test.dstest;

import test.dspages.AllUsersPage;
import test.dspages.NewUserPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;

public class T4PageSwitchTest extends FluentLeniumTest {

    @Page
    private AllUsersPage allUsersPage;

    @Page
    private NewUserPage newUserPage;

    @Test
    public void pageSwitchTest(){
        newUserPage.go();
        newUserPage.allUserButtonClick();
        //assert if page not found
        allUsersPage.isAt();
        takeScreenshot("target/screenshots/pageswitchtest/allusersscreen.png");
        allUsersPage.newUserButtonClick();
        takeScreenshot("target/screenshots/pageswitchtest/newuserscreen.png");
        //assert if page not found
        newUserPage.isAt();
    }
}
