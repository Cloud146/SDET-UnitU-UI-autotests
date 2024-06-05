package Tests;

import Pages.AlertsExercisesPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;


public class AlertsExercisesPageTests extends BaseTest{

    private WebDriver driver;
    AlertsExercisesPage alertsExercisesPage;

    @Story("Открытие страницы")
    @Severity(SeverityLevel.BLOCKER)
    @BeforeMethod
    public void openPage() throws IOException {
        driver = getDriver();
        driver.get(configurationProvider.getAlertsExercisesPageURL());
        alertsExercisesPage = new AlertsExercisesPage(driver);
    }


    @Story("Работа с Alert")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Проверка ввода текста в alert", priority = 1, enabled = true)
    public void inputTextInAlertTest(){
        alertsExercisesPage.clickInputAlertTab()
                        .clickInputBoxButton()
                        .inputTextIntoAlertField("Test User")
                        .acceptAlert();

        Assert.assertEquals(alertsExercisesPage.getGreetingsText(), outputData.outputAlertText("Test User"));
    }
}
