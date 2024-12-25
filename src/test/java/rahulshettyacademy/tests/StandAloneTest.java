package rahulshettyacademy.tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.FinalChekoutPage;
import rahulshettyacademy.pageobjects.OrderConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest {
	
//	String email = "bilalahmed786@gmail.com";
//	String password = "12345Aa@";
//	String productName = "ADIDAS ORIGINAL";	
	
	@Test (dataProvider = "getData", groups = {"Purchase"})
	public void StandAlone(HashMap<String, String> input) {		
		
		ProductCatalogue pc = lp.loginApplication(input.get("email"), input.get("password"));
		
		pc.addProductToCart(input.get("productName"));
		CartPage cp = pc.goToCartPage();
		
		Boolean match = cp.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		FinalChekoutPage fcp = cp.goToCheckout();
		
		fcp.countrySelection("India");
		OrderConfirmationPage ocp = fcp.orderPlacement();
		
//		driver.findElement(By.cssSelector(".action__submit")).click(); // This code is not working because element is not clickable on the page
		
		String confirmMessage = ocp.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));		
		

	}
	
	
	// To verify ADIDAS ORIGINAL is displaying in the Orders Page
	@Test (dependsOnMethods = {"StandAlone"}, dataProvider = "getData")
	public void orderHistoryTest(HashMap<String, String> input) {
		
		ProductCatalogue pc = lp.loginApplication(input.get("email"), input.get("password"));
		OrderPage op = lp.goToOrderPage();
		Boolean match = op.verifyOrderDisplay(input.get("productName"));
		Assert.assertTrue(match);
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") +"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		
		return new Object [][] {{data.get(0)}, {data.get(1)}};
	}
	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "anshika@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("productName", "ADIDAS ORIGINAL");
//	
//	HashMap<String, String> map2 = new HashMap<String, String>();
//	map2.put("email", "shetty@gmail.com");
//	map2.put("password", "Iamking@000");
//	map2.put("productName", "ZARA COAT 3");

}
