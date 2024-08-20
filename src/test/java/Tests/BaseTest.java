package Tests;

import Helpers.*;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    @BeforeMethod(enabled = false)
    public void browserSetUp(ITestContext context) throws IOException, IllegalAccessException {
        driver = DriverFactory.getWebDriver("chrome");
        driver.manage().window().setSize(new Dimension(configurationProvider.getScreenWidth(), configurationProvider.getScreenHeight()));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        for(ITestNGMethod method : context.getAllTestMethods()){
            method.setRetryAnalyzerClass(RetryAnalyzer.class);
        }
    }

    @Description("Запуск тестов через Selenoid")
    @BeforeMethod(enabled = true)
    public void selenoidSetUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("127.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        //options.addArguments("--remote-debugging-port=9222");
        options.merge(capabilities);

        //String remoteUrl = "http://localhost:4444/wd/hub"; //Could not start a new session. Possible causes are invalid address of the remote server or browser start-up failure
        //String remoteUrl = "http://host.docker.internal:8000"; //Could not start a new session. Possible causes are invalid address of the remote server or browser start-up failure
        String remoteUrl = "http://host.docker.internal:4444/wd/hub"; //Could not start a new session. Response code 500. Message: create container: Error response from daemon: No such image: selenoid/chrome:latest
        //String remoteUrl = "http://selenoid:4444/wd/hub"; //Could not start a new session. Response code 500. Message: create container: Error response from daemon: No such image: selenoid/chrome:late
        driver = new RemoteWebDriver(new URL(remoteUrl), options);
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
