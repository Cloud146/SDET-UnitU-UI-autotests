package Tests;

import Pages.HomePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Отображение элементов страницы")
@Feature("Страница home")
public class HomePageTests extends BaseTest{
    private WebDriver driver;
    private HomePage homePage;

    @Story("Открытие страницы home")
    @Severity(SeverityLevel.BLOCKER)
    @BeforeMethod
    public void openHomePage() throws IOException {
        driver = getDriver();
        driver.get(configurationProvider.getHomePageURL());
        homePage = new HomePage(driver);
    }

    @Story("Отображение верхнего меню")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Тест наличия верхнего меню", priority = 1, enabled = true)
    public void headerCheckTest(){
        softAssert.assertTrue(homePage.headerCheck(), "Header not found");
        softAssert.assertEquals(homePage.header.getText(), outputData.headerText);
        softAssert.assertAll();
    }

    @Story("Отображение нижнего меню")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Тест наличия футера", priority = 2, enabled = true)
    public void footerCheckTest(){
        homePage.closePopUp()
                        .scrollToTheBottom();
        softAssert.assertTrue(homePage.footerCheck(), "Footer not found");
        softAssert.assertEquals(homePage.footer.getText(), outputData.footerText);
        softAssert.assertAll();
    }


    @Story("Отображение верхнего меню после скролла")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Тест наличия верхнего меню после скролла", priority = 4, enabled = true)
    public void headerCheckAfterScrollingTest(){
        homePage.closePopUp()
                        .scrollToTheBottom();
        waitings.waitTimeForElement(2, driver, homePage.header);
        softAssert.assertTrue(homePage.headerCheck(), "Header not found");
        softAssert.assertEquals(homePage.header.getText(), outputData.headerText);
        softAssert.assertAll();
    }
    
    @Story("Отображение блока курсов при прокрутке слайдера")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Тест блока с курсами (слайдер)", priority = 3, enabled = true)
    public void courseSwapperTest(){
        homePage.closePopUp()
                        .actionScroll(0, 1100);
        homePage.swipe(outputData.swiperTextAfterSwiping, homePage.courseSwiperNext);
        Assert.assertEquals(outputData.swiperTextAfterSwiping, homePage.courseSwiper.getText());
    }
}
