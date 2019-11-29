package rest.util;

import static WebServices.util.Constants.GET_BASE_URI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.ClientConfig;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rest.model.User;



public class REST_Utils<E>
{
	public static WebTarget GetREST_Service()
	{
	    ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget service = client.target(GET_BASE_URI);
	    
	    return service;
	}
	
	
	
	
	public static ArrayList<User> GetREST_List(String JSON_string)
			throws JsonParseException, JsonMappingException, IOException
	{
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(JSON_string, new TypeReference<List<User>>(){});
	}
}
