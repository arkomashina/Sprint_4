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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Parameterized.class)
public class MainPageTests {
    private WebDriver driver;
    private final String browser;
    private final String dropdownId;
    private final String panelId;
    private final String expectedText;

    public MainPageTests(String browser, String dropdownId, String panelId, String expectedText) {
        this.browser = browser;
        this.dropdownId = dropdownId;
        this.panelId = panelId;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] values () {
        return new Object[][] {
                {"chrome", "accordion__heading-0", "accordion__panel-0",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"firefox", "accordion__heading-7", "accordion__panel-7",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }
    @Before
    public void setUp() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testDropdownList() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        WebElement element = driver.findElement(By.id(dropdownId));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        String actualText = driver.findElement(By.id(panelId)).getText();
        Assert.assertEquals("Текст не совпадает", expectedText, actualText);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
