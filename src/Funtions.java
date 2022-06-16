import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
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
	boolean avoidStale = false;
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
		while(!isScrapped) {
			try {
				try{
					WebElement element = driver.findElement(By.xpath("//div[@id=\"PromoteSignUpPopUp\"]/div[@class=\"right\"]/i[@class=\"popupCloseIcon largeBannerCloser\"]"));
					if (element.isDisplayed() && element.isEnabled()) {
						element.click();
					}
				}catch (NoSuchElementException e) {
					System.out.println(e);
				}
				// save table rows to the rows list
				while(!avoidStale) {
					try {
							List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"curr_table\"]/tbody/tr"));
							for(int i = 1; i <= rows.size(); i++) {
								try {
									// before crawl table data, check if there is popup ads or not / delete pop up. 
									NoSuchElementHandler(driver);
									// save table datas to the each list(date, price, open, high, low, vol and change lists).
									WebElement date = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 1 +"]"));
									dateList.add(date.getText());
									NoSuchElementHandler(driver);
									WebElement price = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 2 +"]"));
									priceList.add(price.getText());
									NoSuchElementHandler(driver);
									WebElement open = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 3 +"]"));
									openList.add(open.getText());
									NoSuchElementHandler(driver);
									WebElement high = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 4 +"]"));
									highList.add(high.getText());
									NoSuchElementHandler(driver);
									WebElement low = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 5 +"]"));
									lowList.add(low.getText());
									NoSuchElementHandler(driver);
									WebElement vol = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 6 +"]"));
									volList.add(vol.getText());
									NoSuchElementHandler(driver);
									WebElement change = driver.findElement(By.xpath("//*[@id=\"curr_table\"]/tbody/tr["+ i +"]/td["+ 7 +"]"));
									changeList.add(change.getText());
								} catch(NoSuchElementException e) {
									System.out.println("Row index error" + e);
								}
							}
							Collections.reverse(dateList);
							Collections.reverse(priceList);
							Collections.reverse(openList);
							Collections.reverse(highList);
							Collections.reverse(lowList);
							Collections.reverse(volList);
							Collections.reverse(changeList);
							isScrapped = true; 
							avoidStale = true;
					} catch(StaleElementReferenceException e) {
						System.out.println(e);
					}
				}
				} catch(NoSuchElementException e) {
					System.out.println(e);
				}
			}
		}
	
		public void initializer() {
			//set values to empty
			startDate = null;
			endDate = null;
			dropDownValue = null;
			selectedStock = null;
			url = null;
			isScrapped = false;
			isFinished = false;
			avoidStale = false;
			dateList.clear();
			priceList.clear();
			openList.clear();
			highList.clear();
			lowList.clear();
			volList.clear();
			changeList.clear();
			getModel.setRowCount(0);
		}
		
		public void addColumnToTable() {
			Object columnData[] = new Object[7];
			for(int i = 0; i < dateList.size(); i++) {
				// make data with pure number
				String dateListData = dateList.get(i);
				dateListData = dateListData.replaceAll(",","");
				if(dateListData.contains("Jan")) {
					dateListData = dateListData.replaceAll("Jan","01");
				}else if(dateListData.contains("Feb")) {
					dateListData = dateListData.replaceAll("Jan","02");
				}else if(dateListData.contains("Mar")) {
					dateListData = dateListData.replaceAll("Mar","03");
				}else if(dateListData.contains("Apr")) {
					dateListData = dateListData.replaceAll("Apr","04");
				}else if(dateListData.contains("May")) {
					dateListData = dateListData.replaceAll("May","05");
				}else if(dateListData.contains("Jun")) {
					dateListData = dateListData.replaceAll("Jun","06");
				}else if(dateListData.contains("Jul")) {
					dateListData = dateListData.replaceAll("Jul","07");
				}else if(dateListData.contains("Aug")) {
					dateListData = dateListData.replaceAll("Aug","08");
				}else if(dateListData.contains("Sep")) {
					dateListData = dateListData.replaceAll("Sep","09");
				}else if(dateListData.contains("Oct")) {
					dateListData = dateListData.replaceAll("Oct","10");
				}else if(dateListData.contains("Nov")) {
					dateListData = dateListData.replaceAll("Nov","11");
				}else if(dateListData.contains("Dec")) {
					dateListData = dateListData.replaceAll("Dec","12");
				}
				dateListData = reverseBySpace(dateListData);
				
				String volListData = volList.get(i);
				volListData = volListData.replaceAll("M","");
				
				String changeListData = changeList.get(i);
				changeListData = changeListData.replaceAll("%","");
				
				String priceListData = priceList.get(i);
				priceListData = priceListData.replaceAll(",","");
				
				String openListData = openList.get(i);
				openListData = openListData.replaceAll(",","");
				
				String highListData = highList.get(i);
				highListData = highListData.replaceAll(",","");
				
				String lowListData = lowList.get(i);
				lowListData = lowListData.replaceAll(",","");
				// add to the stockPriceHistory table
				columnData[0] = dateListData;
				columnData[1] = priceListData;
				columnData[2] = openListData;
				columnData[3] = highListData;
				columnData[4] = lowListData;
				columnData[5] = volListData;
				columnData[6] = changeListData;
				getModel.addRow(columnData);
			}
		}
		
		public String reverseBySpace(String str) {
			// Specifying the pattern to be searched
	        Pattern pattern = Pattern.compile("\\s");
	  
	        // splitting str with a pattern
	        String[] temp = pattern.split(str);
	        String result = "";
	        String month = "";
	        String day = "";
	        String year = "";
	  
	        month = temp[0];
	        day = temp[1];
	        year = temp[2];
	        result = year + "-" + month + "-" + day;

	        return result;
		}
		
		public void saveCsv(TableModel getModel) {
		    try {
		        FileWriter csv = new FileWriter(new File("D:\\SourceCode\\Java\\eclipse-workspace\\GUI_assginment\\stockHistory.csv"));
		        for (int i = 0; i < getModel.getColumnCount(); i++) {
		            csv.write(getModel.getColumnName(i) + ",");
		        }
		        csv.write("\n");
		        for (int i = 0; i < getModel.getRowCount(); i++) {
		            for (int j = 0; j < getModel.getColumnCount(); j++) {
		                csv.write(getModel.getValueAt(i, j).toString() + ",");
		            }
		            csv.write("\n");
		        }
		        csv.close();
		    } catch (IOException e) {
		       System.out.println(e);
		    }
		}
		
		public void NoSuchElementHandler(WebDriver driver) {
			try{
				WebElement element = driver.findElement(By.xpath("//div[@id=\"PromoteSignUpPopUp\"]/div[@class=\"right\"]/i[@class=\"popupCloseIcon largeBannerCloser\"]"));
				if (element.isDisplayed() && element.isEnabled()) {
					element.click();
				}
			}catch (NoSuchElementException e) {
				System.out.println(e);
			}
		}
	}
	
	
