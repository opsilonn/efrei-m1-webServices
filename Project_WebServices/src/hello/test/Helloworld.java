package hello.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/employee")
public class Helloworld {

  // This method is called if TEXT_PLAIN is request
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String GetEmployee() {
	  //DBaction db = new DBaction();
	  
	  String query = "SELECT * FROM EMPLOYEE";
	  //db.execute_query(query);
	  //on lit le result set et on créer le JSON
	   System.out.println("coucou");
	   String JSON = "";
	   //status
	   return JSON;
 
  }

} 