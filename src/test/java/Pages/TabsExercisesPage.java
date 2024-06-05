package Pages;

import Helpers.Waitings;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

/**
 * Класс pageObject Страница TabsExercises
 * @author Alex Seburev
 * @version 1.0
 */
public class TabsExercisesPage {

    private WebDriver driver;
    Waitings waitings = new Waitings();

    /**
     * Объект страницы TabsExercises
     */
    public TabsExercisesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Локатор iframe frames-windows/defult1.html
     */
    @FindBy(css = "iframe[src='frames-windows/defult1.html")
    public WebElement iframeWindowsDefault;

    /**
     * Локатор кнопки открытия новой вкладки в браузере
     */
    @FindBy(xpath = "//div[@class='farme_window']//a")
    public WebElement newBrowserTabButton;


    /**
     * Функция нажатия кнопки
     */
    @Step("Ввод текста в поле login")
    public void clickNewBrowserTab(){
        driver.findElement(By.xpath("//div[@class='farme_window']//a")).click();
        waitings.waitTimeForElement(100, driver, newBrowserTabButton);
    }

    /**
     * Получение дескриптора вкладки браузера номера n
     */
    @Step("Получение дескриптора вкладки №")
    public String getBrowserTabByNumber(Set<String> handles, Iterator<String> iterator, int tabIndex){
        for (int i = 1; i < tabIndex; i++) {
            iterator.next();
        }
        return iterator.next();
    }
}