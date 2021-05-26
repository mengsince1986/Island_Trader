package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.GUIGameEnvironment;
import map.Route;

import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * The PortWindow is one of the the GUI interface objects for running the game. 
 * It provides all the available command options at port, gets users choices and
 * calls the related methods through the attribute manager 
 * - {@link GUIGameEnvironment} object - to change the state of the game.
 * <p>
 * The PortWindow also updates the status bar and prints the report after 
 * the implementation of each command.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class PortWindow {

	/**
	 * Attribute portFrame stores the Frame object of this window.
	 */
	private JFrame portFrame;
	
	/**
	 * Attribute destinationButtonGroup stores a ButtonGroup object including 
	 * all the RadioButtons with the name of the available Island objects.
	 */
	private final ButtonGroup destinationButtonGroup = new ButtonGroup();
	
	/**
	 * Attribute manager is the {@link GUIGameEnvironment} object which maintains the 
	 * state of the program and makes instances of window classes.
	 */
	private GUIGameEnvironment manager;
	
	// This static main method is only used for initializing this window frame
	// individually for testing
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PortWindow window = new PortWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * This constructor sets the value of manager attribute, initializes this
	 * Frame object and sets this frame to be visible.
	 * @param incomingManager the current {@link GUIGameEnvironment} object
	 */
	public PortWindow(GUIGameEnvironment incomingManager) {
		this.manager = incomingManager;
		initialize();
		portFrame.setVisible(true);
	}
	
	/**
	 * This method disposes this window frame.
	 */
	public void closePortWindow() {
		portFrame.dispose();
	}
	
	/**
	 * This method closes this window frame and launches the StoreWindow frame 
	 * by calling the closeSetupWindow method from manager which is the
	 * current {@link GUIGameEnvironment} object.
	 */
	public void finishedPortWindow() {
		this.manager.closePortWindow(this);
	}
	
	/**
	 * This method closes this window frame and launches the SetupWindow frame by
	 * calling the restartSetupWindow method from manager which is the
	 * current {@link GUIGameEnvironment} object.
	 */
	public void restartSetupWindow() {
		
		// close portWindow and start setupWindow
		this.manager.restartSetupWindow(this);
	}

	/**
	 * This method initializes the contents of the frame.
	 */
	private void initialize() {
		portFrame = new JFrame();
		portFrame.setTitle("Island Trader  - PORT");
		portFrame.setBounds(100, 100, 752, 901);
		portFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		portFrame.getContentPane().setLayout(null);
		
		JLabel portLabel = new JLabel("Port");
		portLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		portLabel.setBounds(0, 10, 689, 20);
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		portFrame.getContentPane().add(portLabel);
		
		JLabel NameLabel = new JLabel("Name: ");
		NameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		NameLabel.setBounds(61, 54, 70, 15);
		portFrame.getContentPane().add(NameLabel);
		
		JLabel nameDisplayLabel = new JLabel("N/A");
		portLabel.setLabelFor(nameDisplayLabel);
		nameDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		nameDisplayLabel.setBounds(130, 54, 200, 15);
		portFrame.getContentPane().add(nameDisplayLabel);
		
		JLabel DaysLabel = new JLabel("Remaining Days:");
		DaysLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		DaysLabel.setBounds(350, 54, 150, 15);
		portFrame.getContentPane().add(DaysLabel);
		
		JLabel DaysDisplayLabel = new JLabel("N/A");
		DaysLabel.setLabelFor(DaysDisplayLabel);
		DaysDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		DaysDisplayLabel.setBounds(500, 54, 140, 15);
		portFrame.getContentPane().add(DaysDisplayLabel);
		
		JLabel moneyLabel = new JLabel("Money: ");
		moneyLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		moneyLabel.setBounds(61, 85, 70, 15);
		portFrame.getContentPane().add(moneyLabel);
		
		JLabel moneyDisplayLabel = new JLabel("N/A");
		moneyLabel.setLabelFor(moneyDisplayLabel);
		moneyDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		moneyDisplayLabel.setBounds(130, 85, 140, 15);
		portFrame.getContentPane().add(moneyDisplayLabel);
		
		JLabel locationLabel = new JLabel("Location: ");
		locationLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		locationLabel.setBounds(350, 85, 85, 15);
		portFrame.getContentPane().add(locationLabel);
		
		JLabel locationDisplayLabel = new JLabel("N/A");
		locationDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		locationDisplayLabel.setBounds(440, 85, 200, 15);
		portFrame.getContentPane().add(locationDisplayLabel);
		
		// update status labels
		
		String name = manager.getTraderName();
		int days = manager.getRemainingDays();
		int money = manager.getOwnedMoney();
		String islandLocation =manager.getCurrentIslandName();
		
		nameDisplayLabel.setText(name);
		DaysDisplayLabel.setText(String.valueOf(days));
		moneyDisplayLabel.setText(String.valueOf(money));
		locationDisplayLabel.setText(islandLocation);
		
	
		// ====================
		
		
		JTextArea reportText = new JTextArea();
		reportText.setBackground(Color.WHITE);
		reportText.setEditable(false);
		reportText.setText("The report will be shown here.");
		reportText.setFont(new Font("Dialog", Font.BOLD, 14));
		reportText.setLineWrap(true);
		reportText.setWrapStyleWord(true);
		reportText.setBounds(50, 127, 600, 276);
		portFrame.getContentPane().add(reportText);
		
		JScrollPane scrollPane = new JScrollPane(reportText);
		scrollPane.setBounds(48, 127, 650, 276);
		portFrame.getContentPane().add(scrollPane);
		
		
		// update report when initializing
		reportText.setText("A new advanture has started! What's next captain?\n\n\n"
				          + manager.getTrader().getCurrentIsland().getRoutesString(manager.getShip()));
		// ======================
		
		
		// set scroll bar to top
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
			       scrollPane.getVerticalScrollBar().setValue(0);
			   }
			});
	
		
		JButton storeButton = new JButton("Go to Store");
		storeButton.setFont(new Font("Dialog", Font.BOLD, 14));
		storeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedPortWindow();
			}
		});
		storeButton.setBounds(510, 790, 180, 25);
		portFrame.getContentPane().add(storeButton);
		
		// Repair Button
		JButton repairButton = new JButton("Repair Ship");
		repairButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		repairButton.setFont(new Font("Dialog", Font.BOLD, 14));
		repairButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// call repair method from manager
				String report = manager.repair();
				reportText.setText(report);
				
				// update status labels
				int money = manager.getOwnedMoney();
				moneyDisplayLabel.setText(String.valueOf(money));

			}
		});
		repairButton.setBackground(new Color(204, 153, 102));
		repairButton.setBounds(255, 547, 180, 25);
		portFrame.getContentPane().add(repairButton);
		
		// Quit Button
		JButton quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Dialog", Font.BOLD, 14));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		// Summary Button
		JButton summaryButton = new JButton("Summary");
		summaryButton.setFont(new Font("Dialog", Font.BOLD, 14));
		summaryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String report = manager.gameOver();
				reportText.setText(report);
			}
		});
		summaryButton.setBounds(300, 790, 117, 25);
		portFrame.getContentPane().add(summaryButton);
		summaryButton.setVisible(false);
		
		JLabel costLable = new JLabel("Cost:");
		costLable.setFont(new Font("Dialog", Font.BOLD, 14));
		costLable.setBounds(491, 636, 70, 15);
		portFrame.getContentPane().add(costLable);
		
		JLabel cannonCostLable = new JLabel("N/A");
		costLable.setLabelFor(cannonCostLable);
		cannonCostLable.setFont(new Font("Dialog", Font.BOLD, 14));
		cannonCostLable.setBounds(541, 636, 150, 15);
		portFrame.getContentPane().add(cannonCostLable);
		
		JSlider cannonNumSlider = new JSlider();
		cannonNumSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				// update cannon cost
				int cannonNum = cannonNumSlider.getValue();
				int totalCost = cannonNum * manager.getCostPerCannon();
				cannonCostLable.setText(String.valueOf(totalCost) + " coins");
			}
		});
		cannonNumSlider.setValue(1);
		cannonNumSlider.setSnapToTicks(true);
		cannonNumSlider.setPaintTicks(true);
		cannonNumSlider.setPaintLabels(true);
		cannonNumSlider.setMajorTickSpacing(1);
		cannonNumSlider.setMinimum(1);
		cannonNumSlider.setMaximum(10);
		cannonNumSlider.setBounds(251, 636, 220, 40);
		portFrame.getContentPane().add(cannonNumSlider);
		
		//Upgrade Cannon Button
		JButton upgradeCannonsButton = new JButton("Upgrade Cannons");
		upgradeCannonsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// get upgrade cannon number
				int cannonNum = cannonNumSlider.getValue();
				
				// call upgrade method from manager
				String report = manager.upgradeCannons(cannonNum);
				reportText.setText(report);
				
				// update status labels
				int money = manager.getOwnedMoney();
				moneyDisplayLabel.setText(String.valueOf(money));
				
			}
		});
		upgradeCannonsButton.setFont(new Font("Dialog", Font.BOLD, 14));
		upgradeCannonsButton.setBackground(new Color(204, 153, 102));
		upgradeCannonsButton.setBounds(51, 632, 180, 25);
		portFrame.getContentPane().add(upgradeCannonsButton);
		
		// update cannon cost when initializing
		int cannonNum = cannonNumSlider.getValue();
		int totalCost = cannonNum * manager.getCostPerCannon();
		cannonCostLable.setText(String.valueOf(totalCost) + " coins");
		
		// Sail Button
		JButton sailButton = new JButton("Sail to");
		sailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sailButton.setFont(new Font("Dialog", Font.BOLD, 14));
		sailButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// get selected island
				String destinationName = "";
				
				Enumeration<AbstractButton> destionations = destinationButtonGroup.getElements();
				while (destionations.hasMoreElements()) {
					JRadioButton destination = (JRadioButton) destionations.nextElement();
					if(destination.isSelected()) {
						destinationName = destination.getText();
					}
				}
				
				// call manager to implement sail method
				if (destinationName == "N/A") {
					
					reportText.setText("The island you choose does not exist,\n"
								     + "please choose your destination again.");
					
				} else {
					
					String report = manager.sail(destinationName);
					
					
					//check if game over
					if (manager.getTrader().noTimeToSail() || 
						manager.getTrader().noMoneyToSail()) {
						
						// update status labels
						int days = manager.getRemainingDays();
						int money = manager.getOwnedMoney();
						String islandLocation = manager.getCurrentIslandName();
						
						DaysDisplayLabel.setText(String.valueOf(days));
						moneyDisplayLabel.setText(String.valueOf(money));
						locationDisplayLabel.setText(islandLocation);
						
						// update report
						report = report 
							   + "\n\n==============GAME OVER============";
						reportText.setText(report);
						
						// set all buttons invisible except for "Quit"
						sailButton.setVisible(false);
						repairButton.setVisible(false);
						storeButton.setVisible(false);
						
						// set Summary button visible
						summaryButton.setVisible(true);
						
						javax.swing.SwingUtilities.invokeLater(new Runnable() {
							   public void run() { 
							       scrollPane.getVerticalScrollBar().setValue(0);
							   }
							});
						
					} else {
						
						// update report 
						reportText.setText(report);
						
						// update status labels
						int days = manager.getRemainingDays();
						int money = manager.getOwnedMoney();
						String islandLocation =manager.getCurrentIslandName();
						
						DaysDisplayLabel.setText(String.valueOf(days));
						moneyDisplayLabel.setText(String.valueOf(money));
						locationDisplayLabel.setText(islandLocation);
						
						// update cannon cost
						int cannonNum = cannonNumSlider.getValue();
						int totalCost = cannonNum * manager.getCostPerCannon();
						cannonCostLable.setText(String.valueOf(totalCost) + " coins");
						
						// update sail destination radio buttons
						ArrayList<String> islandNames = new ArrayList<String>(); 
						for (Route route : manager.getCurrentRoutes()) {
							islandNames.add(route.getDest().getName());
						}

						int i = 0;
						Enumeration<AbstractButton> destinations = destinationButtonGroup.getElements();
						while (destinations.hasMoreElements()) {
							JRadioButton destination = (JRadioButton) destinations.nextElement();
							if(i < islandNames.size()) {
								destination.setText(islandNames.get(i));
							} else {
								destination.setText("N/A");
							}
							i += 1;
						}
						
						javax.swing.SwingUtilities.invokeLater(new Runnable() {
							   public void run() { 
							       scrollPane.getVerticalScrollBar().setValue(0);
							   }
							});
						
					}
					
				}
				
				
			}
		});
		
		
		sailButton.setBackground(new Color(204, 153, 102));
		sailButton.setBounds(50, 450, 180, 25);
		portFrame.getContentPane().add(sailButton);
		
		// Destination Radio Buttons
		JRadioButton destRadionButton1 = new JRadioButton("N/A");
		destRadionButton1.setFont(new Font("Dialog", Font.BOLD, 14));
		destRadionButton1.setSelected(true);
		destinationButtonGroup.add(destRadionButton1);
		destRadionButton1.setBounds(255, 428, 250, 23);
		portFrame.getContentPane().add(destRadionButton1);
		
		JRadioButton destRadionButton2 = new JRadioButton("N/A");
		destRadionButton2.setFont(new Font("Dialog", Font.BOLD, 14));
		destinationButtonGroup.add(destRadionButton2);
		destRadionButton2.setBounds(255, 455, 250, 23);
		portFrame.getContentPane().add(destRadionButton2);
		
		JRadioButton destRadionButton3 = new JRadioButton("N/A");
		destRadionButton3.setFont(new Font("Dialog", Font.BOLD, 14));
		destinationButtonGroup.add(destRadionButton3);
		destRadionButton3.setBounds(255, 480, 250, 23);
		portFrame.getContentPane().add(destRadionButton3);
		
		// update destination radio buttons when initializing PortWindow
		ArrayList<String> islandNames = new ArrayList<String>(); 
		for (Route route : manager.getCurrentRoutes()) {
			islandNames.add(route.getDest().getName());
		}

		int i = 0;
		Enumeration<AbstractButton> destinations = destinationButtonGroup.getElements();
		while (i < islandNames.size() && destinations.hasMoreElements()) {
			JRadioButton destination = (JRadioButton) destinations.nextElement();
			if(destination.getText() == "N/A") {
				destination.setText(islandNames.get(i));
			}
			i += 1;
		}
		// ========================
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(45, 515, 650, 2);
		portFrame.getContentPane().add(separator1);
		quitButton.setBackground(new Color(255, 102, 102));
		quitButton.setBounds(175, 790, 100, 25);
		portFrame.getContentPane().add(quitButton);
		
		
		
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(45, 690, 650, 2);
		portFrame.getContentPane().add(separator2);
		
		JButton restartButton = new JButton("Restart");
		restartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				restartSetupWindow();
				
			}
		});
		restartButton.setFont(new Font("Dialog", Font.BOLD, 14));
		restartButton.setBackground(new Color(255, 102, 102));
		restartButton.setBounds(50, 790, 100, 25);
		portFrame.getContentPane().add(restartButton);
		
		// View Routes Button
		JButton viewRoutesButton = new JButton("Available Routes");
		viewRoutesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewRoutesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String routeReport = manager.getRoutesDescription();
				reportText.setText(routeReport);
				
				// set scroll bar to top
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					   public void run() { 
					       scrollPane.getVerticalScrollBar().setValue(0);
					   }
					});
				
			}
		});
		viewRoutesButton.setFont(new Font("Dialog", Font.BOLD, 14));
		viewRoutesButton.setBackground(new Color(204, 153, 102));
		viewRoutesButton.setBounds(50, 547, 180, 25);
		portFrame.getContentPane().add(viewRoutesButton);
		
		// View Ship Button
		JButton viewShipButton = new JButton("Ship Status");
		viewShipButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String shipStatus = manager.getShipDescription() + "\n" +
				                    manager.getCargosDescription() + "\n" + 
				                    manager.getUpgradeLogsDescription();
				reportText.setText(shipStatus);
				
				// set scroll bar to top
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					   public void run() { 
					       scrollPane.getVerticalScrollBar().setValue(0);
					   }
					});
			}
		});
		viewShipButton.setFont(new Font("Dialog", Font.BOLD, 14));
		viewShipButton.setBackground(new Color(204, 153, 102));
		viewShipButton.setBounds(255, 720, 180, 25);
		portFrame.getContentPane().add(viewShipButton);
		
		JButton viewTraderButton = new JButton("Trader Status");
		viewTraderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String traderStatus = manager.getTraderDescription() + "\n\n" +
						              manager.getTradingLogsDescription();
				reportText.setText(traderStatus);
				
				// set scroll bar to top
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					   public void run() { 
					       scrollPane.getVerticalScrollBar().setValue(0);
					   }
					});
				
			}
		});
		viewTraderButton.setFont(new Font("Dialog", Font.BOLD, 14));
		viewTraderButton.setBackground(new Color(204, 153, 102));
		viewTraderButton.setBounds(50, 720, 180, 25);
		portFrame.getContentPane().add(viewTraderButton);
		
		JSeparator separator2_1 = new JSeparator();
		separator2_1.setBounds(45, 600, 650, 2);
		portFrame.getContentPane().add(separator2_1);
		
	}
}
