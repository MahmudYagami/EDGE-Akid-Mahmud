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

            // Navigate to the registrar office page
            WebElement registrarOfficeButton = driver.findElement(By.cssSelector("a[href='grade.php']"));
            registrarOfficeButton.click();

            // Wait for the registrar-office page to load
            wait.until(ExpectedConditions.titleContains("Grade"));

            // Navigate to 'Add New Registrar Office User' page
            WebElement addNewUserButton = driver.findElement(By.cssSelector("a[href='grade-add.php']"));
            addNewUserButton.click();

            // Wait for the "Add New Registrar Office User" form to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("form")));

            // Fill out the form fields
            WebElement fnameField = driver.findElement(By.name("grade_code"));
            WebElement lnameField = driver.findElement(By.name("grade"));
     

            // Fill the form with information
            fnameField.sendKeys("C-2");
            lnameField.sendKeys("2");

            // Find the "Add" button by class and make sure it’s clickable
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary")));

            // Scroll the "Add" button into view before clicking (optional but useful)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);

            try {
                // Try clicking the button normally
                addButton.click();
            } catch (Exception e) {
                // If clicking fails, use JavaScript to click the button
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
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
