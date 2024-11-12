package Nedelja8;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {
    public static void main(String[] args) {
        //Otvoriti youtube i pustiti pesmu po zelji

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.youtube.com/");
        driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div/ytd-masthead/div[4]/div[2]/ytd-searchbox/form/div[1]/div[1]/input")).sendKeys("Bajaga i Instruktori - Ruski voz - (Audio 1988)");
        driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div/ytd-masthead/div[4]/div[2]/ytd-searchbox/form/div[1]/div[1]/input")).sendKeys(Keys.ENTER);

        try {
            Thread.sleep(3000);
            WebElement prviVideo= driver.findElement(By.cssSelector("ytd-video-renderer a#video-title"));
            prviVideo.click();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
