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
import rest.model.User;
import rest.model.util.Timestamp;
import rest.service.util.Constants;
import rest.util.DB_web_services;

public class BookService {
	
	
	private long id_book;
	private Map<Long, Book> books = DB_web_services.getBooks();
	
	public BookService() throws SQLException
	{
		DB_web_services db = new DB_web_services();
		
		PreparedStatement ppsm = db.getPreparedStatement(Constants.Book.getAll);
    
		
    	ResultSet rs = ppsm.executeQuery();
    	books.clear();

    	while(rs.next()){
    		books.put(rs.getLong("ID_book"),new Book(rs.getLong("ID_book"), rs.getString("author"), rs.getString("publisher"), rs.getLong("ID_multimedia")));
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
	
	
	public Book addBook(String author, String publisher, long id_multimedia)
			throws SQLException{
		
	
		DB_web_services db = new DB_web_services();
    	
    	PreparedStatement ppsm = db.getPreparedStatement(Constants.Book.post);
    	
    	ppsm.setString(1, author);
    	ppsm.setString(2, publisher);
    	ppsm.setLong(3, id_multimedia);
    	
    	int rs = ppsm.executeUpdate();

    	if(rs == 1){
    		ResultSet generated_id = ppsm.getGeneratedKeys();
    		
    		if(generated_id.next()){
    			ppsm = db.getPreparedStatement(Constants.Book.getByID);
    			
    			ppsm.setLong(1, generated_id.getLong(1));
    			ppsm.setLong(2, id_multimedia);
    			
    			ResultSet rs_book = ppsm.executeQuery();
    	    	
    	    	if(rs_book.next()){
    	    		Book new_book = new Book(rs_book.getLong("ID_book"), rs_book.getString("author"), rs_book.getString("publisher"), rs_book.getLong("ID_multimedia"));
    	    		
    	    		this.books.put(rs_book.getLong("ID_book"), new_book);
    	    		
    	    		
    	    		return new_book;
    	    	}
    		}
    	}

    	
    	return null;
	}
	
	
	
	
	
	
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
