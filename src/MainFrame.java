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
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textStockName;
	private JTextField textStartDate;
	private JTextField textEndDate;

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
		panel_1.setBounds(10, 11, 302, 291);
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
		checkboxDate.setBounds(7, 105, 289, 23);
		panel_1.add(checkboxDate);
		
		JCheckBox checkboxPrice = new JCheckBox("                                      Price");
		checkboxPrice.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxPrice.setBounds(7, 131, 289, 23);
		panel_1.add(checkboxPrice);
		
		JCheckBox checkboxOpen = new JCheckBox("                                      Open");
		checkboxOpen.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxOpen.setBounds(7, 157, 289, 23);
		panel_1.add(checkboxOpen);
		
		JCheckBox checkboxHigh = new JCheckBox("                                      High");
		checkboxHigh.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxHigh.setBounds(7, 183, 289, 23);
		panel_1.add(checkboxHigh);
		
		JCheckBox checkboxLow = new JCheckBox("                                      Low");
		checkboxLow.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxLow.setBounds(7, 209, 289, 23);
		panel_1.add(checkboxLow);
		
		JCheckBox checkboxVol = new JCheckBox("                                      Vol");
		checkboxVol.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxVol.setBounds(7, 235, 289, 23);
		panel_1.add(checkboxVol);
		
		JCheckBox checkboxChange = new JCheckBox("                                      Change");
		checkboxChange.setHorizontalAlignment(SwingConstants.LEFT);
		checkboxChange.setBounds(7, 261, 289, 23);
		panel_1.add(checkboxChange);
		
		JComboBox timeFramecomboBox = new JComboBox();
		timeFramecomboBox.setModel(new DefaultComboBoxModel(new String[] {"Daily", "Weekly", "Monthly"}));
		timeFramecomboBox.setBounds(84, 75, 211, 23);
		panel_1.add(timeFramecomboBox);
		
		JLabel timeFrameLabel = new JLabel("Time Frame :");
		timeFrameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		timeFrameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timeFrameLabel.setBounds(6, 75, 76, 23);
		panel_1.add(timeFrameLabel);
		
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
