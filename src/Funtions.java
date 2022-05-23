import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Funtions {
	public void startCrawl_apple() {
		System.setProperty("webdriver.edge.driver", "D:\\SourceCode\\Java\\eclipse-workspace\\GUI_assginment\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://au.investing.com/equities/apple-computer-inc-historical-data");
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
