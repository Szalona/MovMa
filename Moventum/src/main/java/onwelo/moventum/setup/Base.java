package onwelo.moventum.setup;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.logging.log4j.LogManager.getLogger;

public abstract class Base {

    private static final String CHROME_FILEPATH = "/drivers/chromedriver.exe";
    private static final String FIREFOX_FILEPATH = "/drivers/geckodriver.exe";

    public static final String PLATFORM_URL = "";

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebElement waitWithBy;
    public static Logger log;
    public static Credentials credentials;

    private static void setObjects() {
        wait = new WebDriverWait(driver, 10);
        credentials = new Credentials();
    }

    public void setLogger(String loggerClass){
        log = getLogger(loggerClass);
    }

    public void getURL(String url){
        driver.get(url);
        driver.manage().window().maximize();
        setLogger(Base.class.getName());
        log.info(String.format("'%s' url has been opened", url));
    }

    public void setup(String url, Browser browser){
        openBrowserAndGetURL(url, browser);
        setObjects();
    }

    private void openBrowserAndGetURL(String url, Browser browser){
        switch (browser){
            case CHROME:
                System.setProperty("webdriver.chrome.driver", getClass().getResource(CHROME_FILEPATH).getPath());
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", getClass().getResource(FIREFOX_FILEPATH).getPath());
                driver = new FirefoxDriver();
                break;
        }
        setLogger(Base.class.getName());
        log.info(String.format("%s browser has been started", browser));
        getURL(url);
    }

    public WebElement chooseWait(Wait wait, By by){
        switch (wait){
            case VISIBILITY:
                waitWithBy = visibility(by);
                break;
            case CLICKABLE:
                waitWithBy = clickable(by);
                break;
        }
        return waitWithBy;
    }

    public void visibilityAndInvisibility(By by){
        visibility(by);
        invisibility(by);
    }

    private WebElement clickable(By by){
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement visibility(By by){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public boolean invisibility(By by){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitAndClick(Wait wait, By by, String elementName){
        chooseWait(wait, by).click();
        log.debug(String.format("%s has been clicked", elementName));
    }

    public void waitAndSendkeys(Wait wait, By by, String inputName, String text){
        chooseWait(wait, by).sendKeys(text);
        log.debug(String.format("%s has been filled by '%s' data", inputName, text));
    }

    public void closeBrowserTab(Browser browser){
        driver.close();
        setLogger(Base.class.getName());
        log.info(String.format("%s browser tab has been closed", browser));
    }

    public void quitBrowser(Browser browser){
        driver.quit();
        setLogger(Base.class.getName());
        log.info(String.format("%s browser has been closed", browser));
    }
}
