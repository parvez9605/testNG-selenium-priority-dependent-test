package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGPractice {
	
	@Test(priority = 1, description = "This test case will run first")
	public void loginApp() {
		System.out.println("Here is my first test");
		
		Assert.assertEquals(12, 13);
	}
	
	@Test(priority = 2, description = "This test case will select item")
	public void selectItems() {
		System.out.println("Item selected");
	}
	
	@Test(priority = 3, description = "This test case will perform check title")
	public void checkTitle() {
		System.out.println("This is check title");
	}

}
