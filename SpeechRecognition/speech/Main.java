package speech;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import tts.TextToSpeech;

/**
 * Class to set up the different speech threads and gives the responses to the
 * statements in the .gram file. This program works best with the MacOS.
 * 
 * @author DylanPace
 *
 */
public class Main {

	/**
	 * Instantiate the different class to be called throughout this drive class.
	 * They perform different operations depending on the input.
	 */
	NumberToString numberToString = new NumberToString();
	StringToNumber stringToNumber = new StringToNumber();
	TextToSpeech textToSpeech = new TextToSpeech();
	panelSetup frameSet = new panelSetup();

	// Logs the information of the speech program.
	private Logger logger = Logger.getLogger(getClass().getName());

	// Result of the inputed data.
	protected String result;
	protected String out;

	// The different threads used for calling the resources and creating the speech
	// input/output.
	Thread speechThread;
	Thread resourcesThread;

	// LiveRecognizer recognises the words spoken into the system.
	private LiveSpeechRecognizer recogniser;

	/**
	 * Constructor, starts the listener.
	 */
	public Main() {

		// Loading message, tells the user the program is loading.
		logger.log(Level.INFO, "Loading..\n");

		// Configuration class instantiated to add the grammar and language for the
		// speech synthesis.
		Configuration config = new Configuration();

		// Load model from the jar.
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

		// configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

		// Grammar file holds key phrases recognised by this program.
		config.setGrammarPath("grammar");
		config.setGrammarName("grammar");
		config.setUseGrammar(true);

		try {
			// Allows the words in the .gram file to be recognised.
			recogniser = new LiveSpeechRecognizer(config);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}

		// Start recognition process pruning previously cached data.
		recogniser.startRecognition(true);

		// Run the threads.
		startSpeechThread();
		startResourcesThread();

	}

	/**
	 * 
	 * Starting the main Thread of speech recognition
	 */
	protected void startSpeechThread() {

		// Make sure the thread has started.
		if (speechThread != null && speechThread.isAlive())
			return;

		// Initialise the thread and allow the speech to be entered.
		speechThread = new Thread(() -> {

			logger.log(Level.INFO, "You can start to speak...\n");

			try {
				while (true) {

					/*
					 * This method will return when the end of speech is reached. Note that the end
					 * pointer will determine the end of speech.
					 */
					SpeechResult speechResult = recogniser.getResult();
					if (speechResult != null) {

						// Output the result that was inputed by the user.
						result = speechResult.getHypothesis();
						System.out.println("You said: [" + result + "]\n");
						// frameSet.label3.setText(result);
						makeGeneralDecision(result);

					} else
						// If the program couldn't understand what was said.
						logger.log(Level.INFO, "I can't understand what you said.\n");

				}
				// Catches any not handled exceptions.
			} catch (Exception ex) {
				logger.log(Level.WARNING, null, ex);
			}

			// Output when the thread ends.
			logger.log(Level.INFO, "SpeechThread has exited...");
		});

		// Start the thread when everything is set up.
		speechThread.start();

	}

	/**
	 * Starting a Thread that checks if the resources needed to the
	 * SpeechRecognition library are available
	 */
	protected void startResourcesThread() {

		// Make sure the thread has started.
		if (resourcesThread != null && resourcesThread.isAlive())
			return;

		// Sets up a new thread for resources.
		resourcesThread = new Thread(() -> {
			try {

				// Detect if the microphone is available.
				while (true) {
					if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
						// logger.log(Level.INFO, "Microphone is available.\n");
					} else {
						logger.log(Level.INFO, "Microphone is not available.\n");
						textToSpeech.speak("microphone not available", 1.5f, false, true);

					}

					// Sleep so it isn't constantly running.
					Thread.sleep(350);
				}

				// Catch the interrupts it the thread.
			} catch (InterruptedException ex) {
				logger.log(Level.WARNING, null, ex);
				resourcesThread.interrupt();
			}
		});

