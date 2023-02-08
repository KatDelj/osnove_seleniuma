package d02_02_2023;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import p24_01_2023.Helper;

import java.io.IOException;
import java.time.Duration;

public class BootstrapTableTests {
//Kreirati BootstrapTableTests klasu koja ima:
//Base url: https://s.bootsnipp.com
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl ="https://s.bootsnipp.com ";
    private String firstName="Pera";
    private String lastName="Peric";
    private String middleName="Perica";
//Test #1: Edit Row
//Podaci:
//First Name: ime polaznika
//Last Name: prezime polaznika
//Middle Name: srednje ime polanzika
//Koraci:
//Ucitati stranu /iframe/K5yrx
//Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//Klik na Edit dugme prvog reda
//Sacekati da dijalog za Editovanje bude vidljiv
//Popuniti formu podacima.
//Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
//Klik na Update dugme
//Sacekati da dijalog za Editovanje postane nevidljiv
//Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
//Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
//Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
//Za sve validacije ispisati odgovarajuce poruke u slucaju greske
    @BeforeClass
    public void setup() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    this.driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
    }
    @Test (priority = 1)
    @Description ("Edit Row")
    public void editRow (){
        driver.get(baseUrl + "/iframe/K5yrx");
        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Invalid title ");
        this.driver.findElement(By.xpath("//tr[1]//button[@data-target='#edit']")).click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.className("modal-content")));
        this.driver.findElement(By.id("fn")).clear();
        this.driver.findElement(By.id("ln")).clear();
        this.driver.findElement(By.id("mn")).clear();
        driver.findElement(By.id("fn")).sendKeys(firstName);
        driver.findElement(By.id("ln")).sendKeys(lastName);
        driver.findElement(By.id("mn")).sendKeys(middleName);
        driver.findElement(By.id("up")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.className("modal-content")));
        Assert.assertEquals(driver.findElement(By.id("f1")).getText(),"Pera","Incorrect First name entered");
        Assert.assertEquals(driver.findElement(By.id("l1")).getText(),"Peric","Incorrect Last name entered");
        Assert.assertEquals(driver.findElement(By.id("m1")).getText(),"Perica","Incorrect Middle name entered");


    }
    //Test #2: Delete Row
    //Podaci:
    //First Name: ime polaznika
    //Last Name: prezime polaznika
    //Middle Name: srednje ime polanzika
    //Koraci:
    //Ucitati stranu /iframe/K5yrx
    //Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
    //Klik na Delete dugme prvog reda
    //Sacekati da dijalog za brisanje bude vidljiv
    //Klik na Delete dugme iz dijaloga
    //Sacekati da dijalog za Editovanje postane nevidljiv
    //Verifikovati da je broj redova u tabeli za jedan manji
    //Za sve validacije ispisati odgovarajuce poruke u slucaju greske
    @Test (priority = 2)
    @Description ("Delete Row")
    public void deleteRow() {
        driver.get(baseUrl + "/iframe/K5yrx");
        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Invalid title ");
        this.driver.findElement(By.xpath("//tr[1]//button[@data-target='#delete']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("delete"))));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("delete"))));

    }
   //Test #3: Take a Screenshot
   //Koraci:
   //Ucitati stranu  /iframe/K5yrx
   //Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
   //Kreirati screenshot stranice.
   //Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: screenshots/slike.png
    @Test (priority = 3)
    @Description ("Take a Screenshot")
    public void takeAScreenshot() throws IOException {
        driver.get(baseUrl + "/iframe/K5yrx");
        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Invalid title ");

        new Helper().takeScreenshot(driver, "screenshots/slike.png");
    }
    @AfterMethod
    public void afterMethod(){
    }
    @AfterClass
    public void afterClass() throws InterruptedException {

        Thread.sleep(2000);
        driver.quit();
    }

            }







