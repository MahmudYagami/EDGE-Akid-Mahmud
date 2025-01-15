package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Edge {

    public static void main(String[] args) {
    	// Path to your chromedriver executable (ensure it's correct)
    	System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\User\\\\lib\\\\chromedriver-win64\\\\chromedriver.exe");

    	// Set ChromeOptions to avoid issues
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");

    	// Initialize WebDriver
    	WebDriver driver = new ChromeDriver(options);

    	// Create a WebDriverWait instance with a longer time
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    	try {
    	    // Open the login page
    	    driver.get("http://schoolapps.free.nf/login.php");

    	    // Wait for the username field and enter login details
    	    wait.until(ExpectedConditions.presenceOfElementLocated(By.name("uname")));
    	    WebElement usernameField = driver.findElement(By.name("uname"));
    	    WebElement passwordField = driver.findElement(By.name("pass"));
    	    WebElement roleField = driver.findElement(By.name("role"));

    	    // Fill out the login form
    	    usernameField.sendKeys("elias");
    	    passwordField.sendKeys("123");
    	    roleField.sendKeys("Admin");

    	    // Submit login form
    	    WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
    	    loginButton.click();

    	    // Wait for the Dashboard to load
    	    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".container.mt-5")));

    	    // Navigate to the settings page
    	    WebElement settingsButton = driver.findElement(By.cssSelector("a[href='settings.php']"));
    	    settingsButton.click();

    	    // Wait for the settings page to load
    	    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));

    	    // Locate and fill out the fields
    	    WebElement schoolNameField = driver.findElement(By.name("school_name"));
    	    WebElement sloganField = driver.findElement(By.name("slogan"));
    	    WebElement aboutField = driver.findElement(By.name("about"));
    	    WebElement currentYearField = driver.findElement(By.name("current_year"));
    	    WebElement currentSemesterField = driver.findElement(By.name("current_semester"));

    	    // Update fields with example values
    	    schoolNameField.clear();
    	    schoolNameField.sendKeys("Example International School");
    	    sloganField.clear();
    	    sloganField.sendKeys("Inspiring Light and Knowledge");
    	    aboutField.clear();
    	    aboutField.sendKeys("This is an example card with inspiring text below as a natural lead-in to additional content. This content is exemplary.");
    	    currentYearField.clear();
    	    currentYearField.sendKeys("2026");
    	    currentSemesterField.clear();
    	    currentSemesterField.sendKeys("III");

    	    // Find the "Update" button and ensure it’s clickable
    	    WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary")));

    	    // Scroll the "Update" button into view before clicking (optional but useful)
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateButton);

    	    try {
    	        // Try clicking the button normally
    	        updateButton.click();
    	    } catch (Exception e) {
    	        // If clicking fails, use JavaScript to click the button
    	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateButton);
    	    }

    	    // Wait for success message to appear after form submission
    	    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert.alert-success")));

    	    // Optionally, validate that the success message contains the expected text
    	    WebElement successMessage = driver.findElement(By.cssSelector(".alert.alert-success"));
    	    System.out.println("Success Message: " + successMessage.getText());

    	} catch (Exception e) {
    	    e.printStackTrace();
    	} finally {
    	    // Close the browser after the test is done
    	    driver.quit();
    	}

    }
}
