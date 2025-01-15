package test;

import org.openqa.selenium.By;
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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\lib\\chromedriver-win64\\chromedriver.exe");

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

            // Navigate to the registrar office page
            WebElement registrarOfficeButton = driver.findElement(By.cssSelector("a[href='grade.php']"));
            registrarOfficeButton.click();

            // Wait for the registrar-office page to load
            wait.until(ExpectedConditions.titleContains("Grade"));

            // Debugging: Check if the URL contains 'registrar-office.php' after redirection
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL after clicking registrar office: " + currentUrl);

            // Now go to the "Edit" page for the first registrar user
            WebElement editUserButton = driver.findElement(By.cssSelector("a[href*='grade-edit.php']"));
            editUserButton.click();

            // Wait for the 'Edit' form to load (make sure form is loaded and fields are visible)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("grade_code")));

            // Debugging: Check if the URL contains 'registrar-office-edit.php' after redirection
            currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL after clicking 'Edit': " + currentUrl);

            // Locate the 'First Name' field and change the value
            WebElement firstNameField = driver.findElement(By.name("grade_code"));
            firstNameField.clear();  // Clear any existing value in the 'First Name' field
            firstNameField.sendKeys("UpdatedFirstName");  // Set new first name value

            // Instead of clicking the update button, submit the form directly
            WebElement form = driver.findElement(By.tagName("form"));
            
            // If the form is ready to submit, do it via submit() method
            form.submit();  // This triggers the form submission action.

            // Wait for the form submission to complete (wait for the next page load or a success message)
            wait.until(ExpectedConditions.urlContains("grade.php"));

            // Debugging: Verify final URL to confirm successful navigation
            currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL after updating: " + currentUrl);

            System.out.println("Test completed: First name updated successfully.");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser (uncomment the next line if you want to close the browser after testing)
            driver.quit();
        }
	}

}
