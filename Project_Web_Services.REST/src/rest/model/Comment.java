package rest.model;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import rest.model.util.Link;




@XmlRootElement
public class Comment {

	private long id_comment;
	
	private String value;
	
	private long id_user;
	@JsonProperty(access = Access.WRITE_ONLY)
	private long id_multimedia;
	
	private List<Link> links = new ArrayList<Link>();
	
	
	
	
	public Comment() { }
	
	
	public Comment(long id_comment, String value, long id_user, long id_multimedia){
		this.id_comment = id_comment;
		this.value = value;
		this.id_user = id_user;
		this.id_multimedia = id_multimedia;
	}
	
	/**
	 * @return the id_comment
	 */
	public long getId_comment() {
		return id_comment;
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
    @XmlTransient
	public long getId_user() {
		return this.id_user;
	}
	
	/**
	 * @return the multimedia
	 */
    @XmlTransient
	public long getId_multimedia() {
		return this.id_multimedia;
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
