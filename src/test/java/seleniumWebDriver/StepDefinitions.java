package seleniumWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepDefinitions {

    private WebDriver driver;

    @Given("I open the {string} browser")
    public void openBrowser(String browser) {
        switch (browser) {
            case "Chrome":
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("--headless=new");
                driver = new ChromeDriver(optionsChrome);
                break;
            case "Firefox":
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                optionsFirefox.addArguments("--headless");
                driver = new FirefoxDriver(optionsFirefox);
                break;
            case "Edge":
                EdgeOptions optionsEdge = new EdgeOptions();
                optionsEdge.addArguments("headless");
                driver = new EdgeDriver(optionsEdge);
                break;
            default:
                throw new RuntimeException("Unsupported browser type: " + browser);
        }
    }

    @Given("I am on the DuckDuckGo search page")
    public void I_visit_DuckDuckGo() {
        driver.get("https://duckduckgo.com/");
    }

    @When("I navigate to Gmail")
    public void navigateToGmail() {
        driver.get("https://www.gmail.com");
    }

    @When("I search for {string}")
    public void search_for(String query) {
        WebElement element = driver.findElement(By.name("q"));
        // Enter something to search for
        element.sendKeys(query);
        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
    }

    @When("I input the user name of the Gmail")
    public void inputGmailUsrName() {
        WebElement inputUsrName = driver.findElement(By.xpath("//input[@id='identifierId']"));
        inputUsrName.sendKeys("seleniumautotest@gmail.com");
    }

    @When("I input the password of the Gmail")
    public void inputGmailPassword() {
        WebElement inputPwd = driver.findElement(By.id("password"));
        inputPwd.sendKeys("SeleniumAutoTest666");
    }

    @When("I click Next button on the Gmail user name input page")
    public void clickNextBtnOnGmailUsrInput() {
        WebElement nextBtn = driver.findElement(By.id("identifierNext"));
        nextBtn.click();
    }

    @When("I click Next button on the Gmail password input page")
    public void clickNextBtnOnGmailPwdInput() {
        WebElement nextBtn = driver.findElement(By.id("passwordNext"));
        nextBtn.click();
    }

    @Then("the page title should start with {string}")
    public void checkTitle(String titleStartsWith) {
        // Google's search is rendered dynamically with JavaScript
        // Wait for the page to load timeout after ten seconds
        new WebDriverWait(driver, Duration.ofSeconds(3)).until((ExpectedCondition<Boolean>) d -> {
            assert d != null;
            return d.getTitle().startsWith(titleStartsWith);
        });
    }

    @Then(("I see the input for user name on google authentication page"))
    public void assertVisibilityGmailInputUsrName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement inputUsrName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='identifierId']")));
        inputUsrName.isEnabled();
    }

    @Then("I see the input for password on google authentication page")
    public void assertVisibilityGmailInputPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement inputPwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        inputPwd.isEnabled();
    }

    @After()
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] data =((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", scenario.getName());
        }
        driver.quit();
    }
}
