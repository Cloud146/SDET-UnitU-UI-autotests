package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;


public class HomePage {

    private WebDriver driver;
    private JavascriptExecutor jse;
    private Actions actions;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.jse = (JavascriptExecutor)driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".site-header-primary-section-right .ast-builder-menu-1")
    public WebElement header;

    @FindBy(css = "#page > div.elementor.elementor-25361.elementor-location-footer.nitro-lazy-render > div > section > div.elementor-container.elementor-column-gap-default > div > div > section.elementor-section.elementor-inner-section.elementor-element.elementor-element-173a727c.elementor-section-boxed.elementor-section-height-default.elementor-section-height-default > div > div > div > div > div > h2")
    public WebElement footer;

    @FindBy(css = "section.elementor-section.elementor-element-1e537621 h1")
    public WebElement headingTitle;

    @FindBy(css = ".dialog-lightbox-close-button")
    public WebElement closePopUpButton;

    @FindBy(css = ".elementor-section.elementor-element-166618a .elementor-container > div")
    public WebElement courseSwiper;

    @FindBy(css = "section.elementor-element-166618a .pp-slider-arrow.swiper-button-next")
    public WebElement courseSwiperNext;

    @FindBy(css = "section.elementor-element-166618a .pp-slider-arrow.swiper-button-prev")
    public WebElement courseSwiperPrev;


    public boolean headerCheck(){
        return header.isDisplayed();
    }

    public boolean footerCheck(){
        return footer.isDisplayed();
    }

    public void scroll(int xPixels, int yPixels){
        jse.executeScript("window.scrollBy(" +xPixels +"," +yPixels +")");
    }

    public void scroll2(int xPixels, int yPixels){
        actions.scrollByAmount(xPixels, yPixels).build().perform();
    }

    public void scrollToTheBottom(){
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void closePopUp(){
        headingTitle.click();
        int waitTimeInSeconds = 3;
        Duration waitTime = Duration.ofMillis(waitTimeInSeconds * 100000);
        WebElement dynamicElement = (new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(closePopUpButton)));
        closePopUpButton.click();
    }

    public void swipe(String text, WebElement button){
        while (true){
            button.click();
            String swiperText = courseSwiper.getText();
            if (Objects.equals(text, swiperText)){
                break;
            }
        }
    }
}
