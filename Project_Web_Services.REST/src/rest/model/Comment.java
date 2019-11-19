package rest.model;


import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement
public class Comment {

	private long id_comment;
	
	private String value;
	
	private User user;
	private Multimedia multimedia;
	
	/**
	 * @return the id_comment
	 */
	public long getId_comment() {
		return id_comment;
	}
	/**
	 * @param id_comment the id_comment to set
	 */
	public void setId_comment(long id_comment) {
		this.id_comment = id_comment;
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
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the multimedia
	 */
	public Multimedia getMultimedia() {
		return multimedia;
	}
	/**
	 * @param multimedia the multimedia to set
	 */
	public void setMultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}
	
}
