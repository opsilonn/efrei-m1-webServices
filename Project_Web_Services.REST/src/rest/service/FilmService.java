package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rest.exception.DataNotFoundException;
import rest.model.Film;
import rest.model.util.Date;
import rest.model.util.Timestamp;
import rest.resource.util.Constants;
import rest.util.DB_web_services;

public class FilmService {

	
	private Map<Long, Film> films ;
	
	public FilmService() throws SQLException
	{
		// We initialize some helpful variables
		try(DB_web_services db = new DB_web_services()){
			Film f;
			PreparedStatement stmt = db.getPreparedStatement(Constants.Film.getAll);
			
			ResultSet rs = stmt.executeQuery();
			

	    	// We empty our current map
			films = new HashMap<Long, Film>();

	    	// As long as the database returns a row, we fill the map
			while(rs.next())
			{
				f = new Film();
				f.setId_film(rs.getInt("ID_film"));
				f.setDirector(rs.getString("director"));
				f.setProductor(rs.getString("productor"));
				f.setMainCast(rs.getString("mainCast"));
				f.setDuration(rs.getTime("duration"));

	    		// We search for the corresponding Multimedia row
				PreparedStatement stmt2 = db.getPreparedStatement(Constants.Multimedia.getByID);
				stmt2.setLong(1, rs.getLong("ID_multimedia"));
				ResultSet rs2 = stmt2.executeQuery();
				
				// If the said row exist :
				if(rs2.next())
				{
					f.setId_multimedia(rs.getLong("ID_multimedia"));
					f.setTitle(rs2.getString("title"));
					f.setDescription(rs2.getString("description"));
					f.setDate_release(new Date(rs2.getString("date_release")));
					f.setID_uploader(rs2.getLong("ID_uploader"));
					f.setLanguage(rs2.getString("language"));
					f.setGenre(rs2.getString("genre"));
					f.setCategory(rs2.getInt("category"));
					f.setStatus(rs2.getInt("status"));
					f.setDate_status(new Timestamp(rs2.getString("date_status")));
					f.setDate_upload(new Timestamp(rs2.getString("date_upload")));

		    		// We put our values in the map
					films.put(f.getId_film(), f);
				}
			}	
		}
		
	}
	
	
	
	
	public List<Film> getFilms(){
		List<Film> films = new ArrayList<Film>(this.films.values());
		
		if(films.isEmpty())
			throw new DataNotFoundException("No films was found !");
		
		
		return films;
	}
	
	
	public Film getFilm(long id){		
		Film film = films.get(id);
		
		if(film == null)
			throw new DataNotFoundException("The film with the id `" + id + "` was not found !");
		
		return film;
	}
	
	
	public Film addFilm(Film film) throws SQLException{
				
		try(DB_web_services db = new DB_web_services()){
			PreparedStatement psmt = db.getPreparedStatement(Constants.Multimedia.post);
			
			psmt.setString(1, film.getTitle());
			psmt.setString(2, film.getLanguage());
			psmt.setString(3, film.getGenre());
			psmt.setInt(4, film.getCategory());
			psmt.setInt(5, film.getStatus());
			psmt.setLong(6, film.getID_uploader());
			psmt.setString(7, film.getDescription());
			psmt.setString(8, film.getDate_release().toString());
			
			int success = psmt.executeUpdate();
			
			if(success == 1)
			{
				ResultSet id = psmt.getGeneratedKeys();
				
				if(id.next()){
					film.setId_multimedia(id.getLong(1));
					
					PreparedStatement psmt2 = db.getPreparedStatement(Constants.Film.post);
					
					psmt2.setString(1, film.getDirector());
					psmt2.setString(2, film.getProductor());
					psmt2.setString(3, film.getMainCast());
					psmt2.setTime(4, film.getDuration());
					psmt2.setLong(5, film.getId_multimedia());
					
					psmt2.executeUpdate();
					
					
					return film;
				}
			}
			
			
			return null;
		}
		
	}
	
	public boolean deleteFilm(long id)
			throws SQLException{
		
		try(DB_web_services db = new DB_web_services()){

			if(this.films.get(id) == null)
				throw new DataNotFoundException("The film with the id `" + id + "` doesn't exist !");
						
			PreparedStatement psmt = db.getPreparedStatement(Constants.Film.delete);
			psmt.setLong(1, id);
			
			
			int succes = psmt.executeUpdate();
			
			if(succes == 1){
				films.remove(id);
				
				
				return true;
			}
			
			
			return false;
		}
		
	}
	
	
}
