package Helpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Вспомогательный класс с методами действий на странице
 * @author Alex Seburev
 */
public class PageActions {

    public JavascriptExecutor jse;
    public Actions actions;

    /**
     * Функция скрола страницы c помощью JavascriptExecutor
     * @param xPixels - количество пикселей по оси x
     * @param yPixels - количество пикселей по оси y
     * */
    @Step("Скролл страницы c помощью JavascriptExecutor")
    public void jseScroll(int xPixels, int yPixels) {
        jse.executeScript("window.scrollBy(" + xPixels + "," + yPixels + ")");
    }

    /**
     * Функция скрола страницы c помощью Actions
     * @param xPixels - количество пикселей по оси x
     * @param yPixels - количество пикселей по оси y
     * */
    @Step("Скролл страницы c помощью Actions")
    public void actionScroll(int xPixels, int yPixels){
        actions.scrollByAmount(xPixels, yPixels).build().perform();
    }

    /**
     * Функция скрола вниз
     * */
    @Step("Скролл в низ страницы")
    public void scrollToTheBottom(){
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    /**
     * Функция создания скриншота всей страницы
     * @param driver = объект вебдрайвера
     * */
    @Step("Сделать скриншот страницы")
    public static void takeScreenshot(WebDriver driver) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenshot.getImage(), "PNG", baos);
            byte[] imageBytes = baos.toByteArray();
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
