package speech;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ibm.icu.util.Calendar;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import tts.TextToSpeech;

/**
 * Class that is filled with different general commands and responses used for
 * conversational flow. The methods are called in the {@link Main} class.
 * 
 * @author DylanPace
 *
 */
public class generalCommands implements generalInterface {

	// Sets up the output string.
	protected String output;
	
	panelSetup frameSet = new panelSetup();

	// Logs the information of the speech program.
	private Logger logger2 = Logger.getLogger(getClass().getName());

	// The different threads used for calling the resources and creating the speech
	// input/output.
	Thread speechThread2;

	// LiveRecognizer recognises the words spoken into the system.
	private LiveSpeechRecognizer recogniser2;
	
	// Instantiate the outputs to be accessed by the {@Link Main} class.
	protected String helloResult;
	protected String thanksResult;
	protected String hour;
	protected String min;
	protected String weekDay;
	protected String day;
	protected String month;
	protected String year;
	
	//panelSetup panel = new panelSetup();

	/**
	 * Instantiate the different class to be called throughout this drive class.
	 * They perform different operations depending on the input.
	 */
	TextToSpeech textToSpeech = new TextToSpeech();

	/**
	 * Method that gives a spoken output of the current time.
	 * 
	 * @param speech spoken input by user.
	 * @return give the current time.
	 */
	@Override
	public String time(String speech) {
		// Saves the time in a particular format.
		hour = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
		min = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
		// Speaks the time, hour then minute.
		textToSpeech.speak(hour, 1.5f, false, true);
		textToSpeech.speak(min, 1.5f, false, true);

		return "The time is: " + hour + ":" + min;
	}

