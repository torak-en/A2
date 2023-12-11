package Profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class ProfileHandler {

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

	public void updateProfile(Profile p){
		System.out.println("Profile being updated");
		File profileToUpdate = new File("Profiles/" + p.getProfileName() + ".txt");
		if (profileToUpdate.exists()){
			try {
				FileWriter writer = new FileWriter(profileToUpdate);
				System.out.println(p.getMaxLevelNumUnlocked());
				writer.write(String.valueOf(p.getMaxLevelNumUnlocked()));
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new RuntimeException("The profile you are trying to update doesn't exist.");
		}


	}

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

	public void deleteProfile(String name){
//		System.out.println(name);
		File newProfile = new File("Profiles/" + name + ".txt");
		if (!newProfile.delete()){
			throw new RuntimeException("Profile Unable to be Deleted");
		}

	}
}
