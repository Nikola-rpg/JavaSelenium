package Nedelja9.c_Cetvrtak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Zadatak1 {
    //Koristeci anotacije, ulogujte se na demoqa (https://demoqa.com/ -> Book Store Application) preko cookies-a

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://demoqa.com/");
    }

    @Test
    public void logInTest() {
        List<WebElement> storeButton = driver.findElements(By.cssSelector(".card.mt-4.top-card"));
        Actions action = new Actions(driver);
        action.moveToElement(storeButton.get(5)).click().build().perform();

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();

        boolean isPresent = false;
        try {
            isPresent = driver.findElement(By.id("submit")).isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(isPresent);

        Cookie cookie1 = new Cookie("expires", "2024-10-10T21%3A14%3A58.524Z");
        driver.manage().addCookie(cookie1);
        Cookie cookie2 = new Cookie("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IlBldGFyUUEiLCJwYXNzd29yZCI6IlBldGFyUUExMDAlIiwiaWF0IjoxNzI3OTkwNDYzfQ.dW0Gx_v4Hqt6cfr2j71Xqi_P4tu8Bvf3axuaqOrXTIA");
        driver.manage().addCookie(cookie2);
        Cookie cookie3 = new Cookie("userID", "54928ffd-8945-42d0-9669-3a35d24ddf6f");
        driver.manage().addCookie(cookie3);
        Cookie cookie4 = new Cookie("userName", "PetarQA");
        driver.manage().addCookie(cookie4);
        driver.navigate().refresh();

        List<WebElement> userName = driver.findElements(By.className("form-label"));
        Assert.assertEquals(userName.get(1).getText(), "PetarQA");

        WebElement logOutButton = driver.findElement(By.id("submit"));
        Assert.assertTrue(logOutButton.isDisplayed());
        Assert.assertEquals(logOutButton.getText(), "Log out");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