	/**
	 * Method that gives a spoken output of the current date.
	 * 
	 * @param speech spoken input given by user.
	 * @return gives the current date.
	 */
	@Override
	public String date(String speech) {
		// Saves the date in a particular format.
		weekDay = new SimpleDateFormat("EEEE").format(new Date());
		month = new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime());
		day = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		year = new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime());
		// Speaks the current date.
		textToSpeech.speak(weekDay + " the " + day + " of " + month + ", " + year, 1.5f, false, true);

		return "The date is " + weekDay + ", the " + day + " of " + month + ", " + year;
	}

	/**
	 * Gives random response when the user says 'thank you'.
	 * 
	 * @param speech spoken input by user.
	 * @param name the name of the user.
	 * @return return a 'you're welcome' message.
	 */
	@Override
	public String thankYou(String speech, String name) {
		// Creates a random number generator to pick a random response to the 'thank
		// you' input.
		Random rand2 = new Random();
		// Sets up an array of responses.
		String thanksResponse[] = { "you're welcome " + name, "Glad I could help " + name };
		// Randomly picks a number from the length of the array to output.
		int randomIndex2 = rand2.nextInt(thanksResponse.length);
		// Saves the output from the array as a string.
		thanksResult = thanksResponse[randomIndex2];
		// Speaks the chosen array value.
		textToSpeech.speak(thanksResult, 1.5f, false, true);

		return "You're welcome";
	}

	/**
	 * Gives random response when the user says 'hello'.
	 * 
	 * @param speech spoken input by user.
	 * @param name the name of the user.
	 * @return 'hello' will be answered.
	 */
	@Override
	public String hello(String speech, String name) {
		// Creates a random number generator to pick a random response to the 'hello'
		// input.
		Random rand = new Random();
		// Sets up an array of responses.
		String helloResponse[] = { "Hello " + name, "Hi " + name, "Hey " + name };
		// Randomly picks a number from the length of the array to output.
		int randomIndex = rand.nextInt(helloResponse.length);
		// Saves the output from the array as a string.
		helloResult = helloResponse[randomIndex];
		// Speaks the chosen array value.
		textToSpeech.speak(helloResult, 1.5f, false, true);

		return helloResult;
	}

	/**
	 * Ends the program when spoken by the user.
	 * 
	 * @param speech spoken input by user.
	 * @param name the name of the user.
	 * @return program will terminate.
	 */
	@Override
	public String terminate(String speech, String name) {
		textToSpeech.speak("goodbye " + name, 1.5f, false, true);
		// If this statement is spoken, the program will end.
		System.exit(1);

		return "System end";
	}

	/**
	 * Allows the user to input a string which will be searched for on google.
	 * 
	 * @param speech spoken input by user.
	 * @return google will be searched.
	 * @throws IOException
	 */
	@Override
	public String searchGoogle(String speech) throws IOException{

		// Configuration class instantiated to add the grammar and language for the
		// speech synthesis.
		Configuration config = new Configuration();

		// Load model from the jar.
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

		config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

		try {
			// Allows the words in the .gram file to be recognised.
			recogniser2 = new LiveSpeechRecognizer(config);
		} catch (IOException ex) {
			logger2.log(Level.SEVERE, null, ex);
		} 

		// Start recognition process pruning previously cached data.
		recogniser2.startRecognition(true);

		// Make sure the thread has started.
		if (speechThread2 != null && speechThread2.isAlive())
			return speech;

		// Initialise the thread and allow the speech to be entered.
		speechThread2 = new Thread(() -> {
			logger2.log(Level.INFO, "You can start to speak...\n");

			try {
				while (true) {
					/*
					 * This method will return when the end of speech is reached. Note that the end
					 * pointer will determine the end of speech.
					 */
					SpeechResult speechResult2 = recogniser2.getResult();
					if (speechResult2 != null) {

						// Output the result that was inputed by the user.
						output = speechResult2.getHypothesis();
						if (output.contains("search")) {
							System.out.println("You said: [" + output + "]\n");
							makeDecision(output);
						} else {
							speechThread2 = null;
							recogniser2 = null;

							// Load model from the jar.
							config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
							config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

							// Grammar file holds key phrases recognised by this program.
							config.setGrammarPath("grammar");
							config.setGrammarName("grammar");
							config.setUseGrammar(true);
						}

					} else
						// If the program couldn't understand what was said.
						logger2.log(Level.INFO, "I can't understand what you said.\n");

				}
				// Catches any not handled exceptions.
			} catch (Exception ex) {
				// logger2.log(Level.WARNING, null, ex);
				System.out.println("Search finished.");
			}

			// Output when the thread ends.
			logger2.log(Level.INFO, "SpeechThread has exited...");
		});

		// Start the thread when everything is set up.
		speechThread2.start();

		return "Google will be searched.";

	}

	/**
	 * Allows the user to input a string which will be searched for on Spotify.
	 * 
	 * @param speech spoken input by user.
	 * @return a song on Spotify will be searched and played.
	 * @throws IOException
	 */
	@Override
	public String searchSong(String speech) throws IOException{

		
		// Configuration class instantiated to add the grammar and language for the
		// speech synthesis.
		Configuration config2 = new Configuration();

		/*
		// Load model from the jar.
		config2.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config2.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		config2.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		*/
		
		// Load model from the jar.
		config2.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config2.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

		// Grammar file holds key phrases recognised by this program.
		config2.setGrammarPath("grammar");
		config2.setGrammarName("grammar");
		config2.setUseGrammar(true);

		try {
			// Allows the words in the .gram file to be recognised.
			recogniser2 = new LiveSpeechRecognizer(config2);
		} catch (IOException ex) {
			logger2.log(Level.SEVERE, null, ex);
		}

		// Start recognition process pruning previously cached data.
		recogniser2.startRecognition(true);

		// Make sure the thread has started.
		if (speechThread2 != null && speechThread2.isAlive())
			return speech;

		// Initialise the thread and allow the speech to be entered.
		speechThread2 = new Thread(() -> {
			logger2.log(Level.INFO, "You can start to speak...\n");

			try {
				while (true) {
					
					/*
					 * This method will return when the end of speech is reached. Note that the end
					 * pointer will determine the end of speech.
					 */
					SpeechResult speechResult2 = recogniser2.getResult();
					if (speechResult2 != null) {

						// Output the result that was inputed by the user.
						output = speechResult2.getHypothesis();
						if (output.contains("play")) {
							System.out.println("You said: [" + output + "]\n");
							makeDecision(output);
						}
						else {
							output = "";
							speechThread2 = null;
							recogniser2 = null;

							// Load model from the jar.
							config2.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
							config2.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

							// Grammar file holds key phrases recognised by this program.
							config2.setGrammarPath("grammar");
							config2.setGrammarName("grammar");
							config2.setUseGrammar(true);
						}

					} else
						// If the program couldn't understand what was said.
						logger2.log(Level.INFO, "I can't understand what you said.\n");

				}
				// Catches any generic unchecked exceptions.
			} catch (Exception ex) {
				// logger2.log(Level.WARNING, null, ex);
				System.out.println("Search finished.");
			}

			// Output when the thread ends.
			logger2.log(Level.INFO, "SpeechThread has exited...");
		});

		// Start the thread when everything is set up.
		speechThread2.start();

		return "Spotify will be searched.";

	}

	/**
	 * Method that either searches google for the search term inputed by the user or plays
	 * a song defined by the user, depending on the input they chose.
	 * @param output either google is searched or a song is played
	 * @throws IOException file management errors when opening apps.
	 * @throws AWTException deals with an error surrounding java.awt.robot;
	 */
	private void makeDecision(String output) throws IOException, AWTException {

		// Checks if the user correctly asked to play a song.
		if (output.contains("play")) {

			if (output != null) {

				try {
					
					// Split then save the song choice.
					String[] splits = output.split("play ");
					String searchTerm = splits[1];

					// Kill the thread when an output has been given.
					speechThread2 = null;
					recogniser2 = null;

					// Configuration class instantiated to add the grammar and language for the
					// speech synthesis.
					Configuration config2 = new Configuration();

					// Load model from the jar.
					config2.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
					config2.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

					// Grammar file holds key phrases recognised by this program.
					config2.setGrammarPath("grammar");
					config2.setGrammarName("grammar");
					config2.setUseGrammar(true);
					
					//Execution order to run spotify.
					Runtime.getRuntime().exec("open /Applications/Spotify.app");
					
					//Copies the search term.
					StringSelection stringSelection = new StringSelection(searchTerm);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, stringSelection);
					
					//Robot to automatically perform commands.
					Robot robot = new Robot();
					robot.delay(300);
					
					//Copies the search term.
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.delay(200);
					
					//Opens the search bar.
					robot.keyPress(KeyEvent.VK_META);
					robot.keyPress(KeyEvent.VK_L);	
					robot.keyRelease(KeyEvent.VK_META);
					robot.keyRelease(KeyEvent.VK_L);
					robot.delay(200);

					//Pastes the search term.
					robot.keyPress(KeyEvent.VK_META);
					robot.keyPress(KeyEvent.VK_V);	
					robot.keyRelease(KeyEvent.VK_META);
					robot.keyRelease(KeyEvent.VK_V);
					robot.delay(200);
					
					//Presses enter.
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(200);
					
					//Presses tab four times.
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.delay(200);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.delay(200);
					
					//Presses play to play the song.
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					textToSpeech.speak("Now playing " + searchTerm, 1.5f, false, true);
					robot.delay(500);
					robot.keyPress(KeyEvent.VK_ENTER);
					
					//frameSet.label5.setText("play " + searchTerm);
					
					// Exception if there is a null value.
				} catch (NoSuchElementException e) {
					textToSpeech.speak("Couldn't search spotify", 1.5f, false, true);
					System.out.println("Couldn't search spotify");
				}
			}
		} else {
			// Return blank statement if output starts with anything other than 'play'.
			textToSpeech.speak("", 1.5f, false, true);
		}

		// Makes sure the user searches something to google.
		if (output.contains("search")) {
			// Save the google search URL to be added to later and inputed into the browser.
			final String GOOGLE_SEARCH_URL = "https://www.google.com/search";

			if (output != null) {

				try {
					// Split then save the search term.
					String[] splits = output.split("search ");
					String searchTerm = splits[1];
					int num = 5;

					// String to be searched is set up as a URL.
					String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm + "&num=" + num;

					// Connect to Internet with the JSoup reference libraries.
					Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

					Elements results = doc.select("h3.r > a");

					// Parse through results and show top five urls.
					for (Element result : results) {
						String linkHref = result.attr("href");
						String linkText = result.text();
						// String to output the urls in a readable order.
						System.out.println(
								"Text: " + linkText + ". URL: " + linkHref.substring(6, linkHref.indexOf("&")));
					}

					// Kills the threads when the output is given.
					speechThread2 = null;
					recogniser2 = null;

					// Configuration class instantiated to add the grammar and language for the
					// speech synthesis.
					Configuration config = new Configuration();

					// Load model from the jar.
					config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
					config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

					// Grammar file holds key phrases recognised by this program.
					config.setGrammarPath("grammar");
					config.setGrammarName("grammar");
					config.setUseGrammar(true);

					// Execution order to run Safari.
					Runtime.getRuntime().exec(new String[] { "open", "-a", "Safari", searchURL });
					//frameSet.label5.setText("search " + searchTerm);
					// Exception if there is a null value.
				} catch (NoSuchElementException e) {
					textToSpeech.speak("Couldn't search google", 1.5f, false, true);
					System.out.println("Couldn't search google");
				}
			}
		}
	}
}