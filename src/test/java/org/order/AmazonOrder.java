package org.order;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonOrder { 
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("woodland sandals for men");
		driver.findElement(By.id("nav-search-submit-button")).click();
		driver.findElement(By.xpath("//div[@data-asin='B07RFWPM1W']")).click();
		String parent = driver.getWindowHandle();
		Set<String> both = driver.getWindowHandles();
		for (String child : both) {
			if (!parent.equals(child)) {
				driver.switchTo().window(child);
			}
		}
		WebElement element = driver.findElement(By.id("native_dropdown_selected_size_name"));
		Select sel = new Select(element);
		sel.selectByIndex(4);
		driver.findElement(By.id("buy-now-button")).click();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("madhes.1010@gmail.com");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("12217779");
		driver.findElement(By.id("signInSubmit")).click();
		
		
	}

}
