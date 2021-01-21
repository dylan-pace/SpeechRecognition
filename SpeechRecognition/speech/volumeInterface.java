package speech;

/**
 * Interface to set up the different method involved 
 * with changing the volume when prompted by the user.
 * {@link changeVolume}
 * @author DylanPace
 *
 */
public interface volumeInterface {
	
	/**
	 * Changes the volume to its highest setting
	 * @param speech spoken input by user.
	 * @return changes volume loud.
	 */
	public String maxVolume(String speech);
	
	/**
	 * Changes the volume to its lowest setting. (Not mute, just quiet)
	 * @param speech spoken input by user.
	 * @return changes volume quiet.
	 */
	public String minVolume(String speech);
	
	/**
	 * Changes the volume to a medium setting.
	 * @param speech spoken input by user.
	 * @return changes volume medium.
	 */
	public String mediumVolume(String speech);
	
	/**
	 * Mutes the computer.
	 * @param speech spoken input by user.
	 * @return turns off volume.
	 */
	public String muteVolume(String speech);

}
