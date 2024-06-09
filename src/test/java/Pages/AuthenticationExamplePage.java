package Pages;

import Helpers.Waitings;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationExamplePage {

    private WebDriver driver;
    Waitings waitings = new Waitings();

    /**
     * Объект страницы AlertsExercisesPage
     */
    public AuthenticationExamplePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Локатор элемента кнопки Display Image
     */
    @FindBy(xpath = "//input[@class='button2 buttonspace']")
    public WebElement displayImageButton;

    /**
     * Локатор элемента картинки успешной авторизации
     */
    @FindBy(xpath = "//img[@id]")
    public WebElement authenticatedImage;

    /**
     * Функция нажатия кнопки Display Image
     * @return возвращает объект страницы AuthenticationExamplePage
     */
    @Step("Нажатие кнопки Display Image")
    public AuthenticationExamplePage clickDisplayImageButton(){
        displayImageButton.click();
        return this;
    }

    /**
     * Функция проверки отображения картинки успешной авторизации
     * @return возвращает булевое значение
     */
    @Step("Проверки отображения картинки успешной авторизации")
    public boolean isAuthenticatedImageDisplayed(){
        waitings.waitTimeForElement(5000, driver, authenticatedImage);
        return authenticatedImage.isDisplayed();
    }


}
