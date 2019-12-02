package rest.util;

import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import WebServices.util.ServiceAuthorization;
import rest.model.Film;



public class REST_Film extends REST_Utils
{
	/** Transform a JSON list of {@link Film} into a JAVA ArrayList of {@link Film}
	 * 
	 * @param JSON_string JSON list of {@link Film}
	 * @return a JAVA list of {@link Film}
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ArrayList<Film> REST_Films_GET()
			throws JsonParseException, JsonMappingException, IOException
	{
		service = REST_GetService();
		
		Response resp = ServiceAuthorization.
				getWebTarget( service.path("rest/v1/films").request() ).
				accept(MediaType.APPLICATION_JSON).
	    		get();

		return resp.readEntity( new GenericType<ArrayList<Film>>() {} );
	}	
	
	
	
	
	/** Adds a new {@link Film} to the Database
	 * 
	 * @param newUser {@link Film} to add to the database
	 */
	public static Film REST_Film_POST(Film newFilm)
	{
		// Add it to the database
		service = REST_GetService();
		
		Response resp = ServiceAuthorization.
				getWebTarget( service.path("rest/v1/films").request() ).
				accept(MediaType.APPLICATION_JSON).
	    		post(Entity.entity(newFilm, MediaType.APPLICATION_JSON), Response.class);
		
		return resp.readEntity( Film.class );
	}	
}