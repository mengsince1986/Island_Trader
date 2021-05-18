package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import main.GUIGameEnvironment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoreWindow {

	private JFrame frame;
	private GUIGameEnvironment manager;
	private final ButtonGroup saleItembuttonGroup = new ButtonGroup();
	private final ButtonGroup purchaseItembuttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreWindow window = new StoreWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	public StoreWindow(GUIGameEnvironment incomingManager) {
		this.manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeStoreWindow() {
		frame.dispose();
	}
	
	public void finishedStoreWindow() {
		this.manager.closeStoreWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 686, 1089);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel storeLabel = new JLabel("Store");
		storeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		storeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		storeLabel.setBounds(295, 12, 70, 15);
		frame.getContentPane().add(storeLabel);
		
		JTextArea reportText = new JTextArea();
		reportText.setText("Trading report");
		reportText.setFont(new Font("Dialog", Font.BOLD, 14));
		reportText.setLineWrap(true);
		reportText.setWrapStyleWord(true);
		reportText.setBounds(40, 127, 600, 559);
		frame.getContentPane().add(reportText);
		
		JLabel NameLabel = new JLabel("Name: ");
		NameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		NameLabel.setBounds(59, 58, 70, 15);
		frame.getContentPane().add(NameLabel);
		
		JLabel nameDisplayLabel = new JLabel("<dynamic>");
		nameDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		nameDisplayLabel.setBounds(128, 58, 200, 15);
		frame.getContentPane().add(nameDisplayLabel);
		
		JLabel DaysLabel = new JLabel("Remaining Days:");
		DaysLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		DaysLabel.setBounds(348, 58, 150, 15);
		frame.getContentPane().add(DaysLabel);
		
		JLabel DaysDisplayLabel = new JLabel("0");
		DaysDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		DaysDisplayLabel.setBounds(498, 58, 140, 15);
		frame.getContentPane().add(DaysDisplayLabel);
		
		JLabel moneyLabel = new JLabel("Money: ");
		moneyLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		moneyLabel.setBounds(59, 89, 70, 15);
		frame.getContentPane().add(moneyLabel);
		
		JLabel moneyDisplayLabel = new JLabel("0");
		moneyDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		moneyDisplayLabel.setBounds(128, 89, 140, 15);
		frame.getContentPane().add(moneyDisplayLabel);
		
		JLabel locationLabel = new JLabel("Location: ");
		locationLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		locationLabel.setBounds(348, 89, 85, 15);
		frame.getContentPane().add(locationLabel);
		
		JLabel locationDisplayLabel = new JLabel("<dynamic>");
		locationDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		locationDisplayLabel.setBounds(438, 89, 200, 15);
		frame.getContentPane().add(locationDisplayLabel);
		
	// update status labels
		
		String name = manager.getTrader().getName();
		int days = manager.getTrader().getRemainingDays();
		int money = manager.getTrader().getOwnedMoney();
		String islandLocation =manager.getTrader().getCurrentIsland().getName();
		
		nameDisplayLabel.setText(name);
		DaysDisplayLabel.setText(String.valueOf(days));
		moneyDisplayLabel.setText(String.valueOf(money));
		locationDisplayLabel.setText(islandLocation);
		// ====================
		
		JButton buyButton = new JButton("Buy");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buyButton.setBackground(new Color(204, 153, 102));
		buyButton.setBounds(40, 747, 117, 25);
		frame.getContentPane().add(buyButton);
		
		JButton sellButton = new JButton("Sell");
		sellButton.setBackground(new Color(204, 153, 102));
		sellButton.setBounds(40, 808, 117, 25);
		frame.getContentPane().add(sellButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 788, 600, 2);
		frame.getContentPane().add(separator);
		
		JRadioButton saleItem1Button = new JRadioButton("N/A");
		saleItembuttonGroup.add(saleItem1Button);
		saleItem1Button.setSelected(true);
		saleItem1Button.setBounds(198, 749, 120, 23);
		frame.getContentPane().add(saleItem1Button);
		
		JRadioButton saleItem2Button = new JRadioButton("N/A");
		saleItembuttonGroup.add(saleItem2Button);
		saleItem2Button.setSelected(true);
		saleItem2Button.setBounds(339, 749, 120, 23);
		frame.getContentPane().add(saleItem2Button);
		
		JRadioButton saleItem3Button = new JRadioButton("N/A");
		saleItembuttonGroup.add(saleItem3Button);
		saleItem3Button.setSelected(true);
		saleItem3Button.setBounds(481, 749, 120, 23);
		frame.getContentPane().add(saleItem3Button);
		
		JRadioButton purchaseItem1Button = new JRadioButton("N/A");
		purchaseItembuttonGroup.add(purchaseItem1Button);
		purchaseItem1Button.setSelected(true);
		purchaseItem1Button.setBounds(198, 810, 120, 23);
		frame.getContentPane().add(purchaseItem1Button);
		
		JRadioButton purchaseItem2Button = new JRadioButton("N/A");
		purchaseItembuttonGroup.add(purchaseItem2Button);
		purchaseItem2Button.setSelected(true);
		purchaseItem2Button.setBounds(339, 810, 120, 23);
		frame.getContentPane().add(purchaseItem2Button);
		
		JRadioButton purchaseItem3Button = new JRadioButton("N/A");
		purchaseItembuttonGroup.add(purchaseItem3Button);
		purchaseItem3Button.setSelected(true);
		purchaseItem3Button.setBounds(481, 810, 120, 23);
		frame.getContentPane().add(purchaseItem3Button);
		
		JButton portButton = new JButton("Go to Port");
		portButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				finishedStoreWindow();
				
			}
		});
		portButton.setBounds(523, 938, 117, 25);
		frame.getContentPane().add(portButton);
	}

}
