package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //Кнопка заказать в шапке профиля
    private By orderButtonInHeader = By.cssSelector("[class = 'Button_Button__ra12g']");

    public void clickOnOrderButtonInHeader(){
        driver.findElement(orderButtonInHeader).click();
    }

    //Кнопка заказать в теле DOM
    private By orderButtonInBody = By.cssSelector("[class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    public By getOrderButtonInBody() {
        return orderButtonInBody;
    }

    public void clickOnOrderButtonInBody(){
        driver.findElement(orderButtonInBody).click();
    }

    // Заголовки "Вопросы о важном"
    private final List<By> accordionHeadings = List.of(
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    );
    public By getAccordionHeading(int index) {
        return accordionHeadings.get(index);
    }
    // Ответы на "Вопросы о важном"

    private final List<By> accordionPanels = List.of(
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    );
    public By getAccordionPanel(int index) {
        return accordionPanels.get(index);
    }
}
