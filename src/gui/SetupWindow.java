package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingConstants;

import main.GUIGameEnvironment;
import map.World;

import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;

public class SetupWindow {

	private JFrame frame;
	private JTextField nameTextField;
	private final ButtonGroup shipButtonGroup = new ButtonGroup();
	private GUIGameEnvironment manager;
	private final ButtonGroup homeButtonGroup = new ButtonGroup();

	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupWindow window = new SetupWindow();
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
	public SetupWindow(GUIGameEnvironment incomingManager) {
		this.manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		this.manager.closeSetupWindow(this);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 494, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to Island Trader");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		welcomeLabel.setBounds(100, 12, 300, 15);
		frame.getContentPane().add(welcomeLabel);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(68, 54, 70, 15);
		frame.getContentPane().add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setText("Jon Snow");
		nameLabel.setLabelFor(nameTextField);
		nameTextField.setBounds(148, 52, 114, 19);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel dayLabel = new JLabel("Days:");
		dayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dayLabel.setBounds(64, 97, 70, 15);
		frame.getContentPane().add(dayLabel);
		
		JSlider daysSlider = new JSlider();
		daysSlider.setMaximum(90);
		daysSlider.setMinimum(30);
		daysSlider.setPaintLabels(true);
		daysSlider.setPaintTicks(true);
		daysSlider.setSnapToTicks(true);
		daysSlider.setMinorTickSpacing(20);
		daysSlider.setMajorTickSpacing(20);
		dayLabel.setLabelFor(daysSlider);
		daysSlider.setBounds(139, 89, 200, 48);
		frame.getContentPane().add(daysSlider);
		
		JLabel homeLabel = new JLabel("Home:");
		homeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		homeLabel.setBounds(68, 147, 70, 15);
		frame.getContentPane().add(homeLabel);
		
		JRadioButton island1Button = new JRadioButton("Lord Matheson Island");
		island1Button.setSelected(true);
		homeButtonGroup.add(island1Button);
		island1Button.setBounds(144, 145, 190, 23);
		frame.getContentPane().add(island1Button);
		
		JRadioButton island2Button = new JRadioButton("Ceylon");
		homeButtonGroup.add(island2Button);
		island2Button.setBounds(144, 170, 149, 23);
		frame.getContentPane().add(island2Button);
		
		JLabel lblNewLabel = new JLabel("Ship:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(64, 202, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JRadioButton fastShipRadioButton = new JRadioButton("Black Pearl");
		shipButtonGroup.add(fastShipRadioButton);
		fastShipRadioButton.setSelected(true);
		fastShipRadioButton.setBounds(144, 199, 149, 23);
		frame.getContentPane().add(fastShipRadioButton);
		
		JRadioButton balancedShipRadioButton = new JRadioButton("Redcoasts");
		shipButtonGroup.add(balancedShipRadioButton);
		balancedShipRadioButton.setBounds(144, 226, 149, 23);
		frame.getContentPane().add(balancedShipRadioButton);
		
		JRadioButton battleShipRadioButton = new JRadioButton("Endeavour");
		shipButtonGroup.add(battleShipRadioButton);
		battleShipRadioButton.setBounds(289, 199, 149, 23);
		frame.getContentPane().add(battleShipRadioButton);
		
		JRadioButton baoShipRadioButton = new JRadioButton("Empress");
		shipButtonGroup.add(baoShipRadioButton);
		baoShipRadioButton.setBounds(289, 226, 149, 23);
		frame.getContentPane().add(baoShipRadioButton);
		
		JButton quitBtn = new JButton("Quit");
		quitBtn.setBackground(new Color(255, 102, 102));
		quitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//actions for quit button
				System.exit(0);
			}
		});
	
		
		JLabel reportLabel = new JLabel("Report:");
		reportLabel.setBounds(93, 320, 70, 15);
		frame.getContentPane().add(reportLabel);
		
		JTextArea reportText = new JTextArea();
		reportText.setWrapStyleWord(true);
		reportText.setLineWrap(true);
		reportText.setFont(new Font("Dialog", Font.BOLD, 13));
		reportText.setEditable(false);
		reportText.setText("Let's create a new player!");
		reportText.setBounds(60, 347, 400, 50);
		frame.getContentPane().add(reportText);
		
		quitBtn.setBounds(50, 269, 80, 25);
		frame.getContentPane().add(quitBtn);
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// actions for confirm button
				String name;
				int days;
				String islandName = "";
				String shipName = "";
				
				//get name and days
				name = nameTextField.getText();
				System.out.println("name: " + name);
				days = daysSlider.getValue();
				System.out.println("days: " + days);
				
				// get island name
				Enumeration<AbstractButton> homes = homeButtonGroup.getElements();
				while (homes.hasMoreElements()) {
					JRadioButton home = (JRadioButton) homes.nextElement();
					if(home.isSelected()) {
						islandName = home.getText();
						System.out.println("home name: " + islandName);
					}
				}
				
				// get ship name
				Enumeration<AbstractButton> ships = shipButtonGroup.getElements();
				while (ships.hasMoreElements()) {
					JRadioButton ship = (JRadioButton) ships.nextElement();
					if(ship.isSelected()) {
						shipName = ship.getText();
						System.out.println("ship name: " + shipName);
					}
				}
				
				
				String report;
				report = manager.setTrader(name, days, islandName, shipName);
				reportText.setText(report);
			}
		});
		confirmBtn.setBounds(180, 269, 117, 25);
		frame.getContentPane().add(confirmBtn);
		
		JButton startButton = new JButton("Start Game");
		startButton.setBounds(321, 269, 117, 25);
		frame.getContentPane().add(startButton);
		
		
	}
}
