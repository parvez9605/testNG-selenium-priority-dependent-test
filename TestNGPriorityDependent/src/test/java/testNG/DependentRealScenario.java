package testNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DependentRealScenario {
	WebDriver driver;
	
	@BeforeClass
	public void startBroowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		System.out.println("=======Browser Started=======");
	}
	
	@Test
	public void startApp() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("auth/login"));
		
		System.out.println("Application Loaded");
	}
	
	@Test(dependsOnMethods = "startApp")
	public void loginApp() {
		WebElement userName = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		
		userName.sendKeys("Admin22");
		password.sendKeys("admin123");
		
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"));
		
		loginBtn.click();
		
		Boolean status = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[8]/a/span")).isDisplayed();
		
		Assert.assertTrue(status);
		
		System.out.println("Login successfull");
	}
	
	@Test(dependsOnMethods = "loginApp")
	public void logoutApp() throws InterruptedException {
Boolean name = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[2]/ul/li/span/p")).isDisplayed();
		
		Assert.assertTrue(name);
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[2]/ul/li/span/i")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[2]/ul/li/ul/li[4]/a")).click();
		
		System.out.println("Logout successfull");
	}
	
	@AfterClass
	public void closeApp() {
		driver.quit();
		System.out.println("========Browser Closed=========");
	}
}
