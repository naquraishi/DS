package test.dstest;

import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import test.dspages.AllUsersPage;
import test.dspages.NewUserPage;
import test.utils.FluentLeniumTest;
import java.util.ArrayList;


/*
    Total users count,traversing through all users DOM already covered in other tests
*/

public class T3AllUsersTest extends FluentLeniumTest {

    @Page
    private AllUsersPage allUsersPage;

    @Page
    private NewUserPage newUserPage;

    @Test
    public void allUsersPageOpenTest(){
        allUsersPage.go();
        allUsersPage.isAt();
        takeScreenshot("target/screenshots/alluserstest/allUsersPageOpenTest.png");
    }


    @Test
    public void newUserAddFromAllUser(){
        allUsersPage.go();
        allUsersPage.newUserButtonClick();
        ArrayList<String> userCredentialsList = newUserPage.randomUserDataGenerator();
        newUserPage.newUserSubmit(userCredentialsList.get(0), userCredentialsList.get(1), userCredentialsList.get(2), userCredentialsList.get(2) );
        takeScreenshot("target/screenshots/alluserstest/newUserAddFromAllUser.png");
        //verification of added user details on the alluserspage covered in successfullyAddUser @ T1NewUserAddTest
        allUsersPage.isAt();
}

    @Test
    public void openingAllUsersPageFromNewUserPage(){
        newUserPage.go();
        newUserPage.allUserButtonClick();
        takeScreenshot("target/screenshots/alluserstest/openingAllUsersPageFromNewUserPage.png");
        //assert for alluserpage open
        allUsersPage.isAt();
    }

}
