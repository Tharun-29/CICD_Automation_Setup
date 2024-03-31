package automation.framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {
 
	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tr//td[@class='box']")
	WebElement orderConfirmation;
	
	
	By orderId = By.xpath("//td/label[@class='ng-star-inserted']");
	
	public void getOrderId() {
		String CustomerOrderId = orderConfirmation.findElement(orderId).getText().split("\\|")[1].trim();
		System.out.print(CustomerOrderId);
	}
}
