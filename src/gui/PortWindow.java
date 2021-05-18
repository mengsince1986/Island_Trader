package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class PortWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public PortWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 699, 573);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel portLabel = new JLabel("Port");
		portLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		portLabel.setBounds(0, 10, 689, 20);
		portLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(portLabel);
		
		JTextArea reportText = new JTextArea();
		reportText.setBackground(Color.WHITE);
		reportText.setEditable(false);
		reportText.setText("The report of each round will ben shown here.");
		reportText.setFont(new Font("Dialog", Font.BOLD, 14));
		reportText.setLineWrap(true);
		reportText.setWrapStyleWord(true);
		reportText.setBounds(50, 50, 600, 150);
		frame.getContentPane().add(reportText);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		JLabel sailLabel = new JLabel("Sail to:");
		sailLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		sailLabel.setBounds(50, 211, 70, 15);
		frame.getContentPane().add(sailLabel);
		
		JRadioButton destRadionButton1 = new JRadioButton("N/A");
		destRadionButton1.setBounds(130, 208, 250, 23);
		frame.getContentPane().add(destRadionButton1);
		
		JRadioButton destRadionButton2 = new JRadioButton("N/A");
		destRadionButton2.setBounds(130, 235, 250, 23);
		frame.getContentPane().add(destRadionButton2);
		
		JRadioButton destRadionButton3 = new JRadioButton("N/A");
		destRadionButton3.setBounds(130, 258, 250, 23);
		frame.getContentPane().add(destRadionButton3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(50, 285, 600, 2);
		frame.getContentPane().add(separator);
		quitButton.setBackground(new Color(255, 102, 102));
		quitButton.setBounds(50, 442, 80, 25);
		frame.getContentPane().add(quitButton);
		
		JButton nextButton = new JButton("Next Round");
		nextButton.setBounds(300, 442, 117, 25);
		frame.getContentPane().add(nextButton);
		
		JButton storeButton = new JButton("Go to Store");
		storeButton.setBounds(500, 442, 117, 25);
		frame.getContentPane().add(storeButton);
	}
}
