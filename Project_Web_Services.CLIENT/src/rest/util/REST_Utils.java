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
import rest.model.User;



public class REST_Utils<E>
{	   
    // Linking to the REST Backend
    private static URI GET_BASE_URI = UriBuilder.fromUri("http://localhost:8080/Project_Web_Services.REST").build();

	
	public static WebTarget REST_GetService()
	{
	    ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget service = client.target(GET_BASE_URI);
	    
	    return service;
	}
	
	
	
	
	public static ArrayList<User> REST_GetList(String JSON_string)
			throws JsonParseException, JsonMappingException, IOException
	{
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON_string, new TypeReference<List<User>>(){});
	}
}
