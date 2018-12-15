package onwelo.moventum.testcases.TC001_LoginToPlatform.firefox;

import onwelo.moventum.setup.Browser;
import onwelo.moventum.testcases.TC001_LoginToPlatform.LoginToPlatform;
import org.testng.annotations.*;

public class LoginToPlatformFirefox extends LoginToPlatform {

    @BeforeClass
    public void set(){
        setup(PLATFORM_URL, Browser.FIREFOX);
    }

    @Test
    public void should_login_to_platform_by_chrome_browser(){
        shouldLoginToPlatform();
    }

    @AfterClass
    public void close(){
//        quitBrowser(Browser.FIREFOX);
    }
}