package d26_01_2023;
//Napisati program koji:
//Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//POMOC: Brisite elemente odozdo.

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://s.bootsnipp.com/iframe/Dq2X");

        List<WebElement> buttons=driver.findElements(By.className("close"));

        for (int i = 0; i <buttons.size() ; i++) {
            buttons.get(i).findElement(By.className("close")).click();

            Thread.sleep(1000);

        }

        driver.quit();



    }
}
