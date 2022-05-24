import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class Funtions {
	String startDate;
	String endDate;
	String dropDownValue;
	String selectedStock;
	String url;
	boolean isScrapped = false;
	boolean isFinished = false;
	int counter = 0;
	ArrayList<String> dateList = new ArrayList<String>();
	ArrayList<String> priceList = new ArrayList<String>();
	ArrayList<String> openList = new ArrayList<String>();
	ArrayList<String> highList = new ArrayList<String>();
	ArrayList<String> lowList = new ArrayList<String>();
	ArrayList<String> volList = new ArrayList<String>();
	ArrayList<String> changeList = new ArrayList<String>();
	
	WebDriver driver1;
	
	public void startCrawl() {
		if(selectedStock == "Apple") {
			url = "https://au.investing.com/equities/apple-computer-inc-historical-data";
		} else if(selectedStock == "nasdaq 100 index") {
			url = "https://au.investing.com/indices/nq-100-historical-data";
		} else if(selectedStock == "S&P 500 index") {
			url = "https://au.investing.com/indices/us-spx-500-historical-data";
		}
		System.setProperty("webdriver.edge.driver", "D:\\SourceCode\\Java\\eclipse-workspace\\GUI_assginment\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		while(!isFinished) {
			try {
				try{
					WebElement element = driver.findElement(By.xpath("//div[@id=\"PromoteSignUpPopUp\"]/div[@class=\"right\"]/i[@class=\"popupCloseIcon largeBannerCloser\"]"));
					if (element.isDisplayed() && element.isEnabled()) {
						element.click();
					}
				}catch (NoSuchElementException e) {
					System.out.println(e);
				}
				webClicker(driver);
			} catch(ElementClickInterceptedException e){
				System.out.println(e);
			}
		}
		
		initializer();
		
		System.out.println(dateList);
		System.out.println(priceList);
		System.out.println(openList);
		System.out.println(highList);
		System.out.println(lowList);
		System.out.println(volList);
		System.out.println(changeList);
	}
	
	public void webClicker(WebDriver driver) {
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
		//scraping table data from the site
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while(!isScrapped) {					
			try{
				webScrape(driver);
			}catch (IndexOutOfBoundsException e) {
				System.out.println("IndexOutOfBoundsException");
			}
		}
		isFinished = true;
	}
	
	public void webScrape(WebDriver driver) {
			List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"curr_table\"]/tbody/tr"));
			while(!isScrapped) {
				for(int i = 1; i <= rows.size(); i++) {
					WebElement date = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 1 +"]"));
					dateList.add(date.getText());
					WebElement price = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 2 +"]"));
					priceList.add(price.getText());
					WebElement open = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 3 +"]"));
					openList.add(open.getText());
					WebElement high = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 4 +"]"));
					highList.add(high.getText());
					WebElement low = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 5 +"]"));
					lowList.add(low.getText());
					WebElement vol = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 6 +"]"));
					volList.add(vol.getText());
					WebElement change = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 7 +"]"));
					changeList.add(change.getText());
				}
				isScrapped = true; 
			}
		}
	
		public void initializer() {
			//set values to empty
			startDate = null;
			endDate = null;
			dropDownValue = null;
			isScrapped = false;
		}
	}
	
	
