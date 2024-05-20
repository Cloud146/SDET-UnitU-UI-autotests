package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waitings {

    public void waitTimeForElement(int waitTimeInSeconds, WebDriver driver, WebElement element){
        Duration waitTime = Duration.ofSeconds(waitTimeInSeconds * 1000);
        WebElement dynamicElement = (new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(element)));
    }

    public void waitForVisibility(WebDriver driver, WebElement element, int waitTimeInSeconds) {
        Duration waitTime = Duration.ofSeconds(waitTimeInSeconds * 1000);
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
