package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class OrderConfirmationPage extends AbstractComponents {
	
	WebDriver driver;
	
	By messageBy = By.cssSelector(".hero-primary");
	
	@FindBy (css = ".hero-primary")
	WebElement confirmMessage;
	
	public OrderConfirmationPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getConfirmMessage() {
		waitForElementToAppear(messageBy);
		return confirmMessage.getText();
	}

}
