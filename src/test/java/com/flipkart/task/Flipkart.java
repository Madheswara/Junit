package com.flipkart.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class Flipkart {
	static WebDriver driver;
	static String text;
@BeforeClass
public static void login() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("https://www.flipkart.com/");
}
@AfterClass
	public static void quitbr() {
    driver.quit();
}
//@Before
//public void beforem() {
//	
//}
//@After
//public void afterm() {
//	
//}
@Test
public void method1() {
	driver.findElement(By.xpath("//button[text()='✕']")).click();
}
@Test
public void method2() throws Exception {
	WebDriverWait wait = new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title='Search for products, brands and more']")));
	driver.findElement(By.name("q")).sendKeys("apple mobiles");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Thread.sleep(2000);
	List<WebElement> elements = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
	for (int i = 0; i < elements.size(); i++) {
		WebElement gett = elements.get(i);
		text = gett.getText();
		System.out.println(text);
	}
		File file = new File("C:\\Users\\et\\eclipse-workspace\\JunitTask1\\src\\test\\resources\\excel\\task1.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet Sheet = wb.createSheet("mobilelist");
		Sheet.createRow(0).createCell(0).setCellValue(text);
		try {
			FileOutputStream fo = new FileOutputStream(file);
			wb.write(fo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

@Test
public void method3() {
	driver.findElement(By.xpath("(//div[@class='_4rR01T'])[2]")).click();
	String parent = driver.getWindowHandle();
	Set<String> all = driver.getWindowHandles();
	for (String child : all) {
		if (!parent.equals(child)) {
			driver.switchTo().window(child);
		}
	}
}
@Test
public void method4() throws Exception {
	WebElement element2 = driver.findElement(By.xpath("//span[@class='B_NuCI']"));
	String text1 = element2.getText();
	System.out.println(text1);
	try {
		FileInputStream fi = new FileInputStream("C:\\Users\\et\\eclipse-workspace\\JunitTask1\\src\\test\\resources\\excel\\task1.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet sheetAt = wb.getSheetAt(0);
		XSSFRow row = sheetAt.getRow(1);
		XSSFCell cell = row.getCell(0);
		System.out.println(cell);
		String value = cell.getStringCellValue();
		System.out.println(value);
		
		Assert.assertEquals(text1, value);
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}}
@Test
public void method5() throws Exception {
	JavascriptExecutor jss = (JavascriptExecutor)driver;
	WebElement element3 = driver.findElement(By.xpath("//div[text()='Specifications']"));
	jss.executeScript("arguments[0].scrollintoview(true)",element3);
	TakesScreenshot tss = (TakesScreenshot)driver;
	File as = tss.getScreenshotAs(OutputType.FILE);
	File Despath = new File("C:\\Users\\et\\eclipse-workspace\\JunitTask1\\src\\test\\resources\\ssc");
	FileUtils.copyFile(as, Despath);
	
}
}