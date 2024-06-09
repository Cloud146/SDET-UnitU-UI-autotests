package Tests;

import Pages.AuthenticationExamplePage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class AuthenticationExamplePageTests extends BaseTest{

    private WebDriver driver;
    AuthenticationExamplePage authenticationExamplePage;

    @Story("Открытие страницы")
    @Severity(SeverityLevel.BLOCKER)
    @BeforeMethod
    public void openPage() throws IOException {
        driver = getDriver();
        driver.get(configurationProvider.getAuthenticationExamplePageURL());
        authenticationExamplePage = new AuthenticationExamplePage(driver);
    }


    @Story("Basic Authentication Window")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Проверка аутентификации через HTTP окно", priority = 1, enabled = true)
    public void authenticationByHTTPWindowTest(){
        authenticationExamplePage.clickDisplayImageButton();
        try {
            Runtime.getRuntime().exec("authenticationScriptAutoIt.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(authenticationExamplePage.isAuthenticatedImageDisplayed(), "Authenticated image is not displayed");
    }
}
