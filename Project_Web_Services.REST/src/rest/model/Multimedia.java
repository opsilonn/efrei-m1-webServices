package rest.model;


import rest.model.util.Date;
import rest.model.util.Timestamp;
import rest.resource.BookResource;
import rest.resource.FilmResource;
import rest.resource.VideoGameResource;
import rest.service.util.Constants;
import rest.util.DB_web_services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement
public class Multimedia {

	private long id_multimedia;
	
	private String title;
	private String description;
	private String language;
	private String genre;
	private int category;
	
	private int status;
	private Timestamp status_date;
	
	private Timestamp upload_date;
	private Date release_date;
	
	private User uploader;
	
	
	public enum Category{
		BOOK,
		FILM,
		VIDEO_GAME
	}
	
	
	
	
	public Multimedia() { }
	

	public Multimedia(long id_multimedia, String title, String description, String language, String genre, int category,
			int status, Timestamp status_date, Timestamp upload_date, Date release_date, User uploader) {
		super();
		this.id_multimedia = id_multimedia;
		this.title = title;
		this.description = description;
		this.language = language;
		this.genre = genre;
		this.category = category;
		this.status = status;
		this.status_date = status_date;
		this.upload_date = upload_date;
		this.release_date = release_date;
		this.uploader = uploader;
	}

	public Multimedia(String title, String description, String language, String genre, int category,
			int status, User upp, Date Realease){
		
		this.title = title;
		this.description = description;
		this.language = language;
		this.genre = genre;
		this.category = category;
		this.status = status;
		this.uploader = upp;
		this.release_date = Realease;
		
	}
	
	
	public static Category getChildCategory(long id_multimedia) throws SQLException{
		
		DB_web_services db = new DB_web_services();
    	
    	PreparedStatement ppsm = db.getPreparedStatement(Constants.Multimedia.getTypeByID);
    	    	
    	ppsm.setLong(1, id_multimedia);
    	
    	ResultSet rs = ppsm.executeQuery();
    	
    	if(rs.next()){
    		int type = rs.getInt(1);
    		
    		switch(type){
    		case 1:
    			return Category.BOOK;
    		case 2:
    			return Category.FILM;
    		case 3:
    			return Category.VIDEO_GAME;
    		}
    	}
    	
    	
    	return null;
	}
	
	
	public static Class getChildClass(long id_multimedia)
			throws SQLException{
		Category category = getChildCategory(id_multimedia);
		
		switch(category){
		case BOOK:
			return BookResource.class;
		case FILM:
			return FilmResource.class;
		case VIDEO_GAME:
			return VideoGameResource.class;
		}
		
		return null;
	}
	
	
	public static long getChildID(long id_multimedia)
			throws SQLException{
		Category category = getChildCategory(id_multimedia);
		String query;
		
		switch(category){
		case BOOK:
			query = Constants.Multimedia.getBookChildID;
			break;
		case FILM:
			query = Constants.Multimedia.getFilmChildID;
			break;
		case VIDEO_GAME:
			query = Constants.Multimedia.getVideoGameChildID;
			break;
		default:
			return 0;
		}
		
		DB_web_services db = new DB_web_services();
    	
    	PreparedStatement ppsm = db.getPreparedStatement(query);
    	
    	ppsm.setLong(1, id_multimedia);
    	
    	ResultSet rs = ppsm.executeQuery();
    	
    	if(rs.next()){
    		return rs.getInt(1);
    	}
    	
    	
    	return 0;
	}



	/**
	 * @return the id_multimedia
	 */
	public long getId_multimedia() {
		return id_multimedia;
	}

	/**
	 * @param id_multimedia the id_multimedia to set
	 */
	public void setId_multimedia(long id_multimedia) {
		this.id_multimedia = id_multimedia;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(int category) {
		this.category = category;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the status_date
	 */
	public Timestamp getStatus_date() {
		return status_date;
	}

	/**
	 * @param status_date the status_date to set
	 */
	public void setStatus_date(Timestamp status_date) {
		this.status_date = status_date;
	}

	/**
	 * @return the upload_date
	 */
	public Timestamp getUpload_date() {
		return upload_date;
	}

	/**
	 * @param upload_date the upload_date to set
	 */
	public void setUpload_date(Timestamp upload_date) {
		this.upload_date = upload_date;
	}

	/**
	 * @return the release_date
	 */
	public Date getRelease_date() {
		return release_date;
	}

	/**
	 * @param release_date the release_date to set
	 */
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	/**
	 * @return the uploader
	 */
	public User getUploader() {
		return uploader;
	}

	/**
	 * @param uploader the uploader to set
	 */
	public void setUploader(User uploader) {
		this.uploader = uploader;
	}
}
