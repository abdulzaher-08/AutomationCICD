package rahulshettyacademy.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test (groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() {		
			
		String email = "bilalahmed786@gmail.com";
		String password = "12345Aa@567";
		
		lp.loginApplication(email, password);
		Assert.assertEquals(lp.getErrorMessage(), "Incorrect email or password.");

	}
	
	@Test
	public void ProductErrorValidation() {	
	
	String productName = "ADIDAS ORIGINAL";		
	String email = "abdulzaher786@gmail.com";
	String password = "12345Aa@";
	
	ProductCatalogue pc = lp.loginApplication(email, password);
	
	pc.addProductToCart(productName);
	CartPage cp = pc.goToCartPage();
	
	Boolean match = cp.verifyProductDisplay("ADIDAS ORIGINAL33");
	Assert.assertFalse(match);
	}

}
