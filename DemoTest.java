package com.java.test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoTest {	
	public void getMethodsTest(WebDriver driver) throws InterruptedException	{
		//	Launches a new browser and opens the specified URL in the browser instance
		driver.get("https://www.toolsqa.com/");
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		//	To retrieve the Class object that represents the runtime class of this object
		System.out.println("The class name is : " + driver.getClass());
		//	To retrieve the URL of the web page the user is currently accessing
		System.out.println("The current URL of the page is : " + driver.getCurrentUrl());
		//	To retrieve the page source	of the web page the user is currently accessing
		boolean result = driver.getPageSource().contains("Selenium  tutorial");
		System.out.println("The result of this page source is: " + result);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[text()='Selenium Online Trainings']"))));
		driver.findElement(By.xpath("//img[@alt='close-link']")).click();		
		
		Actions builder = new Actions(driver);
		// Locate the element by mouse hover to Tutorial
		WebElement mainMenu = driver.findElement(By.xpath("(//span[text()='Tutorial'])[1]"));
		mainMenu.click();
		// Locate the element by mouse hover to Front-End Testing Automation
		builder.moveToElement(mainMenu).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).click().build().perform();
		//Thread.sleep(2000);
		
		// Locate the element by mouse hover to Selenium Tutorial in Java
		WebElement subMenu = driver.findElement(By.xpath("(//span[text()='Selenium in Java'])[1]"));
		wait.until(ExpectedConditions.visibilityOf(subMenu));
		builder.moveToElement(subMenu).click().build().perform();
		//Thread.sleep(2500);
		
		//	Locate elements using LinkText and Partial Link Text
		WebElement headerEle = driver.findElement(By.xpath("//h1[contains(text(),'Selenium Tutorial')]"));
		wait.until(ExpectedConditions.visibilityOf(headerEle));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.linkText("Set Up Java"));
		//This will scroll the page till the element is found
		//jse.executeScript("arguments[0].scrollIntoView();", element);
		jse.executeScript("window.scrollBy(0,700)");
		element.click();
		//driver.findElement(By.partialLinkText("Set Up")).click();
		
		//	To retrieve the title of the webpage the user is currently working on.
		String strTitle =driver.getTitle();
		System.out.println("The title of the current page is " + strTitle);
		//	Click the search icon
		driver.findElement(By.xpath("//a[contains(@class,'submit') and contains(@class,'text-disable')]")).click();
		WebElement weSearchText = driver.findElement(By.id("the7-micro-widget-search"));
		weSearchText.clear();
		weSearchText.sendKeys("Exceptions");

		//To retrieve the value of the specified attribute
		@SuppressWarnings("unused")
		String sText = weSearchText.getAttribute("value");
		System.out.println("Entered value in the Search Textbox is: " +sText);
		driver.findElement(By.xpath("//a[@href='#go']")).click();
		//	To close the browser
		//driver.close();		
	}
	
	public void testLocators(WebDriver driver) throws InterruptedException	{		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement wRadio, wText, wChkbox;
		
		wRadio = driver.findElement(By.xpath("//input[@value='radio1']"));
		wRadio.click();
		wText = driver.findElement(By.id("autocomplete"));
		wText.sendKeys("India");
		Select dropdown = new Select(driver.findElement(By.id("dropdown-class-example")));
		dropdown.selectByValue("option2");
		driver.findElement(By.name("checkBoxOption1")).click();
		wChkbox = driver.findElement(By.name("checkBoxOption2"));
		wChkbox.click();
		
		switchToWin(driver);
		switchToTab(driver);
		switchToAlert(driver);
		driver.close();		
	}

	public void switchToWin(WebDriver driver) throws InterruptedException {
		String parentWndHandle = driver.getWindowHandle();	// Get the current window handle
		System.out.println("The Parent Win Handle is : " + parentWndHandle);		
		WebElement openWnd = driver.findElement(By.id("openwindow"));	// Click the button to open a new window
		openWnd.click();
		
		// Get the window handles of all the open windows
		Set<String>winHandles = driver.getWindowHandles();
		for(String handle : winHandles)	
		{
			if(!handle.equals(parentWndHandle))
			{
				driver.switchTo().window(handle);
				Thread.sleep(1000);
				System.out.println("Tile of the new window " + driver.getTitle());
			}
		}
		
		driver.switchTo().window(parentWndHandle);	//	Switching the control back to the parent window
		System.out.println("Parent window URL: " + driver.getCurrentUrl());		
	}
	
	public void switchToTab(WebDriver driver)	{
		driver.findElement(By.id("opentab")).click();	//	Click on Open Tab link
		driver.getTitle();
		driver.switchTo().defaultContent();	//	To Switch back to the original tab
	}
	
	public void switchToAlert(WebDriver driver)	{
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Chandrasekar");
		driver.findElement(By.id("alertbtn")).click();	//	Click on Alert button
		Alert alertBox = driver.switchTo().alert();
		alertBox.accept();
		
		driver.findElement(By.id("confirmbtn")).click();	//	Click on Confirm button
		Alert confirmBox = driver.switchTo().alert();
		confirmBox.dismiss();
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Softwares\\Selenium_Files\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		DemoTest demo = new DemoTest();
		//demo.getMethodsTest(driver);
		demo.testLocators(driver);
	}		
}	

