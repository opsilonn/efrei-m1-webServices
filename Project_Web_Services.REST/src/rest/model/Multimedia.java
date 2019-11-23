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
	private Timestamp date_status;
	
	private Timestamp date_upload;
	private Date date_release;
	
	private long ID_uploader;
	
	
	
	
	public Multimedia() { }
	

	public Multimedia(long id_multimedia, String title, String description, String language, String genre, int category,
			int status, Timestamp status_date, Timestamp upload_date, Date release_date, long ID_uploader)
	{
		this.id_multimedia = id_multimedia;
		this.title = title;
		this.description = description;
		this.language = language;
		this.genre = genre;
		this.category = category;
		this.status = status;
		this.date_status = status_date;
		this.date_upload = upload_date;
		this.date_release = release_date;
		this.ID_uploader = ID_uploader;
	}

	public Multimedia(String title, String description, String language, String genre, int category,
			int status, long ID_uploader, Date release){
		
		this.title = title;
		this.description = description;
		this.language = language;
		this.genre = genre;
		this.category = category;
		this.status = status;
		this.ID_uploader = ID_uploader;
		this.date_release = release;
	}
	
	
	public static Class getChildClass(long id_multimedia) throws SQLException{
		
		DB_web_services db = new DB_web_services();
    	
    	PreparedStatement ppsm = db.getPreparedStatement(Constants.Multimedia.getTypeByID);
    	    	
    	ppsm.setLong(1, id_multimedia);
    	
    	ResultSet rs = ppsm.executeQuery();
    	
    	if(rs.next()){
    		int type = rs.getInt(1);
    		
    		switch(type){
    		case 1:
    			return BookResource.class;
    		case 2:
    			return FilmResource.class;
    		case 3:
    			return VideoGameResource.class;
    		}
    	}
    	
    	
    	return null;
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
	public Timestamp getDate_Status() {
		return date_status;
	}

	/**
	 * @param date_status the status_date to set
	 */
	public void setDate_status(Timestamp date_status) {
		this.date_status = date_status;
	}

	/**
	 * @return the upload_date
	 */
	public Timestamp getDate_upload() {
		return date_upload;
	}

	/**
	 * @param date_upload the upload_date to set
	 */
	public void setDate_upload(Timestamp date_upload) {
		this.date_upload = date_upload;
	}

	/**
	 * @return the release_date
	 */
	public Date getDate_release() {
		return date_release;
	}

	/**
	 * @param date_release the release_date to set
	 */
	public void setDate_release(Date date_release) {
		this.date_release = date_release;
	}

	/**
	 * @return the uploader's ID
	 */
	public long getID_uploader() {
		return ID_uploader;
	}

	/**
	 * @param ID_uploader the uploader's ID to set
	 */
	public void setID_uploader(long ID_uploader) {
		this.ID_uploader = ID_uploader;
	}
}
