package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import rest.exception.DataNotFoundException;
import rest.model.User;
import rest.model.util.Timestamp;
import rest.resource.util.Constants;
import rest.util.DB_web_services;

public class UserService {
	
	private Map<Long, User> users = DB_web_services.getUsers();
	
	
	public UserService()
			throws SQLException
	{
    	DB_web_services db = new DB_web_services();
	    	
    	PreparedStatement ppsm = db.getPreparedStatement(Constants.User.getAll);
    	
    	ResultSet rs = ppsm.executeQuery();
    	users.clear();
    	
    	while(rs.next()){
    		users.put(rs.getLong("ID_user"), new User(rs.getLong("ID_user"), rs.getString("pseudo"), rs.getString("email"), new Timestamp(rs.getString("date_creation"))));
    	}
	}

	public List<User> getAllUsers()
	{
		List<User> return_users = new ArrayList<User>(users.values());
		
		if(return_users.isEmpty())
			throw new DataNotFoundException("No users was found !");
		
		
    	return return_users;
	}
	

	public User getUser(long id){
		User user = users.get(id);

		if(user == null)
			throw new DataNotFoundException("The user with the id `" + id + "` was not found !");
		
		
		return user;
	}
	
	
	public int getUserCount(){
		return users.size();
	}
	
	
	public User addUser(User user)
			throws SQLException{
		
		if(user.getPassword() == null || user.getPassword() == ""){
			throw new SQLIntegrityConstraintViolationException("Le champ 'password' ne peut être vide (null)");
		}
		
		DB_web_services db = new DB_web_services();
    	
    	PreparedStatement ppsm = db.getPreparedStatement(Constants.User.post);
    	
    	ppsm.setString(1, user.getPseudo());
    	ppsm.setString(2, user.getPassword());
    	ppsm.setString(3, user.getEmail());
    	
    	int rs = ppsm.executeUpdate();

    	if(rs == 1){
    		ResultSet generated_id = ppsm.getGeneratedKeys();
    		
    		if(generated_id.next()){
    			ppsm = db.getPreparedStatement(Constants.User.getByID);
    			
    			ppsm.setLong(1, generated_id.getLong(1));
    			
    			ResultSet rs_user = ppsm.executeQuery();
    	    	
    	    	if(rs_user.next()){
    	    		User new_user = new User(rs_user.getLong("ID_user"), rs_user.getString("pseudo"), rs_user.getString("email"), new Timestamp(rs_user.getString("date_creation")));
    	    		users.put(rs_user.getLong("ID_user"), new_user);
    	    		
    	    		
    	    		return new_user;
    	    	}
    		}
    	}

    	
    	return null;
	}
	
	
	public boolean updateUser(long id, String existing_password, String new_password, String email) 
			throws SQLException{

		DB_web_services db = new DB_web_services();
    	

    	PreparedStatement ppsm = db.getPreparedStatement(Constants.User.checkPasswordByID);

    	ppsm.setString(1, existing_password);
    	ppsm.setLong(2, id);
		
		ResultSet rs_check = ppsm.executeQuery();
    	
    	if(rs_check.next()){
    		
        	ppsm = db.getPreparedStatement(Constants.User.putByID);
        	
        	ppsm.setString(1, new_password);
        	ppsm.setString(2, email);
        	ppsm.setLong(3, id);
        	
        	int rs = ppsm.executeUpdate();

        	if(rs == 1){
        		User user = getUser(id);
        		
        		user.setEmail(email);
        	}
        	
        	
        	return (rs == 1) ? true : false;
    	}
    	
    	
    	return false;
	}
	
	
	public boolean removeUser(long id) 
			throws SQLException{
		DB_web_services db = new DB_web_services();

		PreparedStatement ppsm = db.getPreparedStatement(Constants.User.deleteByID);

    	ppsm.setLong(1, id);
    	
    	int rs = ppsm.executeUpdate();

    	if(rs == 1){
    		users.remove(id);
    		
    		
    		return true;
    	}
    	
    	
    	throw new DataNotFoundException("The user with the id `" + id + "` doesn't exist !");
	}
}
