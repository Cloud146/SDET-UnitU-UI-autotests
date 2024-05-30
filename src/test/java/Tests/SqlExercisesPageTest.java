package Tests;

import Helpers.CookieUtils;
import Pages.SqlExercisesPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Epic("UI автотесты")
@Feature("Работа с cookie")
public class SqlExercisesPageTest extends BaseTest{
    private WebDriver driver;
    private SqlExercisesPage sqlExercisesPage;

    @Story("Открытие страницы")
    @Severity(SeverityLevel.BLOCKER)
    @BeforeMethod
    public void openHomePage() throws IOException {
        driver = getDriver();
        driver.get(configurationProvider.getSqlExercisesPageURL());
        sqlExercisesPage = new SqlExercisesPage(driver);
    }

    @Story("Сохранения cookie авторизированного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Тест авторизации пользователя и сохранения его cookie в файл", priority = 1, enabled = false)
    public void loginAndSaveCookieTest() throws IOException {
        sqlExercisesPage.authorization("loginTest1", "sqlTest1");
        Assert.assertEquals(sqlExercisesPage.usernameCheck(), "userTest1");
        CookieUtils.saveCookiesToFile(driver, configurationProvider.getCookieFilePath());
        Path path = Paths.get(configurationProvider.getCookieFilePath());
        Assert.assertTrue(Files.exists(path), "Cookie file was not saved successfully.");
    }

    @Story("Загрузка cookie авторизированного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Тест загрузки cookie авторизированного пользователя из файла", priority = 2, enabled = false)
    public void loginWithSavedCookieTest() throws IOException{
        driver.manage().deleteAllCookies();
        CookieUtils.loadCookiesFromFile(driver, configurationProvider.getCookieFilePath());
        driver.navigate().refresh();
        Assert.assertEquals(sqlExercisesPage.usernameCheck(), "userTest1");
    }

    @Story("Поле ввода login")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Тест сброса фокуса из выбранного поля ввода", priority = 3, enabled = false)
    public void clickAndBlurInputFieldTest(){
        sqlExercisesPage.loginField.click();
        pageActions.blurInputField(driver, sqlExercisesPage.loginField);
        Assert.assertFalse(pageActions.checkCursorInInputField(driver, sqlExercisesPage.loginField), "Input field is focused");
    }

    @Story("Вертикальный скрол")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Проверка наличия вертикального скрола на странице", priority = 4, enabled = false)
    public void checkForVerticalScrollTest(){
        Assert.assertTrue(pageActions.checkVerticalScroll(driver), "Vertical scroll not found");
    }
}
