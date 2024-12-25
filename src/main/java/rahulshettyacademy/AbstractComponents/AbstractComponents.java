package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//button[contains(@routerlink,'cart')]")
	WebElement cart;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement order;

	public void waitForElementToAppear(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Explicit wait
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	public void waitForWebElementToAppear(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Explicit wait
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	
	public void waitForElementToDisappear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Explicit wait
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		
	}
	
	public CartPage goToCartPage() {
		cart.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrderPage goToOrderPage() {
		waitForWebElementToAppear(order);
		order.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}

}
