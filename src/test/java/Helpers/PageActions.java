package Helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class PageActions {

    public JavascriptExecutor jse;
    public Actions actions;

    @Step("Скролл страницы c помощью JavascriptExecutor")
    public void jseScroll(int xPixels, int yPixels) {
        jse.executeScript("window.scrollBy(" + xPixels + "," + yPixels + ")");
    }

    @Step("Скролл страницы c помощью Actions")
    public void actionScroll(int xPixels, int yPixels){
        actions.scrollByAmount(xPixels, yPixels).build().perform();
    }

    @Step("Скролл в низ страницы")
    public void scrollToTheBottom(){
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
