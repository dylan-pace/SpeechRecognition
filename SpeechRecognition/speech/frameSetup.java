package speech;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Class to start the program, calling the menu bar and panels to add to the frame.
 * @author DylanPace
 *
 */
public class frameSetup {
	
	/**
	 * Starts the program, setting up the components.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Create and set up the window.
		JFrame frame = new JFrame("JARVIS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		addMenuBar menuBar = new addMenuBar();

		// Setup the menu
		frame.setJMenuBar(menuBar.menuSetup());

		// Gives a layout to the frame.
		frame.setLayout(new BorderLayout(5, 5));

		// Add the panel
		frame.getContentPane().add(menuBar.panel);

		// Display the window.
		frame.setResizable(false);
		frame.setSize(500, 250);
		frame.setVisible(true);

	}

}
