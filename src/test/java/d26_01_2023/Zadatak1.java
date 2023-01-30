package d26_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

//Zadatak
//Niz todo-a (niz stringova) koje treba da uneti. Niz je:
//Visit Paris
//Visit Prague
//Visit London
//Visit New York
//Visit Belgrade
//Maksimizirati prozor
//Ucitati stranicu https://example.cypress.io/todo
//Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
//Nakon svakog unosa todo-a, unosi se enter
//Validira da li je novi todo dodat na stranici
//Na kraju programa proci petljom i izbrisati svaki todo sa stranice (klikom na x dugme svakog todo-a)
//Validirati da je na kraju programa broj todo-a na stranici 0.
//Cekanje od 5s
//Zatvorite pretrazivac
public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        ArrayList<String> todo = new ArrayList<>();
        todo.add("Visit Paris");
        todo.add("Visit Prague");
        todo.add("Visit London");
        todo.add("Visit New York");
        todo.add("Visit Belgrade");

        driver.manage().window().maximize();

        driver.get("https://example.cypress.io/todo");
        for (int i = 0; i < todo.size(); i++) {
            driver.findElement(By.className("new-todo")).sendKeys(todo.get(i));
            driver.findElement(By.className("new-todo")).sendKeys(Keys.ENTER);

            if (todo.get(i).equals(driver.findElement(
                    By.xpath("//li[last()]//div[@class='view']/label")).getText())) {
                System.out.println("Novi toDo je unet.");
            }
        }

        List<WebElement> elementsList = driver.findElements(By.xpath("//li//div[@class='view']"));
        for (int i = 0; i < elementsList.size(); i++) {
            WebElement li = driver.findElement(By.xpath("//li[last()]//div[@class='view']"));

            actions.moveToElement(li);
            actions.perform();
            driver.findElement(By.xpath("//button[contains(@class,'destroy')]")).click();

            Thread.sleep(1000);
        }

        if (elementsList.size()==0){
            System.out.println("Lista je prazna.");
        }


        driver.quit();


        }
    }

