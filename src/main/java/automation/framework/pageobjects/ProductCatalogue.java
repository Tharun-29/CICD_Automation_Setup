package automation.framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.Reusemethods.ReuseMethods;

public class ProductCatalogue extends ReuseMethods {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By product = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(product);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public cartPage addProductToCart(String productName) {
	     WebElement prod = getProductByName(productName);
	     prod.findElement(addToCart).click();
	     waitForElementToAppear(toastMessage);
	     waitForElementToDisappear(spinner);  
	     cartPage cp = new cartPage(driver);
	     return cp;
	}
}
