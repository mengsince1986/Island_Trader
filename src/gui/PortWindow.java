package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import main.GUIGameEnvironment;
import map.Island;
import map.Route;

import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PortWindow {

	private JFrame frame;
	private GUIGameEnvironment manager;
	private final ButtonGroup destinationButtonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	
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
	 * Create the application.
	 */
	public PortWindow(GUIGameEnvironment incomingManager) {
		this.manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closePortWindow() {
		frame.dispose();
	}
	
	public void finishedPortWindow() {
		this.manager.closePortWindow(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 699, 832);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel portLabel = new JLabel("Port");
		portLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		portLabel.setBounds(0, 10, 689, 20);
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(portLabel);
		
		JLabel NameLabel = new JLabel("Name: ");
		NameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		NameLabel.setBounds(61, 54, 70, 15);
		frame.getContentPane().add(NameLabel);
		
		JLabel nameDisplayLabel = new JLabel("N/A");
		portLabel.setLabelFor(nameDisplayLabel);
		nameDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		nameDisplayLabel.setBounds(130, 54, 200, 15);
		frame.getContentPane().add(nameDisplayLabel);
		
		JLabel DaysLabel = new JLabel("Remaining Days:");
		DaysLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		DaysLabel.setBounds(350, 54, 150, 15);
		frame.getContentPane().add(DaysLabel);
		
		JLabel DaysDisplayLabel = new JLabel("N/A");
		DaysLabel.setLabelFor(DaysDisplayLabel);
		DaysDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		DaysDisplayLabel.setBounds(500, 54, 140, 15);
		frame.getContentPane().add(DaysDisplayLabel);
		
		JLabel moneyLabel = new JLabel("Money: ");
		moneyLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		moneyLabel.setBounds(61, 85, 70, 15);
		frame.getContentPane().add(moneyLabel);
		
		JLabel moneyDisplayLabel = new JLabel("N/A");
		moneyLabel.setLabelFor(moneyDisplayLabel);
		moneyDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		moneyDisplayLabel.setBounds(130, 85, 140, 15);
		frame.getContentPane().add(moneyDisplayLabel);
		
		JLabel locationLabel = new JLabel("Location: ");
		locationLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		locationLabel.setBounds(350, 85, 85, 15);
		frame.getContentPane().add(locationLabel);
		
		JLabel locationDisplayLabel = new JLabel("N/A");
		locationDisplayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		locationDisplayLabel.setBounds(440, 85, 200, 15);
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
		
		
		JTextArea reportText = new JTextArea();
		reportText.setBackground(Color.WHITE);
		reportText.setEditable(false);
		reportText.setText("The report will be shown here.");
		reportText.setFont(new Font("Dialog", Font.BOLD, 14));
		reportText.setLineWrap(true);
		reportText.setWrapStyleWord(true);
		reportText.setBounds(50, 127, 600, 276);
		frame.getContentPane().add(reportText);
		
		// update report when initializing
		//reportText.setText("Hello, port!");
		// ======================
		
		JButton storeButton = new JButton("Go to Store");
		storeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				finishedPortWindow();
			}
		});
		storeButton.setBounds(500, 730, 117, 25);
		frame.getContentPane().add(storeButton);
		
		JButton repairButton = new JButton("Repair");
		repairButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// call repair method from manager
				String report = manager.repair();
				reportText.setText(report);
			}
		});
		repairButton.setBackground(new Color(204, 153, 102));
		repairButton.setBounds(50, 530, 117, 25);
		frame.getContentPane().add(repairButton);
		
		
		JButton quitButton = new JButton("Quit");
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
		
		JButton summaryButton = new JButton("Summary");
		summaryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String report = manager.gameOver();
				reportText.setText(report);
			}
		});
		summaryButton.setBounds(161, 730, 117, 25);
		frame.getContentPane().add(summaryButton);
		summaryButton.setVisible(false);
		
		JButton sailButton = new JButton("Sail to");
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
						String name = manager.getTrader().getName();
						int days = manager.getTrader().getRemainingDays();
						int money = manager.getTrader().getOwnedMoney();
						String islandLocation =manager.getTrader().getCurrentIsland().getName();
						
						nameDisplayLabel.setText(name);
						DaysDisplayLabel.setText(String.valueOf(days));
						moneyDisplayLabel.setText(String.valueOf(money));
						locationDisplayLabel.setText(islandLocation);
						// ====================
						
						// update report
						report = "==============GAME OVER============\n" + report;
						reportText.setText(report);
						
						// set all buttons invisible except for "Quit"
						sailButton.setVisible(false);
						repairButton.setVisible(false);
						storeButton.setVisible(false);
						
						// set Summary button visible
						summaryButton.setVisible(true);
						
					} else {
						
						// update report 
						reportText.setText(report);
						
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
						
						// update sail destination radio buttons
						ArrayList<String> islandNames = new ArrayList<String>(); 
						for (Route route : manager.getTrader().getCurrentIsland().getRoutes()) {
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
						
					}
					
				}
				
				
			}
		});
		
		
		sailButton.setBackground(new Color(204, 153, 102));
		sailButton.setBounds(50, 450, 117, 25);
		frame.getContentPane().add(sailButton);
		
		JRadioButton destRadionButton1 = new JRadioButton("N/A");
		destRadionButton1.setSelected(true);
		destinationButtonGroup.add(destRadionButton1);
		destRadionButton1.setBounds(190, 428, 250, 23);
		frame.getContentPane().add(destRadionButton1);
		
		JRadioButton destRadionButton2 = new JRadioButton("N/A");
		destinationButtonGroup.add(destRadionButton2);
		destRadionButton2.setBounds(190, 455, 250, 23);
		frame.getContentPane().add(destRadionButton2);
		
		JRadioButton destRadionButton3 = new JRadioButton("N/A");
		destinationButtonGroup.add(destRadionButton3);
		destRadionButton3.setBounds(190, 480, 250, 23);
		frame.getContentPane().add(destRadionButton3);
		
		// update destination radio buttons when initializing PortWindow
		
		ArrayList<String> islandNames = new ArrayList<String>(); 
		for (Route route : manager.getTrader().getCurrentIsland().getRoutes()) {
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
		
		JSeparator separator = new JSeparator();
		separator.setBounds(50, 505, 600, 2);
		frame.getContentPane().add(separator);
		quitButton.setBackground(new Color(255, 102, 102));
		quitButton.setBounds(51, 730, 80, 25);
		frame.getContentPane().add(quitButton);
		
		
	}
}
