package automation.framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.Reusemethods.ReuseMethods;

public class checkoutPage extends ReuseMethods {
	
   WebDriver driver;
   public checkoutPage(WebDriver driver) {
	   super(driver);
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
   }
   
   @FindBy(css = "[placeholder='Select Country']")
   WebElement country;
		
   @FindBy(css = ".ta-results button span")
   List<WebElement> countryList;
   
   @FindBy(css = ".actions a")
   WebElement placeOrder;
   
   @FindBy(css = ".btnn")
   WebElement checkoutOrder;
   
	public void selectCountry() {
		country.sendKeys("india");
	}
	
	// AutoSuggestive Dropdown for selecting the country
	public void selectCountryFromList() {
		for (WebElement c : countryList) {
			if (c.getText().equalsIgnoreCase("India")) {
				c.click();
				break;
			}
		}
	}
	
	public void scrollToPlaceOrder() {
		Actions act = new Actions(driver);
		int x = placeOrder.getLocation().getX();
		int y = placeOrder.getLocation().getY();
		act.scrollByAmount(x, y).perform();
	}
	
	public OrderConfirmationPage checkTheOrder() {
	 waitForElementToClickable(checkoutOrder);
	 checkoutOrder.click();
	 OrderConfirmationPage confirmation = new OrderConfirmationPage(driver);
	 return confirmation;
	}
}
