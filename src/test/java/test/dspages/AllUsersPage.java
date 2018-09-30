package test.dspages;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;


@PageUrl("http://85.93.17.135:9000/users/all")

public class AllUsersPage extends FluentPage {

    @FindBy(xpath = "//a[@class='btn btn-default']")
    private FluentWebElement newUserButton;


    @FindBy(xpath = "//table[@id='users']/tbody/tr")
    private static FluentList<FluentWebElement> usersTable;

    //check if the user details which were attempted to add, do exist on AllUsersPage after addition
    public boolean checkUserCredentialExists(String uname, String email, String pwd) {
        for (FluentWebElement eachRow : usersTable) {
            FluentList<FluentWebElement> eachColumn = eachRow.find(By.tagName("td"));
            if(!eachColumn.get(0).text().equals("No Users")){
                if (uname.equals(eachColumn.get(0).text()) && email.equals(eachColumn.get(1).text()) && pwd.equals(eachColumn.get(2).text())) {
                    //System.out.println(uname + ": " + eachColumn.get(0).text() + email + " : " +  eachColumn.get(1).text() );
                    return true;
                }
            } else{

                return false;
            }
        }
        return false;
    }

    public void newUserButtonClick(){
        newUserButton.click();
    }

    @Override
    public void isAt() {
        assertThat(window().title()).contains("All User");
    }

}