package Profile;

public class Profile {
	private String profileName;
	private int maxLevelNumUnlcoked;

	public Profile(String profileName, int maxLevelNumUnlcoked){
		this.profileName = profileName;
		this.maxLevelNumUnlcoked = maxLevelNumUnlcoked;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public int getMaxLevelNumUnlcoked() {
		return maxLevelNumUnlcoked;
	}

	public void setMaxLevelNumUnlcoked(int maxLevelNumUnlcoked) {
		this.maxLevelNumUnlcoked = maxLevelNumUnlcoked;
	}
}
