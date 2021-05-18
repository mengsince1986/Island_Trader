package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class SetupWindow {

	private JFrame frame;
	private JTextField nameTextField;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public SetupWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to Island Trader");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		welcomeLabel.setBounds(104, 12, 215, 15);
		frame.getContentPane().add(welcomeLabel);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(29, 54, 70, 15);
		frame.getContentPane().add(nameLabel);
		
		nameTextField = new JTextField();
		nameLabel.setLabelFor(nameTextField);
		nameTextField.setBounds(109, 52, 114, 19);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel dayLabel = new JLabel("Days:");
		dayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dayLabel.setBounds(25, 97, 70, 15);
		frame.getContentPane().add(dayLabel);
		
		JSlider daysSlider = new JSlider();
		daysSlider.setMaximum(90);
		daysSlider.setPaintLabels(true);
		daysSlider.setPaintTicks(true);
		daysSlider.setSnapToTicks(true);
		daysSlider.setMinorTickSpacing(20);
		daysSlider.setMajorTickSpacing(20);
		dayLabel.setLabelFor(daysSlider);
		daysSlider.setMinimum(10);
		daysSlider.setBounds(100, 89, 200, 48);
		frame.getContentPane().add(daysSlider);
		
		JLabel lblNewLabel = new JLabel("Ship:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(25, 153, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JRadioButton fastShipRadioButton = new JRadioButton("Black Pearl");
		fastShipRadioButton.setBounds(105, 150, 149, 23);
		frame.getContentPane().add(fastShipRadioButton);
		
		JRadioButton balancedShipRadioButton = new JRadioButton("Redcoasts");
		balancedShipRadioButton.setBounds(105, 177, 149, 23);
		frame.getContentPane().add(balancedShipRadioButton);
		
		JRadioButton battleShipRadioButton = new JRadioButton("Endeavour");
		battleShipRadioButton.setBounds(250, 150, 149, 23);
		frame.getContentPane().add(battleShipRadioButton);
		
		JRadioButton baoShipRadioButton = new JRadioButton("Empress");
		baoShipRadioButton.setBounds(250, 177, 149, 23);
		frame.getContentPane().add(baoShipRadioButton);
		
		JButton quitBtn = new JButton("Quit");
		quitBtn.setBounds(68, 220, 117, 25);
		frame.getContentPane().add(quitBtn);
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(230, 220, 117, 25);
		frame.getContentPane().add(confirmBtn);
	}
}
