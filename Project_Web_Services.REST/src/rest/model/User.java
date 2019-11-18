package rest.model;


import java.util.ArrayList;
import java.util.List;

import rest.model.util.Date;




public class User {
	
	private long id_user;
	
	private String pseudo;
	private String email;
	
	private Date creation_date;

	private List<Rate> rates;
	private List<Comment> comments;
	
	

	public User() {	}
	
	
	public User(long id_user, String pseudo, String email, Date creation_date) {
		this.id_user = id_user;
		this.pseudo = pseudo;
		this.email = email;
		this.creation_date = creation_date;
		this.rates = new ArrayList<Rate>();
		this.comments = new ArrayList<Comment>();
	}
	

	public User(long id_user, String pseudo, String email, Date creation_date, List<Rate> rates,
			List<Comment> comments) {
		this.id_user = id_user;
		this.pseudo = pseudo;
		this.email = email;
		this.creation_date = creation_date;
		this.rates = rates;
		this.comments = comments;
	}

	/**
	 * @return the id
	 */
	public long getId_user() {
		return id_user;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the creation_date
	 */
	public Date getCreation_date() {
		return creation_date;
	}

	/**
	 * @return the rates
	 */
	public List<Rate> getRates() {
		return rates;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id_user=" + this.id_user + ", pseudo=" + this.pseudo + ", email=" + this.email + ", creation_date="
				+ this.creation_date + ", rates=" + this.rates + ", comments=" + this.comments + "]";
	}
	
	
}
