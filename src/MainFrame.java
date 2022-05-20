import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textStockName;
	private JTextField textStartDate;
	private JTextField textEndDate;
	private JTextField textPassword;

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
		
		// orange panel
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(10, 11, 302, 30);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textUsername = new JTextField();
		textUsername.setBounds(6, 5, 143, 20);
		textUsername.setText("username");
		textUsername.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(textUsername);
		
		textPassword = new JTextField();
		textPassword.setText("password");
		textPassword.setHorizontalAlignment(SwingConstants.CENTER);
		textPassword.setBounds(152, 5, 143, 20);
		panel.add(textPassword);
		
		// green panel
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(10, 52, 302, 250);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textStockName = new JTextField();
		textStockName.setHorizontalAlignment(SwingConstants.CENTER);
		textStockName.setText("Stock name");
		textStockName.setBounds(6, 11, 289, 20);
		panel_1.add(textStockName);
		
		textStartDate = new JTextField();
		textStartDate.setHorizontalAlignment(SwingConstants.CENTER);
		textStartDate.setText("start date(mm/dd/yyyy)");
		textStartDate.setBounds(6, 36, 143, 20);
		panel_1.add(textStartDate);
		
		textEndDate = new JTextField();
		textEndDate.setHorizontalAlignment(SwingConstants.CENTER);
		textEndDate.setText("end date(mm/dd/yyyy)");
		textEndDate.setColumns(10);
		textEndDate.setBounds(152, 36, 143, 20);
		panel_1.add(textEndDate);
		
		JCheckBox checkboxDate = new JCheckBox("                                      Date");
		checkboxDate.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxDate.setBounds(6, 63, 289, 23);
		panel_1.add(checkboxDate);
		
		JCheckBox checkboxPrice = new JCheckBox("                                      Price");
		checkboxPrice.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxPrice.setBounds(6, 89, 289, 23);
		panel_1.add(checkboxPrice);
		
		JCheckBox checkboxOpen = new JCheckBox("                                      Open");
		checkboxOpen.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxOpen.setBounds(6, 115, 289, 23);
		panel_1.add(checkboxOpen);
		
		JCheckBox checkboxHigh = new JCheckBox("                                      High");
		checkboxHigh.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxHigh.setBounds(6, 141, 289, 23);
		panel_1.add(checkboxHigh);
		
		JCheckBox checkboxLow = new JCheckBox("                                      Low");
		checkboxLow.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxLow.setBounds(6, 167, 289, 23);
		panel_1.add(checkboxLow);
		
		JCheckBox checkboxVol = new JCheckBox("                                      Vol");
		checkboxVol.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxVol.setBounds(6, 193, 289, 23);
		panel_1.add(checkboxVol);
		
		JCheckBox checkboxChange = new JCheckBox("                                      Change");
		checkboxChange.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxChange.setBounds(6, 219, 289, 23);
		panel_1.add(checkboxChange);
		
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
		
		// buttons
		JButton startCrawlButton = new JButton("Start Crawling");
		startCrawlButton.setBounds(10, 313, 302, 96);
		contentPane.add(startCrawlButton);
		
		JButton saveCsvButton = new JButton("Save to csv file");
		saveCsvButton.setBounds(10, 417, 145, 23);
		contentPane.add(saveCsvButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.setBounds(167, 417, 145, 23);
		contentPane.add(resetButton);
	}
}
