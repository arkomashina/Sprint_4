package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //Кнопка заказать в шапке профиля
    private By orderButtonInHeader = By.className("Button_Button__ra12g");

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
}
