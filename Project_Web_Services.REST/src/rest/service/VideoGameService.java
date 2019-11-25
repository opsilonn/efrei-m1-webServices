package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rest.exception.DataNotFoundException;
import rest.model.VideoGame;
import rest.model.util.Date;
import rest.model.util.Timestamp;
import rest.service.util.Constants;
import rest.util.DB_web_services;

public class VideoGameService {
	private Map<Long, VideoGame> videoGames = DB_web_services.getVideoGames();

	/**
	 * Constructor of the class {@link VideoGameService}
	 * 
	 * @throws SQLException
	 */
	public VideoGameService() throws SQLException
	{
		// We initialize some helpful variables
		try(DB_web_services db = new DB_web_services()){
			
			PreparedStatement ppsm = db.getPreparedStatement(Constants.VideoGame.getAll);
			ResultSet rs = ppsm.executeQuery();

			// We empty our current map
			videoGames.clear();

			// As long as the database returns a row, we fill the map
			while (rs.next())
			{
				
				VideoGame videoGame = new VideoGame(
						rs.getLong("ID_multimedia"),
						rs.getString("title"),
						rs.getString("description"),
						rs.getString("language"),
						rs.getString("genre"),
						rs.getInt("category"),
						rs.getInt("status"),
						rs.getLong("ID_uploader"),
						new Timestamp(rs.getString("date_status")),
						new Timestamp(rs.getString("date_upload")),
						new Date(rs.getString("date_release")),
						rs.getLong("ID_videoGame"),
						rs.getString("developer"),
						rs.getString("publisher")
						);

				// We put our values in the map
				videoGames.put(rs.getLong("ID_videoGame"), videoGame);
			}
		}
		
	}

	
	
	
	/**
	 * Gets all the row from the database's table {@link VideoGame}
	 * 
	 * @return All the row from the database's table {@link VideoGame}
	 */
	public List<VideoGame> getAllVideoGames() {
		List<VideoGame> return_videoGames = new ArrayList<VideoGame>(videoGames.values());

		if (return_videoGames.isEmpty())
			throw new DataNotFoundException("No video games was found !");

		return return_videoGames;
	}
	
	
	

	/**
	 * Returns a specific {@link VideoGame} row from the database according to
	 * its ID
	 * 
	 * @param id
	 *            ID of the {@link VideoGame} we want to return
	 * @return A {@link VideoGame} row from the database according to its ID
	 */
	public VideoGame getVideoGame(long id) {
		VideoGame videoGame = this.videoGames.get(id);

		if (videoGame == null)
			throw new DataNotFoundException("The video game with the id `" + id + "` was not found !");

		return videoGame;
	}
	
	
	/**
	 * Returns the number of row in the database's table {@link VideoGame}
	 * 
	 * @return The number of row in the database's table {@link VideoGame}
	 */
	public int getVideoGameCount() {
		return videoGames.size();
	}
	

	/**
	 * PLEASE ADD THE DOCUMENTATION PLEASE PLEASE PLEASE
	 */
	public VideoGame addVideoGame(VideoGame videoGame) throws SQLException
	{
		// DO ALL VERIFICATION HERE
		//(I only do title for the moment, should we implement all the other fields ?)
		System.out.println("LANGUAGE : " + videoGame.getLanguage());
		if (videoGame.getTitle() == null || videoGame.getTitle().length() == 0)
		{
			throw new SQLIntegrityConstraintViolationException("Le champ 'title' ne peut être vide (null)");
		}
		

		
		// We initialize some variables
		try(DB_web_services db = new DB_web_services()){
			
			PreparedStatement ppsm = db.getPreparedStatement(Constants.Multimedia.post);
			
			System.out.println(videoGame);
			
			
			// We initialize our statement's values
			ppsm.setString(1, videoGame.getTitle());
			ppsm.setString(2, videoGame.getLanguage());
			ppsm.setString(3, videoGame.getGenre());
			ppsm.setInt(4, 3); //category
			ppsm.setInt(5, videoGame.getStatus());
			ppsm.setLong(6, videoGame.getID_uploader());
			ppsm.setString(7, videoGame.getDescription());
			ppsm.setString(8, videoGame.getDate_release().toString());
	
			int succes = ppsm.executeUpdate();
	
			if (succes == 1) {
				ResultSet generated_id = ppsm.getGeneratedKeys();
				
				if(generated_id.next()){
					
					PreparedStatement ppsm2 = db.getPreparedStatement(Constants.VideoGame.post);
					
					// We initialize our statement's values
					
					ppsm2.setString(1, videoGame.getDeveloper());
					ppsm2.setString(2, videoGame.getPublisher());
					ppsm2.setLong(3, generated_id.getLong(1));
			
					int succes2 = ppsm2.executeUpdate();
			
					if (succes2 == 1) {
						ResultSet generated_id2 = ppsm2.getGeneratedKeys();
			
						if (generated_id2.next())
						{
							
							PreparedStatement ppsm3 = db.getPreparedStatement(Constants.VideoGame.getByID);

							ppsm3.setLong(1, generated_id2.getLong(1));
			
							ResultSet rs = ppsm3.executeQuery();
							
							if(rs.next()){
								
								
								return new VideoGame(
										rs.getLong("ID_multimedia"),
										rs.getString("title"),
										rs.getString("description"),
										rs.getString("language"),
										rs.getString("genre"),
										rs.getInt("category"),
										rs.getInt("status"),
										rs.getLong("ID_uploader"),
										new Timestamp(rs.getString("date_status")),
										new Timestamp(rs.getString("date_upload")),
										new Date(rs.getString("date_release")),
										rs.getLong("ID_videoGame"),
										rs.getString("developer"),
										rs.getString("publisher")
										);
							}
						}
					}
				}
			}
	
			
			return null;
			
		}
		
	}

	
	public boolean removeVideoGame(long id)
			throws SQLException{
	
		try(DB_web_services db = new DB_web_services()){
			
			if(this.videoGames.get(id) == null)
				throw new DataNotFoundException("The video game with the id `" + id + "` doesn't exist !");

			PreparedStatement ppsm = db.getPreparedStatement(Constants.VideoGame.deleteByID);
	
	    	ppsm.setLong(1, id);
	    	
	    	int rs = ppsm.executeUpdate();
	
	    	if(rs == 1){
	    		videoGames.remove(id);
	    		
	    		
	    		return true;
	    	}	
			
	    	
			return false;
		}
	}
	
}
