import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.swing.table.DefaultTableModel;

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
	ArrayList<String> dateList = new ArrayList<String>();
	ArrayList<String> priceList = new ArrayList<String>();
	ArrayList<String> openList = new ArrayList<String>();
	ArrayList<String> highList = new ArrayList<String>();
	ArrayList<String> lowList = new ArrayList<String>();
	ArrayList<String> volList = new ArrayList<String>();
	ArrayList<String> changeList = new ArrayList<String>();
	
	DefaultTableModel getModel;
	
	public void startCrawl() {
		// select stock history by GUI dropdown menu
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
		
		// if the site popup ads open, close and activate webClicker function.
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
		
		// table setup
		addColumnToTable();
		
		// initiate global values.
		initializer();
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
		//set dropdown menu
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
			// save table rows to the rows list
			List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"curr_table\"]/tbody/tr"));
			while(!isScrapped) {
				for(int i = 1; i <= rows.size(); i++) {
					// save table datas to the each list(date, price, open, high, low, vol and change lists).
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
				Collections.reverse(dateList);
				Collections.reverse(priceList);
				Collections.reverse(openList);
				Collections.reverse(highList);
				Collections.reverse(lowList);
				Collections.reverse(volList);
				Collections.reverse(changeList);
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
		
		public void addColumnToTable() {
			Object columnData[] = new Object[7];
			for(int i = 0; i < dateList.size(); i++) {
				columnData[0] = dateList.get(i);
				columnData[1] = priceList.get(i);
				columnData[2] = openList.get(i);
				columnData[3] = highList.get(i);
				columnData[4] = lowList.get(i);
				columnData[5] = volList.get(i);
				columnData[6] = changeList.get(i);
				getModel.addRow(columnData);
			}
		}
	}
	
	
