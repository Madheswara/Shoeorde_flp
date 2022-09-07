package org.order;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class IrctcLogin {
	
	public static void main(String[] args) throws IOException, Exception {
		try {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.get("https://www.irctc.co.in");
			driver.manage().window().maximize();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//button[text()='OK']")).click();
			driver.findElement(By.xpath("//div[@class='h_menu_drop_button hidden-xs']")).click();
			driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
			driver.findElement(By.xpath("//input[@placeholder='User Name']")).sendKeys("madhesirct");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("12217779Am");
			WebDriverWait wait = new WebDriverWait(driver,30);
			WebElement element = driver.findElement(By.xpath("(//img[@border='0'])[2]"));
			wait.until(ExpectedConditions.visibilityOf(element));
			File scrsht = element.getScreenshotAs(OutputType.FILE);
			File path = new File("C:\\Users\\et\\eclipse-workspace\\Augclass\\captchaimg");
			FileHandler.copy(scrsht,path);
			ITesseract tess = new Tesseract();
			Thread.sleep(3000);
			String doOCR = tess.doOCR(path);
			System.out.println(doOCR);
//			WebElement element2 = driver.findElement(By.id("nlpAnswer"));
//			element2.sendKeys(doOCR);
//			driver.findElement(By.xpath("//button[text()='SIGN IN']")).click();
//			driver.close();
		}
		catch (Exception e) {
			System.out.println("Exception occure:"+e);
		}
	}
}
