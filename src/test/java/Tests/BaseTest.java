package Tests;


import Helpers.ConfigurationProvider;
import Helpers.OutputData;
import Helpers.PageActions;
import Helpers.Waitings;
import io.qameta.allure.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    OutputData outputData = new OutputData();
    Waitings waitings = new Waitings();
    ConfigurationProvider configurationProvider = new ConfigurationProvider();
    PageActions pageActions = new PageActions();

    public WebDriver getDriver(){
        return driver;
    }

    @Description("Открытие браузера с соответствующими настройками")
    @BeforeMethod
    public void browserSetUp() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://172.30.112.1:4444/wd/hub"), capabilities);

//        driver = new ChromeDriver(new ChromeOptions()
//                .addArguments("--remote-allow-origins=**")
//                .addArguments("--disable-gpu")
//                .addArguments("--disable-infobars")
//                .addArguments("--start-maximized"));
//        driver.manage().window().setSize(new Dimension(configurationProvider.getScreenWidth(), configurationProvider.getScreenHeight()));
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @Description("Закрытие браузера/Проверка выполнения теста")
    @AfterMethod
    public void browserTearDown(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            PageActions.takeScreenshot(driver);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
