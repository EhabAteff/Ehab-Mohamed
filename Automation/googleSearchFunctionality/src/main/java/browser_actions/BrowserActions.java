package browser_actions;

import credentials.ReadPropertiesFile;
import driver.DriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class BrowserActions {
	static ReadPropertiesFile dataReader = new ReadPropertiesFile();

	public static void newDriver(String browser){
		DriverSingleton.setDriver(browser);
	}

	public static WebDriver getDriver() { return DriverSingleton.getDriver(); }

	public static void navigate(String site) throws IOException {
		String url = dataReader.getUrl(site);
		System.out.println("in Navigate , trying to navigate to " + url);
		getDriver().get(url);
	}

	public static void navigateNewTab(String site) throws IOException {
		String url = dataReader.getUrl(site);
		((JavascriptExecutor) getDriver()).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(1));
		getDriver().get(url);
	}

	public static void refresh() {
		getDriver().navigate().refresh();
	}

	public static Boolean click(String identifierType, String identifierValue) {
		try {
			WebElement elementidentifier = elementCreator(identifierType, identifierValue);
			elementidentifier.click();
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
	}

	public static void clear(String identifierType, String identifierValue) {
		WebElement elementIdentifier = elementCreator(identifierType, identifierValue);
		elementIdentifier.clear();
	}

	public static void input(String identifierType, String identifierValue, String Value) {
		WebElement elementIdentifier = elementCreator(identifierType, identifierValue);
		elementIdentifier.clear();
		elementIdentifier.sendKeys(Value);
	}

	public static void closeBrowser() {
		getDriver().quit();
	}

	public static void maximize() {
		getDriver().manage().window().maximize();
	}

	public static void mouseHover(String identifierType, String identifierValue) {
		Actions action = new Actions(getDriver());
		WebElement elementIdentifier = elementCreator(identifierType, identifierValue);
		action.moveToElement(elementIdentifier).moveToElement(elementIdentifier).perform();
	}

	public static Boolean verifyLink(String urlLink) throws Exception {
		URL link = new URL(urlLink);
		// create a connection using URL object
		HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
		httpConn.setConnectTimeout(2000);
		httpConn.connect();

		// use getResponseCode() to get the response code
		if (httpConn.getResponseCode() == 200) {
			System.out.println(urlLink + " - " + httpConn.getResponseMessage());
			return true;
		}
		else if (httpConn.getResponseCode() == 404) {
			System.out.println(urlLink + " - " + httpConn.getResponseMessage());
			return false;
		}
		return false;
	}

	public static void alerts(String status) {
		Alert alert = getDriver().switchTo().alert();
		if (status.equalsIgnoreCase("confirm")) {
			alert.accept();
		} else if (status.equalsIgnoreCase("dismiss")) {
			alert.dismiss();
		}
	}

	public static WebElement elementCreator(String identifierType, String identifierValue) {
		WebElement identifier = null;
		try {
			if (identifierType.equalsIgnoreCase("Id")) {
				identifier = getDriver().findElement(By.id(identifierValue));
			} else if (identifierType.equalsIgnoreCase("css")) {
				identifier = getDriver().findElement(By.cssSelector(identifierValue));
			} else if (identifierType.equalsIgnoreCase("xPath")) {
				identifier = getDriver().findElement(By.xpath(identifierValue));
			} else if (identifierType.equalsIgnoreCase("className")) {
				identifier = getDriver().findElement(By.className(identifierValue));
			} else if (identifierType.equalsIgnoreCase("linkText")) {
				identifier = getDriver().findElement(By.linkText(identifierValue));
			} else if (identifierType.equalsIgnoreCase("Name")) {
				identifier = getDriver().findElement(By.name(identifierValue));
			} else if (identifierType.equalsIgnoreCase("partialLinkText")) {
				identifier = getDriver().findElement(By.partialLinkText(identifierValue));
			} else if (identifierType.equalsIgnoreCase("tagName")) {
				identifier = getDriver().findElement(By.tagName(identifierValue));
			}
		} catch (NoSuchElementException e) {
//			System.out.println(e.toString());
		}
		return identifier;
	}

	public static boolean isElementPresent(String identifierType, String identifierValue) {
		try {
			WebElement elementIdentifier = elementCreator(identifierType, identifierValue);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public static String getElementValue(String identifierType, String identifierValue) {
			WebElement elementIdentifier = elementCreator(identifierType, identifierValue);
		return elementIdentifier.getAttribute("value");
	}

}