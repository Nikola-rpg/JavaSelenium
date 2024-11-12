package Nedelja9.a_Ponedeljak;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Zadatak1 {
    public static void main(String[] args) {
        /*Testirati bar 3 razlicita test case-a za logovanje na sledecem linku:
        https://www.saucedemo.com*/

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.saucedemo.com");

        String loginPageURL = driver.getCurrentUrl();

        WebElement userNameField = driver.findElement(By.id("user-name"));
        WebElement passField = driver.findElement(By.id("password"));

        userNameField.clear();
        passField.clear();

        userNameField.sendKeys("standard_user");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement errorIcon = driver.findElement(By.cssSelector(".svg-inline--fa.fa-times-circle.fa-w-16.error_icon"));
        Assert.assertTrue(errorIcon.isDisplayed());

        WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container.error"));
        Assert.assertTrue(errorMessage.isDisplayed());

        WebElement errorMessageText = driver.findElement(By.cssSelector(".error-message-container.error"));
        Assert.assertEquals(errorMessageText.getText(), "Epic sadface: Password is required");

        passField.sendKeys("secret_sauce");
        loginButton.click();

        Assert.assertNotEquals(driver.getCurrentUrl(), loginPageURL);

        String expectedURL = "https://www.saucedemo.com/inventory.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
    }
}
