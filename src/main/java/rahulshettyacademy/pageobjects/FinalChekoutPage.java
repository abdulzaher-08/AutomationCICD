package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class FinalChekoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(css = "[placeholder*='Country']")
	WebElement country;
	
	@FindBy (css = "span.ng-star-inserted")
	List <WebElement> countryList;
	
	By countryListBy = By.cssSelector("span.ng-star-inserted");
	
	@FindBy(css = ".action__submit")
	WebElement submit;

	public FinalChekoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void countrySelection (String countryName) {
		country.sendKeys(countryName);
		waitForElementToAppear(countryListBy);
		
		WebElement myCountry = countryList.stream().filter(country -> country.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		myCountry.click();
	}
	
	public OrderConfirmationPage orderPlacement() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		OrderConfirmationPage ocp = new OrderConfirmationPage(driver);
		return ocp;
	}
	
	
	

}
