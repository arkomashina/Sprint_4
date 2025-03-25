import PageObjects.MainPage;
import PageObjects.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


@RunWith(Parameterized.class)
public class OrderPageTests {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final boolean isOrderButtonInHeader;

    public OrderPageTests(String name, String surname, String address, String metro, String phone, boolean isOrderButtonInHeader) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.isOrderButtonInHeader = isOrderButtonInHeader;
    }

    @Parameterized.Parameters
    public static Object[][] values () {
        return new Object[][] {
                {"Аркадий", "Илюхин", "Пятницкое шоссе 38", "Пятницкое шоссе", "89211111112", true},
                {"Мария", "Петрова", "Ленинградский проспект 45", "Сокол", "89222222222", false}
        };
    }

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
    }

    @Test
    public void orderSamokatFromButtonTest() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        if (isOrderButtonInHeader) {
            mainPage.clickOnOrderButtonInHeader();
        } else {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            WebElement element = driver.findElement(mainPage.getOrderButtonInBody());
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
