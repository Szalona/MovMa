package onwelo.moventum.platform.login;

import onwelo.moventum.setup.Base;
import onwelo.moventum.setup.Credentials;
import onwelo.moventum.setup.Wait;
import org.openqa.selenium.By;

public class LoginPage extends Base {

    private By username = By.id("Login_UserName");
    private By password = By.id("Login_Password");

    public By getUsername() {
        return username;
    }

    public By getPassword() {
        return password;
    }

    public void login(){
        fillUsername();
    }

    private void fillUsername(){
        waitAndSendkeys(Wait.VISIBILITY, getUsername(), "Username input", Credentials.DELTA_USERNAME);
    }

    private void fillPassword(){

    }
}
