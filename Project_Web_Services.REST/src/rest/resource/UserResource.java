package rest.resource;


import java.net.URI;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import rest.service.UserService;
import rest.model.User;



@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    UserService userService;
    

    @GET
    public Response getUsers()
    		throws SQLException {
		this.userService = new UserService();
		
		
		return Response.status(Status.OK)
				.entity((userService.getAllUsers()))
				.build();
    }


    @Path("/{user_id}")
    @GET
    public Response getUser(@PathParam("user_id") long id) 
    		throws SQLException {
		this.userService = new UserService();
		
		
        return Response.status(Status.OK)
				.entity(userService.getUser(id))
				.build();
    }


    @GET
    @Path("/count")
    public Response getCount() 
    		throws SQLException {
		this.userService = new UserService();
		
		
        return Response.status(Status.OK)
        		.entity(userService.getUserCount())
        		.build();
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(User user) 
    		throws SQLException {
		this.userService = new UserService();
		
		User new_user = userService.addUser(user);
		URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(new_user.getId_user())).build();
		
		
		return Response.created(location)
				.entity(new_user)
				.build();
    }


    @Path("/{user_id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("user_id")Long id, @FormParam("e_psw")String existing_password, @FormParam("n_psw")String new_password, @FormParam("mail")String email)
    		throws SQLException {
		this.userService = new UserService();
		
		
		return Response.status(Status.OK) 
				.entity(userService.updateUser(id, existing_password, new_password, email))
				.build();
    }

    
    @Path("/{id_user}")
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteUser(@PathParam("id_user")Long id)
    		throws SQLException {
		this.userService = new UserService();
		
		
		return Response.status(Status.OK) 
				.entity(userService.removeUser(id))
				.build();
    }

    
    
    
    @Path("/{id_user}/rates")
    public RateResource getRateResource() {
    	return new RateResource();
    }

}