package rest.model;


import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement
public class VideoGame extends Multimedia {

	private long id_videoGame;
	
	private String developper;
	private String publisher;
	
	/**
	 * @return the id_videoGame
	 */
	public long getId_videoGame() {
		return id_videoGame;
	}
	/**
	 * @param id_videoGame the id_videoGame to set
	 */
	public void setId_videoGame(long id_videoGame) {
		this.id_videoGame = id_videoGame;
	}
	/**
	 * @return the developper
	 */
	public String getDevelopper() {
		return developper;
	}
	/**
	 * @param developper the developper to set
	 */
	public void setDevelopper(String developper) {
		this.developper = developper;
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
