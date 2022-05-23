import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class Funtions {
	String startDate;
	String endDate;
	String dropDownValue;
	boolean isOk = false;
	boolean isLoading = true;
	int counter = 0;
	
	
	public void startCrawl_apple() {
		System.setProperty("webdriver.edge.driver", "D:\\SourceCode\\Java\\eclipse-workspace\\GUI_assginment\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://au.investing.com/equities/apple-computer-inc-historical-data");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		while(isLoading) {
			try{
				WebElement element = driver.findElement(By.xpath("//div[@id=\"PromoteSignUpPopUp\"]/div[@class=\"right\"]/i[@class=\"popupCloseIcon largeBannerCloser\"]"));
				if (element.isDisplayed() && element.isEnabled()) {
					element.click();
					isOk = true;
					isLoading = false;
				}
			}catch (NoSuchElementException e) {
			    System.out.println("Element is not present, hence not displayed as well");
			}
			System.out.println(counter);
			++counter;
			if(counter == 10000) {
				isOk = true;
				isLoading = false;
			}
			
		}
		if(isOk) {
				//set start date and end date 
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.id("widgetFieldDateRange")).click();
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.id("startDate")).clear();
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.id("endDate")).clear();
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.id("startDate")).sendKeys(startDate);
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.id("endDate")).sendKeys(endDate);
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.id("applyBtn")).click();
				//set dropdown menue 
				WebElement dropdown = driver.findElement(By.id("data_interval"));
				Select select = new Select(dropdown);
				select.selectByValue(dropDownValue);
	
		}
	}
	public void startCrawl_nasdaq100() {
		System.setProperty("webdriver.edge.driver", "D:\\SourceCode\\Java\\eclipse-workspace\\GUI_assginment\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://au.investing.com/indices/nq-100-historical-data");
	}
	public void startCrawl_sp500() {
		System.setProperty("webdriver.edge.driver", "D:\\SourceCode\\Java\\eclipse-workspace\\GUI_assginment\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://au.investing.com/indices/us-spx-500-historical-data");
	}
}
