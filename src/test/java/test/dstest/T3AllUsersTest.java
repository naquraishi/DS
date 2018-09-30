package test.dstest;

import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import test.dspages.AllUsersPage;

/*
    Total users count,traversing through all users DOM, click to new user from All User page are
    already covered in other tests. So, limited this to just opening of page.
     */

public class T3AllUsersTest extends FluentLeniumTest{

    @Page
    private AllUsersPage allUsersPage;

    @Test
    public void allUsersPageOpenTest(){
        allUsersPage.go();
        allUsersPage.isAt();
        takeScreenshot("target/screenshots/alluserstest/allUsersPageOpenTest.png");
    }

}
