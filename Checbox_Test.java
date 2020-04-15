package com.java.test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Checbox_Test {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Softwares\\Selenium_Files\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		
		WebElement weChkbox = driver.findElement(By.cssSelector("#checkBoxOption1"));
		weChkbox.click();
		boolean bVal = weChkbox.isSelected();
		//Assert.assertEquals(bVal, true);
		Assert.assertTrue(bVal);
		System.out.println("1. Value: " + bVal);
		Thread.sleep(500);
		weChkbox.click();
		bVal = weChkbox.isSelected();
		Assert.assertFalse(bVal);
		System.out.println("2. Value: " + bVal);
		Thread.sleep(500);
		
		int nChkBox = driver.findElements(By.cssSelector("input[type='checkbox']")).size();
		System.out.println("Checkbox count: " + nChkBox);
		driver.quit();
	}

}
