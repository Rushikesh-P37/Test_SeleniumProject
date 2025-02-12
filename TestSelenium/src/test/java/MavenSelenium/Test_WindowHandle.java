package MavenSelenium;

import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test_WindowHandle {

	@Test
	public void muiltipleWindowHandle() throws InterruptedException {
		//System.getProperty("user.dir");
		//System.setProperty("webdriver.driver.chrome", "/Drivers/chromedriver-win32/chromedriver.exe");

		String path ="C:\\Users\\admin\\git\\repository\\TestSelenium\\Drivers";
		System.setProperty("webdriver.chrome.driver", path+"/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.google.com");
		String parent_window = driver.getWindowHandle();

		driver.switchTo().newWindow(WindowType.TAB);

		driver.get("https://www.javatpoint.com/");
//		String child_window = driver.getWindowHandle();

		String copiedText = "";

		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> itr = tabs.iterator();

		while (itr.hasNext()) 
		{
			String chil_window = itr.next();
			
			if (!parent_window.equals(chil_window)) 
			{
				driver.switchTo().window(chil_window);
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@onclick,'closePopup()')]"))).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Java"))).click();
				
				Thread.sleep(2000);
				WebElement element =  driver.findElement(By.xpath("//div[@id=\"bottomnextup\"]/../h1"));
				copiedText = element.getText();
				System.out.println("Extracted text: "+copiedText);
				//driver.close();
			}
		}
		driver.switchTo().window(parent_window);
		WebElement textFiled = driver.findElement(By.name("q"));
		textFiled.sendKeys(copiedText);
		System.out.println("Pasted text: "+copiedText);
	}

	}
