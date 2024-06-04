package Tests;

import Pages.TabsExercisesPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class TabsExercisesPageTests extends BaseTest{

    private WebDriver driver;
    TabsExercisesPage tabsExercisesPage;

    @Story("Открытие страницы")
    @Severity(SeverityLevel.BLOCKER)
    @BeforeMethod
    public void openPage() throws IOException {
        driver = getDriver();
        driver.get(configurationProvider.getTabsExercisesPageURL());
        tabsExercisesPage = new TabsExercisesPage(driver);
    }


    @Story("Вкладки браузера")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Тест открытия 3х вкладок", priority = 1, enabled = true)
    public void dragAndDropTestDefault(){
        driver.switchTo().frame(tabsExercisesPage.iframeWindowsDefault);
        tabsExercisesPage.clickNewBrowserTab();
        waitings.waitTimeForElement(100, driver, tabsExercisesPage.newBrowserTabButton);
        tabsExercisesPage.clickNewBrowserTab();

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        driver.switchTo().window(tabsExercisesPage.getBrowserTabByNumber(handles, iterator, 3));

        Assert.assertEquals(driver.getWindowHandles().size(), 3);
        Assert.assertEquals(driver.getCurrentUrl(), "https://way2automation.com/way2auto_jquery/frames-windows/defult1.html#");
        Assert.assertEquals(tabsExercisesPage.newBrowserTabButton.getText(), "New Browser Tab");

    }
}
