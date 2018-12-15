package onwelo.moventum.testcases.TC001_LoginToPlatform.chrome;

import onwelo.moventum.setup.Browser;
import onwelo.moventum.testcases.TC001_LoginToPlatform.LoginToPlatform;
import org.testng.annotations.*;

public class LoginToPlatformChrome extends LoginToPlatform {

    @BeforeClass
    public void set(){
        setup(PLATFORM_URL, Browser.CHROME);
    }

    @Test
    public void should_login_to_platform_by_chrome_browser(){
        shouldLoginToPlatform();
    }

    @AfterClass
    public void close(){
//        quitBrowser(Browser.CHROME);
    }
}