package ace.turtle.services.test.selenuim;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App 
{
//	private static void closeAllFrame(WebDriver webDriver) {
//		try{
//			int frameCount = webDriver.findElements(By.tagName("iframe")).size();
//			for(int count = 0; count < frameCount; count++) {
//				webDriver.switchTo().frame(count);
//				webDriver.switchTo().parentFrame();
//			}
//		}
//		catch (Exception e) {
//			//			e.printStackTrace();
//		}
//		finally {
//			webDriver.switchTo().parentFrame();
//		}
//	}
//
//	private static void clickOnElement(WebDriver webDriver, By locator) {
//		try {
//			webDriver.findElement(locator).click();
//		}
//		catch (Exception e) {
//			//			e.printStackTrace();
//			closeAllFrame(webDriver);
//			webDriver.findElement(locator).click();
//		}
//	}
//
//	public static void main( String[] args )
//	{
//		System.setProperty("webdriver.chrome.driver", "//Users//vishnugupta//Documents//chromedriver");
//		WebDriver webDriver = new ChromeDriver();
//		webDriver.get("https://in.puma.com/");
//		//		webDriver.manage().window().maximize();
//		//       closeAllFrame(webDriver);
//		WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"header-nav\"]/ul/li[1]/a"));
//		Actions action = new Actions(webDriver);
//		action.moveToElement(webElement).build().perform();
//		//       closeAllFrame(webDriver);
//		//		webDriver.findElement(By.xpath("//*[@id=\"men-subnav\"]/div/div/div[2]/ul/li[2]/a")).click();
//		clickOnElement(webDriver, By.xpath("//*[@id=\"men-subnav\"]/div/div/div[2]/ul/li[2]/a"));
//		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
//		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product-collection-image-61642")));
//		//		System.out.println(webDriver.getTitle());
//		//		closeAllFrame(webDriver);
//		System.out.println(webDriver.getTitle());
//		//		webDriver.findElement(By.id("product-collection-image-61642")).click();
//		clickOnElement(webDriver,By.xpath("//*[@id=\"mp-pusher\"]/div/div[4]/div[2]/div[2]/div[2]/div/ul/li[2]/a"));
//		ArrayList<String> tabs2 = new ArrayList<String> (webDriver.getWindowHandles());
//		webDriver.switchTo().window(tabs2.get(0)).close();
//		webDriver.switchTo().window(tabs2.get(1));
//		String size = null;
//		clickOnElement(webDriver, By.xpath("//*[@id=\"size_label\"]/div[1]/div[2]/span"));
//		clickOnElement(webDriver, By.xpath("//*[@id=\"option71\"]"));
//		size = webDriver.findElement(By.xpath("//*[@id=\"select_label_size\"]")).getText();
//		//		System.out.println(webDriver.findElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[7]/div[2]/div/button")).getText());
//		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[7]/div[2]/div/button")));
//		if(webDriver.findElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[7]/div[2]/div/button")).isDisplayed()) {
//			try {
//				Thread.sleep(1000*5);
//			} catch (InterruptedException e) {
//
//			}finally {
//				clickOnElement(webDriver, By.xpath("//*[@id=\"product_addtocart_form\"]/div[3]/div[7]/div[2]/div/button"));
//				if(!webDriver.getTitle().equalsIgnoreCase("Shopping cart")) {
//					System.out.println("Product is out of stock");
//				}
//				else {
//					//check cart
//				}
//			}
//		}
//	}
}
