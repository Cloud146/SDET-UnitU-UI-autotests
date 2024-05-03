package Tests;

import Helpers.OutputData;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest{
    private WebDriver driver;
    private HomePage homePage;
    OutputData outputData = new OutputData();

    @BeforeMethod
    public void setup(){
        driver = getDriver();
        driver.get("https://www.way2automation.com/");
        homePage = new HomePage(driver);
    }

    @Test(description = "Тест наличия верхнего меню", priority = 1, enabled = true)
    public void headerCheckTest(){
        Assert.assertEquals(homePage.headerCheck(), true);
        Assert.assertEquals(homePage.header.getText(), outputData.headerText);
    }

    @Test(description = "Тест наличия футера", priority = 2, enabled = true)
    public void footerCheckTest(){
        homePage.closePopUp();
        homePage.scrollToTheBottom();
        Assert.assertEquals(homePage.footerCheck(), true);
        Assert.assertEquals(homePage.footer.getText(), outputData.footerText);
    }

    @Test(description = "Тест наличия верхнего меню после скролла", priority = 4, enabled = true)
    public void headerCheckAfterScrollingTest(){
        homePage.closePopUp();
        homePage.scrollToTheBottom();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(homePage.headerCheck(), true);
        Assert.assertEquals(homePage.header.getText(), outputData.headerText);
    }

    @Test(description = "Тест блока с курсами (слайдер)", priority = 3, enabled = true)
    public void courseSwapperTest(){
        homePage.closePopUp();
        homePage.scroll2(0, 1100);
        homePage.swipe(outputData.swiperTextAfterSwiping, homePage.courseSwiperNext);
        Assert.assertEquals(outputData.swiperTextAfterSwiping, homePage.courseSwiper.getText());
    }
}