		// Start the thread.
		resourcesThread.start();
	}

	/**
	 * Takes a decision based on the given result.
	 * 
	 * @param speech The speech inputed by the user.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void makeGeneralDecision(String speech) throws InterruptedException, IOException {

		// Saves my name.
		String name = "Dylan";
		addMenuBar menu = new addMenuBar();
		if (menu.infoField.getText() != null || menu.infoField.getText() != "" || menu.infoField.getText() != " ") {
			name = menu.infoField.getText();
			// name = menu.info;
		}

		// Instantiates the {@link generalCommands} class.
		generalCommands genComs = new generalCommands();
		// Instantiates the {@link openApps} class.
		openApps open = new openApps();
		// Instantiates the {@link closeApps} class.
		closeApps close = new closeApps();
		// Instantiates the {@link changeVolume} class.
		changeVolume changeVol = new changeVolume();

		/*
		 * These if statements are set up as to make sure that if any of these phrases
		 * are heard, as set up in the .gram file, an appropriate response will be
		 * given.
		 */
		if ("how're you".equalsIgnoreCase(speech)) {
			// Speaks output.
			textToSpeech.speak("Good thank you " + name, 1.5f, false, true);
			out = "Good thank you " + name;
			// frameSet.label5.setText(out);

		}

		// Deals with the input 'hello'.
		else if (speech.contains("hello")) {
			// Passes the speech through the class to gain a response.
			genComs.hello(speech, name);
			String helloRes = genComs.helloResult;
			// frameSet.label5.setText(helloRes);
		}

		// Changes the voice to 'cmu-slt-hsmm' when prompted.
		else if (speech.contains("change to voice one")) {
			// Changes voice.
			textToSpeech.setVoice("cmu-slt-hsmm");
			textToSpeech.speak("Done", 1.5f, false, true);
			// frameSet.label5.setText("(program voice changed to voice one)");
		}

		// Changes the voice to 'dfki-poppy-hsmm' when prompted.
		else if (speech.contains("change to voice two")) {
			// Changes voice.
			textToSpeech.setVoice("dfki-poppy-hsmm");
			textToSpeech.speak("Done", 1.5f, false, true);
			// frameSet.label5.setText("(program voice changed to voice two)");
		}

		// Changes the voice to 'cmu-rms-hsmm' when prompted.
		else if (speech.contains("change to voice three")) {
			// Changes voice.
			textToSpeech.setVoice("cmu-rms-hsmm");
			textToSpeech.speak("Done", 1.5f, false, true);
			// frameSet.label5.setText("(program voice changed to voice three)");
		}

		// Says you're welcome. General greeting for conversational flow.
		else if (speech.contains("thank you")) {
			// Gains a random response from the method.
			genComs.thankYou(speech, name);
			String ty = genComs.thanksResult;
			// frameSet.label5.setText(ty);
		}

		// Opens the notepad.
		else if (speech.contains("open notepad")) {
			// Call the method that opens TextEdit.
			open.openNotepad(speech);
			// frameSet.label5.setText("opening notepad");
		}

		// Closes notepad if it is running.
		else if (speech.contains("close notepad")) {
			// Call the method that closes the TextEdit.
			close.closeNotepad(speech);
			// frameSet.label5.setText("closing notepad");
		}

		// Opens twitter.
		else if (speech.contains("open twitter")) {
			// Call the method that opens twitter.
			open.openTwitter(speech);
			// frameSet.label5.setText("opening twitter");
		}

		// Closes twitter if it is running.
		else if (speech.contains("close twitter")) {
			// Call the method that closes twitter.
			close.closeTwitter(speech);
			// frameSet.label5.setText("closing twitter");
		}

		// Opens spotify.
		else if (speech.contains("open spotify")) {
			// Call the method that opens spotify.
			open.openSpotify(speech);
			// frameSet.label5.setText("opening spotify");
		}

		// Closes spotify if it is running.
		else if (speech.contains("close spotify")) {
			// Call the method that closes spotify.
			close.closeSpotify(speech);
			// frameSet.label5.setText("closing spotify");
		}

		// Opens safari.
		else if (speech.contains("open safari")) {
			// Call the method that opens safari.
			open.openSafari(speech);
			// frameSet.label5.setText("opening safari");
		}

		// Closes safari if it is running.
		else if (speech.contains("close safari")) {
			// Call the method that closes safari.
			close.closeSafari(speech);
			// frameSet.label5.setText("closing safari");
		}

		// Opens mail.
		else if (speech.contains("open mail")) {
			// Call the method that opens mail.
			open.openMail(speech);
			// frameSet.label5.setText("opening mail");
		}

		// Closes mail if it is running.
		else if (speech.contains("close mail")) {
			// Call the method that closes mail.
			close.closeMail(speech);
			// frameSet.label5.setText("closing mail");
		}

		// Opens word.
		else if (speech.contains("open word")) {
			// Call the method that opens word.
			open.openWord(speech);
			// frameSet.label5.setText("opening word");
		}

		// Closes word if it is running.
		else if (speech.contains("close word")) {
			// Call the method that closes word.
			close.closeWord(speech);
			// frameSet.label5.setText("closing word");
		}

		// Outputs the time.
		else if (speech.contains("what time is it")) {
			// Gets the time from the appropriate method.
			genComs.time(speech);
			String h = genComs.hour;
			String m = genComs.min;
			// frameSet.label5.setText(h + ":" + m);
		}

		// Outputs the date.
		else if (speech.contains("what is the date")) {
			// Gets the date from the appropriate method.
			genComs.date(speech);
			String wd = genComs.weekDay;
			String d = genComs.day;
			String m = genComs.month;
			String y = genComs.year;
			// frameSet.label5.setText(wd + ", " + d + " " + m + " " + y);
		}

		// Sets the computer to its highest setting.
		else if (speech.contains("change volume to max")) {
			// Calls method to change volume to max.
			changeVol.maxVolume(speech);
			// frameSet.label5.setText("(volume changed to max)");
		}

		// Turns the computers volume down to its lowest setting.
		else if (speech.contains("change volume to min")) {
			// Calls method to change volume down low.
			changeVol.minVolume(speech);
			// frameSet.label5.setText("(volume changed to min");
		}

		// Turns the computers volume down to a medium setting.
		else if (speech.contains("change volume to medium")) {
			// Calls method to set volume to medium.
			changeVol.mediumVolume(speech);
			// frameSet.label5.setText("(volume changed to medium");
		}

		// Mutes the computer.
		else if (speech.contains("mute")) {
			// Runs the method in the {@link changeVolume} class
			changeVol.muteVolume(speech);
			// frameSet.label5.setText("(volume changed to mute");
		}

		// Search google.
		else if (speech.contains("search google")) {
			// Runs the method to search google.
			genComs.searchGoogle(speech);
		}

		// Search a song on spotify.
		else if (speech.contains("search song")) {
			// Runs the method to search spotify.
			genComs.searchSong(speech);
		}
		
		// What is your purpose?
		else if(speech.contains("what is your purpose")) {
			// Speak.
			textToSpeech.speak("I am a speech recognition system. I have the ability to synthesise speech.", 1.5f, false, true);
		}

		// Terminates the program.
		else if (speech.contains("terminate program")) {
			// Calls method to end the program.
			genComs.terminate(speech, name);
		}

		// Split the sentence
		String[] inputArray = speech.split(" ");

		// Return if user said only one number
		if (inputArray.length != 3) 
			return;

		if (inputArray[1].contains("plus") || inputArray[1].contains("minus") || inputArray[1].contains("multiply") || inputArray[1].contains("divide")) {

			// Find the two numbers
			int number1 = stringToNumber.convert(inputArray[0]);
			int number2 = stringToNumber.convert(inputArray[2]);

			// Calculation result in number representation
			int calcResult = 0;
			String symbol = "?";

			// Find the mathematical symbol matching the spoken input.
			if ("plus".equals(inputArray[1])) {
				calcResult = number1 + number2;
				symbol = "+";
			} else if ("minus".equals(inputArray[1])) {
				calcResult = number1 - number2;
				symbol = "-";
			} else if ("multiply".equals(inputArray[1])) {
				calcResult = number1 * number2;
				symbol = "*";
			} else if ("divided by".equals(inputArray[1])) {
				calcResult = number1 / number2;
				symbol = "/";
			} else {
				return;
			}

			String res = numberToString.convert(Math.abs(calcResult));

			// Output the result with words.
			System.out.println("Said:[ " + speech + " ]\n\t\t which after calculation is:[ "
					+ (calcResult >= 0 ? "" : "minus ") + res + " ] \n");

			// Output the result with numbers and math.
			System.out.println("Said:[ " + number1 + " " + symbol + " " + number2
					+ "]\n\t\t which after calculation is:[ " + calcResult + " ]");

			// Makes the program speak the result.
			textToSpeech.speak((calcResult >= 0 ? "" : "minus ") + res, 1.5f, false, true);

		} else {
			//textToSpeech.speak("no", 1.5f, false, true);
			return;
		}
	}

}