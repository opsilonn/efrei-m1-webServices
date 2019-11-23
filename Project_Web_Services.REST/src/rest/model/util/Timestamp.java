package rest.model.util;

import javax.xml.bind.annotation.XmlTransient;

public class Timestamp {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	private String value;
	
	
	public Timestamp(String date_string){
		this.value = date_string;
		
		
		String spliter[] = date_string.split("-");

		this.year = Integer.valueOf(spliter[0]);
		this.month = Integer.valueOf(spliter[1]);

		spliter = spliter[2].split(" ");

		this.day = Integer.valueOf(spliter[0]);

		spliter = spliter[1].split(":");

		this.hour = Integer.valueOf(spliter[0]);
		this.minute = Integer.valueOf(spliter[1]);
		this.second = Integer.valueOf(spliter[2]);
	}
	
	
	public Timestamp(int year, int month, int day, int hour, int minute, int second){
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	
	
	/**
	 * @return the year
	 */
    @XmlTransient
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
		this.setValue();
	}
	/**
	 * @return the month
	 */
    @XmlTransient
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
		this.setValue();
	}
	/**
	 * @return the day
	 */
    @XmlTransient
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
		this.setValue();
	}
	/**
	 * @return the hour
	 */
    @XmlTransient
	public int getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
		this.setValue();
	}
	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}
    @XmlTransient
	/**
	 * @param minute the minute to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
		this.setValue();
	}
	/**
	 * @return the second
	 */
    @XmlTransient
	public int getSecond() {
		return second;
	}
	/**
	 * @param second the second to set
	 */
	public void setSecond(int second) {
		this.second = second;
		this.setValue();
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue() {
		this.value = year + "-" + month + "-" + day + " " + hour + ":" + minute
				+ ":" + second;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return value;
	}
	
	
	
}
