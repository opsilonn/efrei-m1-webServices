package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rest.exception.DataNotFoundException;
import rest.model.User;
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
		DB_web_services db = new DB_web_services();
		PreparedStatement ppsm = db.getPreparedStatement(Constants.VideoGame.getAll);
		ResultSet rs = ppsm.executeQuery();

		// We empty our current map
		videoGames.clear();

		// As long as the database returns a row, we fill the map
		while (rs.next())
		{
			// We create our map values (Key & Value)
			long mapKey = rs.getLong("ID_videoGame");
			VideoGame mapValue = new VideoGame();

			// We search for the corresponding Multimedia row
			PreparedStatement stmt2 = db.getPreparedStatement(Constants.Multimedia.getByID);
			stmt2.setLong(1, rs.getLong("ID_multimedia"));
			ResultSet rs2 = stmt2.executeQuery();

			// If the said row exist :
			if (rs2.next())
			{
				mapValue = new VideoGame(
						rs2.getLong("ID_multimedia"), rs2.getString("title"),
						rs2.getString("description"), rs2.getString("language"),
						rs2.getString("genre"),
						rs2.getInt("category"),
						rs2.getInt("status"),
						rs2.getLong("ID_uploader"),
						new Timestamp(rs2.getString("date_status")),
						new Timestamp(rs2.getString("date_upload")),
						new Date(rs2.getString("date_release")),
						rs.getLong("ID_videoGame"), rs.getString("developer"),
						rs.getString("publisher")
						);
			}

			// We put our values in the map
			videoGames.put(mapKey, mapValue);
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
			throw new DataNotFoundException("No videoGames was found !");

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
			throw new DataNotFoundException("The videoGame with the id `" + id + "` was not found !");

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
		
		if (videoGame.getTitle() == null || videoGame.getTitle().length() == 0)
		{
			throw new SQLIntegrityConstraintViolationException("Le champ 'title' ne peut être vide (null)");
		}
		

		
		// We initialize some variables
		DB_web_services db = new DB_web_services();
		PreparedStatement ppsm = db.getPreparedStatement(Constants.Multimedia.post);

		// We initialize our statement's values
		ppsm.setString(1, videoGame.getTitle());
		ppsm.setString(2, videoGame.getDescription());
		ppsm.setString(3, videoGame.getLanguage());
		ppsm.setString(4, videoGame.getGenre());
		ppsm.setInt(5, videoGame.getCategory());
		ppsm.setInt(6, videoGame.getStatus());
		ppsm.setLong(7, videoGame.getID_uploader());
		ppsm.setString(8, videoGame.getDate_release().toString());

		int rs = ppsm.executeUpdate();

		if (rs == 1) {
			ResultSet generated_id = ppsm.getGeneratedKeys();

			if (generated_id.next())
			{
				ppsm = db.getPreparedStatement(Constants.VideoGame.post);

				ppsm.setString(1, videoGame.getDeveloper());
				ppsm.setString(2, videoGame.getPublisher());
				ppsm.setLong(3, generated_id.getLong(1));

				ppsm.executeUpdate();

				return videoGame;
			}
		}

		return null;
	}

	
	// public int removeVideoGame(long id) throws SQLException{
	//
	// DB_web_services db = new DB_web_services();
	//
	// PreparedStatement ppsm =
	// db.getPreparedStatement(Constants.VideoGame.deleteByID);
	//
	// ppsm.setLong(1, id);
	//
	// int rs = ppsm.executeUpdate();
	//
	// if(rs == 1){
	// users.remove(id);
	//
	//
	// return true;
	// }
	//
	//
	// throw new DataNotFoundException("The user with the id `" + id + "`
	// doesn't exist !");
	// }
	// }
	//
}
