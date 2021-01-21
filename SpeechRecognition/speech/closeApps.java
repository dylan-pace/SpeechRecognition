package speech;

import java.io.IOException;

import tts.TextToSpeech;

/**
 * Class that closes the application if they are 
 * currently running. The methods are called from the 
 * {@link Main} class. If applications are not running, nothing 
 * happens if they are called.
 * @author DylanPace
 *
 */
public class closeApps implements closeInterface{
	
	/**
	 * Instantiate the different class to be called throughout this drive
	 * class. They perform different operations depending on the input.
	 */
	TextToSpeech textToSpeech = new TextToSpeech();
	
	/**
	 * Method to close the twitter application if it is running.
	 * @param speech spoken input by user.
	 * @return Twitter closes.
	 */
	@Override
	public String closeTwitter(String speech) {
		System.out.println("Closing twitter...");
		try {
			textToSpeech.speak("closing twitter.", 1.5f, false, true);
			//Kill command for twitter.
			String cmds[] = {"killall","Twitter"};
			Runtime rt = Runtime.getRuntime();
			//Runs kill command.
			rt.exec(cmds);
		//Speaks error if twitter couldn't be closed.
		} catch (IOException e) {
			textToSpeech.speak("Could not close twitter.", 1.5f, false, true);
		}
		return "Closing Twitter...";
	}
	
	/**
	 * Method to close the TextEdit if it is running.
	 * @param speech spoken input by user.
	 * @return TextEdit closes.
	 */
	@Override
	public String closeNotepad(String speech) {
		System.out.println("Closing notepad...");
		try {
			textToSpeech.speak("closing notepad.", 1.5f, false, true);
			//Kill command for notepad.
			String cmds[] = {"killall","TextEdit"};
			Runtime rt = Runtime.getRuntime();
			//Runs kill command.
			rt.exec(cmds);
		//Speaks error if notepad couldn't be closed.
		} catch (IOException e) {
			textToSpeech.speak("Could not close notepad.", 1.5f, false, true);
		}
		return "Closing Notepad...";
	}
	
	/**
	 * Method to close spotify if it is running.
	 * @param speech spoken input by user.
	 * @return Spotify closes.
	 */
	@Override
	public String closeSpotify(String speech) {
		System.out.println("Closing spotify...");
		try {
			textToSpeech.speak("closing spotify.", 1.5f, false, true);
			//Kill command for spotify.
			String cmds[] = {"killall","Spotify"};
			Runtime rt = Runtime.getRuntime();
			//Runs kill command.
			rt.exec(cmds);
		//Speaks error if spotify couldn't be closed.
		} catch (IOException e) {
			textToSpeech.speak("Could not close spotify.", 1.5f, false, true);
		}
		return "Closing Spotify...";
	}
	
	/**
	 * Method to close the safari web browser if it is running.
	 * @param speech spoken input by user.
	 * @return Safari closes.
	 */
	@Override
	public String closeSafari(String speech) {
		System.out.println("Closing safari...");
		try {
			textToSpeech.speak("closing safari.", 1.5f, false, true);
			//Kill command for safari.
			String cmds[] = {"killall","Safari"};
			Runtime rt = Runtime.getRuntime();
			//Runs kill command.
			rt.exec(cmds);
		//Speaks error if safari couldn't be closed.
		} catch (IOException e) {
			textToSpeech.speak("Could not close safari.", 1.5f, false, true);
		}
		return "Closing Safari...";
	}
	
	/**
	 * Method to close Microsoft Word if it is running.
	 * @param speech spoken input by user.
	 * @return Word closes.
	 */
	@Override
	public String closeWord(String speech) {
		System.out.println("Closing word...");
		try {
			textToSpeech.speak("closing word.", 1.5f, false, true);
			//Kill command for word.
			String cmds[] = {"killall","Microsoft Word"};
			Runtime rt = Runtime.getRuntime();
			//Runs kill command.
			rt.exec(cmds);
		//Speaks error if word couldn't be closed.
		} catch (IOException e) {
			textToSpeech.speak("Could not close word.", 1.5f, false, true);
		}
		return "Closing Word...";
	}
	
	/**
	 * Method to close the mail application if it is running.
	 * @param speech spoken input by user.
	 * @return Mail closes.
	 */
	@Override
	public String closeMail(String speech) {
		System.out.println("Closing mail...");
		try {
			textToSpeech.speak("closing mail", 1.5f, false, true);
			//Kill command for mail.
			String cmds[] = {"killall","Mail"};
			Runtime rt = Runtime.getRuntime();
			//Runs kill command.
			rt.exec(cmds);
		//Speaks error if mail couldn't be closed.
		} catch (IOException e) {
			textToSpeech.speak("Could not close mail.", 1.5f, false, true);
		}
		return "Closing Mail...";
	}

}
