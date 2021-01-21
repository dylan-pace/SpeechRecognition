package tts;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import marytts.util.data.audio.MonoAudioInputStream;
import marytts.util.data.audio.StereoAudioInputStream;

/**
 * A single Thread Audio Player. Once used it has to be initialised again
 * 
 * @author DylanPace
 *
 */
public class AudioPlayer extends Thread {

	//Set up the audio variables to start the player.
	public static final int	MONO = 0;
	public static final int	STEREO = 3;
	public static final int	LEFT_ONLY = 1;
	public static final int	RIGHT_ONLY = 2;
	private AudioInputStream inputStream;
	private LineListener listener;
	private SourceDataLine dataLine;
	private int	outputMode;

	//Status of the threads.
	private Status status = Status.WAITING;
	private boolean	exitRequested = false;
	private float gain = 1.0f;

	/**
	 * The status of the player
	 * 
	 * @author DylanPace
	 *
	 */
	public enum Status {
		/**
		 * 
		 */
		WAITING,
		/**
		* 
		*/
		PLAYING;
	}

	/**
	 * AudioPlayer which can be used if audio stream is to be set separately,
	 * using setAudio().
	 *
	 */
	public AudioPlayer() {
	}

	/**
	 * @param audioFile
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public AudioPlayer(File audioFile) throws IOException, UnsupportedAudioFileException {
		this.inputStream = AudioSystem.getAudioInputStream(audioFile);
	}

	/**
	 * @param ais
	 */
	public AudioPlayer(AudioInputStream ais) {
		this.inputStream = ais;
	}

	/**
	 * @param audioFile
	 * @param lineListener
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public AudioPlayer(File audioFile, LineListener lineListener) throws IOException, UnsupportedAudioFileException {
		this.inputStream = AudioSystem.getAudioInputStream(audioFile);
		this.listener = lineListener;
	}

	/**
	 * @param ais
	 * @param lineListener
	 */
	public AudioPlayer(AudioInputStream ais, LineListener lineListener) {
		this.inputStream = ais;
		this.listener = lineListener;
	}

	/**
	 * @param audioFile
	 * @param line
	 * @param lineListener
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public AudioPlayer(File audioFile, SourceDataLine line, LineListener lineListener)
			throws IOException, UnsupportedAudioFileException {
		this.inputStream = AudioSystem.getAudioInputStream(audioFile);
		this.dataLine = line;
		this.listener = lineListener;
	}

	/**
	 * @param ais
	 * @param line
	 * @param lineListener
	 */
	public AudioPlayer(AudioInputStream ais, SourceDataLine line, LineListener lineListener) {
		this.inputStream = ais;
		this.dataLine = line;
		this.listener = lineListener;
	}

	/**
	 * 
	 * @param audioFile audio file
	 * @param line line of data
	 * @param lineListener lineListener
	 * @param outputMode
	 *            if MONO, force output to be mono; if STEREO, force output to
	 *            be STEREO; if LEFT_ONLY, play a mono signal over the left
	 *            channel of a stereo output, or mute the right channel of a
	 *            stereo signal; if RIGHT_ONLY, do the same with the right
	 *            output channel.
	 * @throws IOException file couldn't be found
	 * @throws UnsupportedAudioFileException audio file wasn't supported.
	 */
	public AudioPlayer(File audioFile, SourceDataLine line, LineListener lineListener, int outputMode)
			throws IOException, UnsupportedAudioFileException {
		this.inputStream = AudioSystem.getAudioInputStream(audioFile);
		this.dataLine = line;
		this.listener = lineListener;
		this.outputMode = outputMode;
	}

	/**
	 * 
	 * @param ais ais
	 * @param line line
	 * @param lineListener lineListener
	 * @param outputMode
	 *            if MONO, force output to be mono; if STEREO, force output to
	 *            be STEREO; if LEFT_ONLY, play a mono signal over the left
	 *            channel of a stereo output, or mute the right channel of a
	 *            stereo signal; if RIGHT_ONLY, do the same with the right
	 *            output channel.
	 */
	public AudioPlayer(AudioInputStream ais, SourceDataLine line, LineListener lineListener, int outputMode) {
		this.inputStream = ais;
		this.dataLine = line;
		this.listener = lineListener;
		this.outputMode = outputMode;
	}

