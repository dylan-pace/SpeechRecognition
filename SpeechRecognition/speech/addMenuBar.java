package speech;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * Class that adds a menu bar to the frame so the user may navigate
 * around and learn more about the program.
 * @author DylanPace
 *
 */
@SuppressWarnings("serial")
public class addMenuBar extends JPanel {

	// Instantiates {@link frameSetup} to call components and allow the frame to add the panel from {@link Driver}
	panelSetup panel = new panelSetup();

	// Sets up the menu items.
	private JMenuItem menuItem3;
	private JMenuItem menuItem4;
	private JMenuItem menuItem5;
	private JMenuItem menuItem6;
	
	JFrame infoFrame;
	JTextField infoField = new JTextField();
	JLabel label = new JLabel();
	String info;

	/**
	 * Method to setup the menu bar and the components that will be 
	 * added to it for the user to perform actions.
	 * @return menuBar starts the menu bar.
	 */
	JMenuBar menuSetup() {

		// Create the menu bar itself.
		JMenuBar menuBar = new JMenuBar();

		// Create a menu.
		JMenu menu1 = new JMenu("File");
		JMenu menu2 = new JMenu("Help");
		
		//Add shortcuts to the menu buttons.
		menu2.setMnemonic(KeyEvent.VK_H);
		menu1.setMnemonic(KeyEvent.VK_F);

		// Add the menu components to the menu bar.
		menuBar.add(menu1);
		menuBar.add(menu2);

		// Add the different attributes to the components.
		menuItem3 = new JMenuItem("Exit");
		menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
		menuItem4 = new JMenuItem("About");
		menuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		menuItem5= new JMenuItem("Commands");
		menuItem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
		menuItem6 = new JMenuItem("Personal Info");
		menuItem6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK));
		
		// Set up the menu bar.
		menu1.add(menuItem3);
		menu2.add(menuItem4);
		menu2.add(menuItem5);
		//menu1.add(menuItem6);

		/**
		 * Inner anonymous class to exit the program when selected by the user.
		 */
		menuItem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Opens a new window giving the user the option whether they want to quit or not.
				int exit = JOptionPane.showOptionDialog(null, "Press OK to exit.", "Exit", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if (exit == JOptionPane.OK_OPTION) {
					// Quits the program.
					System.exit(1);
				}
			}
		});

		/**
		 * Inner anonymous class to give some about the program.
		 */
		menuItem4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				//Runs a new window to give the information.
				JOptionPane.showMessageDialog(null,
						"This is a speech API which will listen for a speech input and perform an action.");

			}
		});
		
		/**
		 * Inner anonymous class that opens a new frame giving information about the
		 * different commands that can be given in the program.
		 */
		menuItem5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Sets up the new frame with information about the commands {@link commandList}.
				new commandList();
				
			}
		});
		
		menuItem6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Setup a new frame to display the commands.
				infoFrame = new JFrame("JARVIS");
				infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// Display the window.
				infoFrame.setResizable(false);
				infoFrame.setLocation(300, 300);
				infoFrame.setSize(350, 100);
				infoFrame.setVisible(true);
				// Gives a layout to the frame.
				infoFrame.setLayout(new BorderLayout(5, 5));
				
				// Sets up a panel so information can be added.
				JPanel infoPanel = new JPanel();
				infoPanel.setBackground(Color.WHITE);
				infoPanel.setLayout(new BorderLayout(5,5));
				
				label.setText("Please enter your name: ");
				infoField.setText("Dylan");
				
				// Adds components to the frame.
				infoFrame.add(infoPanel);
				infoPanel.add(label);
				infoPanel.add(infoField, BorderLayout.SOUTH);
				
				info = infoField.getText();
				
			}
			
		});

		// Runs the menu bar.
		return menuBar;

	}

}