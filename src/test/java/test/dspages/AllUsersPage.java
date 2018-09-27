package test.dspages;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;


@PageUrl("http://85.93.17.135:9000/users/all")

public class AllUsersPage extends FluentPage {

    @FindBy(xpath = "//a[@class='btn btn-default']")
    private FluentWebElement newUserButton;

    //check if the user details which got added exists

    @FindBy(xpath = "//table[@id='users']/tbody/tr")
    private FluentList<FluentWebElement> usersTable;

    public boolean checkUserCredentialExists(String uname, String email, String pwd) {
        for (FluentWebElement eachRow : usersTable) {
            System.out.println(eachRow.id());
            FluentList<FluentWebElement> eachCoulmn = eachRow.find(By.tagName("td"));
            System.out.println(eachCoulmn.get(0).text());
            if(!eachCoulmn.get(0).text().equals("No Users")){
                if (uname.equals(eachCoulmn.get(0).text()) | email.equals(eachCoulmn.get(1).text()) | pwd.equals(eachCoulmn.get(2).text())) {
                    System.out.println(uname + ": " + eachCoulmn.get(0).text() + email + " : " +  eachCoulmn.get(1).text() );
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


}