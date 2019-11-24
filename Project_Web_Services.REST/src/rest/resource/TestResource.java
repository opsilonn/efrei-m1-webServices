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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import rest.service.UserService;
import rest.model.User;
import rest.model.util.Date;



@Path("/tests")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    UserService userService;
    



	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getTest(Date date){
		System.out.println(date);
		
		return Response
				.status(Status.OK)
				.entity(date)
				.build();
	}

}