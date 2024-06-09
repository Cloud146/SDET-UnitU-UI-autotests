package Tests;

import Helpers.*;
import io.qameta.allure.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;


import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    OutputData outputData = new OutputData();
    Waitings waitings = new Waitings();
    ConfigurationProvider configurationProvider = new ConfigurationProvider();
    PageActions pageActions = new PageActions();
    SoftAssert softAssert = new SoftAssert();

    //private RemoteWebDriver driver;
    //DesiredCapabilities capabilities = new DesiredCapabilities();

    public CapabilityFactory capabilityFactory = new CapabilityFactory();

    public WebDriver getDriver(){
        return driver;
    }

    @Description("Открытие браузера с соответствующими настройками")
    @BeforeMethod(enabled = true)
    public void browserSetUp(ITestContext context) throws IOException, IllegalAccessException {
       driver = DriverFactory.getWebDriver("chrome");
       driver.manage().window().setSize(new Dimension(configurationProvider.getScreenWidth(), configurationProvider.getScreenHeight()));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        for(ITestNGMethod method : context.getAllTestMethods()){
//            method.setRetryAnalyzerClass(RetryAnalyzer.class);
//        }
    }

    @Description("СетАп для параллельного тестирования")
    @BeforeMethod(enabled = false)
    public void parallelSetUp() throws IOException {
        driver = new RemoteWebDriver(new URL(configurationProvider.getGridHubURL()), capabilityFactory.getCapabilities());
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
