package automation.framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.Reusemethods.ReuseMethods;

public class LandingPage extends ReuseMethods {

	WebDriver driver;
	
    //constructor of Landing Page (First thing execute in class is constructor)
	public LandingPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	
	
	//Design Pattern - PageFactory is used 

	@FindBy(id = "userEmail")
	WebElement loginId;
  
	// At Run Time the above 2 line works like this statement -> WebElement loginID = driver.findElement(By.id("userEmail"));
	
	
	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;
	
	//css class for Invalid Login Error message -  .ng-tns-c4-21.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue LoginApplication(String email,String pass) {
		loginId.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
		
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
