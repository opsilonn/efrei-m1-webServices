package rest.model.util;

import javax.xml.bind.annotation.XmlTransient;

public class Date {

	private int year;
	private int month;
	private int day;
	
	private String value;
	
	public Date(){
		
	}
	
	public Date(String date_string){
		this.value = date_string;
		
		
		String spliter[] = date_string.split("-");

		this.year = Integer.valueOf(spliter[0]);
		this.month = Integer.valueOf(spliter[1]);
		this.day = Integer.valueOf(spliter[2]);
	}
	
	
	public Date(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
		setValue();
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
	 * @return the value
	 */
	public String getValue() {
		setValue();
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue() {
		this.value = year + "-" + month + "-" + day;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		setValue();
		return value;
	}
	
	
	
}
