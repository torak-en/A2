package Highscore;

import java.util.Date;

public class Highscore {
	private String name;
	private int timeTaken;
	private int day;
	private int month;
	private int year;

	public Highscore(String name, int timeTaken,int day, int month, int year){
		this.name = name;
		this.timeTaken = timeTaken;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
