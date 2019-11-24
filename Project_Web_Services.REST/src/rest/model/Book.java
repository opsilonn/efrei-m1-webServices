package rest.model;


import javax.xml.bind.annotation.XmlRootElement;

import rest.model.util.Date;




@XmlRootElement
public class Book extends Multimedia{

	private long id_book;
	private String author;
	private String publisher;
	private long id_multimedia;
	
	public Book() { }
	
	
	public Book(long id_book, String author, String publisher, String title, 
			String description, String language, String genre, int category,
			int status, long ID_upp, Date release)
	{
		super(title, description, language, genre, category, status, ID_upp, release);
		this.id_book = id_book;
		this.author = author;
		this.publisher = publisher;	
	}
	
	/**
	 * @return the id_book
	 */
	public long getId_book() {
		return id_book;
	}
	/**
	 * @param id_book the id_book to set
	 */
	public void setId_book(long id_book) {
		this.id_book = id_book;
	}
	
	/**
	 * @return the id_book
	 */
	public long getId_multimedia() {
		return id_multimedia;
	}
	/**
	 * @param id_book the id_book to set
	 */
	public void setId_multimedia(long id_multimedia) {
		this.id_multimedia = id_multimedia;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
