package Nedelja9.b_Utorak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class Zadatak1 {
    public static void main(String[] args) {
        //https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2
        //Testirati dodavanje knjige u korpu i da li se knjiga obrise kada obrisete kolacice

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.navigate().to("https://www.amazon.com/Selenium-Framework-Design-Data-Driven-Testing/dp/1788473574/ref=sr_1_2?dchild=1&keywords=selenium+test&qid=1631829742&sr=8-2");

        WebElement brojPredmeta = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(brojPredmeta.getText(), "0");

        brojPredmeta.click();

        WebElement praznoObavestenje = driver.findElement(By.cssSelector(".a-size-large.a-spacing-top-base.sc-your-amazon-cart-is-empty"));
        Assert.assertEquals(praznoObavestenje.getText(), "Your Amazon Cart is empty");

        driver.navigate().back();

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        brojPredmeta = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(brojPredmeta.getText(), "1");

        WebElement uspesnaPoruka = driver.findElement(By.id("NATC_SMART_WAGON_CONF_MSG_SUCCESS"));
        Assert.assertEquals(uspesnaPoruka.getText(), "Added to Cart");

        WebElement tekstNaDugmetu = driver.findElement(By.id("sw-ptc-form"));
        Assert.assertEquals(tekstNaDugmetu.getText(), "Proceed to checkout (1 item)");

        brojPredmeta.click();

        String subtotal1 = driver.findElement(By.id("sc-subtotal-label-activecart")).getText();
        String subtotal2 = driver.findElement(By.id("sc-subtotal-label-buybox")).getText();

        Assert.assertTrue(subtotal1.contains("Subtotal (1 item)"));
        Assert.assertTrue(subtotal2.contains("Subtotal (1 item)"));

        String padajuci = driver.findElement(By.className("a-dropdown-container")).getText();
        Assert.assertTrue(padajuci.contains("1"));

        String opisKnjige = driver.findElement(By.className("a-truncate-cut")).getText();
        Assert.assertTrue(opisKnjige.contains("Selenium Framework Design in Data-Driven Testing"));

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        praznoObavestenje = driver.findElement(By.cssSelector(".a-size-large.a-spacing-top-base.sc-your-amazon-cart-is-empty"));
        Assert.assertEquals(praznoObavestenje.getText(), "Your Amazon Cart is empty");

        brojPredmeta = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(brojPredmeta.getText(), "0");
    }
}
