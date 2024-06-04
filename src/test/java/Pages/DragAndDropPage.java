package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс pageObject Страница DragAndDropPage
 * @author Alex Seburev
 * @version 1.0
 */
public class DragAndDropPage {
    private WebDriver driver;

    /**
     * Объект страницы
     */
    public DragAndDropPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Локатор iframe droppable/default.html
     */
    @FindBy(css = "iframe[src='droppable/default.html")
    public WebElement iframeDroppableDefault;

    /**
     * Локатор элемента draggable
     */
    @FindBy(id = "draggable")
    public WebElement draggableElement;

    /**
     * Локатор элемента droppable
     */
    @FindBy(id = "droppable")
    public WebElement droppableElement;

    /**
     * Локатор элемента поля ввода username
     */
    @FindBy(css = "#droppable > p")
    public WebElement droppableText;
}
