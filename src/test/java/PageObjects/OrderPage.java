package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.*;

public class OrderPage {
    private WebDriver driver;

    public OrderPage (WebDriver driver) {
        this.driver = driver;
    }

    // поле Имя
    private By nameField = By.cssSelector("[placeholder= '* Имя']");

    // поле Фамилия
    private By surnameField = By.cssSelector("[placeholder= '* Фамилия']");

    // поле Адрес

    private By addressField = By.cssSelector("[placeholder= '* Адрес: куда привезти заказ']");

    // поле Станция метро

    private By metroField = By.cssSelector("[placeholder= '* Станция метро']");

    // поле Номер телефона

    private By phoneField = By.cssSelector("[placeholder= '* Телефон: на него позвонит курьер']");

    // кнопка Далее

    private By nextButton = By.cssSelector("[class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    // поле Дата

    private By dateField = By.cssSelector("[placeholder= '* Когда привезти самокат']");

    // поле Срок аренды

    private By rentalPeriodField = By.xpath(".//div[@class = 'Dropdown-placeholder']");

    // выпадающий список с кол-вом дней Аренды самоката
    private By rentalDays = By.xpath(".//div[@class = 'Dropdown-option' and text() ='двое суток']");

    // чек бокс с выбором самоката цвер серый

    private By chooseColorGrey = By.id("grey");

    // чек бокс с выбором самоката цвер черный
    private By chooseColorBlack = By.id("black");

    // кнопка Заказать

    private By orderButton = By.cssSelector("[class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    // кнопка Да

    private By buttonYes = By.xpath(".//*[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");

    // кнопка Нет

    private By buttonNo = By.xpath(".//*[@class = 'Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text() = 'Нет']");

    // Всплывающее окно

    private By confirmationPopupWindow = By.cssSelector(".Order_ModalHeader__3FDaJ");



    public String getTextFromConfirmationPopupWindow() {
        return driver.findElement(confirmationPopupWindow).getText();

    }

    public void setName(String name) {
        WebElement nameElement = driver.findElement(nameField);
        nameElement.sendKeys(name);
        String actual = nameElement.getDomAttribute("value");
        Assert.assertEquals("Поле 'Имя' не заполнено", name, actual);
    }

    public void setSurname(String surname) {
        WebElement surnameElement = driver.findElement(surnameField);
        surnameElement.sendKeys(surname);
        String actual = surnameElement.getDomAttribute("value");
        Assert.assertEquals("Поле 'Фамилия' не заполнено корректно", surname, actual);
    }

    public void setAddressField(String address) {
        WebElement addressElement = driver.findElement(addressField);
        addressElement.sendKeys(address);
        String actual = addressElement.getDomAttribute("value");
        Assert.assertEquals("Поле 'Адресс' не заполнено", address, actual);
    }
    public void setMetroField(String metro) {
        WebElement metroElement = driver.findElement(metroField);
        metroElement.sendKeys(metro);
        WebElement metroResult = driver.findElement(By.xpath(".//*[@class = 'Order_Text__2broi' and text() = '" + metro + "']"));
        metroResult.click();
        String actual = metroElement.getDomAttribute("value");
        Assert.assertEquals("Поле 'Метро' не заполнено", metro, actual);
    }

    public void setPhoneField(String phone) {
        WebElement phoneElement = driver.findElement(phoneField);
        phoneElement.sendKeys(phone);
        String actual = phoneElement.getDomAttribute("value");
        Assert.assertEquals("Поле 'Телефон' не заполнено", phone, actual);
    }

    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }

    public void fillFirstRegistrationForm(String name, String surname, String address, String metro, String phone){
        setName(name);
        setSurname(surname);
        setAddressField(address);
        setMetroField(metro);
        setPhoneField(phone);
        clickNextButton();
    }

    public void chooseDate(){
        driver.findElement(dateField).click();
        driver.findElement(By.cssSelector("[aria-label = 'Choose четверг, 27-е марта 2025 г.']")).click();
    }

    public void setRentalPeriod(){
        driver.findElement(rentalPeriodField).click();
        driver.findElement(rentalDays).click();
    }

    public void setColorBlack() {
        driver.findElement(chooseColorBlack).click();
    }

    public void clickOrderToAccept(){
        driver.findElement(orderButton).click();
    }

    public void clickYesButtonToAcceptOrder(){
        driver.findElement(buttonYes).click();

    }

    public void fillSecondRegistrationForm(){
        chooseDate();
        setRentalPeriod();
        setColorBlack();
        clickOrderToAccept();
        clickYesButtonToAcceptOrder();
    }



}
