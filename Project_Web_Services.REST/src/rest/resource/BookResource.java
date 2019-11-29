package rest.resource;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import rest.service.BookService;

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
    



	private URI getUriForSelf(Book book) {
		return this.uriInfo.getBaseUriBuilder()
				.path(BookResource.class)
				.path(String.valueOf(book.getId_book()))
				.build();
	}
    
    
	private URI getUriForUploader(Book book) {		
		return this.uriInfo.getBaseUriBuilder()
				.path(UserResource.class)
				.path(String.valueOf(book.getID_uploader()))
				.build();
	}


	private void addLinks(Book book) {
		book.addLink("self", getUriForSelf(book).toString());
		book.addLink("uploader", getUriForUploader(book).toString());
	}
	
	
	
    
    @GET
    public Response getBooks()throws SQLException {
		this.bookService = new BookService();
		
		List<Book> books = bookService.getAllBooks();
		
		for(Book book : books){
			addLinks(book);
		}
		
		
        return Response.status(Status.OK)
				.entity(books)
				.build();

    }


    @Path("/{id_book}")
    @GET
    public Response getBook(@PathParam("id_book") long id) 
    		throws SQLException {
		this.bookService = new BookService();
		
		Book book = bookService.getBook(id);
		
		addLinks(book);
		
		
        return Response.status(Status.OK)
				.entity(book)
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
		this.bookService = new BookService();
		
		Book new_book = bookService.addBook(book);
		
		addLinks(new_book);
		 
		return Response.status(Status.OK).entity(new_book).build();
	
    }


    @Path("/{id_book}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("id_book")Long id, Book book)
    		throws SQLException {
		this.bookService = new BookService();
		
		
		return Response.status(Status.OK) 
				.entity(bookService.updateBook(id, book))
				.build();
    }
    
    @Path("/{id_book}")
    @DELETE
    public Response deleteBook(@PathParam("id_book")Long id)
    		throws SQLException {
		this.bookService = new BookService();
		
		
		return Response.status(Status.OK) 
				.entity(bookService.removeBook(id))
				.build();
    }
}
