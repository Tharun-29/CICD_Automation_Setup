package AutomationTest.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.framework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class standAloneTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		
	  WebDriver driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.get("https://rahulshettyacademy.com/client");
	  
	  LandingPage lpage = new LandingPage(driver);
	  
	  driver.findElement(By.id("userEmail")).sendKeys("DavidMDiaz@rhyta.com");
	  driver.findElement(By.id("userPassword")).sendKeys("David@54321");
	  driver.findElement(By.id("login")).click();

         //GitHub Webhook Test
         //Adding Comment to Test Auto trigger of Jenkins - This line nothing do with the code in this class
	  
	 /* Normal Approach
	  List<WebElement> lists = driver.findElements(By.xpath("//div[@class='container']//div//div//div//h5"));
	  for(WebElement e: lists) {
		  if(e.getText().equalsIgnoreCase("ZARA COAT 3")){
			  System.out.println("I found It");
		  }
		  
	  } */
	  
	  String item = "ADIDAS ORIGINAL";
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	  //Using Streams
	  List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	  WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(item)).findFirst().orElse(null);
	  prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	  
	  
	  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
	  
	  wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	  
	  //cart icon - to check the products added to the basket
	  driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
	 
	  List<WebElement> basketProducts = driver.findElements(By.cssSelector(".cart h3"));
	  boolean match = basketProducts.stream().anyMatch(productName->productName.getText().equals(item));
	  Assert.assertTrue(match);
	  
	  //Clicking on Checkout button
	  driver.findElement(By.cssSelector(".totalRow button")).click();
	  
	  //Entering the value in country field
	  driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
	  
	  //AutoSuggestive Dropdown for selecting the country
	  List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-results button span"));
	  for(WebElement c: countryList) {
		  if(c.getText().equalsIgnoreCase("India")) {
			  c.click();
			  break;
		  }
	  }
	  
	  Actions act = new Actions(driver);
	  WebElement placeOrder = driver.findElement(By.cssSelector(".actions a"));
	  
	  int x = placeOrder.getLocation().getX();
	  int y = placeOrder.getLocation().getY();
	  act.scrollByAmount(x, y).perform();
	  //Click on Place Order button
	  WebElement checkoutOrder = driver.findElement(By.cssSelector(".btnn"));
	  wait.until(ExpectedConditions.elementToBeClickable(checkoutOrder));
	  checkoutOrder.click();
	  
	  //Order Confirmation Page Section
	  WebElement orderConfirmation = driver.findElement(By.xpath("//tr//td[@class='box']"));
	  String orderId = orderConfirmation.findElement(By.xpath("//td/label[@class='ng-star-inserted']")).getText().split("\\|")[1].trim();
	  System.out.print(orderId);
	  
	  
	  
	  
	}
	
	 

}
