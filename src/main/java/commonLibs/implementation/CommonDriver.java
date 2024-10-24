package commonLibs.implementation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CommonDriver {

	private WebDriver driver;

	private int pageLoadTimeout;

	private int untilClickable;

	private String currentWDir;

	public CommonDriver(String browserType) throws Exception {

		pageLoadTimeout = 10;
		untilClickable = 8;
		currentWDir = System.getProperty("user.dir");

		if (browserType.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", currentWDir + "/drivers/chromedriver/chromediver.exe");
			driver = new ChromeDriver();

		} else if (browserType.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver", currentWDir + "/drivers/edgedriver/msedgediver.exe");
			driver = new EdgeDriver();

		} else {

			throw new Exception("Invalid Browser, please  try again" + browserType);
		}

		// Maximize window and clear browser cookies
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	public void goToUrl(String url) {

		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(untilClickable, TimeUnit.SECONDS);

		driver.get(url);
	}
}
