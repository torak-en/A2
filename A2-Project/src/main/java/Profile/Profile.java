package Profile;

/**
 * Represents a player's profile in the game.
 */
public class Profile {
	private String profileName;
	private int maxLevelNumUnlocked;

	/**
     * Constructor for a Profile object.
     *
     * @param profileName         The name of the player's profile.
     * @param maxLevelNumUnlocked The maximum level number unlocked in the profile.
     */
	public Profile(String profileName, int maxLevelNumUnlcoked){
		this.profileName = profileName;
		this.maxLevelNumUnlocked = maxLevelNumUnlcoked;
	}

	/**
     * Retrieves the name of the player's profile.
     * @return The profile name.
     */
	public String getProfileName() {
		return profileName;
	}

	/**
     * Retrieves the maximum level number unlocked in the profile.
	 * @param profileName The profile name to set.
     * @return The maximum level number unlocked.
     */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
     * Sets the maximum level number unlocked in the profile.
     * @param maxLevelNumUnlocked The maximum level number to set.
     */
	public int getMaxLevelNumUnlocked() {
		return maxLevelNumUnlocked;
	}

	/**
	 * Sets the maximum level number unlocked in the profile.
	 * @param maxLevelNumUnlocked The maximum level number to set.
	 */
	public void setMaxLevelNumUnlocked(int maxLevelNumUnlocked) {
		this.maxLevelNumUnlocked = maxLevelNumUnlocked;
	}
}
