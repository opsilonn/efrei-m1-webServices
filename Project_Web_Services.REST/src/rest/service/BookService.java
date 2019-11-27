package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rest.exception.DataNotFoundException;
import rest.model.Book;
import rest.model.Film;
import rest.model.VideoGame;
import rest.model.util.Date;
import rest.model.util.Time;
import rest.model.util.Timestamp;
import rest.service.util.Constants;
import rest.util.DB_web_services;

public class BookService {
	
	
	private Map<Long, Book> books = DB_web_services.getBooks();
	
	public BookService() throws SQLException
	{
		// We initialize some helpful variables
		try(DB_web_services db = new DB_web_services()){

			PreparedStatement ppsm = db.getPreparedStatement(Constants.Book.getAll);	
	    	ResultSet rs = ppsm.executeQuery();

	    	// We empty our current map
	    	books.clear();

	    	// As long as the database returns a row, we fill the map
	    	while(rs.next())
	    	{	
				Book book = new Book(
						rs.getLong("ID_multimedia"),
						rs.getString("title"),
						rs.getString("language"),
						rs.getString("description"),
						rs.getString("genre"),
						rs.getInt("category"),
						rs.getInt("status"),
						rs.getLong("ID_uploader"),
						new Timestamp(rs.getString("date_status")),
						new Timestamp(rs.getString("date_upload")),
						new Date(rs.getString("date_release")),
						rs.getLong("ID_book"),
						rs.getString("author"),
						rs.getString("publisher")
						);

	    		// We put our values in the map
				books.put(book.getId_book(), book);

	    	}
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
	
			try(DB_web_services db = new DB_web_services()){

			PreparedStatement ppsm = db.getPreparedStatement(Constants.Multimedia.post);
	    	
			// We initialize our statement's values of multimedia
			ppsm.setString(1, book.getTitle());
			ppsm.setString(2, book.getLanguage());
			ppsm.setString(3, book.getGenre());
			ppsm.setInt(4, book.getCategory());
			ppsm.setInt(5, book.getStatus());
			ppsm.setLong(6, book.getID_uploader());
			ppsm.setString(7, book.getDescription());
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
					
					int rs2 = ppsm2.executeUpdate();
					if(rs2 == 1)
					{
						ResultSet generated_id2 = ppsm2.getGeneratedKeys();
						if(generated_id2.next())
						{
							PreparedStatement ppsm3 = db.getPreparedStatement(Constants.Book.getByID);
							ppsm3.setLong(1, generated_id2.getLong(1));
							
							
							ResultSet rs3 = ppsm3.executeQuery();
							
							if(rs3.next()){
			
								return new Book(
										rs3.getLong("ID_multimedia"),
										rs3.getString("title"),
										rs3.getString("language"),
										rs3.getString("description"),
										rs3.getString("genre"),
										rs3.getInt("category"),
										rs3.getInt("status"),
										rs3.getLong("ID_uploader"),
										new Timestamp(rs3.getString("date_status")),
										new Timestamp(rs3.getString("date_upload")),
										new Date(rs3.getString("date_release")),
										rs3.getLong("ID_book"),
										rs3.getString("author"),
										rs3.getString("publisher")
										);
							}
						}
						
						
					}
	    		}
	    	}

	    	return null;
		}
    	
	}
	

	/*public boolean updateBook(long id, ) 
			throws SQLException{

		DB_web_services db = new DB_web_services();
    	

    	PreparedStatement ppsm = db.getPreparedStatement(Constants.Rate.putByID);
        	
    	ppsm.setInt(1, value);
    	ppsm.setLong(2, id);
    	ppsm.setLong(3, this.id_user);

    	
    	int rs = ppsm.executeUpdate();

    	if(rs == 1){
    		Rate rate = getRate(id);
    		
    		rate.setValue(value);
    	}
    	
    	
    	return (rs == 1) ? true : false;
	}*/
	
	
	
	public boolean removeBook(long id) throws SQLException{
		
		try(DB_web_services db = new DB_web_services()){
			
			if(this.books.get(id) == null)
				throw new DataNotFoundException("The book with the id `" + id + "` doesn't exist !");

			PreparedStatement ppsm = db.getPreparedStatement(Constants.Book.deleteByID);
	
	    	ppsm.setLong(1, id);
	    	
	    	int rs = ppsm.executeUpdate();
	
	    	if(rs == 1){
	    		books.remove(id);
	    		
	    		
	    		return true;
	    	}	
			
	    	
			return false;
		}
		
	}
	
	
	

}
