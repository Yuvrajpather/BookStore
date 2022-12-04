package Testpackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePage {
	
	WebDriver driver;
	
	@BeforeTest
	public void setUp() throws InterruptedException
	{
		System.setProperty("webdriver.edge.driver", "driver//msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://99booksstore.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//*[@id=\"NewsletterPopup-newsletter-popup\"]/div/div/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".site-nav__link:nth-child(2) > .site-nav__icon-label")).click();
	    driver.findElement(By.id("CustomerEmail")).sendKeys("Mastertester02@gmail.com");
	    driver.findElement(By.id("CustomerPassword")).click();
	    driver.findElement(By.id("CustomerPassword")).sendKeys("Mastertester!1");
	    driver.findElement(By.cssSelector(".btn--full")).click();
	    driver.findElement(By.xpath("//*[@id=\"SiteHeader\"]/div[2]/div/ul/li[1]/a")).click();
	}
	
	@BeforeMethod
	public void condition() throws InterruptedException {
		Thread.sleep(1000);
	}
	
	@Test
	public void TitleTest() {
		String actualtitle = driver.getTitle();
		String expectedtitle = "99BooksStore | Buy New, Preloved and Old Books Online in India";
		Assert.assertEquals(actualtitle, expectedtitle, "Titletest passed");
		System.out.println("Titletest passed");
	}

	@Test
	public void LogoTest() {
		WebElement Logo = driver.findElement(By.xpath("//*[@id=\"SiteHeader\"]/div[1]/div[1]/div/div[2]/h1/a"));
		Assert.assertTrue(Logo.isDisplayed(), "Logo is present testcase passed");
		System.out.println("LogoTest is passed");
	}
	
	@Test
	public void SearchbarTest() {
		WebElement Searchbar = driver.findElement(By.name("q"));
		Assert.assertTrue(Searchbar.isDisplayed(), "Searchbar is present testcase passed");
		System.out.println("SearchbarTest is passed");
	}
	
	@Test
	public void UsernameTest() {
		String actualusername = driver.findElement(By.linkText("YUVRAJ")).getText();
		String expectedusername = "YUVRAJ";
	    Assert.assertEquals(actualusername, expectedusername, "Username is visible");
	    System.out.println("UsernameTest is passed");
	}
	
	@Test
	public void AboutUsTest() {
		WebElement aboutus = driver.findElement(By.xpath("//*[@id=\"FooterMenus\"]/div/div/div[2]/ul/li[1]/a"));
		Assert.assertTrue(aboutus.isDisplayed(), "About Us is present testcase passed");
		System.out.println("AboutUsTest is passed");
	}
	
	@Test
	public void ContactUsTest() {
		WebElement contactus = driver.findElement(By.xpath("//*[@id=\"FooterMenus\"]/div/div/div[2]/ul/li[3]/a"));
		Assert.assertTrue(contactus.isDisplayed(), "Contact Us is present testcase passed");
		System.out.println("ContactUsTest is passed");
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
