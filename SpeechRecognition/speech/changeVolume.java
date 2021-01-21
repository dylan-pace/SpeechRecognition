package speech;

import java.io.IOException;

import tts.TextToSpeech;

/**
 * Class to change the volume depending on the spoken input
 * by the user. Methods are called from the {@link Main} class.
 * @author DylanPace
 *
 */
public class changeVolume implements volumeInterface{
	
	/**
	 * Instantiate the different class to be called throughout this drive
	 * class. They perform different operations depending on the input.
	 */
	TextToSpeech textToSpeech = new TextToSpeech();
	
	/**
	 * Method that changes the volume to the highest setting.
	 * @param speech spoken input by user.
	 * @return volume changes to max
	 */
	@Override
	public String maxVolume(String speech) {
		//Script required to change computer volume to low.
		String[] max = {"osascript", "-e","set volume 15"};
		try {
			//Runs the script.
			Runtime.getRuntime().exec(max);
		//Error if volume couldn't be maxed.
		} catch (IOException e) {
			textToSpeech.speak("Couldn't max volume.", 1.5f, false, true);
		}
		return "Volume on the highest setting";
	}
	
	/**
	 * Method that changes the volume to the lowest setting.
	 * @param speech spoken input by user.
	 * @return volume changes to lowest setting.
	 */
	@Override
	public String minVolume(String speech) {
		//Script required to change computer volume to low.
		String[] min = {"osascript", "-e","set volume 1"};
		try {
			//Runs the script.
			Runtime.getRuntime().exec(min);
		//Error if volume couldn't be lowered.
		} catch (IOException e) {
			textToSpeech.speak("Couldn't lower volume.", 1.5f, false, true);
		}
		return "Volume on the lowest setting";
	}
	
	/**
	 * Method that changes the volume to a medium setting.
	 * @param speech spoken input by user.
	 * @return volume chnages to a medium setting.
	 */
	@Override
	public String mediumVolume(String speech) {
		//Script required to change computer volume to medium.
		String[] mid = {"osascript", "-e","set volume 4"};
		try {
			//Runs the script.
			Runtime.getRuntime().exec(mid);
		//Error if volume couldn't be set to medium.
		} catch (IOException e) {
			textToSpeech.speak("Couldn't set volume to middle settings.", 1.5f, false, true);
		}
		return "Volume on a medium setting";
	}
	
	/**
	 * Method that mutes the program.
	 * @param speech spoken input by user.
	 * @return mutes the computer.
	 */
	@Override
	public String muteVolume(String speech) {
		//Script required to mute computer.
		String[] mute = {"osascript", "-e","set volume 0"};
		try {
			//Runs the script.
			Runtime.getRuntime().exec(mute);
		//Error if volume coudn't be muted.
		} catch (IOException e) {
			textToSpeech.speak("Couldn't mute volume.", 1.5f, false, true);
		}
		return "Volume has been muted";
	}

}
