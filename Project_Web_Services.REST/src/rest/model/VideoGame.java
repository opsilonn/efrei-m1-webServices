package rest.model;


import javax.xml.bind.annotation.XmlRootElement;
import rest.model.util.Date;
import rest.model.util.Timestamp;




@XmlRootElement
public class VideoGame extends Multimedia
{
	private long id_videoGame;
	
	private String developer;
	private String publisher;
	
	
	public VideoGame() { }
	
	
	public VideoGame(String title, String description, String language, String genre, int category,
			int status, long ID_upp, Date release, String developer, String publisher)
	{
		super(title, description, language, genre, category, status, ID_upp, release);
		this.developer = developer;
		this.publisher = publisher;	
	}	
	
	public VideoGame(long ID_multimedia, String title, String description, String language, String genre, int category,
			int status, long ID_upp, Timestamp status_date, Timestamp upload_date, Date release, long ID_videoGame, String developer, String publisher)
	{	
		super(ID_multimedia, title, description, language, genre, category, status, status_date,  upload_date, release, ID_upp);
		this.id_videoGame = ID_videoGame;
		this.developer = developer;
		this.publisher = publisher;	
	}
	
	
	
	/**
	 * @return the id_videoGame
	 */
	public long getId_videoGame() { return id_videoGame; }
	/**
	 * @param id_videoGame the id_videoGame to set
	 */
	public void setId_videoGame(long id_videoGame) { this.id_videoGame = id_videoGame; }
	
	
	/**
	 * @return the developer
	 */
	public String getDeveloper() { return developer; }
	/**
	 * @param developper the developer to set
	 */
	public void setDeveloper(String developer) { this.developer = developer; }
	
	
	/**
	 * @return the publisher
	 */
	public String getPublisher() { return publisher; }
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) { this.publisher = publisher; }
}
