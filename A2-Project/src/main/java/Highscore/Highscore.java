package Highscore;

import java.util.Date;

/**
 * Represents a highscore entry in the game.
 */
public class Highscore {
	private String name;
	private int timeTaken;
	private int day;
	private int month;
	private int year;


	/**
     * Constructor for a Highscore object.
     * @param name The name associated with the highscore entry.
     * @param timeTaken The time taken to achieve the highscore.
     * @param day The day of the recorded highscore.
     * @param month The month of the recorded highscore.
     * @param year The year of the recorded highscore.
     */

	public Highscore(String name, int timeTaken,int day, int month, int year){
		this.name = name;
		this.timeTaken = timeTaken;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
     * Retrieves the name associated with the highscore entry.
     * @return The name associated with the highscore.
     */
	public String getName() {
		return name;
	}

	/**
     * Sets the name associated with the highscore entry.
     * @param name The name to set for the highscore.
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Retrieves the time taken to achieve the highscore.
     * @return The time taken for the highscore.
     */
	public int getTimeTaken() {
		return timeTaken;
	}

	/**
     * Sets the time taken to achieve the highscore.
     * @param timeTaken The time taken to set for the highscore.
     */
	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

	/**
     * Retrieves the day of the recorded highscore.
     * @return The day of the highscore.
     */
	public int getDay() {
		return day;
	}

	/**
     * Sets the day of the recorded highscore.
     * @param day The day to set for the highscore.
     */
	public void setDay(int day) {
		this.day = day;
	}

	/**
     * Retrieves the month of the recorded highscore.
     * @return The month of the highscore.
     */
	public int getMonth() {
		return month;
	}

	/**
     * Sets the month of the recorded highscore.
     * @param month The month to set for the highscore.
     */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
     * Retrieves the year of the recorded highscore.
     * @return The year of the highscore.
     */
	public int getYear() {
		return year;
	}

	/**
     * Sets the year of the recorded highscore.
     * @param year The year to set for the highscore.
     */
	public void setYear(int year) {
		this.year = year;
	}
}
