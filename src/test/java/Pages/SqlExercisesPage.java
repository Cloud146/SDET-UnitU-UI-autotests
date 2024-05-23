package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс pageObject Страница SqlExercises
 * @author Alex Seburev
 * @version 1.0
 */
public class SqlExercisesPage {
    private WebDriver driver;

    /**
     * Объект страницы SqlExercises
     */
    public SqlExercisesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Локатор элемента поля ввода login
     */
    @FindBy(xpath = "//input[@name='login'][@type='text']")
    public WebElement loginField;

    /**
     * Локатор элемента поля ввода password
     */
    @FindBy(xpath = "//input[@name='psw'][@type='password']")
    public WebElement passwordField;

    /**
     * Локатор элемента кнопка Submit
     */
    @FindBy(xpath = "//input[@name='subm1']")
    public WebElement submitButton;

    /**
     * Локатор элемента надпись имени авторизованного пользователя
     */
    @FindBy(xpath = "//a[@href='/personal.php'][@class='none']")
    public WebElement authorizedUsernameLabel;

    /**
     * Функция ввода текста в поле login
     * @param login - текст ввода
     * @return возвращает объект страницы SqlExercises
     */
    @Step("Ввод текста в поле login")
    public SqlExercisesPage enterLogin(String login){
        loginField.sendKeys(login);
        return this;
    }

    /**
     * Функция ввода текста в поле password
     * @param password - текст ввода
     * @return возвращает объект страницы SqlExercises
     */
    @Step("Ввод текста в поле password")
    public SqlExercisesPage enterPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    /**
     * Функция нажатия кнопки Submit
     * @return возвращает объект страницы SqlExercises
     */
    @Step("Нажатие кнопки Submit")
    public SqlExercisesPage clickSubmitButton(){
        submitButton.click();
        return this;
    }

    /**
     * Функция авторизации
     * @param login - текст ввода
     * @param password - текст ввода
     * @return возвращает объект страницы SqlExercises
     */
    @Step("Авторизация")
    public SqlExercisesPage authorization(String login, String password){
        enterLogin(login)
                .enterPassword(password)
                .clickSubmitButton();
        return this;
    }

    public String usernameCheck(){
        return authorizedUsernameLabel.getText();
    }
}
