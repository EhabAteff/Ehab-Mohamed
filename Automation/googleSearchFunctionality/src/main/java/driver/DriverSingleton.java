package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.Assert;

import java.io.IOException;

public class DriverSingleton {
    public static String chromePath = "./webdriver/chromedriver.exe";
    public static String firefoxPath = "./webdriver/geckodriver.exe";
    public static String iePath = "./webdriver/IEDriverServer.exe";
    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(String browserType) {
        try {
            System.out.println("in OpenBrowser , Trying to Open " + browserType);
            if (browserType.equalsIgnoreCase("FireFox")) {
                try {
                    System.out.println("Quitting FireFox incase is already opened ");
                    driver.quit();
                } catch (Exception ignored) {
                }
                System.out.println("Opening FireFox ");
                driver = new FirefoxDriver();
                System.setProperty("webdriver.gecko.driver", firefoxPath);
            }
            else if (browserType.equalsIgnoreCase("Chrome")) {
                try {
                    System.out.println("Quitting Chrome incase is already opened ");
                    driver.quit();
                }
                catch (Exception ignored) {
                }
                System.out.println("Opening Chrome ");
                System.setProperty("webdriver.chrome.driver", chromePath);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("ignore-certificate-errors");
                driver = new ChromeDriver(options);
            }
            else if (browserType.equalsIgnoreCase("IE")) {
                try {
                    System.out.println("Quitting IE incase is already opened ");
                    driver.quit();
                } catch (Exception ignored) {
                }
                driver = new InternetExplorerDriver(setIEDesiredCapabilities());
            }
            else{
                Assert.fail("Invalid Browser Type");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static InternetExplorerOptions setIEDesiredCapabilities() throws IOException {
        System.out.println("Opening Internet Explorer ");
        System.setProperty("webdriver.ie.driver", iePath);
        InternetExplorerOptions ieCapabilities = new InternetExplorerOptions();
        ieCapabilities.setCapability("requireWindowFocus()", true);
        ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
        ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
        ieCapabilities.setCapability("disable-popup-blocking", true);
        ieCapabilities.setCapability("enablePersistentHover", true);
        ieCapabilities.setCapability("ignoreZoomSetting", true);
        ieCapabilities.setCapability("nativeEvents", false);
        ieCapabilities.setCapability("ie.ensureCleanSession", true);
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        return ieCapabilities;
    }

}
