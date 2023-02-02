package d30_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Napisati program koji testira infinity scroll.
//Ucitati stranu https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html
//Selektujte delay od 2000ms, koristeci Select klasu.
//Skrol do Show more dugmeta koje se nalazi na dnu stranice
//Sacekajte da dugme bude klikljivo
//Klik na Show more dugme
//Sacekjate da broj elemenata bude X (X je koliko se kod vas ucitava)
//Sacekajte da dugme vise ne bude klikljivo
public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

        driver.get("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");

        Select delay = new Select(driver.findElement(By.id("delay-select")));
        delay.selectByValue("2000");

        new Actions(driver).scrollToElement(driver.findElement(By.id("infinite-scroll-button"))).perform();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("infinite-scroll-button")));
        driver.findElement(By.id("infinite-scroll-button")).click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("item"), 8));
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.tagName("button"))));


        driver.quit();
    }
}
