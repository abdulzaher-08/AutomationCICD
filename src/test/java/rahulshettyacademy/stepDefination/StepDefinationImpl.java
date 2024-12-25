package rahulshettyacademy.stepDefination;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.FinalChekoutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinationImpl extends BaseTest {
	
	public LandingPage lp;
	public ProductCatalogue pc;
	public OrderConfirmationPage ocp;
	
	@Given ("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		lp = launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		pc = lp.loginApplication(username, password);
	}
	
	@When ("^I add product (.+) to the cart$")
	public void i_add_product_to_the_cart(String productName) {
		pc.addProductToCart(productName);
	}
	
	@And ("^Checkout the (.+) and submit the order$")
	public void checkout_the_product_submit_the_order(String productName)
	{
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		FinalChekoutPage fcp = cp.goToCheckout();
		fcp.countrySelection("India");
		ocp = fcp.orderPlacement();
	}
	
	@Then("verify {string} is displayed on ConfirmationPage")
	public void verify_String_is_displayed(String message) {
		String confirmMessage = ocp.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then ("{string} message is displayed")
	public void verify_String_message_is_displayed(String message) {
		Assert.assertEquals(lp.getErrorMessage(), message);
		driver.close();
	}

}
