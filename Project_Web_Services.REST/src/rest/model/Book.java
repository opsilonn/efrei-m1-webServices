package rest.model;




public class Book extends Multimedia{

	private long id_book;
	
	private String author;
	private String publisher;
	
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
