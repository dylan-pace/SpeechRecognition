package speech;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import tts.TextToSpeech;

/**
 * Class to deal with speech tests, seeing the capabilities of the program.
 * @author DylanPace
 *
 */
public class speechTests {
	
	TextToSpeech textToSpeech = new TextToSpeech();

	/**
	 * Test to check the program will synthesis speech with a valid input.
	 */
	@Test
	public void speechTest_valid() {
		
		String speech1 = "speech test";
		textToSpeech.speak(speech1, 1.5f, false, true);
		//The program is successful at synthesising an ordinary word.
	}
	
	/**
	 * Test to make sure the program won't try and synthesis an invalid input that isn't a word.
	 */
	@Test
	public void speechTest_invalid() {
		String speech2 = "''";
		textToSpeech.speak(speech2, 1.5f, false, true);
		/*
		 * The program doesn't try and synthesis punctuation or any invalid character and an error 
	     * isn't thrown crashing the program, it just ignores it.
		 */
	}
	
	/**
	 * Test to make sure that is an invalid word is input, the program won't throw a synthesis exception.
	 */
	@Test
	public void speechTest_invalidWord() {
		String speech3 ="gtrzqgajdnckhs";
		textToSpeech.speak(speech3, 1.5f, false, true);
		//The word is synthesised even though it isn't a real word and no synthesis exception is thrown.
	}
	
	/**
	 * Test to make sure that the program is able to convert a integer to a spoken output.
	 */
	@Test
	public void speechTest_number() {
		String number1 = "1";
		textToSpeech.speak(number1, 1.5f, false, true);
		//The program can convert a number to a spoken output.
	}
	
	/**
	 * Test to ensure that the program can deal with long sentences and use the punctuation effectively.
	 */
	@Test
	public void speechTest_sentence() {
		String longSentence = "This is a speech Test. It makes sure it can run longer sentences, dealing with punctuation.";
		textToSpeech.speak(longSentence, 1.5f, false, true);
		//The program can run sentences as long as they need to be and deals with punctuation, such as taking pauses on full stops.
	}
	
	@Test
	public void speechTest_changeVolume() {
		//Script required to change computer volume to low.
		String[] min = {"osascript", "-e","set volume 1"};
		try {
			//Runs the script.
			Runtime.getRuntime().exec(min);
		//Error if volume couldn't be lowered.
		} catch (IOException e) {
			textToSpeech.speak("Couldn't lower volume.", 1.5f, false, true);
		}
	}

}
