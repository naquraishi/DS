package test.dspages;

import com.mifmif.common.regex.Generex;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.Arrays;
import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

@PageUrl("http://85.93.17.135:9000/user/new")
public class NewUserPage extends FluentPage {

    @FindBy(css = "#name")
    private FluentWebElement name;

    @FindBy(css = "#email")
    private FluentWebElement email;

    @FindBy(css = "#password")
    private FluentWebElement password;

    @FindBy(css = "#confirmationPassword")
    private FluentWebElement confirmationPassword;

    @FindBy(css = "button")
    private FluentWebElement submitButton;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    private FluentWebElement allUsersButton;

    @FindBy(xpath = "//p[@id='user.name.error']")
    private FluentWebElement userNameError;

    @FindBy(xpath = "//p[@id='user.email.error']")
    private FluentWebElement userEmailError;

    @FindBy(xpath = "//p[@id='user.password.error']")
    private FluentWebElement userPasswordError;


    public void newUserSubmit(String... args) {
        name.fill().with(args[0]);
        email.fill().with(args[1]);
        password.fill().with(args[2]);
        confirmationPassword.fill().with(args[3]);
        submitButton.click();
    }

    public ArrayList<String> randomUserDataGenerator(){
        Generex userName = new Generex("[a-z]{3}[0-9]{3}");
        Generex passWord = new Generex("[a-z]{4}[A-Z]{2,3}[0-9]{2,3}");
        return new ArrayList<>(Arrays.asList(userName.random(), userName.random() + "@mail.com", passWord.random()));
    }

    public void allUserButtonClick(){
        allUsersButton.click();
    }

    public boolean userNameErrorPresent() {
        return userNameError.present();
    }

    public boolean userEmailErrorPresent() {
        return userEmailError.present();
    }

    public boolean userPasswordErrorPresent() {
        return userEmailError.present();
    }

    @Override
    public void isAt() {
        assertThat(window().title()).contains("New User");
    }

}