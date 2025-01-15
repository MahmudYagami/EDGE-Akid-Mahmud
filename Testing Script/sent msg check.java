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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\lib\\chromedriver-win64\\chromedriver.exe");

        // Set ChromeOptions to avoid issues
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);

        // Create a WebDriverWait instance with a longer time
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Open the website
            driver.get("http://schoolapps.free.nf/index.php");

            // Navigate to the desired page
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[action='req/contact.php']"))); // Replace with the correct navigation if needed
            WebElement contactPageButton = driver.findElement(By.cssSelector("form[action='req/contact.php']"));
            



            // Fill out the email field
            WebElement emailField = driver.findElement(By.name("email"));
            emailField.sendKeys("example@example.com");

            // Fill out the name field
            WebElement nameField = driver.findElement(By.name("full_name"));
            nameField.sendKeys("John Doe");

            // Fill out the message field
            WebElement messageField = driver.findElement(By.name("message"));
            messageField.sendKeys("abc");

            // Find the "Send" button
            WebElement sendButton = driver.findElement(By.cssSelector(".btn.btn-primary"));

            // Scroll the "Send" button into view before clicking (optional but useful)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sendButton);

            try {
                // Try clicking the button normally
                sendButton.click();
            } catch (Exception e) {
                // If clicking fails, use JavaScript to click the button
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sendButton);
            }

            // Wait for success message to appear after submission (if applicable)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert.alert-success"))); // Adjust selector as needed

            // Optionally, validate that the success message contains the expected text
            WebElement successMessage = driver.findElement(By.cssSelector(".alert.alert-success")); // Adjust selector as needed
            System.out.println("Success Message: " + successMessage.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser after the test is done
            driver.quit();
        }
    }
}
