package test.dstest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import test.dspages.AllUsersPage;
import test.dspages.NewUserPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import test.utils.FluentLeniumTest;
import test.utils.MyProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.Assert.*;


public class T1NewUserAddTest extends FluentLeniumTest {

    private static MyProperties myProperties = new MyProperties();
    private ArrayList<String> userCredentialsList;

    @Page
    private AllUsersPage allUsersPage;

    @Page
    private NewUserPage newUserPage;

    @Test
    //this test adds a unique user by generating randomised user details and regenerating and adding again in case of duplicate user error
    public void successfullyAddUser(){
        addUser();
        while (newUserPage.userNameErrorPresent() | newUserPage.userEmailErrorPresent()){
            addUser();
        }
        allUsersPage.go();
        boolean userFound = allUsersPage.checkUserCredentialExists(userCredentialsList.get(0), userCredentialsList.get(1), userCredentialsList.get(2));
        takeScreenshot("target/screenshots/newuseraddtest/successfullyAddUser.png");
        assertTrue(userFound);
    }

    @Test
    public void duplicateUserAdd(){
        while (!newUserPage.userNameErrorPresent() | !newUserPage.userEmailErrorPresent()){
            addUserError();
        }
        takeScreenshot("target/screenshots/newuseraddtest/duplicateUserAdd.png");
        assertTrue(newUserPage.userNameErrorPresent() | newUserPage.userEmailErrorPresent());
    }


    @Test
    public void invalidEmailAdd(){
        addInvalidEmail();
        takeScreenshot("target/screenshots/newuseraddtest/invalidEmailAdd.png");
        assertTrue( newUserPage.userEmailErrorPresent());
    }

    @Test
    public void incompleteNewUserForm() {
        keepARequiredEmpty();
        takeScreenshot("target/screenshots/newuseraddtest/incompleteNewUserForm.png");
        assertTrue( newUserPage.userNameErrorPresent() | newUserPage.userEmailErrorPresent() | newUserPage.userPasswordErrorPresent());
    }


    @Test
    public void multipleUsersAdd(){
        int usersBefore = getUsersJsonArray();
        for(int i = 0; i< Integer.parseInt(myProperties.getProperty("users")); i++){
            userCredentialsList = newUserPage.randomUserDataGenerator();
            newUserPage.go();
            newUserPage.newUserSubmit(userCredentialsList.get(0), userCredentialsList.get(1), userCredentialsList.get(2), userCredentialsList.get(2) );
        }
        int usersAfter = getUsersJsonArray();
        allUsersPage.go();
        takeScreenshot("target/screenshots/newuseraddtest/multipleUsersAdd.png");
        assertEquals((usersAfter - usersBefore), Integer.parseInt(myProperties.getProperty("users")));
    }

    @Test
    public void passwordLessThanSixChar() {
        addShortPassword();
        assertTrue(newUserPage.userPasswordErrorPresent());
    }
    
    private void addUser(){
        newUserPage.go();
        userCredentialsList = newUserPage.randomUserDataGenerator();
        newUserPage.newUserSubmit(userCredentialsList.get(0), userCredentialsList.get(1), userCredentialsList.get(2), userCredentialsList.get(2) );
    }

    private void addUserError(){
        userCredentialsList = newUserPage.randomUserDataGenerator();
        newUserPage.go();
        newUserPage.newUserSubmit("adderrortest", "adderrortest@mail.com", userCredentialsList.get(2), userCredentialsList.get(2) );
    }

    private void addInvalidEmail(){
        userCredentialsList = newUserPage.randomUserDataGenerator();
        newUserPage.go();
        newUserPage.newUserSubmit("testuser", "not_an_email_id", userCredentialsList.get(2), userCredentialsList.get(2) );
    }

    private void addShortPassword(){
        userCredentialsList = newUserPage.randomUserDataGenerator();
        newUserPage.go();
        newUserPage.newUserSubmit(userCredentialsList.get(0), userCredentialsList.get(1), "123", "123" );
    }

    private void keepARequiredEmpty(){
        ArrayList<String> userData = new ArrayList<>(Arrays.asList("testuser@mail.com", "test@mail.com", ""));
        Collections.shuffle(userData);
        newUserPage.go();
        newUserPage.newUserSubmit(userData.get(0),userData.get(1), userData.get(2),userData.get(2) );
    }

    private int getUsersJsonArray(){
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, myProperties.getProperty("all_users_uri_json"));
        String responseBody = response.getBody().asString();
        int index  = responseBody.indexOf("[");
        responseBody = responseBody.substring(index);
        return new JSONArray(responseBody).length();
    }

}


