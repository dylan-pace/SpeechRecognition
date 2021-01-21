package speech;

import java.io.IOException;

/**
 * Interface for the general commands existing in the program. 
 * {@link generalCommands}
 * @author DylanPace
 *
 */
public interface generalInterface {
	
	/**
	 * Calls the current time.
	 * @param speech spoken input by user.
	 * @return speaks the time.
	 */
	public String time(String speech);
	
	/**
	 * Get the current date.
	 * @param speech spoken input by user.
	 * @return speaks the date.
	 */
	public String date(String speech);
	
	/**
	 * Gives a random response when the user says 'thank you'.
	 * @param speech spoken input by user.
	 * @param name the name of the user.
	 * @return spoken 'you're welcome' message
	 */
	public String thankYou(String speech, String name);
	
	/**
	 * Gives a random response when user says 'hello'.
	 * @param speech spoken input by user.
	 * @param name the name of the user.
	 * @return spoken 'hello' message back.
	 */
	public String hello(String speech, String name);
	
	/**
	 * Allows the user to search google.
	 * @param speech spoken input by user.
	 * @return searches google for user input.
	 * @throws IOException 
	 */
	public String searchGoogle(String speech) throws IOException, NullPointerException;
	
	/**
	 * Stops the program from running.
	 * @param speech spoken input by user.
	 * @param name the name of the user.
	 * @return program ends.
	 */
	public String terminate(String speech, String name);

	/**
	 * Allows the user to search Spotify.
	 * @param speech spoken input by user.
	 * @return searches Spotify for user input.
	 * @throws IOException 
	 */
	public String searchSong(String speech) throws IOException, NullPointerException;

}