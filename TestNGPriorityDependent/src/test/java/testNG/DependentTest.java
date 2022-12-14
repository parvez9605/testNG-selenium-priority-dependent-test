package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependentTest {
	@Test
	public void startApp() {
		System.out.println("Starting App");
	}
	
	@Test(dependsOnMethods = "startApp")
	public void loginApp() {
		System.out.println("Login to App");
		Assert.assertEquals(12, 15);
	}
	
	@Test(dependsOnMethods = {"startApp","loginApp"}) // multiple dependent method
	public void logoutApp() {
		System.out.println("Logout to App");
	}
}
