package rest.resource;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import rest.model.Film;
import rest.service.FilmService;

@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
public class FilmResource
{
    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo

	 @Context
	 UriInfo uriInfo;
	 @Context
	 Request request;
	
	 FilmService films;
	 
	 
		
    /** Returns all the {@Film} rows from the database
     * 
     * @return All the {@Film} rows from the database
     * @throws SQLException
     */
	 @GET
	 public Response getFilms() 
			 throws SQLException{
		 this.films = new FilmService();
		 
		 return Response.status(Status.OK).entity(films.getFilm()).build();
	 }
	 
	 
	 
	 
    /** Returns a given {@Film} from the database
     * 
     * @param id ID of the {@Film} we are returning
     * @return a specific {@Film} row
     * @throws SQLException
     */
	 @Path("/{film_id}")
	 @GET
	 public Response getFilm(@PathParam("film_id") long id) 
			 throws SQLException {
		 
		 
		this.films = new FilmService();
		
		 return Response.status(Status.OK)
	        		.entity(films.getFilmByValue(id))
	        		.build();
	 }
	 
	 
	 
	 

	 /** Creates a new instance of the table {@Film}
	  * 
	  * @param film Instance to add to the database
	  * @return
	  * @throws SQLException
	  */
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response PostFilm(Film film)
			 throws SQLException
	 {	 
		 Film f;
		 this.films = new FilmService();
		 f = films.addFilm(film);
		 
		 return Response.status(Status.OK).entity(f).build();
	 }
	 
	 @Path("/{film_id}")
	 @DELETE
	 public Response DeleteFilm(@PathParam("film_id") long id) throws SQLException{
		 
		 this.films = new FilmService();
		 films.delFilm(id);
		 
		 return Response.status(Status.OK).build();
	 }
	 
}
