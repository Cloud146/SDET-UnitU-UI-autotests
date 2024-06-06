package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс pageObject Страница AlertsExercises
 * @author Alex Seburev
 * @version 1.0
 */
public class AlertsExercisesPage {

    private WebDriver driver;

    /**
     * Объект страницы AlertsExercisesPage
     */
    public AlertsExercisesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Локатор элемента переключения на вкладку Input Alert
     */
    @FindBy(xpath = "//a[@href='#example-1-tab-2']")
    public WebElement inputAlertTab;

    /**
     * Локатор iframe alert/input-alert.html
     */
    @FindBy(css = "iframe[src='alert/input-alert.html")
    public WebElement iframeInputAlert;


    /**
     * Локатор элемента кнопки вызова alert
     */
    @FindBy(xpath = "//button[text()='Click the button to demonstrate the Input box.']")
    public WebElement inputBoxButton;

    /**
     * Локатор элемента надписи после заполнения alert
     */
    @FindBy(xpath = "//p[@id='demo']")
    public WebElement greetingsLabel;

    /**
     * Функция нажатия кнопки перехода на вкладку Input Alert
     * @return возвращает объект страницы AlertsExercisesPage
     */
    @Step("Переход на вкладку Input Alert")
    public AlertsExercisesPage clickInputAlertTab(){
        inputAlertTab.click();
        return this;
    }

    /**
     * Функция нажатия кнопки открытия alert
     * @return возвращает объект страницы AlertsExercisesPage
     */
    @Step("Нажатие кнопки открытия alert")
    public AlertsExercisesPage clickInputBoxButton(){
        driver.switchTo().frame(iframeInputAlert);
        inputBoxButton.click();
        return this;
    }

    /**
     * Функция ввода текста в alert
     * @return возвращает объект страницы AlertsExercisesPage
     */
    @Step("Ввод текста в alert")
    public AlertsExercisesPage inputTextIntoAlertField(String inputText){
        driver.switchTo().alert().sendKeys(inputText);
        return this;
    }

    /**
     * Функция принятия alert
     * @return возвращает объект страницы AlertsExercisesPage
     */
    @Step("Принятие alert")
    public AlertsExercisesPage acceptAlert(){
        driver.switchTo().alert().accept();
        return this;
    }

    /**
     * Функция получения текста из надписи после заполнения alert
     * @return возвращает текст надписи
     */
    @Step("Получение текста из надписи после заполнения alert")
    public String getGreetingsText(){
        return greetingsLabel.getText();
    }
}
