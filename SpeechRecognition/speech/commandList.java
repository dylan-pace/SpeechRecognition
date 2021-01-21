package speech;

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
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Class that shows the user different commands to be used
 * in the program.
 * @author DylanPace
 *
 */
@SuppressWarnings("serial")
public class commandList extends JPanel {

	// Sets up the different labels.
	protected JLabel label;
	protected JLabel label2;
	protected JLabel label3;
	protected JLabel label4;
	protected JLabel label5;
	protected JLabel label6;
	protected JLabel label7;
	protected JLabel label8;
	protected JLabel label9;

	/**
	 * Sets up a new frame so information about the program can be displayed and the
	 * different commands can be shown to the user.
	 */
	public commandList() {

		// Setup a new frame to display the commands.
		JFrame commandFrame = new JFrame();
		commandFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Setup the menu
		commandFrame.setJMenuBar(commandMenu());
		// Display the window.
		commandFrame.setResizable(false);
		commandFrame.setLocation(500, 100);
		commandFrame.setSize(450, 300);
		commandFrame.setVisible(true);

		// Sets up a panel so information can be added.
		JPanel commandPanel = new JPanel();
		commandPanel.setBackground(Color.WHITE);

		// Sets up new labels so information can be set to them.
		label = new JLabel("This panel will show the different commands that can be given.");
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();
		label9 = new JLabel();

		// Adds the labels to the panel.
		commandFrame.add(commandPanel);
		commandPanel.add(label);
		commandPanel.add(label2);
		commandPanel.add(label3);
		commandPanel.add(label4);
		commandPanel.add(label5);
		commandPanel.add(label6);
		commandPanel.add(label7);
		commandPanel.add(label8);
		commandPanel.add(label9);

	}

	/**
	 * Sets up the menu bar for easy navigations and to show the different commands
	 * that can be given.
	 * 
	 * @return commandBar
	 */
	JMenuBar commandMenu() {

		// Create the menu bar itself.
		JMenuBar commandBar = new JMenuBar();

		// Create a menu.
		JMenu menu1 = new JMenu("General");
		JMenu menu2 = new JMenu("Application");

		// Add the menu components to the menu bar.
		commandBar.add(menu1);
		commandBar.add(menu2);

		// Add the different attributes to the components.
		JMenuItem menuItem1 = new JMenuItem("General Commands");
		menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK));
		JMenuItem menuItem2 = new JMenuItem("Change Volume");
		menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK));
		JMenuItem menuItem3 = new JMenuItem("Change Voice");
		menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
		JMenuItem menuItem4 = new JMenuItem("Open Apps");
		menuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		JMenuItem menuItem5 = new JMenuItem("Close Apps");
		menuItem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));

		// Set up the menu bar.
		menu1.add(menuItem1);
		menu1.add(menuItem2);
		menu1.add(menuItem3);
		menu2.add(menuItem4);
		menu2.add(menuItem5);

		/**
		 * Inner anonymous class to describe the general commands to the user.
		 */
		menuItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Sets the labels to show some general commands.
				label.setText("General Commands that may be given: ");
				label2.setText("1) Hello - Say hello to the program for a response.");
				label3.setText("2) How're you? - Ask the computer how they are.");
				label4.setText("3) Thank You - Thank the program for its help.");
				label5.setText("4) What Time Is It? - Ask the program for the current time.");
				label6.setText("5) What Is The Date? - Ask the program for the current date.");
				label7.setText("6) Search Google - Allows the user to search google.");
				label8.setText("7) Search Song - Allows the user to search for a song on Spotify.");
				label9.setText("8) Terminate Program - Stops the program from running.");

			}
		});

		/**
		 * Inner anonymous class to describe the different ways to change the volume.
		 */
		menuItem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Sets the labels to tell the user how to change the volume.
				label.setText("Commands to change the volume of the program: ");
				label2.setText("1) Change Volume To Max - Turn volume to its highest setting.");
				label3.setText("2) Change Volume To Min - Turn volume to its lowest setting..");
				label4.setText("3) Change Volume To Medium - Turn volume to a medium setting.");
				label5.setText("4) Mute - Mutes the program.");
				label6.setText("");
				label7.setText("");
				label8.setText("");
				label9.setText("");

			}
		});

		/**
		 * Inner anonymous class to describe the different ways to change the voice of
		 * the program.
		 */
		menuItem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// sets the labels to inform the user of how to change the programs voice.
				label.setText("Commands to change the voice of the program: ");
				label2.setText("1) Change To Voice One - Voice changes to a different accent.");
				label3.setText("2) Change To Voice Two - Voice changes to a different accent.");
				label4.setText("3) Change To Voice Three - Voice changes to a different accent.");
				label5.setText("");
				label6.setText("");
				label7.setText("");
				label8.setText("");
				label9.setText("");

			}
		});

		/**
		 * Inner anonymous class to describe the different ways to open applications.
		 */
		menuItem4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Sets the labels to inform the user about the different applications that can
				// be opened.
				label.setText("Commands to open the different applications: ");
				label2.setText("1) Open Twitter - Opens the twitter application.");
				label3.setText("2) Open Spotify - Opens spotify so you can choose music..");
				label4.setText("3) Open Safari - Opens the internet.");
				label5.setText("4) Open Mail - Opens the mail app.");
				label6.setText("5) Open Word - Opens Microsoft Word.");
				label7.setText("");
				label8.setText("");
				label9.setText("");

			}
		});

		/**
		 * Inner anonymous class to describe the different ways to close applications.
		 */
		menuItem5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Sets the labels to inform the user about how to close different applications.
				label.setText("Commands to close the different applications: ");
				label2.setText("1) Close Twitter - Closes the twitter application.");
				label3.setText("2) Close Spotify - Closes spotify so you can choose music..");
				label4.setText("3) Close Safari - Closes the internet.");
				label5.setText("4) Close Mail - Closes the mail app.");
				label6.setText("5) Close Word - Closes Microsoft Word.");
				label7.setText("");
				label8.setText("");
				label9.setText("");

			}
		});

		// Runs the menu bar.
		return commandBar;

	}

}
