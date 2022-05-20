import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScrape {
    public static void main(String[] args) {
    	List<String> date = new ArrayList<String>();
    	List<String> price = new ArrayList<String>();
    	List<String> open = new ArrayList<String>();
    	List<String> high = new ArrayList<String>();
    	List<String> low = new ArrayList<String>();
    	List<String> vol = new ArrayList<String>();
    	List<String> change = new ArrayList<String>();
        final String url = 
                "https://au.investing.com/equities/apple-computer-inc-historical-data";
        
        try {
            final Document document = Jsoup.connect(url).get();
            Elements table = document.select("table");
            Element tBody = table.get(1); //select the first table.
            Elements rows = tBody.select("tr");
            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                Elements cols = row.select("td");
                date.add(cols.get(0).text());
                price.add(cols.get(1).text());
                open.add(cols.get(2).text());
                high.add(cols.get(3).text());
                low.add(cols.get(4).text());
                vol.add(cols.get(5).text());
                change.add(cols.get(6).text());
            }
            System.out.println(date);
            System.out.println(price);
            System.out.println(open);
            System.out.println(high);
            System.out.println(low);
            System.out.println(vol);
            System.out.println(change);
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