	/**
	 * @param audio
	 */
	public void setAudio(AudioInputStream audio) {
		if (status == Status.PLAYING) {
			throw new IllegalStateException("Cannot set audio while playing");
		}
		this.inputStream = audio;
	}

	/**
	 * Cancel the AudioPlayer which will cause the Thread to exit
	 */
	public void cancel() {
		if (dataLine != null) {
			//The data line will be stopper if it detects a null value, stopping the thread.
			dataLine.stop();
		}
		exitRequested = true;
	}

	/**
	 * @return The SourceDataLine
	 */
	public SourceDataLine getLine() {
		return dataLine;
	}

	/**
	 * Returns the GainValue
	 */
	public float getGainValue() {
		return gain;
	}

	/**
	 * Sets Gain value. Line should be opened before calling this method. Linear
	 * scale.
	 * 
	 * @param fGain
	 */
	public void setGain(float fGain) {

		//Set the value.
		gain = fGain;

		//Give the data line a control value.
		if (dataLine != null && dataLine.isControlSupported(FloatControl.Type.MASTER_GAIN))
			((FloatControl) dataLine.getControl(FloatControl.Type.MASTER_GAIN))
					.setValue((float) (20 * Math.log10(fGain <= 0.0 ? 0.0000 : fGain)));

	}

	/**
	 * Runs the audio player.
	 */
	@Override
	public void run() {

		//Method to set up the audio player for synthesised speech.
		status = Status.PLAYING;
		AudioFormat audioFormat = inputStream.getFormat();
		if (audioFormat.getChannels() == 1) {
			if (outputMode != 0) {
				//Sets the input stream to retrieve the speech inputs.
				inputStream = new StereoAudioInputStream(inputStream, outputMode);
				audioFormat = inputStream.getFormat();
			}
		} else {
			//Checks for which channels are being used by the program.
			assert audioFormat.getChannels() == 2 : "Unexpected number of channels: " + audioFormat.getChannels();
			if (outputMode == 0) {
				inputStream = new MonoAudioInputStream(inputStream);
			} else if (outputMode == 1 || outputMode == 2) {
				//Get the output mode.
				inputStream = new StereoAudioInputStream(inputStream, outputMode);
			} else {
				assert outputMode == 3 : "Unexpected output mode: " + outputMode;
			}
		}

		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

		try {
			if (dataLine == null) {
				boolean bIsSupportedDirectly = AudioSystem.isLineSupported(info);
				if (!bIsSupportedDirectly) {
					//Gets the format of the audio.
					AudioFormat sourceFormat = audioFormat;
					AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
							//Get the channel which will be used for output.
							sourceFormat.getSampleRate(), sourceFormat.getSampleSizeInBits(),
							sourceFormat.getChannels(),
							sourceFormat.getChannels() * (sourceFormat.getSampleSizeInBits() / 8),
							sourceFormat.getSampleRate(), sourceFormat.isBigEndian());

					inputStream = AudioSystem.getAudioInputStream(targetFormat, inputStream);
					audioFormat = inputStream.getFormat();
				}
				//Set the data line value.
				info = new DataLine.Info(SourceDataLine.class, audioFormat);
				dataLine = (SourceDataLine) AudioSystem.getLine(info);
			}
			if (listener != null) {
				dataLine.addLineListener(listener);
			}
			dataLine.open(audioFormat);
			//Catch any errors that may occur.
		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING, null, ex);
			return;
		}

		//Gains a value depending on input/output.
		dataLine.start();
		setGain(getGainValue());

		int nRead = 0;
		byte[] abData = new byte[65532];
		while ((nRead != -1) && (!exitRequested)) {
			try {
				//Gert the speech input from the stream.
				nRead = inputStream.read(abData, 0, abData.length);
			} catch (IOException ex) {
				Logger.getLogger(getClass().getName()).log(Level.WARNING, null, ex);
			}
			if (nRead >= 0) {
				dataLine.write(abData, 0, nRead);
			}
		}
		if (!exitRequested) {
			dataLine.drain();
		}
		dataLine.close();
	}

}
