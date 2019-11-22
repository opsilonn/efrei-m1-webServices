package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rest.model.Multimedia;
import rest.model.Comment;
import rest.model.User;
import rest.resource.util.Constants;
import rest.util.DB_web_services;

public class CommentService {
	
	private long id_user;
	
	private Map<Long, User> users = DB_web_services.getUsers();
	private Map<Long, Comment> comments = DB_web_services.getComments();
	
	
	public CommentService(long id_user)
			throws SQLException
	{
		this.id_user = id_user;
    	DB_web_services db = new DB_web_services();
	    	
    	PreparedStatement ppsm = db.getPreparedStatement(Constants.Comment.getAll);
    	
    	ppsm.setLong(1, this.id_user);
    	
    	ResultSet rs = ppsm.executeQuery();
    	this.comments.clear();
    	
    	while(rs.next()){
    		this.comments.put(rs.getLong("ID_comment"), new Comment(rs.getLong("ID_comment"), rs.getString("value"), this.users.get(rs.getLong("ID_user")), new Multimedia()));
    	}
	}

	public List<Comment> getAllComments()
	{
    	return new ArrayList<Comment>(this.comments.values());
	}

	public List<Comment> getCommentsByValue(String value)
	{
		List<Comment> return_comments = new ArrayList<Comment>();

		for(Map.Entry<Long, Comment> entry : this.comments.entrySet()){
			if(entry.getValue().getValue() == value){
				return_comments.add(entry.getValue());
			}
		}
		
    	return return_comments;
	}
	

	public Comment getComment(long id){
		return this.comments.get(id);
	}
	
	
	public int getCommentCount(){
		return this.comments.size();
	}
	
	
	public Comment addComment(String value, long id_multimedia) 
			throws Exception{
		
		DB_web_services db = new DB_web_services();
    	
    	PreparedStatement ppsm = db.getPreparedStatement(Constants.Comment.post);
    	
    	ppsm.setString(1, value);
    	ppsm.setLong(2, this.id_user);
    	ppsm.setLong(3, id_multimedia);
    	
    	
    	int rs = ppsm.executeUpdate();

    	if(rs == 1){
    		ResultSet genecommentd_id = ppsm.getGeneratedKeys();
    		
    		if(genecommentd_id.next()){
    			ppsm = db.getPreparedStatement(Constants.Comment.getByID);
    			
    			ppsm.setLong(1, genecommentd_id.getLong(1));
    	    	ppsm.setLong(2, this.id_user);
    			
    			ResultSet rs_comment = ppsm.executeQuery();
    	    	
    	    	if(rs_comment.next()){
    	    		Comment comment = new Comment(rs_comment.getLong("ID_comment"), rs_comment.getString("value"), this.users.get(rs_comment.getLong("ID_user")), new Multimedia());
    	    		this.comments.put(rs_comment.getLong("ID_comment"), comment);
    	    		
    	    		
    	    		return comment;
    	    	}
    		}
    	}

    	
    	return null;
	}
	
	
	public boolean updateComment(long id, String value) 
			throws Exception{

		DB_web_services db = new DB_web_services();
    	

    	PreparedStatement ppsm = db.getPreparedStatement(Constants.Comment.putByID);
        	
    	ppsm.setString(1, value);
    	ppsm.setLong(2, id);
    	ppsm.setLong(3, this.id_user);

    	
    	int rs = ppsm.executeUpdate();

    	if(rs == 1){
    		Comment comment = getComment(id);
    		
    		comment.setValue(value);
    	}
    	
    	
    	return (rs == 1) ? true : false;
	}
	
	
	public boolean removeComment(long id) 
			throws Exception{
		DB_web_services db = new DB_web_services();

		PreparedStatement ppsm = db.getPreparedStatement(Constants.Comment.deleteByID);

    	ppsm.setLong(1, id);
    	ppsm.setLong(2, this.id_user);
    	
    	int rs = ppsm.executeUpdate();

    	if(rs == 1){
    		this.comments.remove(id);
    	}
    	
    	
    	return (rs == 1) ? true : false;
	}
}
