package org.order;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
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

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class TesseractTest {
	public static void main(String[] args) throws IOException, Exception {
		try {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.get("https://www.irctc.co.in");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//button[text()='OK']")).click();
			driver.findElement(By.xpath("//div[@class='h_menu_drop_button hidden-xs']")).click();
			driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
			WebElement element = driver.findElement(By.xpath("(//div[@id='nlpImgContainer']//following::img)[2]"));
			File scrsht = element.getScreenshotAs(OutputType.FILE);
			File path = new File("C:\\Users\\et\\eclipse-workspace\\Augclass\\captchaimg");
			FileUtils.copyFile(scrsht, path);
			Thread.sleep(3000);
			ITesseract tess = new Tesseract();
			String doOCR = tess.doOCR(path);
			System.out.println(doOCR);
		}
		catch (Exception e) {
			System.out.println("Exception occure:"+e);
		}
	}

}
