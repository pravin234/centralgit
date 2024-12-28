package PageLayer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

public class CheckoutPage {

    private WebDriver driver;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By getProductNameLocator(String productName) {
        return By.xpath("//td[@class='product-name']/a[contains(text(),'" + productName + "')]");
    }

    private By getProductQuantityLocator(String productName) {
        return By.xpath("//td[@class='product-name']/a[contains(text(),'" + productName + "')]/parent::td/following-sibling::td[@class='product-quantity']/div/input");
    }

    public void verifyProductDetails(String productName, int expectedQuantity) {
        // Locate elements
        WebElement productElement = driver.findElement(getProductNameLocator(productName));
        WebElement quantityElement = driver.findElement(getProductQuantityLocator(productName));

        // Verify product name
        String actualProductName = productElement.getText();
        assertEquals(actualProductName, productName, "Product name does not match!");

        // Verify product quantity
        int actualQuantity = Integer.parseInt(quantityElement.getAttribute("value"));
        assertEquals(actualQuantity, expectedQuantity, "Product quantity does not match!");

        System.out.println("Verified product: " + productName + " with quantity: " + actualQuantity);
    }
}
