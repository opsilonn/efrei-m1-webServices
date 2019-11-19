package rest.model;


import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement
public class Rate {

	private long id_rate;
	
	private int value;
	
	private User user;
	private Multimedia multimedia;
	
	
	
	
	public Rate() { }
	
	
	public Rate(long id_rate, int value, User user, Multimedia multimedia){
		this.id_rate = id_rate;
		this.value = value;
		this.user = user;
		this.multimedia = multimedia;
	}
	
	/**
	 * @return the id_rate
	 */
	public long getId_rate() {
		return id_rate;
	}
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @return the multimedia
	 */
	public Multimedia getMultimedia() {
		return multimedia;
	}
	
}
