package Testpackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAndLogout {
	
	WebDriver driver;

	@BeforeMethod
	public void setUp() 
			throws InterruptedException {
		System.setProperty("webdriver.edge.driver", "driver//msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://99booksstore.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//*[@id=\"NewsletterPopup-newsletter-popup\"]/div/div/button")).click();
		Thread.sleep(2000);
	}
	
	
	@Test
	public void logininvalid() 
			throws InterruptedException {
		driver.findElement(By.cssSelector(".site-nav__link:nth-child(2) > .site-nav__icon-label")).click();
	    driver.findElement(By.id("CustomerEmail")).sendKeys("Mastertester02@gmail.com");
	    driver.findElement(By.id("CustomerPassword")).click();
	    driver.findElement(By.id("CustomerPassword")).sendKeys("Mastertester");
	    driver.findElement(By.cssSelector(".btn--full")).click();
	    
	    WebElement incorrectDetails = driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/ul/li"));
	    Assert.assertEquals(incorrectDetails.isDisplayed(), true, "User login failed");
	    System.out.println("logininvalid Testcase is passed");
	}
	
	
	@Test
	public void loginvalid() 
			throws InterruptedException {
		driver.findElement(By.cssSelector(".site-nav__link:nth-child(2) > .site-nav__icon-label")).click();
	    driver.findElement(By.id("CustomerEmail")).sendKeys("Mastertester02@gmail.com");
	    driver.findElement(By.id("CustomerPassword")).click();
	    driver.findElement(By.id("CustomerPassword")).sendKeys("Mastertester!1");
	    driver.findElement(By.cssSelector(".btn--full")).click();
	    
	    WebElement Username = driver.findElement(By.linkText("YUVRAJ"));
	    Assert.assertEquals(Username.isDisplayed(), true, "User login succesful");
	    driver.findElement(By.linkText("Log out")).click();
	    System.out.println("loginvalid Testcase is passed");
	}
	
	
	@Test
	public void logouttest() 
			throws InterruptedException {
		driver.findElement(By.cssSelector(".site-nav__link:nth-child(2) > .site-nav__icon-label")).click();
	    driver.findElement(By.id("CustomerEmail")).sendKeys("Mastertester02@gmail.com");
	    driver.findElement(By.id("CustomerPassword")).click();
	    driver.findElement(By.id("CustomerPassword")).sendKeys("Mastertester!1");
	    driver.findElement(By.cssSelector(".btn--full")).click();
	    driver.findElement(By.xpath("//*[@id=\"MainContent\"]/div/header/a")).click();
	    Thread.sleep(2000);
	    WebElement acc = driver.findElement(By.xpath("//*[@id=\"SiteHeader\"]/div[1]/div[1]/div/div[4]/div/div[1]/a[2]/span"));
	    String acc1 = acc.getText();
	    System.out.println(acc1);
	    Assert.assertEquals("ACCOUNT", acc1,"User logout test passed");
	    System.out.println("logouttest Testcase is passed");
	}
	
	
	@Test
	public void forgotpasswordvalid() 
			throws InterruptedException {
	    driver.findElement(By.cssSelector(".site-nav__link:nth-child(2) > .site-nav__icon-label")).click();
	    driver.findElement(By.id("RecoverPassword")).click();
	    driver.findElement(By.id("RecoverEmail")).click();
	    driver.findElement(By.id("RecoverEmail")).sendKeys("Mastertester02@gmail.com");
	    driver.findElement(By.cssSelector("p:nth-child(5) > .btn")).click();
	    Thread.sleep(2000);
	    WebElement reset = driver.findElement(By.id("ResetSuccess"));
	    Assert.assertTrue(reset.isDisplayed(), "User will get email to reset password");
	    System.out.println("forgotpasswordvalid Testcase is passed");
	}
	
	
	@Test
	public void forgotpasswordinvalid() 
			throws InterruptedException {
	    driver.findElement(By.cssSelector(".site-nav__link:nth-child(2) > .site-nav__icon-label")).click();
	    driver.findElement(By.id("RecoverPassword")).click();
	    driver.findElement(By.id("RecoverEmail")).click();
	    driver.findElement(By.id("RecoverEmail")).sendKeys("Mastertester@gmail.com");
	    driver.findElement(By.cssSelector("p:nth-child(5) > .btn")).click();
	    Thread.sleep(2000);
	    WebElement reset2 = driver.findElement(By.xpath("//*[@id=\"RecoverPasswordForm\"]/div/form/div/ul/li"));
	    Assert.assertTrue(reset2.isDisplayed(), "User given incorrect email to reset password");
	    System.out.println("forgotpasswordinvalid Testcase is passed");
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
