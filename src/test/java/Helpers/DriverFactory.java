package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.IOException;
import java.net.URL;

public class DriverFactory extends  OptionsManager{

    public static WebDriver getWebDriver(String browser) throws IllegalAccessException, IOException {
        WebDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(OptionsManager.getChromeOptions());
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "ie":
                capabilities.setCapability("ignoreProtectedModeSettings", true);
                driver = new InternetExplorerDriver(OptionsManager.getIEOptions());
            default:
                throw new IllegalAccessException("Invalid browser: " +browser);
        }
        return driver;
    }

    public static WebDriver getDriver(String browser, String gridUrl) throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (browser.toLowerCase()) {
            case "chrome":
                capabilities.setBrowserName("chrome");
                capabilities.merge(OptionsManager.getChromeOptions());
                break;
            case "firefox":
                capabilities.setBrowserName("firefox");
                break;
            case "edge":
                capabilities.setBrowserName("MicrosoftEdge");
                break;
            case "ie":
                capabilities.setBrowserName("internet explorer");
                capabilities.setCapability("ignoreProtectedModeSettings", true);
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
        return new RemoteWebDriver(new URL(gridUrl), capabilities);
    }
}
