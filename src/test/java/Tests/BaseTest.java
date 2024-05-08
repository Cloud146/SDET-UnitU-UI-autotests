package Tests;

import Helpers.InputData;
import io.qameta.allure.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    public WebDriver getDriver(){
        return driver;
    }

    InputData inputData = new InputData();

    @Description("Открытие браузера с соответствующими настройками")
    @BeforeMethod
    public void setUp() throws IOException {
        driver = new ChromeDriver(new ChromeOptions()
                .addArguments("--remote-allow-origins=**")
                .addArguments("--disable-gpu")
                .addArguments("--disable-infobars")
                .addArguments("--start-maximized"));
        driver.manage().window().setSize(new Dimension(inputData.getScreenWidth(), inputData.getScreenHeight()));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Description("Закрытие браузера")
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
