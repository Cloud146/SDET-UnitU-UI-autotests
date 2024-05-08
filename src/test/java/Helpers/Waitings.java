package Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waitings {

    public void waitTimeForElement(int waitTimeInSeconds, WebDriver driver, WebElement element ){
        Duration waitTime = Duration.ofMillis(waitTimeInSeconds * 100000);
        WebElement dynamicElement = (new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.elementToBeClickable(element)));
    }
}
