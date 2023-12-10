package Highscore;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Represents a highscore entry in the game.
 */
public class Highscore {
	private StringProperty name = new SimpleStringProperty(" ");
	private IntegerProperty timeTaken = new SimpleIntegerProperty( );
	private IntegerProperty day = new SimpleIntegerProperty( );
	private IntegerProperty month = new SimpleIntegerProperty( );
	private IntegerProperty year = new SimpleIntegerProperty( );


	/**
     * Constructor for a Highscore object.
     * @param name The name associated with the highscore entry.
     * @param timeTaken The time taken to achieve the highscore.
     * @param day The day of the recorded highscore.
     * @param month The month of the recorded highscore.
     * @param year The year of the recorded highscore.
     */

	public Highscore(String name, int timeTaken, int day, int month, int year){
		this.name.set(name);
		this.timeTaken.set(timeTaken);
		this.day.set(day);
		this.month.set(month);
		this.year.set(year);
	}

	/**
     * Retrieves the name associated with the highscore entry.
     * @return The name associated with the highscore.
     */
	public SimpleStringProperty getName() {
		return (SimpleStringProperty) name;
	}

	/**
     * Sets the name associated with the highscore entry.
     * @param name The name to set for the highscore.
     */
	public void setName(String name) {
		this.name.set(name);
	}

	/**
     * Retrieves the time taken to achieve the highscore.
     * @return The time taken for the highscore.
     */
	public SimpleIntegerProperty getTimeTaken() {
		return (SimpleIntegerProperty) timeTaken;
	}

	/**
     * Sets the time taken to achieve the highscore.
     * @param timeTaken The time taken to set for the highscore.
     */
	public void setTimeTaken(int timeTaken) {
		this.timeTaken.set(timeTaken);
	}

	/**
     * Retrieves the day of the recorded highscore.
     * @return The day of the highscore.
     */
	public SimpleIntegerProperty getDay() {
		return (SimpleIntegerProperty) day;
	}

	/**
     * Sets the day of the recorded highscore.
     * @param day The day to set for the highscore.
     */
	public void setDay(int day) {
		this.day.set(day);
	}

	/**
     * Retrieves the month of the recorded highscore.
     * @return The month of the highscore.
     */
	public SimpleIntegerProperty getMonth() {
		return (SimpleIntegerProperty) month;
	}

	/**
     * Sets the month of the recorded highscore.
     * @param month The month to set for the highscore.
     */
	public void setMonth(int month) {
		this.month.set(month);
	}

	/**
     * Retrieves the year of the recorded highscore.
     * @return The year of the highscore.
     */
	public SimpleIntegerProperty getYear() {
		return (SimpleIntegerProperty) year;
	}

	/**
     * Sets the year of the recorded highscore.
     * @param year The year to set for the highscore.
     */
	public void setYear(int year) {
		this.year.set(year);
	}
}
