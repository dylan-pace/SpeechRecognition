package speech;

import java.io.IOException;

import tts.TextToSpeech;

/**
 * Class that opens the different applications if the user
 * tells it to. The methods get the speech input from the {@link Main} class.
 * @author DylanPace
 *
 */
public class openApps implements openInterface{
	
	/**
	 * Instantiate the different class to be called throughout this drive
	 * class. They perform different operations depending on the input.
	 */
	TextToSpeech textToSpeech = new TextToSpeech();
	
	/**
	 * Method to open the twitter application.
	 * @param speech the speech input by the user.
	 * @return a string telling the user the opening was successful.
	 */
	@Override
	public String openTwitter(String speech) {
		
		System.out.println("Opening twitter...");
		try {
			//Execution order to run twitter.
			Runtime.getRuntime().exec("open /Applications/Twitter.app");
			textToSpeech.speak("opening twitter", 1.5f, false, true);
		//Error in case twitter couldn't be run
		} catch (IOException e) {
			textToSpeech.speak("Could not open twitter.", 1.5f, false, true);
		}
		
		return "Opening Twitter...";
	}
	
	/**
	 * Method to open the TextEdit.
	 * @param speech the speech input by the user.
	 * @return a string telling the user that TextEdit was opened successfully.
	 */
	@Override
	public String openNotepad(String speech) {
		System.out.println("Opening notepad...");
		try {
			//Execution order to run the notepad.
			Runtime.getRuntime().exec("open /Applications/TextEdit.app");
			textToSpeech.speak("opening notepad", 1.5f, false, true);
		//Error in case notepad couldn't be run
		} catch (IOException e) {
			textToSpeech.speak("Could not open notepad.", 1.5f, false, true);
		}
		return "Opening Notepad...";
	}
	
	/**
	 * Method to open the application Spotify.
	 * @param speech the speech input by user.
	 * @return string telling Spotify was opened.
	 */
	@Override
	public String openSpotify(String speech) {
		System.out.println("Opening spotify...");
		try {
			//Execution order to run spotify.
			Runtime.getRuntime().exec("open /Applications/Spotify.app");
			textToSpeech.speak("opening spotify", 1.5f, false, true);
		//Error caught in case spotify couldn't be run
		} catch (IOException e) {
			textToSpeech.speak("Could not open spotify.", 1.5f, false, true);
		}
		return "Opening Spotify...";
	}
	
	/**
	 * Method to open the web browser Safari.
	 * @param speech the speech input by the user.
	 * @return string telling Safari was opened.
	 */
	@Override
	public String openSafari(String speech) {
		System.out.println("Opening safari...");
		try {
			//Execution order to run safari.
			Runtime.getRuntime().exec("open /Applications/Safari.app");
			textToSpeech.speak("opening safari", 1.5f, false, true);
		//Error in case safari couldn't be run.
		} catch (IOException e) {
			textToSpeech.speak("Could not open safari.", 1.5f, false, true);
		}
		return "Opening Safari...";
	}
	
	/**
	 * Method to open Microsoft Word.
	 * @param speech spoken input by user.
	 * @return string telling Word was opened.
	 */
	@Override
	public String openWord(String speech) {
		System.out.println("Opening word...");
		try {
			String word[] = { "open", "/Applications/Microsoft Word.app" };
			//Execution order to run word.
			Runtime.getRuntime().exec(word);
			textToSpeech.speak("opening word", 1.5f, false, true);
		//Error in case word couldn't be run.
		} catch (IOException e) {
			textToSpeech.speak("Could not open word.", 1.5f, false, true);
		}
		return "Opening Word...";
	}
	
	/**
	 * Method to open the mail application.
	 * @param speech spoken input by user.
	 * @return string telling Mail was opened.
	 */
	@Override
	public String openMail(String speech) {
		System.out.println("Opening mail...");
		try {
			//Execution order to run mail.
			Runtime.getRuntime().exec("open /Applications/Mail.app");
			textToSpeech.speak("opening mail", 1.5f, false, true);
		//Error in case mail couldn't be run.
		} catch (IOException e) {
			textToSpeech.speak("Could not open mail.", 1.5f, false, true);
		}
		return "Opening Mail...";
	}

}
