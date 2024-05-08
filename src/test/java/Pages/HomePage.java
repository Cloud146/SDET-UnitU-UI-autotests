package Pages;

import Helpers.PageActions;
import Helpers.Waitings;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

/**
 * Класс pageObject Страница Home
 * @author Alex Seburev
 * @version 1.1
 */
public class HomePage extends PageActions {

    private WebDriver driver;
    Waitings waitings;

    /**
     * Объект страницы Home
     */
    public HomePage(WebDriver driver){
        this.driver = driver;
        this.jse = (JavascriptExecutor)driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
        waitings = new Waitings();
    }

    @FindBy(css = ".site-header-primary-section-right .ast-builder-menu-1")
    public WebElement header;

    @FindBy(xpath = "//section[2]/div/div/div/div/div/h2")
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

    /**
     * Функция проверки отображения элемента header
     * @return возвращает булевое значение
     */
    @Step("Проверка что header отображается")
    public boolean headerCheck(){
        return header.isDisplayed();
    }

    /**
     * Функция проверки отображения элемента footer
     * @return возвращает булевое значение
     */
    @Step("Проверка что footer отображается")
    public boolean footerCheck(){
        return footer.isDisplayed();
    }

    /**
     * Функция закрытие всплывающего окна
     * @return возвращает объект страницы Home
     */
    @Step("Закрытие всплывающего окна")
    public HomePage closePopUp(){
        headingTitle.click();
        waitings.waitTimeForElement(3, driver, closePopUpButton);
        closePopUpButton.click();
        return this;
    }

    /**
     * Функция прокрутки элемента свайпера
     * @param text - текст элемента на момент завершения прокрутки
     * @param button - кнопка нажатия прокрутки
     * @return возвращает объект страницы Home
     */
    @Step("Прокрутка элементов свайпера")
    public HomePage swipe(String text, WebElement button){
        while (true){
            button.click();
            String swiperText = courseSwiper.getText();
            if (Objects.equals(text, swiperText)){
                break;
            }
        }
        return this;
    }
}
