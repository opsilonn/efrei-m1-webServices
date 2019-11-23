package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rest.exception.DataNotFoundException;
import rest.model.VideoGame;
import rest.model.util.Date;
import rest.model.util.Timestamp;
import rest.service.util.Constants;
import rest.util.DB_web_services;


public class VideoGameService
{	
	private Map<Long, VideoGame> videoGames = DB_web_services.getVideoGames();
	
	public VideoGameService() throws SQLException
	{
		// We initialize some helpful variables
		DB_web_services db = new DB_web_services();	
		PreparedStatement ppsm = db.getPreparedStatement(Constants.VideoGame.getAll);
    	ResultSet rs = ppsm.executeQuery();
    	
    	// We empty our current map
    	videoGames.clear();

    	// As long as the database returns a row, we fill the map
    	while( rs.next() )
		{
    		// We create our map values (Key & Value)
    		long mapKey = rs.getLong("ID_multimedia");
    		VideoGame mapValue = new VideoGame();

    		// We search for the corresponding Multimedia row
        	PreparedStatement stmt2 = db.getPreparedStatement(Constants.Multimedia.getByID);
			stmt2.setLong(1, mapKey);
			ResultSet rs2 = stmt2.executeQuery();
			
			// If the said row exist :
			if(rs2.next())
			{
	    		 mapValue = new VideoGame(
	    				rs2.getLong("ID_multimedia"),
	    				rs2.getString("title"),
	    				rs2.getString("description"),
	    				rs2.getString("language"),
	    				rs2.getString("genre"),
	    				rs2.getInt("category"),
	    				rs2.getInt("status"),
	    				rs2.getLong("ID_uploader"),
	     				new Timestamp(rs2.getString("date_status")),
	     				new Timestamp(rs2.getString("date_upload")),
	     				new Date (rs2.getString("date_release")),
	     				rs.getLong("ID_videoGame"),
	     				rs.getString("developer"),
	     				rs.getString("publisher")
	     				);
			}
    		
    		
    		// We put our values in the map
    		videoGames.put(mapKey, mapValue);
    	}
	}
	
	
	public List<VideoGame> getAllVideoGames()
	{
		List<VideoGame> return_videoGames = new ArrayList<VideoGame>(videoGames.values());
		
		if(return_videoGames.isEmpty())
			throw new DataNotFoundException("No videoGames was found !");
		
		
    	return return_videoGames;
	}
	
	public VideoGame getVideoGame(long id){
		VideoGame videoGame = videoGames.get(id);

		if(videoGame == null)
			throw new DataNotFoundException("The videoGame with the id `" + id + "` was not found !");
		
		
		return videoGame;
	}
	
	public int getVideoGameCount(){
		return videoGames.size();
	}
	
//	public int removeVideoGame(long id) throws SQLException{
//		
//		DB_web_services db = new DB_web_services();
//
//		PreparedStatement ppsm = db.getPreparedStatement(Constants.VideoGame.deleteByID);
//
//    	ppsm.setLong(1, id);
//    	
//    	int rs = ppsm.executeUpdate();
//
//    	if(rs == 1){
//    		users.remove(id);
//    		
//    		
//    		return true;
//    	}
//	    	
//	    	
//	    	throw new DataNotFoundException("The user with the id `" + id + "` doesn't exist !");
//		}
//	}
//	
	
	

}
