package rest.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rest.model.*;



public class REST_Utils
{	   
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
	public static ArrayList<User> REST_GetListUsers(String JSON_string)
			throws JsonParseException, JsonMappingException, IOException
	{
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
	 * @param JSON_string JSON list of {@link Book}
	 * @return a JAVA list of {@link Book}
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ArrayList<Book> REST_GetListBooks(String JSON_string)
			throws JsonParseException, JsonMappingException, IOException
	{
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON_string, new TypeReference<List<Book>>(){});
	}		
	
	
	
	
	/** Transform a JSON list of {@link Film} into a JAVA list of {@link Book}
	 * 
	 * @param JSON_string JSON list of {@link Book}
	 * @return a JAVA list of {@link Book}
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ArrayList<Film> REST_GetListFilms(String JSON_string)
			throws JsonParseException, JsonMappingException, IOException
	{
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON_string, new TypeReference<List<Film>>(){});
	}	

	
	
	
	/** Transform a JSON list of {@link VideoGame} into a JAVA list of {@link Book}
	 * 
	 * @param JSON_string JSON list of {@link Book}
	 * @return a JAVA list of {@link Book}
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ArrayList<VideoGame> REST_GetListVideoGames(String JSON_string)
			throws JsonParseException, JsonMappingException, IOException
	{
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON_string, new TypeReference<List<VideoGame>>(){});
	}	
	
	
	
	
	/** Transform a JSON list of {@link Rate} into a JAVA list of {@link Rate}
	 * 
	 * @param JSON_string JSON list of {@link Rate}
	 * @return a JAVA list of {@link Rate}
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static ArrayList<Rate> REST_GetListRates(String JSON_string)
			throws JsonParseException, JsonMappingException, IOException
	{
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON_string, new TypeReference<List<Rate>>(){});
	}
}
