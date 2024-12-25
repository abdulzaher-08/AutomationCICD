package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	By titleBy = By.cssSelector(".cartSection h3");
	
	@FindBy (css = ".cartSection h3")
	List <WebElement> cartProducts;
	
	@FindBy (xpath = "//button[text()='Checkout']")
	WebElement checkoutEle;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyProductDisplay(String productName) {
		waitForElementToAppear(titleBy);
		Boolean match = cartProducts.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public FinalChekoutPage goToCheckout() {
		checkoutEle.click();
		FinalChekoutPage fcp = new FinalChekoutPage(driver);
		return fcp;
	}

}
