package Tests;

import Pages.AuthorizationPage;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Авторизация пользователей")
@Feature("Страница авторизации")
public class AuthorizationPageTests extends BaseTest {
    private WebDriver driver;
    private AuthorizationPage authorizationPage;

    @Story("Открытие страницы авторизации")
    @Severity(SeverityLevel.BLOCKER)
    @BeforeMethod
    public void openHomePage() throws IOException {
        driver = getDriver();
        driver.get(configurationProvider.getAuthorizationPageURL());
        authorizationPage = new AuthorizationPage(driver);
    }

    @DataProvider(name = "loginUsersData")
    public Object[][] loginUsersData() {
        return new Object[][]{
                {"angular", "password", "description", true},
                {"user1", "password1", "description1", false},
                {"", "password", "description", false},
                {"user", "", "description", false},
                {"user", "password", "", false},
                {"", "", "description", false},
                {"user", "", "", false},
                {"", "password", "", false},
                {"", "", "", false},
        };
    }

    @Story("Авторизация с различными данными ввода")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Тест авторизации различных данных ввода", dataProvider = "loginUsersData")
    public void authorizationTest(String username, String password, String description, boolean isSuccessful){
        authorizationPage.authorization(username, password, description);
        if (isSuccessful) {
            Assert.assertEquals(authorizationPage.loggedInLabel.getText(), outputData.loggedInText);
        } else if (!username.equals("") && !password.equals("") && !description.equals("")) {
            Assert.assertEquals(authorizationPage.alertLabel.getText(), outputData.errorLabelText);
        }
        else {
            //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            waitings.waitForVisibility(driver, authorizationPage.loginButton, 100);
            Assert.assertEquals(authorizationPage.loginButton.isEnabled(), false);
        }
    }
}
