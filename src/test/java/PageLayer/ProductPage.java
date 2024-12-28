package PageLayer;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    private WebDriver driver;
    private Logger logger;

    // Global variable to store the product name
    private String globalProductName;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.logger = LogManager.getLogger(ProductPage.class);
    }

    // Locators for Add to Cart button and Cart button
    private By addToCartButton(String productName) {
        return By.xpath("//a[./h2[text()='" + productName + "']]/following-sibling::a[text()='Add to cart']");
    }

    // Dynamic Cart button locator (should reference the correct product name to click "View Cart" button)
    private By cartButton() {
        // Use the globally stored product name
        System.out.println("XPath for cart button: " + "//h2[text()='" + globalProductName + "']/parent::a/following-sibling::a[@title='View cart']");
        return By.xpath("//h2[text()='" + globalProductName + "']/parent::a/following-sibling::a[@title='View cart']");
    }

    // Dynamic locator to find the product in the cart
    public By cartItemLocator() {
        // Use the globally stored product name
        return By.xpath("//td[@class='product-name']/a[contains(normalize-space(text()),'" + globalProductName + "')]");
    }

    // Actions
    public void clickAddToCart(String productName) {
        logger.info("Attempting to click on 'Add to Cart' button for product: " + productName);
        try {
            // Store the product name globally
            this.globalProductName = productName;

            WebElement addToCart = driver.findElement(addToCartButton(productName));
            addToCart.click();
            logger.info("Successfully clicked 'Add to Cart' for product: " + productName);
        } catch (Exception e) {
            logger.error("Failed to click 'Add to Cart' for product: " + productName, e);
        }
    }

    public void clickCartButton() {
        logger.info("Attempting to click on the Cart button for product: " + globalProductName);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for 10 seconds
            WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartButton()));
            cart.click();
            logger.info("Successfully clicked the Cart button.");
        } catch (Exception e) {
            logger.error("Failed to click the Cart button", e);
        }
    }

    // Method to check if a product is in the cart
    public boolean isProductInCart() {
        try {
            WebElement productInCart = driver.findElement(cartItemLocator());
            if (productInCart.isDisplayed()) {
                logger.info(globalProductName + " is present in the cart.");
                return true;
            }
        } catch (Exception e) {
            logger.error("Product not found in the cart: " + globalProductName, e);
        }
        return false;
    }
}
