package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import main.GUIGameEnvironment;
import map.Route;

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
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JSlider;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.ChangeListener;

import items.Item;

import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;

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
		reportText.setEditable(false);
		reportText.setText("Trading report");
		reportText.setFont(new Font("Dialog", Font.BOLD, 14));
		reportText.setLineWrap(true);
		reportText.setWrapStyleWord(true);
		reportText.setBounds(40, 127, 600, 559);
		frame.getContentPane().add(reportText);
		
		// update store report when initializing
		String report = "Welcome to the store!\n\n";
		report += manager.getTrader().getCurrentIsland().getStore().forSale() + "\n\n";
		report += "******************************\n\n";
		report += manager.getTrader().getCurrentIsland().getStore().forPurchase();
		reportText.setText(report);
		//======================================
		
		
		JScrollPane scrollPane = new JScrollPane(reportText);
		scrollPane.setBounds(40, 116, 600, 569);
		frame.getContentPane().add(scrollPane);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
			       scrollPane.getVerticalScrollBar().setValue(0);
			   }
			});
	
		
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
		
	
		
		JRadioButton saleItem1Button = new JRadioButton("N/A");
		saleItembuttonGroup.add(saleItem1Button);
		saleItem1Button.setSelected(true);
		saleItem1Button.setBounds(198, 725, 120, 23);
		frame.getContentPane().add(saleItem1Button);
		
		JRadioButton saleItem2Button = new JRadioButton("N/A");
		saleItembuttonGroup.add(saleItem2Button);
		saleItem2Button.setBounds(339, 725, 120, 23);
		frame.getContentPane().add(saleItem2Button);
		
		JRadioButton saleItem3Button = new JRadioButton("N/A");
		saleItembuttonGroup.add(saleItem3Button);
		saleItem3Button.setBounds(481, 725, 120, 23);
		frame.getContentPane().add(saleItem3Button);
		
		// update sale list when initializing
		ArrayList<String> saleItems = new ArrayList<String>(); 
		for (Item item : manager.getTrader().getCurrentIsland().getStore().getToSell()) {
			saleItems.add(item.getName());
		}

		int i = 0;
		Enumeration<AbstractButton> saleItemButtons = saleItembuttonGroup.getElements();
		while (i < saleItems.size() && saleItemButtons.hasMoreElements()) {
			JRadioButton saleItemButton = (JRadioButton) saleItemButtons.nextElement();
			
			saleItemButton.setText(saleItems.get(i));
			
			i += 1;
		}
		//===================================
		
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 760, 600, 2);
		frame.getContentPane().add(separator);
		
	
		JRadioButton purchaseItem1Button = new JRadioButton("N/A");
		purchaseItembuttonGroup.add(purchaseItem1Button);
		purchaseItem1Button.setSelected(true);
		purchaseItem1Button.setBounds(198, 778, 120, 23);
		frame.getContentPane().add(purchaseItem1Button);
		
		JRadioButton purchaseItem2Button = new JRadioButton("N/A");
		purchaseItembuttonGroup.add(purchaseItem2Button);
		purchaseItem2Button.setBounds(339, 778, 120, 23);
		frame.getContentPane().add(purchaseItem2Button);
		
		JRadioButton purchaseItem3Button = new JRadioButton("N/A");
		purchaseItembuttonGroup.add(purchaseItem3Button);
		purchaseItem3Button.setBounds(481, 778, 120, 23);
		frame.getContentPane().add(purchaseItem3Button);
		
		// update purchase list when initializing
		ArrayList<String> purchaseItems = new ArrayList<String>(); 
		for (Item item : manager.getTrader().getCurrentIsland().getStore().getToBuy()) {
			purchaseItems.add(item.getName());
		}

		int j = 0;
		Enumeration<AbstractButton> purchaseItemButtons = purchaseItembuttonGroup.getElements();
		while (j < purchaseItems.size() && purchaseItemButtons.hasMoreElements()) {
			JRadioButton purchaseItemButton = (JRadioButton) purchaseItemButtons.nextElement();
			
			purchaseItemButton.setText(purchaseItems.get(j));
			
			j += 1;
		}
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(40, 813, 600, 2);
		frame.getContentPane().add(separator_1);
		//===================================
		
		JSlider tradeQuantitySlider = new JSlider();
		
		
		tradeQuantitySlider.setValue(15);
		tradeQuantitySlider.setSnapToTicks(true);
		tradeQuantitySlider.setMajorTickSpacing(1);
		tradeQuantitySlider.setMinimum(1);
		tradeQuantitySlider.setMaximum(200);
		tradeQuantitySlider.setBounds(201, 833, 400, 20);
		frame.getContentPane().add(tradeQuantitySlider);
		
		JLabel tradeQuantityLable = new JLabel("Trading Quantity :");
		tradeQuantityLable.setFont(new Font("Dialog", Font.BOLD, 14));
		tradeQuantityLable.setLabelFor(tradeQuantitySlider);
		tradeQuantityLable.setBounds(40, 830, 150, 20);
		frame.getContentPane().add(tradeQuantityLable);
		
		
		JLabel QuantityLabel = new JLabel("N/A");
		QuantityLabel.setLabelFor(tradeQuantitySlider);
		QuantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		QuantityLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		QuantityLabel.setBounds(371, 856, 70, 20);
		frame.getContentPane().add(QuantityLabel);
		QuantityLabel.setText(String.valueOf(tradeQuantitySlider.getValue())); 
		
		
		tradeQuantitySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				QuantityLabel.setText(String.valueOf(tradeQuantitySlider.getValue()));
			}
		});
		
		JButton portButton = new JButton("Go to Port");
		portButton.setFont(new Font("Dialog", Font.BOLD, 14));
		portButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				finishedStoreWindow();
				
			}
		});
		
		
		JButton buyButton = new JButton("Buy");
		buyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// get selected sale item
				String saleItemName = "";
				
				Enumeration<AbstractButton> items = saleItembuttonGroup.getElements();
				while (items.hasMoreElements()) {
					JRadioButton item = (JRadioButton) items.nextElement();
					if(item.isSelected()) {
						saleItemName = item.getText();
					}
				}
				
				// get quantity
				int quantity = tradeQuantitySlider.getValue();
				
				
				// call manager to implement buy method
				if (saleItemName == "N/A") {
					
					reportText.setText("The item you choose does not exist,\n"
								     + "please choose your item again.");
					
				} else {
					
					String report = manager.buy(saleItemName, quantity);
					reportText.setText(report);
					}
				
			}
		});
		buyButton.setFont(new Font("Dialog", Font.BOLD, 14));
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buyButton.setBackground(new Color(204, 153, 102));
		buyButton.setBounds(40, 723, 117, 25);
		frame.getContentPane().add(buyButton);
		
		JButton sellButton = new JButton("Sell");
		sellButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// get selected purchase item
				String purchaseItemName = "";
				
				Enumeration<AbstractButton> items = purchaseItembuttonGroup.getElements();
				while (items.hasMoreElements()) {
					JRadioButton item = (JRadioButton) items.nextElement();
					if(item.isSelected()) {
						purchaseItemName = item.getText();
					}
				}
				
				// get quantity
				int quantity = tradeQuantitySlider.getValue();
				
				
				// call manager to implement sell method
				if (purchaseItemName == "N/A") {
					
					reportText.setText("The item you choose does not exist,\n"
								     + "please choose your item again.");
					
				} else {
					
					String report = manager.sell(purchaseItemName, quantity);
					reportText.setText(report);
					}
				
			}
		});
		sellButton.setFont(new Font("Dialog", Font.BOLD, 14));
		sellButton.setBackground(new Color(204, 153, 102));
		sellButton.setBounds(40, 776, 117, 25);
		frame.getContentPane().add(sellButton);
		
		
		portButton.setBounds(523, 938, 117, 25);
		frame.getContentPane().add(portButton);
		
		JButton viewStoreButton = new JButton("View Store List");
		viewStoreButton.setBackground(new Color(204, 153, 102));
		viewStoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewStoreButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String report = "Welcome to the store!\n\n";
				report += manager.getTrader().getCurrentIsland().getStore().forSale() + "\n\n";
				report += "******************************\n\n";
				report += manager.getTrader().getCurrentIsland().getStore().forPurchase();
				reportText.setText(report);
				
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					   public void run() { 
					       scrollPane.getVerticalScrollBar().setValue(0);
					   }
					});
			}
		});
		viewStoreButton.setFont(new Font("Dialog", Font.BOLD, 14));
		viewStoreButton.setBounds(40, 938, 200, 25);
		frame.getContentPane().add(viewStoreButton);
		
		JButton viewTradingLogsButton = new JButton("View Trading Logs");
		viewTradingLogsButton.setBackground(new Color(204, 153, 102));
		viewTradingLogsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String report = "Here are your trading records";
				report += manager.getTrader().getTradingLogsString();
				reportText.setText(report);
				
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					   public void run() { 
					       scrollPane.getVerticalScrollBar().setValue(0);
					   }
					});
			}
		});
		viewTradingLogsButton.setFont(new Font("Dialog", Font.BOLD, 14));
		viewTradingLogsButton.setBounds(40, 987, 200, 25);
		frame.getContentPane().add(viewTradingLogsButton);
		
		JButton viewShipCargoesButton = new JButton("View Ship Cargoes");
		viewShipCargoesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String report = "";
				report += manager.getTrader().getOwndedShip().getCargosString();
				reportText.setText(report);
				
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					   public void run() { 
					       scrollPane.getVerticalScrollBar().setValue(0);
					   }
					});
			}
		});
		viewShipCargoesButton.setBackground(new Color(204, 153, 102));
		viewShipCargoesButton.setFont(new Font("Dialog", Font.BOLD, 14));
		viewShipCargoesButton.setBounds(264, 938, 200, 25);
		frame.getContentPane().add(viewShipCargoesButton);
	}
}
