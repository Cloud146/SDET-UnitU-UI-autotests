package Helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Вспомогательный класс с методами ожиданий
 * @author Alex Seburev
 */
public class Waitings {

    /**
     * Функция ожидания появления элемента
     * @param waitTimeInSeconds - количество времени ожидания в секундах
     * @param driver - объект вебдрайвера
     * @param element - элемент
     * */
    @Step("Ожидание появления элемента")
    public void waitTimeForElement(int waitTimeInSeconds, WebDriver driver, WebElement element){
        Duration waitTime = Duration.ofSeconds(waitTimeInSeconds * 1000);
        WebElement dynamicElement = (new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(element)));
    }

    /**
     * Функция ожидания отображения элемента
     * @param waitTimeInSeconds - количество времени ожидания в секундах
     * @param driver - объект вебдрайвера
     * @param element - элемент
     * */
    @Step("Ожидание отображения элемента")
    public void waitForVisibility(WebDriver driver, WebElement element, int waitTimeInSeconds) {
        Duration waitTime = Duration.ofSeconds(waitTimeInSeconds * 1000);
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
