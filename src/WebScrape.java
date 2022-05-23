import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScrape {
    public static void main(String[] args) {
    	ArrayList<ArrayList<ArrayList<String>>> stockdata = new ArrayList<ArrayList<ArrayList<String>>>();
    	ArrayList<String> stockList = new ArrayList<String>();
        stockList.add("https://au.investing.com/equities/apple-computer-inc-historical-data");
        stockList.add("https://au.investing.com/indices/nq-100-historical-data");
        stockList.add("https://au.investing.com/indices/us-spx-500-historical-data");
        
        try {
        	for (int i = 0; i < stockList.size(); i++) {
        		ArrayList<ArrayList<String>> stock = new ArrayList<ArrayList<String>>();
        		ArrayList<String> date = new ArrayList<String>();
        		ArrayList<String> price = new ArrayList<String>();
        		ArrayList<String> open = new ArrayList<String>();
        		ArrayList<String> high = new ArrayList<String>();
        		ArrayList<String> low = new ArrayList<String>();
        		ArrayList<String> vol = new ArrayList<String>();
        		ArrayList<String> change = new ArrayList<String>();
            	
        		final Document document = Jsoup.connect(stockList.get(i)).get();
                Elements table = document.select("table");
                Element tBody = table.get(1); //select the first table.
                Elements rows = tBody.select("tr");
                for (int j = 1; j < rows.size(); j++) { //first row is the col names so skip it.
                    Element row = rows.get(j);
                    Elements cols = row.select("td");
                    date.add(cols.get(0).text());
                    price.add(cols.get(1).text());
                    open.add(cols.get(2).text());
                    high.add(cols.get(3).text());
                    low.add(cols.get(4).text());
                    vol.add(cols.get(5).text());
                    change.add(cols.get(6).text());
                }
                stock.add(date);
                stock.add(price);
                stock.add(open);
                stock.add(high);
                stock.add(low);
                stock.add(vol);
                stock.add(change);
                stockdata.add(stock);
        	}
            
            System.out.println(stockdata.get(0));
            System.out.println(stockdata.get(1));
            System.out.println(stockdata.get(2));
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
