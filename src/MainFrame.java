import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textStartDate;
	private JTextField textEndDate;

	boolean isSelected = false;
	String selectedStock = null;
	
	Funtions f = new Funtions();
	
//start GUI with error handler.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

// creating the frame.
	public MainFrame() {
		
		
		setTitle("StockCrawler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 490);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// green panel
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(10, 11, 302, 395);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textStartDate = new JTextField();
		textStartDate.setHorizontalAlignment(SwingConstants.CENTER);
		textStartDate.setText("start date(mm/dd/yyyy)");
		textStartDate.setBounds(6, 44, 143, 20);
		panel_1.add(textStartDate);
		
		textEndDate = new JTextField();
		textEndDate.setHorizontalAlignment(SwingConstants.CENTER);
		textEndDate.setText("end date(mm/dd/yyyy)");
		textEndDate.setColumns(10);
		textEndDate.setBounds(152, 44, 143, 20);
		panel_1.add(textEndDate);
		
		JCheckBox checkboxDate = new JCheckBox("                                      Date");
		checkboxDate.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxDate.setBounds(6, 209, 289, 23);
		panel_1.add(checkboxDate);
		
		JCheckBox checkboxPrice = new JCheckBox("                                      Price");
		checkboxPrice.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxPrice.setBounds(6, 235, 289, 23);
		panel_1.add(checkboxPrice);
		
		JCheckBox checkboxOpen = new JCheckBox("                                      Open");
		checkboxOpen.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxOpen.setBounds(6, 261, 289, 23);
		panel_1.add(checkboxOpen);
		
		JCheckBox checkboxHigh = new JCheckBox("                                      High");
		checkboxHigh.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxHigh.setBounds(6, 287, 289, 23);
		panel_1.add(checkboxHigh);
		
		JCheckBox checkboxLow = new JCheckBox("                                      Low");
		checkboxLow.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxLow.setBounds(6, 313, 289, 23);
		panel_1.add(checkboxLow);
		
		JCheckBox checkboxVol = new JCheckBox("                                      Vol");
		checkboxVol.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxVol.setBounds(6, 339, 289, 23);
		panel_1.add(checkboxVol);
		
		JCheckBox checkboxChange = new JCheckBox("                                      Change");
		checkboxChange.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxChange.setBounds(6, 365, 289, 23);
		panel_1.add(checkboxChange);
		
		JComboBox timeFrameCombobox = new JComboBox();
		timeFrameCombobox.setModel(new DefaultComboBoxModel(new String[] {"Daily", "Weekly", "Monthly"}));
		timeFrameCombobox.setBounds(84, 75, 211, 23);
		panel_1.add(timeFrameCombobox);
		
		JLabel timeFrameLabel = new JLabel("Time Frame :");
		timeFrameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		timeFrameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timeFrameLabel.setBounds(6, 75, 76, 23);
		panel_1.add(timeFrameLabel);
		
		JComboBox stocksCombobox = new JComboBox();
		stocksCombobox.setModel(new DefaultComboBoxModel(new String[] {"None", "Apple", "nasdaq 100 index", "S&P 500 index"}));
		stocksCombobox.setBounds(93, 11, 202, 22);
		panel_1.add(stocksCombobox);
		
		JLabel lblStockPrice = new JLabel("Select stock :");
		lblStockPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblStockPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStockPrice.setBounds(6, 11, 91, 23);
		panel_1.add(lblStockPrice);
		
		JButton startCrawlButton = new JButton("Start Crawling");
		startCrawlButton.setBounds(6, 106, 286, 96);
		panel_1.add(startCrawlButton);
		
		// gray panel
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(322, 11, 498, 429);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		// dataframe panel
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 23, 478, 395);
		panel_2.add(panel_3);
		
		JLabel previewLabel = new JLabel("Dataframe preview");
		previewLabel.setBackground(Color.YELLOW);
		previewLabel.setBounds(10, 5, 164, 14);
		panel_2.add(previewLabel);
		
		JButton saveCsvButton = new JButton("Save to csv file");
		saveCsvButton.setBounds(10, 417, 145, 23);
		contentPane.add(saveCsvButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.setBounds(167, 417, 145, 23);
		contentPane.add(resetButton);
		
		// event handing function
		stocksCombobox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if((String) stocksCombobox.getSelectedItem() == "Apple") {
						isSelected = true;
						selectedStock = "Apple";
					} else if((String) stocksCombobox.getSelectedItem() == "nasdaq 100 index") {
						isSelected = true;
						selectedStock = "nasdaq 100 index";
					} else if((String) stocksCombobox.getSelectedItem() == "S&P 500 index") {
						isSelected = true;
						selectedStock = "S&P 500 index";
					}
				} else {
					isSelected = false;
				}
				
			}
		}) ;
		
		startCrawlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(isSelected == true) {
						startCrawlButton.setText("crawling start");
						if(selectedStock == "Apple") {
							f.startDate = textStartDate.getText();
							f.endDate = textEndDate.getText();
							f.dropDownValue = timeFrameCombobox.getSelectedItem().toString();
							f.startCrawl_apple();
						} else if(selectedStock == "nasdaq 100 index") {
							f.startCrawl_nasdaq100();
						} else if(selectedStock == "S&P 500 index") {
							f.startCrawl_sp500();
						}
					} else {
						startCrawlButton.setText("you need to select stock");
					}
				}
			});
		
		
		// functions
		
	}
	
	

}
