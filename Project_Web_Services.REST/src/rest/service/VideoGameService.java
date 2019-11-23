package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rest.exception.DataNotFoundException;
import rest.model.VideoGame;
import rest.service.util.Constants;
import rest.util.DB_web_services;

public class VideoGameService {
	
	private Map<Long, VideoGame> videoGames = DB_web_services.getVideoGames();
	
	public VideoGameService() throws SQLException
	{
		DB_web_services db = new DB_web_services();
		
		PreparedStatement ppsm = db.getPreparedStatement(Constants.VideoGame.getAll);
    
		
    	ResultSet rs = ppsm.executeQuery();
    	videoGames.clear();

    	while(rs.next()){
    		videoGames.put(rs.getLong("ID_videoGame"),new VideoGame(rs.getLong("ID_videoGame"), rs.getString("developper"), rs.getString("publisher")));
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
