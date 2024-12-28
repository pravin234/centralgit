package stepDefination;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertTrue;

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
        productPage.clickAddToCart(productName); // Store the product name globally and add it to the cart
        productPage.clickCartButton(); // No need to pass the product name
        boolean isProductInCart = productPage.isProductInCart(); // Use the globally stored product name
        System.out.println(isProductInCart);
       
    }

}
