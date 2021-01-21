package speech;

/**
 * Interface which sets up the methods for opening 
 * the different applications. {@link openApps}
 * @author DylanPace
 *
 */
public interface openInterface {

	/**
	 * Opens twitter.
	 * @param speech spoken input by user.
	 * @return twitter opens.
	 */
	public String openTwitter(String speech);
	
	/**
	 * Opens the TextEdit application.
	 * @param speech spoken input by user.
	 * @return TextEdit opens.
	 */
	public String openNotepad(String speech);
	
	/**
	 * Opens the music application Spotify.
	 * @param speech spoken input by user.
	 * @return Spotify opens.
	 */
	public String openSpotify(String speech);
	
	/**
	 * Opens the web browser Safari.
	 * @param speech spoken input by user.
	 * @return Safari opens.
	 */
	public String openSafari(String speech);
	
	/**
	 * Opens Microsoft Word.
	 * @param speech spoken input by user.
	 * @return word opens.
	 */
	public String openWord(String speech);
	
	/**
	 * Opens mail application.
	 * @param speech spoken input by user.
	 * @return mail opens.
	 */
	public String openMail(String speech);
	
}
