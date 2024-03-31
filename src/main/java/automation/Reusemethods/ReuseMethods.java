package automation.Reusemethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.framework.pageobjects.OrderConfirmationPage;
import automation.framework.pageobjects.orderPage;

public class ReuseMethods {
    
	WebDriver driver;
	public ReuseMethods(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink='/dashboard/cart']")
    WebElement cartHeader;
	
	@FindBy(css = "[routerlink='/dashboard/myorders']")
    WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForElementToClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	//cart Selection 
	  //cart icon - to check the products added to the basket
	  //driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
	
	public void goToCartPage() {
		cartHeader.click();
	}
	
	
	public orderPage goToOrderPage() {
		orderHeader.click();
		orderPage ordpg = new orderPage(driver);
		return ordpg;
	}
}
