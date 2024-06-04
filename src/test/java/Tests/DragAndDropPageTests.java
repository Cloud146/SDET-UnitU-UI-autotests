package Tests;

import Pages.DragAndDropPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DragAndDropPageTests extends BaseTest {

    private WebDriver driver;
    DragAndDropPage dragAndDropPage;

    @Story("Открытие страницы")
    @Severity(SeverityLevel.BLOCKER)
    @BeforeMethod
    public void openPage() throws IOException {
        driver = getDriver();
        driver.get(configurationProvider.getDrag_n_DropPageURL());
        dragAndDropPage = new DragAndDropPage(driver);
    }


    @Story("Drag And Drop")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Тест перемещения элемента с помощью Drag And Drop", priority = 1, enabled = true)
    public void dragAndDropTestDefault(){
        driver.switchTo().frame(dragAndDropPage.iframeDroppableDefault);
        pageActions.dragAndDrop(driver , dragAndDropPage.draggableElement, dragAndDropPage.droppableElement);
        Assert.assertEquals(dragAndDropPage.droppableText.getText(), "Dropped!");
    }
}
