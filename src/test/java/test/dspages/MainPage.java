package test.dspages;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import static java.lang.Thread.sleep;


@PageUrl("http://85.93.17.135:9000/user/new")
public class MainPage extends FluentPage {

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

    @FindBy(xpath = "//a[@class='btn btn-primary']" )
    private FluentWebElement allUsersButton;



    public void newUserSubmit(String... args) throws InterruptedException {
        name.fill().with(args[0]);
        email.fill().with(args[1]);
        password.fill().with(args[2]);
        confirmationPassword.fill().with(args[3]);
        sleep(1000);
        submitButton.click();
        //assert if the second page opens
        sleep(10000);
    }

    public void allUserButtonClick(){
        allUsersButton.click();
    }



}