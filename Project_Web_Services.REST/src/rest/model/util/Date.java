package rest.model.util;




public class Date {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	private String value;
	
	
	public Date(String date_string){
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
	
	public Date(int annee, int mois, int jour){
		year = annee;
		month = mois;
		day = jour;
	}
	
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.setValue();
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.setValue();
		this.month = month;
	}
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.setValue();
		this.day = day;
	}
	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.setValue();
		this.hour = hour;
	}
	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}
	/**
	 * @param minute the minute to set
	 */
	public void setMinute(int minute) {
		this.setValue();
		this.minute = minute;
	}
	/**
	 * @return the second
	 */
	public int getSecond() {
		return second;
	}
	/**
	 * @param second the second to set
	 */
	public void setSecond(int second) {
		this.setValue();
		this.second = second;
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
				+ ":" + second;;
	}
	
	public String toDate(){
		String s= year + "-" + month + "-" + day;
		
		return s;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return value;
	}
	
	
	
}
