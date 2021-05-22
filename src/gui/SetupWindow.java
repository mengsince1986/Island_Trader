package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.SwingConstants;

import main.GUIGameEnvironment;

import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JSeparator;

/**
 * The SetupWindow is the GUI interface for setting up a new player. It gets the
 * name of the new player, the number of the remaining days, the name of the
 * home island and the name of the ship chosen by users and passes them to
 * attribute manager - {@link GUIGameEnvironment} object - to create a new Trader 
 * object and a new Ship object.
 * <p>
 * The SetupWindow checks and validates the name input by users and print
 * error report if the new name doesn't pass the preset validation.
 * <p>
 * The SetupWindow also prints the profile of the new created player or an error
 * message if any of the input information is invalid after users click the
 * confirm button.
 * 
 * @author Finn van Dorsser
 * @author Meng Zhang
 */

public class SetupWindow {

	/**
	 * Attribute setupFrame stores the Frame object of this window.
	 * <p>
	 * Attribute nameTextField stores the string of the new name entered by 
	 * users.
	 * <p>
	 * Attribute shipButtonGroup stores a ButtonGroup object including all the
	 * RadioButtons with the name of the available Ship objects.
	 * <p>
	 * Attribute homeButtonGroup stores a ButtonGroup object including all the
	 * RadioButtons with the name of the available Island objects.
	 * <p>
	 * Attribute manager is the {@link GUIGameEnvironment} object which maintains the 
	 * state of the program and makes instances of window classes.
	 */
	private JFrame setupFrame;
	private JTextField nameTextField;
	private final ButtonGroup shipButtonGroup = new ButtonGroup();
	private final ButtonGroup homeButtonGroup = new ButtonGroup();
	private GUIGameEnvironment manager;
	
	// This static main method is only used for initializing this window frame
	// individually for testing
	/*
	 * public static void main(String[] args) {
	 * 
	 * EventQueue.invokeLater(new Runnable() { public void run() { try { SetupWindow
	 * window = new SetupWindow(); window.frame.setVisible(true); } catch (Exception
	 * e) { e.printStackTrace(); } } });
	 * 
	 * 
	 * }
	 */

	/**
	 * This constructor sets the value of manager attribute, initializes this
	 * Frame object and sets this frame to be visible.
	 * @param incomingManager the current {@link GUIGameEnvironment} object
	 */
	public SetupWindow(GUIGameEnvironment incomingManager) {
		this.manager = incomingManager;
		initialize();
		setupFrame.setVisible(true);
	}

	/**
	 * This method disposes this window frame.
	 */
	public void closeSetupWindow() {
		setupFrame.dispose();
	}

	/**
	 * This method closes this window frame and launches the PortWindow frame 
	 * by calling the closeSetupWindow method from manager which is the
	 * current {@link GUIGameEnvironment} object.
	 */
	public void finishedSetupWindow() {
		this.manager.closeSetupWindow(this);
	}

