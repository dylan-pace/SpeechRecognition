package speech;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class to set up a GUI for the user and start the program.
 * 
 * @author DylanPace
 *
 */
@SuppressWarnings("serial")
public class panelSetup extends JPanel{
	
	JLabel label3; 
	JLabel label5;

	/**
	 * Constructor method used for setting up the GUI.
	 */
	public panelSetup() {

		// Sets up components for the GUIs.
		JLabel label;
		JLabel label2;
		JLabel label4;
		JButton button;

		// Puts a panel on the frame so components can be added.
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(new BorderLayout(5,5));
		panel1.setPreferredSize(new Dimension(200,200));
		panel1.setMinimumSize(panel1.getPreferredSize());

		// Add labels to show outputs of what commands are being given and output.
		label = new JLabel("Click the button to give a command:");
		label.setBounds(5, 15, 800, 20);
		label2 = new JLabel("You: ");
		label2.setBounds(5, 80, 300, 20);
		label3 = new JLabel();
		label3.setBounds(45, 80, 300, 20);
		label4 = new JLabel("Jarvis: ");
		label4.setBounds(5, 100, 300, 20);
		label5 = new JLabel();
		label5.setBounds(55, 100, 300, 20);

		// Add a button to start the speech thread.
		button = new JButton("Start");
		button.setBounds(130, 50, 100, 20);

		// Adds the components to the panel.
		setLayout(new BorderLayout());
		add(panel1, BorderLayout.CENTER);
		panel1.add(label);
		//panel1.add(label2);
		panel1.add(label3);
		//panel1.add(label4);
		panel1.add(label5);
		panel1.add(button, BorderLayout.SOUTH);	
		panel1.setVisible(true);

		/**
		 * Performs an action when the button is pressed.
		 */
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Call the program.
					button.setVisible(false);
					label.setText("Speak a command");
					new Main();
					// Deal with an unpredicted errors.
				} catch (Exception f) {
					label.setText("Invalid");
				}
			}

		});

	}

}