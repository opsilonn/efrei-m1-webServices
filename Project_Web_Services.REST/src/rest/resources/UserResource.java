package rest.resources;


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
import javax.ws.rs.core.UriInfo;

import rest.service.UserService;
import rest.resources.util.JSON_response;



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
    public JSON_response getUsers() {
    	try{
    		this.userService = new UserService();
    		return new JSON_response(userService.getAllUsers());
	   	}
	   	catch(Exception e){
	   		System.out.println("ERROR WHILE GETTING ALL THE USERS\n");
    		return new JSON_response(e);
	   	}
    }


    @Path("/{user_id}")
    @GET
    public JSON_response getUser(@PathParam("user_id") long id) {
    	try{
    		this.userService = new UserService();
            return new JSON_response(userService.getUser(id));
	   	}
	   	catch(Exception e){
	   		System.out.println("ERROR WHILE GETTING THE USER " + id + "\n");
    		return new JSON_response(e);
	   	}
    }


    @GET
    @Path("count")
    public JSON_response getCount() {
    	try{
    		this.userService = new UserService();
            return new JSON_response(userService.getUserCount());
	   	}
	   	catch(Exception e){
	   		System.out.println("ERROR WHILE GETTING THE USERS COUNT\n");
    		return new JSON_response(e);
	   	}
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public JSON_response postUser(@FormParam("pseudo")String pseudo, @FormParam("psw")String password, @FormParam("mail")String email) {
    	try {
    		this.userService = new UserService();
			return new JSON_response(userService.addUser(pseudo, password, email));
			
		} catch (Exception e) {
    		System.out.println("ERROR WHILE ADDING NEW USER\n");
    		
    		return new JSON_response(e);
		}
    }


    @Path("/{user_id}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public JSON_response putUser(@PathParam("user_id")Long id, @FormParam("e_psw")String existing_password, @FormParam("n_psw")String new_password, @FormParam("mail")String email) {
    	try {
    		this.userService = new UserService();
    		return new JSON_response(userService.updateUser(id, existing_password, new_password, email));
			
		} catch (Exception e) {
    		System.out.println("ERROR WHILE UPDATING NEW USER\n");
    		
    		return new JSON_response(e);
		}
    }

    
    @Path("/{id_user}")
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public JSON_response deleteUser(@PathParam("id_user")Long id) {
    	try {
    		this.userService = new UserService();
    		return new JSON_response(userService.removeUser(id));
			
		} catch (Exception e) {
    		System.out.println("ERROR WHILE DELETING NEW USER\n");
    		
    		return new JSON_response(e);
		}
    }

}