	/**
	 * This method initializes the contents of the frame.
	 */
	private void initialize() {
		setupFrame = new JFrame();
		setupFrame.setTitle("Island Trader - New Player");
		setupFrame.setBounds(100, 100, 631, 579);
		setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupFrame.getContentPane().setLayout(null);

		JLabel welcomeLabel = new JLabel("Create a new player");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		welcomeLabel.setBounds(175, 12, 300, 30);
		setupFrame.getContentPane().add(welcomeLabel);

		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(87, 70, 70, 15);
		setupFrame.getContentPane().add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Dialog", Font.BOLD, 14));
		nameTextField.setText("Alice");
		nameLabel.setLabelFor(nameTextField);
		nameTextField.setBounds(175, 68, 205, 25);
		setupFrame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		JLabel dayLabel = new JLabel("Days:");
		dayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		dayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dayLabel.setBounds(86, 122, 70, 15);
		setupFrame.getContentPane().add(dayLabel);

		JSlider daysSlider = new JSlider();
		daysSlider.setMaximum(90);
		daysSlider.setMinimum(30);
		daysSlider.setPaintLabels(true);
		daysSlider.setPaintTicks(true);
		daysSlider.setSnapToTicks(true);
		daysSlider.setMinorTickSpacing(20);
		daysSlider.setMajorTickSpacing(20);
		dayLabel.setLabelFor(daysSlider);
		daysSlider.setBounds(166, 117, 205, 40);
		setupFrame.getContentPane().add(daysSlider);

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(90, 175, 450, 2);
		setupFrame.getContentPane().add(separator1);

		JLabel homeLabel = new JLabel("Home:");
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		homeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		homeLabel.setBounds(86, 195, 70, 15);
		setupFrame.getContentPane().add(homeLabel);

		JRadioButton island1Button = new JRadioButton("Mecca Merchantia");
		island1Button.setFont(new Font("Dialog", Font.BOLD, 14));
		island1Button.setSelected(true);
		homeButtonGroup.add(island1Button);
		island1Button.setBounds(166, 193, 190, 23);
		setupFrame.getContentPane().add(island1Button);

		JRadioButton island2Button = new JRadioButton("Ceylon");
		island2Button.setFont(new Font("Dialog", Font.BOLD, 14));
		homeButtonGroup.add(island2Button);
		island2Button.setBounds(393, 193, 149, 23);
		setupFrame.getContentPane().add(island2Button);

		JRadioButton island3Button = new JRadioButton("Lord Matheson Island");
		homeButtonGroup.add(island3Button);
		island3Button.setFont(new Font("Dialog", Font.BOLD, 14));
		island3Button.setBounds(166, 247, 205, 23);
		setupFrame.getContentPane().add(island3Button);

		JRadioButton island4Button = new JRadioButton("Niawall's Tear");
		homeButtonGroup.add(island4Button);
		island4Button.setFont(new Font("Dialog", Font.BOLD, 14));
		island4Button.setBounds(393, 221, 155, 23);
		setupFrame.getContentPane().add(island4Button);

		JRadioButton island5Button = new JRadioButton("The Isle of Dwarves");
		homeButtonGroup.add(island5Button);
		island5Button.setFont(new Font("Dialog", Font.BOLD, 14));
		island5Button.setBounds(166, 221, 200, 23);
		setupFrame.getContentPane().add(island5Button);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(90, 285, 450, 2);
		setupFrame.getContentPane().add(separator2);

		JLabel shipLabel = new JLabel("Ship:");
		shipLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		shipLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		shipLabel.setBounds(86, 300, 70, 15);
		setupFrame.getContentPane().add(shipLabel);

		JRadioButton fastShipRadioButton = new JRadioButton("Black Pearl");
		fastShipRadioButton.setFont(new Font("Dialog", Font.BOLD, 14));
		shipButtonGroup.add(fastShipRadioButton);
		fastShipRadioButton.setSelected(true);
		fastShipRadioButton.setBounds(166, 297, 149, 23);
		setupFrame.getContentPane().add(fastShipRadioButton);

		JRadioButton balancedShipRadioButton = new JRadioButton("Redcoasts");
		balancedShipRadioButton.setFont(new Font("Dialog", Font.BOLD, 14));
		shipButtonGroup.add(balancedShipRadioButton);
		balancedShipRadioButton.setBounds(166, 324, 149, 23);
		setupFrame.getContentPane().add(balancedShipRadioButton);

		JRadioButton battleShipRadioButton = new JRadioButton("Endeavour");
		battleShipRadioButton.setFont(new Font("Dialog", Font.BOLD, 14));
		shipButtonGroup.add(battleShipRadioButton);
		battleShipRadioButton.setBounds(315, 297, 149, 23);
		setupFrame.getContentPane().add(battleShipRadioButton);

		JRadioButton baoShipRadioButton = new JRadioButton("Empress");
		baoShipRadioButton.setFont(new Font("Dialog", Font.BOLD, 14));
		shipButtonGroup.add(baoShipRadioButton);
		baoShipRadioButton.setBounds(315, 324, 149, 23);
		setupFrame.getContentPane().add(baoShipRadioButton);

		JButton quitBtn = new JButton("Quit");
		quitBtn.setBackground(new Color(255, 102, 102));
		quitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// actions for quit button
				System.exit(0);
			}
		});

		JTextArea reportText = new JTextArea();
		reportText.setWrapStyleWord(true);
		reportText.setLineWrap(true);
		reportText.setFont(new Font("Dialog", Font.BOLD, 14));
		reportText.setEditable(false);
		reportText.setText("Welcome to Island Trader!\n\nLet's create a new trader here.");
		reportText.setBounds(90, 438, 450, 70);
		setupFrame.getContentPane().add(reportText);

		quitBtn.setBounds(102, 387, 80, 25);
		setupFrame.getContentPane().add(quitBtn);

		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// actions for confirm button
				String name;
				int days;
				String islandName = "";
				String shipName = "";

				// get and validate name
				name = nameTextField.getText();
				if (name.length() <= 0) {
					reportText.setText("Please enter your name.");
				} else if (!name.matches("[a-zA-Z]+")) {
					reportText.setText("Sorry, the name can only include letters.\n"
							+ "Please check if your name includes space, numbers or special characters.");
				} else if (name.length() < 3 || name.length() > 15) {
					reportText.setText("The name should be between 3 and 15 characters.");
				} else {

					// get days
					days = daysSlider.getValue();

					// get island name
					Enumeration<AbstractButton> homes = homeButtonGroup.getElements();
					while (homes.hasMoreElements()) {
						JRadioButton home = (JRadioButton) homes.nextElement();
						if (home.isSelected()) {
							islandName = home.getText();
						}
					}

					// get ship name
					Enumeration<AbstractButton> ships = shipButtonGroup.getElements();
					while (ships.hasMoreElements()) {
						JRadioButton ship = (JRadioButton) ships.nextElement();
						if (ship.isSelected()) {
							shipName = ship.getText();
						}
					}

					String report;
					report = manager.setTrader(name, days, islandName, shipName);
					reportText.setText(report);

				}

			}
		});
		confirmBtn.setBounds(242, 387, 117, 25);
		setupFrame.getContentPane().add(confirmBtn);

		JButton startButton = new JButton("Start Game");
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (manager.getTrader() != null) {
					finishedSetupWindow();
				}
				reportText.setText("You need to create a new player\n" 
								 + "and click \"Confirm\" button first.");
			}
		});
		startButton.setBounds(383, 387, 117, 25);
		setupFrame.getContentPane().add(startButton);

	}
}
