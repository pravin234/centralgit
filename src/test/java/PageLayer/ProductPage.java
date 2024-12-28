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
	
	private By cartMenu = By.xpath("//a[@class='cart-container']");

	// Locators for Add to Cart button and Cart button
	private By addToCartButton(String productName) {
		return By.xpath("//a[./h2[text()='" + productName + "']]/following-sibling::a[text()='Add to cart']");
	}

	// Dynamic Cart button locator (should reference the correct product name to
	// click "View Cart" button)
	private By cartButton() {
		// Use the globally stored product name
		System.out.println("XPath for cart button: " + "//h2[text()='" + globalProductName
				+ "']/parent::a/following-sibling::a[@title='View cart']");
		return By.xpath("//h2[text()='" + globalProductName + "']/parent::a/following-sibling::a[@title='View cart']");
	}

	// Dynamic locator to find the product in the cart
	public By cartItemLocator() {
		// Use the globally stored product name
		return By.xpath("//td[@class='product-name']/a[contains(normalize-space(text()),'" + globalProductName + "')]");
	}

	// Actions
	public void clickAddToCart(String productName) {
		clickAddToCart(productName, 1); // Default quantity to 1
	}

	public void clickAddToCart(String productName, int quantity) {
		logger.info("Attempting to click on 'Add to Cart' button for product: " + productName + " with quantity: "
				+ quantity);
		try {
			// Store the product name globally
			this.globalProductName = productName;

			for (int i = 0; i < quantity; i++) {
				WebElement addToCart = driver.findElement(addToCartButton(productName));
				addToCart.click();
				logger.info(
						"Clicked 'Add to Cart' for product: " + productName + " (" + (i + 1) + "/" + quantity + ")");
				// Adding a small wait between clicks to ensure proper behavior
				Thread.sleep(500);
			}
			logger.info("Successfully added product: " + productName + " to the cart with quantity: " + quantity);
		} catch (Exception e) {
			logger.error("Failed to add product: " + productName + " to the cart with quantity: " + quantity, e);
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

		
	public String getGlobalProductName() {
	    return this.globalProductName;
	}

	

	public int getProductQuantityInCart(String productName) {
	    By quantityLocator = By.xpath("//td[@class='product-name']/a[contains(text(),'" + productName + "')]/../following-sibling::td/input[@type='number']");
	    WebElement quantityElement = driver.findElement(quantityLocator);
	    return Integer.parseInt(quantityElement.getAttribute("value")); // Return the quantity as an integer
	}
	
	public void clickOnCartMenu() {
		driver.findElement(cartMenu).click();
	}
	

}
