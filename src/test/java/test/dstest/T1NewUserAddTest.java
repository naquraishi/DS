package test.dstest;

import test.dspages.AllUsersPage;
import test.dspages.MainPage;
import com.mifmif.common.regex.Generex;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

public class T1NewUserAddTest extends FluentLeniumTest {

    @Page
    private AllUsersPage allUsersPage;

    @Page
    private MainPage mainPage;

    @Test
    public void shouldSuccessfullyAddUser() throws InterruptedException {

        allUsersPage.go();
        ArrayList<String> userCredentialsList = randomUserDataGenerator();

        while (allUsersPage.checkUserCredentialExists(userCredentialsList.get(0), userCredentialsList.get(1), userCredentialsList.get(2))){
            //this piece of code would be rarely executed, but tested using hardcoded user credential list
            userCredentialsList.clear();
            userCredentialsList = randomUserDataGenerator();
        }

        mainPage.go();
        mainPage.newUserSubmit(userCredentialsList.get(0), userCredentialsList.get(1), userCredentialsList.get(2), userCredentialsList.get(2) );
        takeScreenshot("target/screenshots/newuseraddtest/allusersscreen.png");
        //assert on All User page display
        assertThat(window().title()).contains("All User");
    }

    private ArrayList<String> randomUserDataGenerator(){
        Generex userName = new Generex("[a-z]{3}[0-9]{3}");
        Generex passWord = new Generex("[a-z]{4}[A-Z]{2,3}[0-9]{2,3}");
        return new ArrayList<>(Arrays.asList(userName.random(), userName.random() + "@mail.com", passWord.random()));
    }

}


