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

    /**
     * Локатор элемента header
     * css = ".site-header-primary-section-right .ast-builder-menu-1"
     * xpath = "//div[contains(@class, 'site-header-primary-section-right')]/div[contains(@class, 'ast-builder-menu-1')]"
     */
    @FindBy(css = ".site-header-primary-section-right .ast-builder-menu-1")
    public WebElement header;

    /**
     * * Локатор элемента footer
     * css = ".elementor-element-7c212f0a h2"
     * xpath = "//div[contains(@class, '7c212f0a')]//h2"
     */
    @FindBy(css = ".elementor-element-7c212f0a h2")
    public WebElement footer;

    /**
     * Локатор элемента headingTitle
     * css = ".elementor-hidden-mobile h1"
     * xpath = "//div[contains(@class, 'elementor-hidden-mobile')]//h1"
     */
    @FindBy(css = ".elementor-hidden-mobile h1")
    public WebElement headingTitle;

    /**
     * Локатор элемента closePopUpButton
     * css = ".dialog-close-button"
     * xpath = "//div[contains(@class, 'dialog-close-button')]"
     */
    @FindBy(css = ".dialog-close-button")
    public WebElement closePopUpButton;

    /**
     * Локатор элемента courseSwiper
     * css = ".elementor-element-166618a .elementor-column"
     * xpath = "//section[contains(@class, '166618a')]//div[contains(@class, '50827c4')]"
     */
    @FindBy(css = ".elementor-element-166618a .elementor-column")
    public WebElement courseSwiper;

    /**
     * Локатор элемента courseSwiperNext
     * css = ".swiper-button-next.swiper-button-next-c50f9f0"
     * xpath = "//div[contains(@class, 'swiper-button-next-c50f9f0')]"
     */
    @FindBy(css = ".swiper-button-next.swiper-button-next-c50f9f0")
    public WebElement courseSwiperNext;

    /**
     * Локатор элемента courseSwiperPrev
     * css = ".swiper-button-prev.swiper-button-prev-c50f9f0"
     * xpath = "//div[contains(@class, 'swiper-button-prev-c50f9f0')]"
     */
    @FindBy(css = ".swiper-button-prev.swiper-button-prev-c50f9f0")
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
