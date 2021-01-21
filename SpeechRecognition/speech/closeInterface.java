package speech;

/**
 * Interface which sets up the methods for closing 
 * the applications. {@link closeApps}
 * @author DylanPace
 *
 */
public interface closeInterface {
	
	/**
	 * Closes the twitter application.
	 * @param speech spoken input by user.
	 * @return closes twitter.
	 */
	public String closeTwitter(String speech);
	
	/**
	 * Closes the TextEdit application.
	 * @param speech spoken input by user.
	 * @return closes TextEdit.
	 */
	public String closeNotepad(String speech);
	
	/**
	 * Closes the music application Spotify.
	 * @param speech spoken input by user.
	 * @return Spotify closes.
	 */
	public String closeSpotify(String speech);
	
	/**
	 * Closes the Safari web browser.
	 * @param speech spoken input by user.
	 * @return Safari closes.
	 */
	public String closeSafari(String speech);
	
	/**
	 * Closes Microsoft Word.
	 * @param speech spoken input by user.
	 * @return word closes.
	 */
	public String closeWord(String speech);
	
	/**
	 * Closes the mail application.
	 * @param speech spoken input by user.
	 * @return mail closes.
	 */
	public String closeMail(String speech);

}
