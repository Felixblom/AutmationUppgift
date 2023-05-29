package stepDefinitions;


import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.*;
import automation.Automation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.chrome.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyStepdefs {
    private WebDriver driver;
    public String actual;

    Automation automation;

    @Before
    public void setUp() {
        automation = new Automation();
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @Given("I signup with an email {string}")
    public void iSignupWithAnEmailEmail(String email) {
        WebElement emailBox = driver.findElement(By.id("email"));
        emailBox.sendKeys(email);
    }

    @Given("I choose a username {string}")
    public void iChooseAUsernameUsername(String username) {

        WebElement userBox = driver.findElement(By.id("new_username"));

        // userBox.sendKeys(Keys.CONTROL+"a",Keys.DELETE);
        automation.insertName(username);
        userBox.sendKeys(username);

    }

    @Given("I choose a password {string}")
    public void iChooseAPasswordPassword(String password) {
        WebElement searchBox = driver.findElement(By.name("password"));

        searchBox.sendKeys(password);

    }

    @When("I press the sign up button")
    public void iPressTheSignUpButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement knapp = driver.findElement(By.xpath("//*[@id=\"create-account-enabled\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create-account-enabled\"]")));
        knapp.click();

    }

    @Then("im signed up for the service")
    public void imSignedUpForTheService() {

        if (automation.getName().equals("flixo")) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));// Lång wait så att man hinner skriva in captcha
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span")));
            WebElement upptagetNamn = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span"));
            actual = upptagetNamn.getText();
            String expected = "Great minds think alike - someone already has this username. If it's you, log in.";
            assertEquals(expected, actual);
            driver.quit();
        } else if (automation.getName().equals("somethingKonstigt")) {
            WebElement ingenMail = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[1]/div/span"));
            actual = ingenMail.getText();
            String expected = "An email address must contain a single @.";
            assertEquals(expected, actual);
            driver.quit();

        } else if (automation.getName().equals("filifjonkan123456")) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signup-success\"]/div/div[1]/section/div/h1")));
            WebElement registrerad = driver.findElement(By.xpath("//*[@id=\"signup-success\"]/div/div[1]/section/div/h1"));
            actual = registrerad.getText();
            String expected = "Check your email";
            assertEquals(expected, actual);
            driver.quit();
        } else if (automation.getName().equals("adcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhjklmadcvbnhqesrty")) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span")));
            WebElement nameTooLong = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span"));
            actual = nameTooLong.getText();
            String expected = "Enter a value less than 100 characters long";
            assertEquals(expected, actual);
            driver.quit();

        }


    }
}
