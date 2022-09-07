package org.order;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class ShoeOrder {

	public static void main(String[] args) throws Exception {
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\et\\eclipse-workspace\\Cncapsulation\\Drivers\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.flipkart.com");
			driver.manage().window().maximize();
			
//			WebElement element = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
//			element.sendKeys("9578649918");
//			WebElement element2 = driver.findElement(By.xpath("//input[@type='password']"));
//			element2.sendKeys("12217779");
//			driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[text()='✕']")).click();
			driver.findElement(By.name("q")).sendKeys("nike shoes for men");
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			WebElement element = driver.findElement(By.xpath("(//a[@title='Downshifter 11 Running Shoes For Men'])[2]"));
			element.click();
			String parent = driver.getWindowHandle();
			Set<String> allwindow = driver.getWindowHandles();
			for (String child1 : allwindow) {
				if (!parent.equals(child1)) {
					driver.switchTo().window(child1);
					}}
			WebDriverWait waitu = new WebDriverWait(driver, Duration.ofSeconds(30));
			waitu.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("8")));
			driver.findElement(By.partialLinkText("8")).click();
			driver.findElement(By.xpath("//button[@type='button']")).click();
			driver.findElement(By.xpath("(//input[@type='radio'])[2]")).click();	
			driver.findElement(By.xpath("//button[contains(text(),'Deliver Here')]")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement until1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Cash on Delivery']")));
			until1.click();
			WebElement element3 = driver.findElement(By.xpath("((//div[@class ='_2jIO64 _3Uc2dx'])[17]//following::img)[1]"));
			File as = element3.getScreenshotAs(OutputType.FILE);
			File file = new File("C:\\Users\\et\\eclipse-workspace\\Augclass\\captchaimg");
			//FileUtils.copyFile(file, file)
			FileHandler.copy(as, file);
			ITesseract img = new Tesseract();	//captcha handling
			String doOCR = img.doOCR(file);
			System.out.println("Captcha handling done:");
			System.out.println(doOCR);
			driver.findElement(By.xpath("//input[@class='captcha']")).sendKeys(doOCR);
			
			driver.findElement(By.xpath("//button[@type='button']")).click();
			WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(30));
			WebElement wwait = wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Place Order']")));
			wwait.click();
			
		} catch (Exception e) {
			System.out.println("Exception captured:"+e);
		}
		finally {
			System.out.println("nike");
		}
	}
}
