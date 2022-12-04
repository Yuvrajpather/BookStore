package Testpackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProductBuyFlow {

	WebDriver driver;
	JavascriptExecutor js;
	Actions actions;

	@BeforeTest
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.edge.driver", "driver//msedgedriver.exe");
		driver = new EdgeDriver();
		js = (JavascriptExecutor) driver;
		actions = new Actions(driver);
		driver.get("https://99booksstore.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.xpath("//*[@id=\"NewsletterPopup-newsletter-popup\"]/div/div/button")).click();
		Thread.sleep(2000);
	}
	
	@Test(priority = 1)
	public void AddToCart() throws InterruptedException {
		System.out.println("Searching Book");

		driver.findElement(By.name("q")).click();
		driver.findElement(By.name("q")).sendKeys("Percy Jackson");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".is-active > .site-header__search-btn--submit > .icon")).click();

		System.out.println("Book searched succesfully");

		// selecting from search result
		driver.findElement(By.cssSelector(".grid-item:nth-child(3) > .grid-item__content .lazyautosizes")).click();

		// scroll
		WebElement Element = driver.findElement(By.name("Condition"));
		js.executeScript("arguments[0].scrollIntoView();", Element);

		// add to cart
		driver.findElement(By.cssSelector(".btn--full > span")).click();

		// check cart if item is added
		boolean itemincart = driver.findElement(By.xpath("//*[@id=\"CartPageForm\"]/div/div[1]/div")).isDisplayed();

		Assert.assertTrue(itemincart, "Item is present in cart");
		System.out.println("Item is added to cart succesfully");
	}
	
	@Test(priority = 2)
	public void ItemIncrease() throws InterruptedException {

		// scroll
		WebElement Element = driver.findElement(By.className("section-header__title"));
		js.executeScript("arguments[0].scrollIntoView();", Element);

		// adding click + button
		driver.findElement(By.cssSelector(".js-qty__adjust--plus")).click();

		// check value added is increased
		WebElement v1 = driver.findElement(By.name("updates[]"));
		String excpectedvalue = v1.getAttribute("value");
		String actualvalue = "2";
		Assert.assertEquals(actualvalue, excpectedvalue, "Adding extra item is succesful");

		Thread.sleep(2000);
	}

	@Test(priority = 3)
	public void ItemDecrease() throws InterruptedException {
		Thread.sleep(2000);
		// reducing item -
		driver.findElement(By.cssSelector(".js-qty__adjust--minus")).click();

		Thread.sleep(2000);

		// check value is reduced
		WebElement v2 = driver.findElement(By.name("updates[]"));
		String excpectedvalue2 = v2.getAttribute("value");
		String actualvalue2 = "1";
		Assert.assertEquals(actualvalue2, excpectedvalue2, "Adding extra item is succesful");

		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void RemoveProduct() throws InterruptedException {
		// removing item
		driver.findElement(By.linkText("Remove")).click();

		Thread.sleep(2000);

		//scroll
		WebElement Element2 = driver.findElement(By.className("section-header__title"));
		js.executeScript("arguments[0].scrollIntoView();", Element2);

		// check value is removed
		WebElement r = driver.findElement(
				By.xpath("//*[@id=\"shopify-section-template--15027455525017__main\"]/div/header/div/p[1]"));
		Assert.assertTrue(r.isDisplayed(), "Item is removed from cart");
		System.out.println("Item succesfully removed from cart.");
		System.out.println(r.getText());

		Thread.sleep(5000);

		//scroll
		WebElement Element3 = driver.findElement(By.name("q"));
		js.executeScript("arguments[0].scrollIntoView();", Element3);
	}

	@Test(priority = 5)
	public void CartCheckout() throws InterruptedException {
		Thread.sleep(2000);

		// click home after empty cart
		driver.findElement(By.xpath("//*[@id=\"SiteHeader\"]/div[2]/div/ul/li[1]/a")).click();

		Thread.sleep(2000);

		System.out.println("Searching new book");

		driver.findElement(By.name("q")).click();
		driver.findElement(By.name("q")).sendKeys("Bhagwat gita");
		driver.findElement(By.cssSelector(".is-active > .site-header__search-btn--submit > .icon")).click();
		driver.findElement(By.cssSelector(".grid__image-ratio--portrait > .lazyautosizes")).click();
		{
			WebElement element = driver.findElement(By.cssSelector(".js-photoswipe__zoom"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}

		// add to cart
		driver.findElement(By.cssSelector(".btn--full > span")).click();
		System.out.println("Added new book to cart");

		// scrolling
		WebElement Element = driver.findElement(By.className("section-header__title"));
		js.executeScript("arguments[0].scrollIntoView();", Element);

		// checkout
		driver.findElement(By.cssSelector(".cart__item-row:nth-child(4) > .cart__checkout")).click();
		System.out.println("Clicking Checkout");

		Thread.sleep(2000);

		WebElement login = driver.findElement(By.xpath("//*[@id=\"MainContent\"]/div/header/h1"));
		Assert.assertTrue(login.isDisplayed(), "Asking login details");
		System.out.println("User is asked for login before placing order test is passed");

		Thread.sleep(2000);

		// asking login details
		driver.findElement(By.id("CustomerEmail")).sendKeys("Mastertester02@gmail.com");
		driver.findElement(By.id("CustomerPassword")).click();
		driver.findElement(By.id("CustomerPassword")).sendKeys("Mastertester!1");
		driver.findElement(By.cssSelector(".btn--full")).click();

		System.out.println("User logged in to account");

		Thread.sleep(2000);
	}

	@Test(priority = 6)
	public void ShippingDetails() throws InterruptedException {
		Thread.sleep(2000);
		// shipping address details
//		driver.findElement(By.id("checkout_shipping_address_address1")).sendKeys("Ward no 2");
//		driver.findElement(By.id("checkout_shipping_address_address2")).sendKeys("Plot no 3");
		driver.findElement(By.name("address1")).sendKeys("Ward no 2");
		driver.findElement(By.name("address2")).sendKeys("Plot no 3");
		{
//			WebElement dropdown = driver.findElement(By.id("checkout_shipping_address_province"));
			WebElement dropdown = driver.findElement(By.name("zone"));
			dropdown.findElement(By.xpath("//option[. = 'Maharashtra']")).click();
		}
//		driver.findElement(By.id("checkout_shipping_address_zip")).sendKeys("441104");
//		driver.findElement(By.id("checkout_shipping_address_phone")).sendKeys("8208544114");
		driver.findElement(By.name("postalCode")).sendKeys("441104");
		driver.findElement(By.name("phone")).sendKeys("8208544114");

//		driver.findElement(By.id("continue_button")).click();
		driver.findElement(By.xpath("//*[@id=\"Form1\"]/div[1]/div/div/div[2]/div[1]/button")).click();

		// check user can continue without proper filling details
//		WebElement err = driver.findElement(By.xpath("//*[@id=\"error-for-city\"]"));
		WebElement err = driver.findElement(By.className("ksaxnz0"));
		Assert.assertTrue(err.isDisplayed(), "User can't continue without filling proper shippping details");
		System.out.println("User can't continue without filling proper shippping details");

//		driver.findElement(By.id("TextField1")).sendKeys("Yuvraj");
//		driver.findElement(By.id("TextField2")).sendKeys("Pather");
//		driver.findElement(By.id("checkout_shipping_address_city")).sendKeys("Nagpur");
		driver.findElement(By.name("firstName")).sendKeys("Yuvraj");
		driver.findElement(By.name("lastName")).sendKeys("Pather");
		driver.findElement(By.name("city")).sendKeys("Nagpur");

//		driver.findElement(By.id("continue_button")).click();
		driver.findElement(By.xpath("//*[@id=\"Form1\"]/div[1]/div/div/div[2]/div[1]/button")).click();
		System.out.println("User can continue after filling proper shippping details");
	}

	@Test(priority = 7)
	public void PaymentMode() {
		driver.findElement(By.cssSelector(".\\_1FPaj:nth-child(1) .\\_1x52f9s1")).click();
		driver.findElement(By.cssSelector(".\\_1Kqoj")).click();
		System.out.println("User choosing payment option");

		// Checking multiple payment options
		WebElement pay = driver.findElement(By.id("basic-Razorpay Secure (UPI, Cards, Wallets, NetBanking)-collapsible"));
		Assert.assertTrue(pay.isDisplayed(), "Multiple payment option other than cod is available");
		System.out.println(pay.getText());

		driver.navigate().back();

		driver.findElement(By.cssSelector(".\\_1FPaj:nth-child(2) .\\_1x52f9sn")).click();
		driver.findElement(By.cssSelector(".\\_1Kqoj")).click();
	}

	@Test(priority = 8)
	public void OrderConfirm() {

		// scrolling
		WebElement Element = driver.findElement(By.name("billing_address_selector"));
		js.executeScript("arguments[0].scrollIntoView();", Element);

		driver.findElement(By.cssSelector(".\\_1FPaj:nth-child(2) .\\_1GfMy > .\\_19gi7yt0")).click();

		// Checking option of billing address
		WebElement fname = driver.findElement(By.name("firstName"));
		fname.sendKeys("Yuvraj");
		Assert.assertTrue(fname.isDisplayed(), "Passed");
		System.out.println("User can get option of different billing address");

		driver.findElement(By.cssSelector(".\\_1FPaj:nth-child(1) .\\_1GfMy > .\\_19gi7yt0")).click();
		System.out.println("User can confirm to place the order by checking final details");

		//After clicking checkout button order will be placed
		System.out.println("Order placement will get succesfully");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
