package rest.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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
import javax.ws.rs.core.UriInfo;

import rest.resource.util.JSON_response;
import rest.service.CommentService;




@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    CommentService commentService;
    

    
    
    @GET
    public JSON_response getComments(@PathParam("id_user")long id_user, @QueryParam("value")String value) {
    	try{
    		this.commentService = new CommentService(id_user);
    		
    		if(value != null){

        		return new JSON_response(commentService.getCommentsByValue(value));
    			
    		}
    		
    		return new JSON_response(commentService.getAllComments());
	   	}
	   	catch(NumberFormatException e){

    		return new JSON_response(commentService.getAllComments());
	   	}
	   	catch(Exception e){
	   		System.out.println("ERROR WHILE GETTING ALL THE RATES\n");
    		return new JSON_response(e);
	   	}
    }


    @Path("{comment_id}")
    @GET
    public JSON_response getComment(@PathParam("id_user")long id_user, @PathParam("comment_id") long id) {
    	try{
    		this.commentService = new CommentService(id_user);
            return new JSON_response(commentService.getComment(id));
	   	}
	   	catch(Exception e){
	   		System.out.println("ERROR WHILE GETTING THE RATE " + id + "\n");
    		return new JSON_response(e);
	   	}
    }


    @GET
    @Path("count")
    public JSON_response getCount(@PathParam("id_user")long id_user) {
    	try{
    		this.commentService = new CommentService(id_user);
            return new JSON_response(commentService.getCommentCount());
	   	}
	   	catch(Exception e){
	   		System.out.println("ERROR WHILE GETTING THE RATE COUNT\n");
    		return new JSON_response(e);
	   	}
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public JSON_response postComment(@PathParam("id_user")long id_user, @FormParam("value")String value, @FormParam("id_m")long id_multimedia) {
    	try {
    		this.commentService = new CommentService(id_user);
			return new JSON_response(commentService.addComment(value, id_multimedia));
			
		} catch (Exception e) {
    		System.out.println("ERROR WHILE ADDING NEW RATE\n");
    		
    		return new JSON_response(e);
		}
    }

    
    @Path("/{id_comment}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public JSON_response putComment(@PathParam("id_user")long id_user, @PathParam("id_comment")long id, @FormParam("value")String value) {
    	try {
    		this.commentService = new CommentService(id_user);
    		return new JSON_response(commentService.updateComment(id, value));
			
		} catch (Exception e) {
    		System.out.println("ERROR WHILE UPDATING NEW RATE\n");
    		
    		return new JSON_response(e);
		}
    }

    
    @Path("/{id_comment}")
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public JSON_response deleteComment(@PathParam("id_user")long id_user, @PathParam("id_comment")Long id) {
    	try {
    		this.commentService = new CommentService(id_user);
    		return new JSON_response(commentService.removeComment(id));
			
		} catch (Exception e) {
    		System.out.println("ERROR WHILE DELETING NEW RATE\n");
    		
    		return new JSON_response(e);
		}
    }

}
