package AutomationTest.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTest.TestComponents.BaseTest;
import automation.framework.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException {

		String item = "ADIDAS ORIGINAL";

		lpage.LoginApplication("DaviaMDiaz@rhyta.com", "David4321");
		
		Assert.assertEquals("Incorrt email or password.", lpage.getErrorMessage());
	  
	  
		
	}
}
