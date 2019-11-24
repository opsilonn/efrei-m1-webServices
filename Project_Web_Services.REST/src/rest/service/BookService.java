package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rest.exception.DataNotFoundException;
import rest.model.Book;
import rest.model.Film;
import rest.model.VideoGame;
import rest.model.util.Date;
import rest.model.util.Timestamp;
import rest.service.util.Constants;
import rest.util.DB_web_services;

public class BookService {
	
	
	private long id_book;
	private Map<Long, Book> books = DB_web_services.getBooks();
	
	public BookService() throws SQLException
	{
		// We initialize some helpful variables
		DB_web_services db = new DB_web_services();		
		PreparedStatement ppsm = db.getPreparedStatement(Constants.Book.getAll);	
    	ResultSet rs = ppsm.executeQuery();

    	// We empty our current map
    	books.clear();

    	// As long as the database returns a row, we fill the map
    	while(rs.next())
    	{
    		// We create our map values (Key & Value)
    		long mapKey = rs.getLong("ID_book");
    		Book mapValue = new Book();
    		
    		
    		// We search for the corresponding Multimedia row
    		PreparedStatement stmt2 = db.getPreparedStatement(Constants.Multimedia.getByID);
    		stmt2.setLong(1, rs.getLong("ID_multimedia"));
    		
    		ResultSet rs2 = stmt2.executeQuery();
			
			// If the said row exist :
			if(rs2.next())
			{
				// fill here the good values
	    		 mapValue = new Book(rs2.getLong("ID_multimedia"), rs2.getString("title"),
	    				 rs2.getString("description"), rs2.getString("language"), 
	    				 rs2.getString("genre"), 
	    				 rs2.getInt("category"), 
	    				 rs2.getInt("status"),
	    				 rs2.getLong("ID_uploader"),
	    				 new Timestamp(rs2.getString("date_status")),
	    				new Timestamp(rs2.getString("date_upload")), 
	    				new Date(rs2.getString("date_release")), 
	    				rs.getLong("ID_book"), rs.getString("author"), 
	    				rs.getString("publisher"));
			}    		
    		// We put our values in the map
    		books.put(mapKey, mapValue);

    	}
		
	}
	
	public List<Book> getAllBooks()
	{
		List<Book> return_books = new ArrayList<Book>(books.values());
		
		if(return_books.isEmpty())
			throw new DataNotFoundException("No books was found !");
		
		
    	return return_books;
	}
	
	public Book getBook(long id){
		Book book = books.get(id);

		if(book == null)
			throw new DataNotFoundException("The book with the id `" + id + "` was not found !");
		
		
		return book;
	}
	
	public int getBookCount(){
		return books.size();
	}
	
	
	public Book addBook(Book book)
			throws SQLException{
	
		DB_web_services db = new DB_web_services();
    	
		PreparedStatement ppsm = db.getPreparedStatement(Constants.Multimedia.post);
    	
		// We initialize our statement's values of multimedia
		ppsm.setString(1, book.getTitle());
		ppsm.setString(2, book.getDescription());
		ppsm.setString(3, book.getLanguage());
		ppsm.setString(4, book.getGenre());
		ppsm.setInt(5, book.getCategory());
		ppsm.setInt(6, book.getStatus());
		ppsm.setLong(7, book.getID_uploader());
		ppsm.setString(8, book.getDate_release().toString());
    	
    	int rs = ppsm.executeUpdate();

    	//Now that the multimedia is created, we can add it to the book table
    	if(rs == 1){
    		ResultSet generated_id = ppsm.getGeneratedKeys();
    		
    		if(generated_id.next()){
    			
    			PreparedStatement ppsm2 = db.getPreparedStatement(Constants.Book.post);
    			

				ppsm2.setString(1, book.getAuthor());
				ppsm2.setString(2, book.getPublisher());
				ppsm2.setLong(3, generated_id.getLong(1));
				
    			ppsm2.executeQuery();

    		}
    		return book;
    	}

    	return null;
	}
	
	
//	public void deleteBook(long id) throws SQLException{
//		
//		DB_web_services dba = new DB_web_services();
//		Film f = films.get(id);
//		
//		PreparedStatement psmt = dba.getPreparedStatement(Constants.Rate.DeleteByMult);
//		psmt.setLong(1, f.getId_multimedia());
//		
//		PreparedStatement psmt2 = dba.getPreparedStatement(Constants.Comment.deleteByMult);
//		psmt2.setLong(1, f.getId_multimedia());
//		
//		PreparedStatement psmt3 = dba.getPreparedStatement(Constants.Film.delete);
//		psmt3.setLong(1, id);
//		
//		PreparedStatement psmt4 = dba.getPreparedStatement(Constants.Multimedia.delete);
//		psmt4.setLong(1, f.getId_multimedia());
//		
//		psmt.executeUpdate();
//		psmt2.executeUpdate();
//		psmt3.executeUpdate();
//		psmt4.executeUpdate();
//		
//		System.out.println(f.getId_multimedia());
//		
//	}
	
	
	
	
	
	
//	public int removeBook(long id) throws SQLException{
//		
//		DB_web_services db = new DB_web_services();
//
//		PreparedStatement ppsm = db.getPreparedStatement(Constants.Book.deleteByID);
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
