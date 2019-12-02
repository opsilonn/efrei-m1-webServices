package rest.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rest.model.*;



public class REST_Utils
{	 
	private static WebTarget service;
	
    // Linking to the REST Backend
    private static URI GET_BASE_URI = UriBuilder.fromUri("http://localhost:8080/Project_Web_Services.REST").build();

	
    /** Sets up the connection to the REST project (Back-end)
     * 
     * @return The connection with the REST project
     */
	public static WebTarget REST_GetService()
	{
	    ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget service = client.target(GET_BASE_URI);
	    
	    return service;
	}
	
	
	
	
	/** Transform a JSON list of {@link User} into a JAVA list of {@link User}
	 * 
	 * @param JSON_string JSON list of {@link User}
	 * @return a JAVA list of {@link User}
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ArrayList<User> REST_Users_GET(String JSON_string)
			throws JsonParseException, JsonMappingException, IOException
	{
		// service = REST_GetService();
		
		
		
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON_string, new TypeReference<List<User>>(){});
	}	
	
	
	
	
	/** Transform a JSON list of {@link Multimedia} into a JAVA list of {@link Multimedia}
	 * 
	 * @param JSON_string JSON list of {@link Multimedia}
	 * @return a JAVA list of {@link Multimedia}
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ArrayList<Multimedia> REST_GetListMultimedias(String JSON_string)
			throws JsonParseException, JsonMappingException, IOException
	{
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON_string, new TypeReference<List<Multimedia>>(){});
	}	
	
	
	
	
	/** Transform a JSON list of {@link Book} into a JAVA list of {@link Book}
	 * 
	 * @return a JAVA list of {@link Book}
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ArrayList<Book> REST_Books_GET()
			throws JsonParseException, JsonMappingException, IOException
	{
		service = REST_GetService();
		
		String JSON = service.path("rest/v1/books").request().
	    		accept(MediaType.APPLICATION_JSON).
	    		get(String.class);
		
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON, new TypeReference<List<Book>>(){});
	}		
	
	
	
	
	/** Transform a JSON list of {@link Film} into a JAVA list of {@link Book}
	 * 
	 * @return a JAVA list of {@link Book}
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ArrayList<Film> REST_Films_GET()
			throws JsonParseException, JsonMappingException, IOException
	{
		service = REST_GetService();
		
		String JSON = service.path("rest/v1/films").request().
	    		accept(MediaType.APPLICATION_JSON).
	    		get(String.class);
		
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON, new TypeReference<List<Film>>(){});
	}	

	
	
	
	/** Transform a JSON list of {@link VideoGame} into a JAVA list of {@link Book}
	 * 
	 * @return a JAVA list of {@link Book}
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ArrayList<VideoGame> REST_VideoGames_GET()
			throws JsonParseException, JsonMappingException, IOException
	{
		service = REST_GetService();
		
		String JSON = service.path("rest/v1/videoGames").request().
	    		accept(MediaType.APPLICATION_JSON).
	    		get(String.class);
		
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON, new TypeReference<List<VideoGame>>(){});
	}	

	
	
	/** Adds a new User to the Database
	 * 
	 * @param newUser User to add to the database
	 */
	public static void REST_User_POST(User newUser)
	{
		// Add it to the database
		service = REST_GetService();
		
	    Response resp = service.path("rest/v1/users").
	    		request(MediaType.APPLICATION_JSON).
	    		post(Entity.entity(newUser, MediaType.APPLICATION_JSON),Response.class);
	}
}
