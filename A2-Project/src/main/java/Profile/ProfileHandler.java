package Profile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

/**
 * This class handles profiles, including retrieval and creation.
 */

public class ProfileHandler {

	/**
	 * Retrieves a list of profiles from the "Profiles" directory.
	 * @return A list of Profile objects, each representing a player's profile.
	 * Profiles are constructed from files found in the "Profiles" directory.
	 */
	public List<Profile> getProfiles(){
		List<Profile> profiles = new ArrayList<>();
		File profileDirectory = new File("Profiles");
		File[] profileFiles = profileDirectory.listFiles();
		assert profileFiles != null;
		for (File file : profileFiles) {
			String fileName = file.getName();
			String[] name = fileName.split("\\.");

			Scanner sc = null;
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				throw new RuntimeException("Profile not found: " + fileName);
			}

			int maxUnlockedLevel = Integer.parseInt(sc.nextLine());

			profiles.add(new Profile(name[0], maxUnlockedLevel));
			sc.close();
		}
		return profiles;
	}

	/**
	 * Updates a profile file with any new/changed data in the profile object.
	 * @param profile The profile object to be updated with the new data in its file.
	 * If the profile is invalid then a RuntimeException is thrown.
	 */
	public void updateProfile(Profile profile){
		System.out.println("Profile being updated");
		File profileToUpdate = new File("Profiles/" + profile.getProfileName() + ".txt");
		if (profileToUpdate.exists()){
			try {
				FileWriter writer = new FileWriter(profileToUpdate);
				System.out.println(profile.getMaxLevelNumUnlocked());
				writer.write(String.valueOf(profile.getMaxLevelNumUnlocked()));
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new RuntimeException("The profile you are trying to update doesn't exist.");
		}
	}

	/**
	 * Creates a new player profile with the specified name.
	 * The name must contain only letters; no symbols or whitespaces are allowed.
	 * @param name The name of the new profile.
	 * @return A message indicating the result of profile creation, either a success message or an error message.
	 * If the profile name is invalid or the profile already exists, an error message is returned.
	 */
	public String createNewProfile(String name){
		File newProfile = new File("Profiles/" + name + ".txt");
		char[] characters = name.toCharArray();

		for (char c : characters) {
			if(!Character.isLetter(c)) {
				return "Name can only be made of of characters. No Symbols, whitespaces, etc.";
			}
		}

		try {

			if (newProfile.createNewFile()){
				FileWriter writer = new FileWriter(newProfile);
				writer.write("1");
				writer.close();
			} else {
				return "Error with profile creation, profile may already exist.";
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return "Profile Created";
	}

	/**
	 * Deletes a profile with the specified name.
	 * @param name The name of the new profile.
	 * If the file fails to delete it throws a RuntimeException
	 */
	public void deleteProfile(String name){
//		System.out.println(name);
		File newProfile = new File("Profiles/" + name + ".txt");
		if (!newProfile.delete()){
			throw new RuntimeException("Profile Unable to be Deleted");
		}

	}
}
