package rest.model;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import rest.model.util.Link;




@XmlRootElement
public class Rate {

	private long id_rate;
	
	private int value;
	
	private User user;
	private Multimedia multimedia;
	
	private List<Link> links = new ArrayList<Link>();
	
	
	
	
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
    @XmlTransient
	public User getUser() {
		return user;
	}
	
	/**
	 * @return the multimedia
	 */
    @XmlTransient
	public Multimedia getMultimedia() {
		return multimedia;
	}

	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}


	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}


	/**
	 * @param link the link to add
	 */
	public void addLink(String rel, String href) {
		Link link = new Link(rel, href);
		this.links.add(link);
	}
	
}
