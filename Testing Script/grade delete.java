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

            // Find and click the delete button (assuming we are deleting the first user in the list)
            WebElement deleteButton = driver.findElement(By.cssSelector("a.btn.btn-danger")); // The first delete button
            deleteButton.click();

            // Wait for the confirmation (success/error message)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert-info, .alert-danger")));

            // Verify the result (checking for success message)
            WebElement successMessage = driver.findElement(By.cssSelector(".alert-info"));
            if (successMessage != null) {
                System.out.println("User successfully deleted.");
            } else {
                WebElement errorMessage = driver.findElement(By.cssSelector(".alert-danger"));
                if (errorMessage != null) {
                    System.out.println("Failed to delete user: " + errorMessage.getText());
                }
            }

            // Wait for the page to update and validate that the registrar is deleted (or confirm that the user is no longer listed)
            // Here we can add more assertions as needed to check if the user count is reduced.

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser (uncomment the next line if you want to close the browser after testing)
            //driver.quit();
        }
    
	}

}
