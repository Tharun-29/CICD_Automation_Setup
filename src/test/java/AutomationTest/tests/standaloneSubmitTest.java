package AutomationTest.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTest.TestComponents.BaseTest;
import automation.framework.pageobjects.LandingPage;
import automation.framework.pageobjects.OrderConfirmationPage;
import automation.framework.pageobjects.ProductCatalogue;
import automation.framework.pageobjects.cartPage;
import automation.framework.pageobjects.checkoutPage;
import automation.framework.pageobjects.orderPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class standaloneSubmitTest extends BaseTest {
	
	String item = "ADIDAS ORIGINAL";

	//Obtained Data from Json file method(i.e - getData() method) will be passed to below method through DataProvider
	//Data retrived is captured in method arguments by creating HashMap
	@Test(dataProvider = "getData", groups = {"CreateOrder"})
	public void submitOrder(HashMap<String, String> input) throws IOException {

        //Login to Application
		
		//Creating Object for Login Page Class and accessing Login page methods to perform login actions based on inputs passed
		//Login Page method will create object for Product Page class and return the address to Test/Main method
		
		// input.get() method used to retrieve the parameter value from external json file by providing the parameter or attribute name
		ProductCatalogue productCatalogue = lpage.LoginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();

		cartPage cp = productCatalogue.addProductToCart(input.get("item"));
		cp.goToCartPage();

		Boolean match = cp.VerifyProductDisplay(input.get("item"));
		Assert.assertTrue(match);

		checkoutPage chkPg = cp.goToCheckout();
		chkPg.selectCountry();
		chkPg.selectCountryFromList();

		chkPg.scrollToPlaceOrder();

		OrderConfirmationPage confirmation = chkPg.checkTheOrder();

		// Order Confirmation Page Section
		confirmation.getOrderId();
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		//"ZARA COAT 3"
		ProductCatalogue productCatalogue = lpage.LoginApplication("DavidMDiaz@rhyta.com", "David@54321");
		orderPage ordersPage = productCatalogue.goToOrderPage();
		
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(item));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		//Calling the method created for reading the Json file and converting to HashMap
		// Note - We have passed the Path of JSON File as argument or parameter to the method below
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AutomationTest\\data\\CreateOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		//return new Object[][] {{"DavidMDiaz@rhyta.com","David@54321","ADIDAS ORIGINAL"},{"zalepraddili-9737@yopmail.com","Cillian@54321","ZARA COAT 3"}};
	}
	
}
