package stepDefination;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.aventstack.extentreports.Status;

import BaseLayer.BaseClass;
import PageLayer.ProductPage;
import UtilityLayer.ExtentManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddToCartSteps extends BaseClass {

    private ProductPage productPage;

    public AddToCartSteps() {
        super();
        this.productPage = new ProductPage(driver);  // Initialize the ProductPage
    }

    @Given("I am on the Store Page")
    public void i_am_on_the_store_page() {
        // Open the store page in the browser
        driver.get("https://askomdch.com/");
        ExtentManager.createTest("Add to Cart Test"); // Initialize ExtentTest
        ExtentManager.getExtentTest().log(Status.INFO, "Navigated to Store Page.");
        System.out.println("Navigated to Store Page.");
    }

    @When("I add {string} to the cart")
    public void i_add_to_the_cart(String productName) {
        // Click on the 'Add to Cart' button for the specified product
        ExtentManager.getExtentTest().log(Status.INFO, "Adding " + productName + " to the cart...");
        productPage.clickAddToCart(productName);
        ExtentManager.getExtentTest().log(Status.PASS, productName + " added to the cart successfully.");
        System.out.println("Adding " + productName + " to the cart...");
    }

    @Then("I should see 1 {string} in the cart")
    public void i_should_see_in_the_cart(String productName) {
        productPage.clickCartButton(); // Navigate to the cart
        boolean isProductInCart = productPage.isProductInCart(); // Check if the product is in the cart
        ExtentManager.getExtentTest().log(Status.INFO, "Checking if " + productName + " is in the cart...");
        assertTrue(isProductInCart, productName + " was not found in the cart."); // Validate
        ExtentManager.getExtentTest().log(Status.PASS, "Verified " + productName + " is in the cart.");
        System.out.println("Verified " + productName + " is in the cart.");
    }

    @Given("I have {int} {string} in the cart")
    public void i_have_in_the_cart(Integer quantity, String productName) {
        ExtentManager.getExtentTest().log(Status.INFO, "Adding " + quantity + " " + productName + " to the cart...");
        productPage.clickAddToCart(productName, quantity); // Add specified quantity of the product to the cart
        ExtentManager.getExtentTest().log(Status.PASS, quantity + " " + productName + " added to the cart.");
        System.out.println("Added " + quantity + " " + productName + " to the cart.");
    }

    @When("I add it again")
    public void i_add_it_again() {
        ExtentManager.getExtentTest().log(Status.INFO, "Adding the product again...");
        productPage.clickAddToCart(productPage.getGlobalProductName()); // Use the stored product name
        ExtentManager.getExtentTest().log(Status.PASS, "Product added again to the cart.");
        System.out.println("Added the product again.");
    }

    @Then("I should see {int} {string} in the cart")
    public void i_should_see_in_the_cart(Integer expectedQuantity, String productName) {
        productPage.clickOnCartMenu(); 
        int actualQuantity = productPage.getProductQuantityInCart(productName); 
        ExtentManager.getExtentTest().log(Status.INFO, "Verifying the quantity of " + productName + " in the cart...");
        assertTrue(actualQuantity == expectedQuantity, 
                "Expected quantity: " + expectedQuantity + ", but found: " + actualQuantity);
        ExtentManager.getExtentTest().log(Status.PASS, "Verified " + expectedQuantity + " " + productName + " in the cart.");
        System.out.println("Verified " + expectedQuantity + " " + productName + " in the cart.");
    }
}
