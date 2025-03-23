import PageObjects.MainPage;
import PageObjects.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


@RunWith(Parameterized.class)
public class OrderPageTests {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String browserType;
    private final boolean isOrderButtonInHeader;

    public OrderPageTests(String name, String surname, String address, String metro, String phone, String browserType, boolean isOrderButtonInHeader) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.browserType = browserType;
        this.isOrderButtonInHeader = isOrderButtonInHeader;
    }

    @Parameterized.Parameters
    public static Object[][] values () {
        return new Object[][] {
                {"Аркадий", "Илюхин", "Пятницкое шоссе 38", "Пятницкое шоссе", "89211111112", "chrome", true},
                {"Мария", "Петрова", "Ленинградский проспект 45", "Сокол", "89222222222", "firefox", false}
        };
    }

    @Before
    public void setUp() {
        if (browserType.equals("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void orderSamokatFromHeaderButtonTest() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        if (isOrderButtonInHeader) {
            mainPage.clickOnOrderButtonInHeader();
        } else {
            WebElement element = driver.findElement(mainPage.getOrderButtonInBody());
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            mainPage.clickOnOrderButtonInBody();
        }


        orderPage.fillFirstRegistrationForm(name, surname, address, metro, phone);
        orderPage.fillSecondRegistrationForm();
        Assert.assertTrue("Форма с подтверждением заказа не отобразилась", orderPage.getTextFromConfirmationPopupWindow().contains("Заказ оформлен"));

    }
    @After
    public void teardown() {
        driver.quit();
    }
}
