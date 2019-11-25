package rest.resource;

import java.net.URI;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import rest.model.Book;
import rest.model.Film;
import rest.model.Rate;
import rest.service.BookService;
import rest.service.FilmService;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    
    @Context
    Request request;
    
    BookService bookService;
    
    @GET
    public Response getBooks()throws SQLException {
		this.bookService = new BookService();
		
		return Response.status(Status.OK)
				.entity((bookService.getAllBooks()))
				.build();

    }


    @Path("/{book_id}")
    @GET
    public Response getBook(@PathParam("book_id") long id) 
    		throws SQLException {
		this.bookService = new BookService();
		
		
        return Response.status(Status.OK)
				.entity(bookService.getBook(id))
				.build();
    }


    @GET
    @Path("/count")
    public Response getCount() 
    		throws SQLException {
		this.bookService = new BookService();
		
		
        return Response.status(Status.OK)
        		.entity(bookService.getBookCount())
        		.build();
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postBook(Book book) 
    		throws SQLException {
		
    	Book b = new Book();
		this.bookService = new BookService();
		b = bookService.addBook(book);
		 
		return Response.status(Status.OK).entity(b).build();
	
    }


    @Path("/{user_id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("id_book")Long id, Book new_book)
    		throws SQLException {
		this.bookService = new BookService();
		
		
		return Response.status(Status.OK) 
				.entity(bookService.updateBook(id, new_book))
				.build();
    }
//
//    
//    @Path("/{id_user}")
//    @DELETE
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public Response deleteBook(@PathParam("id_book")Long id)
//    		throws SQLException {
//		this.bookService = new BookService();
//		
//		
//		return Response.status(Status.OK) 
//				.entity(bookService.removeBook(id))
//				.build();
//    }

    
    
}
