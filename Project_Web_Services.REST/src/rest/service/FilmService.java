package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.protocol.Resultset;

import rest.model.Film;
import rest.model.User;
import rest.model.util.Timestamp;
import rest.resource.util.Constants;
import rest.util.DB_web_services;

public class FilmService {

	
	private long id_mult;
	
	private Map<Long, Film> films ;
	
	public FilmService() throws SQLException
	{
		// We initialize some helpful variables
		DB_web_services dba = new DB_web_services();
		Film f;
		PreparedStatement stmt = dba.getPreparedStatement(Constants.Film.getAll);
		PreparedStatement stmt2 ;
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
			stmt2 = dba.getPreparedStatement(Constants.Multimedia.getByID);
			stmt2.setLong(1, rs.getLong("ID_multimedia"));
			ResultSet rs2 = stmt2.executeQuery();
			
			// If the said row exist :
			if(rs2.next())
			{
				f.setId_multimedia(rs.getLong("ID_multimedia"));
				f.setTitle(rs2.getString("title"));
				f.setDescription(rs2.getString("description"));
				//f.setRelease_date(new Date(rs.getString()));
				f.setLanguage(rs2.getString("language"));
				f.setGenre(rs2.getString("genre"));
				f.setCategory(rs2.getInt("category"));
				f.setStatus(rs2.getInt("status"));
				f.setDate_status(new Timestamp(rs2.getString("date_status")));
				f.setDate_upload(new Timestamp(rs2.getString("date_upload")));
			}

    		// We put our values in the map
			films.put(f.getId_film(), f);
		}	
	}
	
	
	public Film getFilmByValue(long id){
		Film f;
		
		f = films.get(id);
		
		return f;
	}
	
	public List<Film> getFilm(){
		List<Film> lf = new ArrayList<Film>(this.films.values());
		return lf;
	}
	
	public Film addFilm(Film film) throws SQLException{
		
		Film f = film;
		long ID_user = film.getID_uploader();
		
		DB_web_services dba = new DB_web_services();
		
		PreparedStatement psmt = dba.getPreparedStatement(Constants.Multimedia.post);
		
		psmt.setString(1, f.getTitle());
		psmt.setString(2, f.getLanguage());
		psmt.setString(3, f.getGenre());
		psmt.setInt(4, f.getCategory());
		psmt.setInt(5, f.getStatus());
		psmt.setLong(6, ID_user);
		psmt.setString(7, f.getDescription());
		psmt.setString(8, f.getDate_release().toString());
		int success = psmt.executeUpdate();
		
		if(success == 1)
		{
			ResultSet id = psmt.getGeneratedKeys();
			if(id.next()){
				psmt = dba.getPreparedStatement(Constants.Film.post);
				psmt.setString(1, f.getDirector());
				psmt.setString(2, f.getProductor());
				psmt.setString(3, f.getMainCast());
				psmt.setTime(4, f.getDuration());
				psmt.setLong(5, id.getLong(1));
				psmt.executeUpdate();
			}
		}
		
		return f;
	}
	
	public void delFilm(long id) throws SQLException{
		
		/*DB_web_services dba = new DB_web_services();
		Film f = films.get(id);
		
		PreparedStatement psmt = dba.getPreparedStatement(Constants.Rate.deleteByMult);
		psmt.setLong(1, f.getId_multimedia());
		
		PreparedStatement psmt2 = dba.getPreparedStatement(Constants.Comment.deleteByMult);
		psmt2.setLong(1, f.getId_multimedia());
		
		PreparedStatement psmt3 = dba.getPreparedStatement(Constants.Film.delete);
		psmt3.setLong(1, id);
		
		PreparedStatement psmt4 = dba.getPreparedStatement(Constants.Multimedia.delete);
		psmt4.setLong(1, f.getId_multimedia());
		
		psmt.executeUpdate();
		psmt2.executeUpdate();
		psmt3.executeUpdate();
		psmt4.executeUpdate();
		
		System.out.println(f.getId_multimedia());*/
		
	}
}
