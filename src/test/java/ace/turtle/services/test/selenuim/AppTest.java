package ace.turtle.services.test.selenuim;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AppTest
{
	private static final String CHROME_DRIVER_KEY ="webdriver.chrome.driver";
	//give your chrome driver path
	private static final String CHROME_DRIVER_PATH = "";
	//give number to be selected from listing
	private static final String SELECT_SHOE_FROM_LISTING = "9"; 
	private static final String URL = "https://in.puma.com/";

	private static WebDriver driver = null;
	private static Actions action = null;
	private static WebElement webElement = null;
	private static WebDriverWait wait = null;
	private static JavascriptExecutor javascriptExecutor = null;
	private static ExpectedCondition<Boolean> expectation = null;
	private static Select select = null;


	//checking for complete page load
	private ExpectedCondition<Boolean> getJavaScriptExpectation(final String script, final String expectedResult, String ... params){
		return expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(script).toString().equalsIgnoreCase(expectedResult);
			}
		};
	}

	//clicking on element using by locator and handling any error encountered due to frames
	private void clickOnWebElement(By by) {

		int frameCount = driver.findElements(By.tagName("iframe")).size();

		for(int start = 0; start < frameCount; start++) {
			try {
				driver.findElement(by).click();
				break;
			}
			catch (Exception e) {
				driver.switchTo().parentFrame();
			}
		}
		if(frameCount == 0)
			driver.findElement(by).click();
	}
	//clicking on web element and handling any error encountered due to frames
	private void clickOnWebElement(WebElement element) {

		int frameCount = driver.findElements(By.tagName("iframe")).size();

		for(int start = 0; start < frameCount; start++) {
			try {
				element.click();
				break;
			}
			catch (Exception e) {
				driver.switchTo().parentFrame();
			}
		}
		if(frameCount == 0)
			element.click();
	}
	//setting chrome driver properties
	@BeforeTest
	public void setup() {
		System.setProperty(CHROME_DRIVER_KEY, CHROME_DRIVER_PATH);
	}

	@AfterTest
	public void destroy() {
		driver.close();
		driver = null;
		action = null;
		webElement = null;
		wait = null;
		javascriptExecutor = null;
		expectation = null;
		select = null;
	}

	@Test
	public void addToCartTest() {
		
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);
		driver.get(URL);
		//driver.manage().window().maximize();
		
		//Click on Men/Shoes/Running
		webElement = driver.findElement(By.xpath("//*[@id=\"header-nav\"]/ul/li[1]/a"));
		action = new Actions(driver);
		action.moveToElement(webElement).build().perform();
		clickOnWebElement(By.xpath("//*[@id=\"men-subnav\"]/div/div/div[2]/ul/li[2]/a"));
		//waiting for full page load
		wait.until(getJavaScriptExpectation("return document.readyState", "complete", null));
		
		//Click on SELECT_SHOE_FROM_LISTING from listing page
		clickOnWebElement(By.xpath("//*[@id=\"mp-pusher\"]/div/div[4]/div[2]/div[2]/div[2]/div/ul/li["+SELECT_SHOE_FROM_LISTING+"]/a"));
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		if(tabs.size() > 1) {
			driver.switchTo().window(tabs.get(0)).close();
			driver.switchTo().window(tabs.get(1));
		}
		String price = null;
		String title = null;
		String qty = null;
		
		//selecting size and iterating over if size is out of stock and then clicking ADD TO CART keeping the default quantity 1.
		clickOnWebElement(By.xpath("//*[@id=\"size_label\"]/div[1]/div[2]/span"));
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"size_label\"]/div[2]/ul/li"));
		String lastPageTitle = driver.getTitle();
		for(WebElement element : elements) {
			clickOnWebElement(element);
			try {
				Thread.sleep(1000*8);//depending on Internet speed and system processing speed
			}
			catch (Exception e) {
			}
			if(driver.findElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[7]/button")).isDisplayed()) {
				clickOnWebElement(By.xpath("//*[@id=\"size_label\"]/div[1]/div[2]/span"));
			}
			else {
				title = element.findElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[1]/span")).getText();
				select = new Select(driver.findElement(By.xpath("//*[@id=\"qty\"]")));
				qty = select.getFirstSelectedOption().getText();
				price = driver.findElement(By.xpath("//*[@id='product-options-wrapper']/dl/div/span/span")).getText();

				try {
					clickOnWebElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[7]/div[2]/div/button"));
					break;
				}
				catch (Exception e) {
					try {
						Thread.sleep(1000*5);
					} catch (InterruptedException e1) {

					}
					finally {
						clickOnWebElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[7]/div[2]/div/button"));
						if(!driver.getTitle().equals(lastPageTitle))
							break;
					}
				}
			}
		}
		wait.until(getJavaScriptExpectation("return document.readyState", "complete", null));
		if(!driver.getTitle().equalsIgnoreCase("Shopping cart")) {
			System.out.println("Product is out of stock");
		}
		else {
			//Asserting that the cart has the correct shoe selected in previous step (Based on name of product/quantity/price)
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[2]/h2")).getText().trim().toLowerCase().equals(title.trim().toLowerCase()));
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[3]/span/span")).getText().trim().toLowerCase().equals(price.trim().toLowerCase()));
			select = new Select(driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/div/select")));
			Assert.assertTrue(select.getFirstSelectedOption().getText().trim().equals(qty.trim()));
			System.out.println("Test passed");
		}


	}
}
