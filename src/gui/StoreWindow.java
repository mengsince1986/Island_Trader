package gui;

import javax.swing.JFrame;

import main.GUIGameEnvironment;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
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
import javax.swing.event.ChangeListener;

import items.Item;

import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;

/**
 * The StoreWindow is one of the the GUI interface objects for running the game.
 * It provides all the available command options at store, gets users choices
 * and calls the related methods through the attribute manager -
 * {@link GUIGameEnvironment} object - to change the state of the game.
 * <p>
 * The StoreWindow also updates the status bar and prints the report after the
 * implementation of each command.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class StoreWindow {

	/**
	 * Attribute storeFrame stores the Frame object of this window.
	 */
	private JFrame storeFrame;

	/**
	 * Attribute saleItemButtonGroup stores a ButtonGroup object including all the
	 * RadioButtons with the name of the available items on sale.
	 */
	private final ButtonGroup saleItembuttonGroup = new ButtonGroup();

	/**
	 * Attribute purchaseItemButtonGroup stores a ButtonGroup object including all
	 * the RadioButtons with the name of the available items to purchase.
	 */
	private final ButtonGroup purchaseItembuttonGroup = new ButtonGroup();

	/**
	 * Attribute manager is the {@link GUIGameEnvironment} object which maintains
	 * the state of the program and makes instances of window classes.
	 */
	private GUIGameEnvironment manager;

	// This static main method is only used for initializing this window frame
	// individually for testing
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { StoreWindow window = new
	 * StoreWindow(); window.frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * This constructor sets the value of manager attribute, initializes this Frame
	 * object and sets this frame to be visible.
	 * 
	 * @param incomingManager the current {@link GUIGameEnvironment} object
	 */
	public StoreWindow(GUIGameEnvironment incomingManager) {
		this.manager = incomingManager;
		initialize();
		storeFrame.setVisible(true);
	}

	/**
	 * This method disposes this window frame.
	 */
	public void closeStoreWindow() {
		storeFrame.dispose();
	}

	/**
	 * This method closes this window frame and launches the PortWindow frame by
	 * calling the closeStoreWindow method from manager which is the current
	 * {@link GUIGameEnvironment} object.
	 */
	public void finishedStoreWindow() {
		this.manager.closeStoreWindow(this);
	}

	/**
	 * This method initializes the contents of the frame.
	 */
	private void initialize() {
		storeFrame = new JFrame();
		storeFrame.setTitle("Island Trader - STORE");
		storeFrame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 14));
		storeFrame.setBounds(100, 100, 686, 795);
		storeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		storeFrame.getContentPane().setLayout(null);

		JLabel storeLabel = new JLabel("Store");
		storeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		storeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		storeLabel.setBounds(295, 12, 70, 15);
		storeFrame.getContentPane().add(storeLabel);

		// Report text area
		JTextArea reportText = new JTextArea();
		reportText.setEditable(false);
		reportText.setText("Trading report");
		reportText.setFont(new Font("Dialog", Font.BOLD, 14));
		reportText.setLineWrap(true);
		reportText.setWrapStyleWord(true);
		reportText.setBounds(40, 127, 600, 559);
		storeFrame.getContentPane().add(reportText);

		// update store report when initializing
		String report = "Welcome to the store, Captain!\n\n";
		report += manager.getListForSale() + "\n\n";
		report += "******************************\n\n";
		report += manager.getListToPurchase();
		reportText.setText(report);
		// ======================================

		JScrollPane scrollPane = new JScrollPane(reportText);
		scrollPane.setBounds(40, 116, 600, 300);
		storeFrame.getContentPane().add(scrollPane);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		});

		JLabel NameLabel = new JLabel("Name: ");
		NameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		NameLabel.setBounds(59, 58, 70, 15);
		storeFrame.getContentPane().add(NameLabel);

		JLabel nameDisplayLabel = new JLabel("<dynamic>");
		nameDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		nameDisplayLabel.setBounds(128, 58, 200, 15);
		storeFrame.getContentPane().add(nameDisplayLabel);

		JLabel DaysLabel = new JLabel("Remaining Days:");
		DaysLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		DaysLabel.setBounds(348, 58, 150, 15);
		storeFrame.getContentPane().add(DaysLabel);

		JLabel DaysDisplayLabel = new JLabel("0");
		DaysDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		DaysDisplayLabel.setBounds(498, 58, 140, 15);
		storeFrame.getContentPane().add(DaysDisplayLabel);

		JLabel moneyLabel = new JLabel("Money: ");
		moneyLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		moneyLabel.setBounds(59, 89, 70, 15);
		storeFrame.getContentPane().add(moneyLabel);

		JLabel moneyDisplayLabel = new JLabel("0");
		moneyDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		moneyDisplayLabel.setBounds(128, 89, 140, 15);
		storeFrame.getContentPane().add(moneyDisplayLabel);

		JLabel locationLabel = new JLabel("Location: ");
		locationLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		locationLabel.setBounds(348, 89, 85, 15);
		storeFrame.getContentPane().add(locationLabel);

		JLabel locationDisplayLabel = new JLabel("<dynamic>");
		locationDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		locationDisplayLabel.setBounds(438, 89, 200, 15);
		storeFrame.getContentPane().add(locationDisplayLabel);

		// update status labels
		String name = manager.getTraderName();
		int days = manager.getRemainingDays();
		int money = manager.getOwnedMoney();
		String islandLocation = manager.getCurrentIslandName();

		nameDisplayLabel.setText(name);
		DaysDisplayLabel.setText(String.valueOf(days));
		moneyDisplayLabel.setText(String.valueOf(money));
		locationDisplayLabel.setText(islandLocation);
		// ====================

		// Sale item radio buttons
		JRadioButton saleItem1Button = new JRadioButton("N/A");
		saleItem1Button.setFont(new Font("Dialog", Font.BOLD, 14));
		saleItembuttonGroup.add(saleItem1Button);
		saleItem1Button.setSelected(true);
		saleItem1Button.setBounds(198, 442, 120, 23);
		storeFrame.getContentPane().add(saleItem1Button);

		JRadioButton saleItem2Button = new JRadioButton("N/A");
		saleItem2Button.setFont(new Font("Dialog", Font.BOLD, 14));
		saleItembuttonGroup.add(saleItem2Button);
		saleItem2Button.setBounds(339, 442, 120, 23);
		storeFrame.getContentPane().add(saleItem2Button);

		JRadioButton saleItem3Button = new JRadioButton("N/A");
		saleItem3Button.setFont(new Font("Dialog", Font.BOLD, 14));
		saleItembuttonGroup.add(saleItem3Button);
		saleItem3Button.setBounds(481, 442, 120, 23);
		storeFrame.getContentPane().add(saleItem3Button);

		// update sale list when initializing
		ArrayList<String> saleItems = new ArrayList<String>();
		for (Item item : manager.getItemsToSell()) {
			saleItems.add(item.getName());
		}

		int i = 0;
		Enumeration<AbstractButton> saleItemButtons = saleItembuttonGroup.getElements();
		while (i < saleItems.size() && saleItemButtons.hasMoreElements()) {
			JRadioButton saleItemButton = (JRadioButton) saleItemButtons.nextElement();

			saleItemButton.setText(saleItems.get(i));

			i += 1;
		}
		// ===================================

		JSeparator separator = new JSeparator();
		separator.setBounds(40, 477, 600, 2);
		storeFrame.getContentPane().add(separator);

		// To purchase item radio buttons
		JRadioButton purchaseItem1Button = new JRadioButton("N/A");
		purchaseItem1Button.setFont(new Font("Dialog", Font.BOLD, 14));
		purchaseItembuttonGroup.add(purchaseItem1Button);
		purchaseItem1Button.setSelected(true);
		purchaseItem1Button.setBounds(198, 495, 120, 23);
		storeFrame.getContentPane().add(purchaseItem1Button);

		JRadioButton purchaseItem2Button = new JRadioButton("N/A");
		purchaseItem2Button.setFont(new Font("Dialog", Font.BOLD, 14));
		purchaseItembuttonGroup.add(purchaseItem2Button);
		purchaseItem2Button.setBounds(339, 495, 120, 23);
		storeFrame.getContentPane().add(purchaseItem2Button);

		JRadioButton purchaseItem3Button = new JRadioButton("N/A");
		purchaseItem3Button.setFont(new Font("Dialog", Font.BOLD, 14));
		purchaseItembuttonGroup.add(purchaseItem3Button);
		purchaseItem3Button.setBounds(481, 495, 120, 23);
		storeFrame.getContentPane().add(purchaseItem3Button);

		// update purchase list when initializing
		ArrayList<String> purchaseItems = new ArrayList<String>();
		for (Item item : manager.getSellableTraderItems()) {
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
		separator_1.setBounds(40, 530, 600, 2);
		storeFrame.getContentPane().add(separator_1);
		// ===================================

		// Trading quantity slider
		JSlider tradeQuantitySlider = new JSlider();

		tradeQuantitySlider.setValue(15);
		tradeQuantitySlider.setSnapToTicks(true);
		tradeQuantitySlider.setMajorTickSpacing(1);
		tradeQuantitySlider.setMinimum(1);
		// set max of slider to be the max capacity of the ship
		tradeQuantitySlider.setMaximum(manager.getShip().getDefaultCapacity());
		tradeQuantitySlider.setBounds(201, 550, 400, 20);
		storeFrame.getContentPane().add(tradeQuantitySlider);

		JLabel tradeQuantityLable = new JLabel("Trading Quantity :");
		tradeQuantityLable.setFont(new Font("Dialog", Font.BOLD, 14));
		tradeQuantityLable.setLabelFor(tradeQuantitySlider);
		tradeQuantityLable.setBounds(40, 547, 150, 20);
		storeFrame.getContentPane().add(tradeQuantityLable);

		JLabel QuantityLabel = new JLabel("N/A");
		QuantityLabel.setLabelFor(tradeQuantitySlider);
		QuantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		QuantityLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		QuantityLabel.setBounds(371, 573, 70, 20);
		storeFrame.getContentPane().add(QuantityLabel);
		QuantityLabel.setText(String.valueOf(tradeQuantitySlider.getValue()));

		tradeQuantitySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				QuantityLabel.setText(String.valueOf(tradeQuantitySlider.getValue()));
			}
		});

		// Go to port button
		JButton portButton = new JButton("Go to Port");
		portButton.setFont(new Font("Dialog", Font.BOLD, 14));
		portButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				finishedStoreWindow();

			}
		});

		// But button
		JButton buyButton = new JButton("Buy");
		buyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// get selected sale item
				String saleItemName = "";

				Enumeration<AbstractButton> items = saleItembuttonGroup.getElements();
				while (items.hasMoreElements()) {
					JRadioButton item = (JRadioButton) items.nextElement();
					if (item.isSelected()) {
						saleItemName = item.getText();
					}
				}

				// get quantity
				int quantity = tradeQuantitySlider.getValue();

				// call manager to implement buy method
				if (saleItemName == "N/A") {

					reportText.setText("The item you choose does not exist,\n" + "please choose your item again.");

				} else {

					// call Trader.buy() through manager
					String report = manager.buy(saleItemName, quantity);
					reportText.setText(report);

					// update sell radio buttons
					ArrayList<String> purchaseItems = new ArrayList<String>();
					for (Item item : manager.getSellableTraderItems()) {
						purchaseItems.add(item.getName());
					}

					int j = 0;
					Enumeration<AbstractButton> purchaseItemButtons = purchaseItembuttonGroup.getElements();
					while (j < purchaseItems.size() && purchaseItemButtons.hasMoreElements()) {
						JRadioButton purchaseItemButton = (JRadioButton) purchaseItemButtons.nextElement();

						purchaseItemButton.setText(purchaseItems.get(j));

						j += 1;
					}

					// update status labels
					int money = manager.getOwnedMoney();
					moneyDisplayLabel.setText(String.valueOf(money));

				}

			}
		});
		buyButton.setFont(new Font("Dialog", Font.BOLD, 14));
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buyButton.setBackground(new Color(204, 153, 102));
		buyButton.setBounds(40, 440, 117, 25);
		storeFrame.getContentPane().add(buyButton);

		// Sell button
		JButton sellButton = new JButton("Sell");
		sellButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// get selected purchase item
				String purchaseItemName = "";
				JRadioButton chosenItem = null;

				Enumeration<AbstractButton> items = purchaseItembuttonGroup.getElements();
				while (items.hasMoreElements()) {
					JRadioButton item = (JRadioButton) items.nextElement();
					if (item.isSelected()) {
						purchaseItemName = item.getText();
						chosenItem = item;
					}
				}

				// get quantity
				int quantity = tradeQuantitySlider.getValue();

				// call manager to implement sell method
				if (purchaseItemName == "N/A") {

					reportText.setText("The item you choose does not exist,\n" + "please choose your item again.");

				} else {

					// call Trader.sell() method through manager
					String report = manager.sell(purchaseItemName, quantity);
					reportText.setText(report);

					// update chosenItem
					// set to "N/A" if Trader sold all of this item from cargos
					ArrayList<String> purchaseItems = new ArrayList<String>();
					for (Item item : manager.getSellableTraderItems()) {
						purchaseItems.add(item.getName());
					}

					if (!purchaseItems.contains(chosenItem.getText())) {
						chosenItem.setText("N/A");
					}

					// update status labels
					int money = manager.getOwnedMoney();
					moneyDisplayLabel.setText(String.valueOf(money));
				}

			}
		});
		sellButton.setFont(new Font("Dialog", Font.BOLD, 14));
		sellButton.setBackground(new Color(204, 153, 102));
		sellButton.setBounds(40, 493, 117, 25);
		storeFrame.getContentPane().add(sellButton);

		portButton.setBounds(523, 640, 117, 25);
		storeFrame.getContentPane().add(portButton);

		// View Store Button
		JButton viewStoreButton = new JButton("Store Trading List");
		viewStoreButton.setBackground(new Color(204, 153, 102));
		viewStoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewStoreButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String report = "Following is our trading list.\n\n";
				report += manager.getListForSale() + "\n\n";
				report += "******************************\n\n";
				report += manager.getListToPurchase();
				reportText.setText(report);

				// set scroll bar to top
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						scrollPane.getVerticalScrollBar().setValue(0);
					}
				});
			}
		});
		viewStoreButton.setFont(new Font("Dialog", Font.BOLD, 14));
		viewStoreButton.setBounds(40, 640, 200, 25);
		storeFrame.getContentPane().add(viewStoreButton);

		// View trading logs button
		JButton viewTradingLogsButton = new JButton("View Trading Logs");
		viewTradingLogsButton.setBackground(new Color(204, 153, 102));
		viewTradingLogsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String report = manager.getTradingLogsDescription();
				reportText.setText(report);

				// set scroll bar to top
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						scrollPane.getVerticalScrollBar().setValue(0);
					}
				});
			}
		});
		viewTradingLogsButton.setFont(new Font("Dialog", Font.BOLD, 14));
		viewTradingLogsButton.setBounds(40, 689, 200, 25);
		storeFrame.getContentPane().add(viewTradingLogsButton);

		// View Ship cargos button
		JButton viewShipCargoesButton = new JButton("View Ship Cargos");
		viewShipCargoesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String report = "";
				report += manager.getCargosDescription();
				reportText.setText(report);

				// set scroll bar to top
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						scrollPane.getVerticalScrollBar().setValue(0);
					}
				});
			}
		});
		viewShipCargoesButton.setBackground(new Color(204, 153, 102));
		viewShipCargoesButton.setFont(new Font("Dialog", Font.BOLD, 14));
		viewShipCargoesButton.setBounds(264, 640, 200, 25);
		storeFrame.getContentPane().add(viewShipCargoesButton);
	}
}
