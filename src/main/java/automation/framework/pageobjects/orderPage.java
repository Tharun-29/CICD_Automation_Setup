package automation.framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.Reusemethods.ReuseMethods;

public class orderPage extends ReuseMethods {
	WebDriver driver;

	public orderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;
	
	

	// Checkout Page Method -> This method is used to validate the product added is
	// correct in basket by fetching all the product
	// - list in the application and matching with the user added product
	// productName is passed as string to this method
	
	// Clicking on Checkout button
		public checkoutPage goToCheckout() {
			checkoutButton.click();
			checkoutPage chkPg = new checkoutPage(driver);
			return chkPg;
		}
		
	 public Boolean VerifyOrderDisplay(String productName) {
		boolean match = productNames.stream().anyMatch(product -> product.getText().equals(productName));
		return match;
	}

	

}