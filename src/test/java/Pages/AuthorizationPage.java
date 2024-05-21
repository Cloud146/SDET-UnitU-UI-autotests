package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс pageObject Страница Authorization
 * @author Alex Seburev
 * @version 1.0
 */
public class AuthorizationPage {

    private WebDriver driver;

    /**
     * Объект страницы Authorization
     */
    public AuthorizationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Локатор элемента поля ввода username
     */
    @FindBy(xpath = "//input[@id = 'username']")
    public WebElement usernameField;

    /**
     * Локатор элемента поля ввода password
     */
    @FindBy(xpath = "//input[@id = 'password']")
    public WebElement passwordField;

    /**
     * Локатор элемента поля ввода description
     */
    @FindBy(xpath = "//input[@name = 'formly_1_input_username_0']")
    public WebElement descriptionField;

    /**
     * Локатор элемента кнопки login
     */
    @FindBy(xpath = "//button[@class = 'btn btn-danger']")
    public WebElement loginButton;

    /**
     * Локатор элемента надпись ошибки входа
     */
    @FindBy(xpath = "//div[@ng-if = 'Auth.error']")
    public WebElement alertLabel;

    /**
     * Локатор элемента надпись успешного входа
     */
    @FindBy(xpath = "//div[@class = 'ng-scope']/p[1]")
    public WebElement loggedInLabel;

    /**
     * Функция ввода текста в поле username
     * @param username - текст ввода
     * @return возвращает объект страницы Authorization
     */
    @Step("Ввод текста в поле username")
    public AuthorizationPage enterUsername(String username){
        usernameField.sendKeys(username);
        return this;
    }

    /**
     * Функция ввода текста в поле password
     * @param password - текст ввода
     * @return возвращает объект страницы Authorization
     */
    @Step("Ввод текста в поле password")
    public AuthorizationPage enterPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    /**
     * Функция ввода текста в поле description
     * @param description - текст ввода
     * @return возвращает объект страницы Authorization
     */
    @Step("Ввод текста в поле description")
    public AuthorizationPage enterDescription(String description){
        descriptionField.sendKeys(description);
        return this;
    }

    /**
     * Функция нажатия кнопки Login
     * @return возвращает объект страницы Authorization
     */
    @Step("Нажатие кнопки Login")
    public AuthorizationPage clickLoginButton(){
        loginButton.click();
        return this;
    }

    /**
     * Функция авторизации
     * @param username - текст ввода
     * @param password - текст ввода
     * @param description - текст ввода
     * @return возвращает объект страницы Authorization
     */
    @Step("Авторизация")
    public AuthorizationPage authorization(String username, String password, String description){
        enterUsername(username)
                .enterPassword(password)
                .enterDescription(description)
                .clickLoginButton();
        return this;
    }
}

