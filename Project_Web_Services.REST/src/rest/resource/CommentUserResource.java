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

import rest.model.Comment;
import rest.service.CommentUserService;


@Produces(MediaType.APPLICATION_JSON)
public class CommentUserResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    Request request;
    
    CommentUserService commentService;
    



	private URI getUriForSelf(Comment comment, UriInfo uriInfo) {	
		return uriInfo.getBaseUriBuilder()
				.path(UserResource.class)
				.path(UserResource.class, "getCommentResource")
				.path(String.valueOf(comment.getId_comment()))
				.resolveTemplate("id_user", comment.getId_user())
				.build();
	}
    
    
	private URI getUriForParentUser(Comment comment, UriInfo uriInfo) {	
		return uriInfo.getBaseUriBuilder()
				.path(UserResource.class)
				.path(String.valueOf(comment.getId_user()))
				.build();
	}
    
    
	private URI getUriForMultimedia(Comment comment, UriInfo uriInfo)
			throws SQLException {			
		return uriInfo.getBaseUriBuilder()
				.path( rest.model.Multimedia.getChildClass(comment.getId_multimedia()) ) 
				.path( String.valueOf(rest.model.Multimedia.getChildID(comment.getId_multimedia())) )
				.build();
	}


	private void addLinks(Comment comment, UriInfo uriInfo)
			throws SQLException {
		comment.addLink("self", getUriForSelf(comment, uriInfo).toString());
		comment.addLink("author", getUriForParentUser(comment, uriInfo).toString());
		comment.addLink("multimedia", getUriForMultimedia(comment, uriInfo).toString());
	}
    

    
    
    @GET
    public Response getComments(@PathParam("id_user")long id_user, @QueryParam("start")int start, @QueryParam("end")int end,
    		@Context UriInfo uriInfo) 
    		throws SQLException{
   		this.commentService = new CommentUserService(id_user);
		
		List<Comment> comments = commentService.getAllComments();

		for(Comment comment : comments){
			addLinks(comment, uriInfo);
		}
		

		if(start >= 0 && end != 0 && start < end){
			
			if(end > comments.size()){
				end = comments.size();
			}
			
			comments = comments.subList(start, end);
		}
		
		
		return Response
				.status(Status.OK)
				.entity(comments)
				.build();
    }


    @Path("{comment_id}")
    @GET
    public Response getComment(@PathParam("id_user")long id_user, @PathParam("comment_id") long id,
    		@Context UriInfo uriInfo) 
    		throws SQLException{
		this.commentService = new CommentUserService(id_user);
		
		Comment comment = commentService.getComment(id);
		
		addLinks(comment, uriInfo);
		
		
        return Response
				.status(Status.OK)
				.entity(comment)
				.build();
    }


    @GET
    @Path("count")
    public Response getCount(@PathParam("id_user")long id_user) 
    		throws SQLException{
		this.commentService = new CommentUserService(id_user);
		
		
        return Response
				.status(Status.OK)
				.entity(commentService.getCommentCount())
				.build();
	   	
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postComment(@PathParam("id_user")long id_user, Comment comment,
    		@Context UriInfo uriInfo) 
    		throws SQLException{
		this.commentService = new CommentUserService(id_user);
		
		Comment new_comment = commentService.addComment(comment.getValue(), comment.getId_multimedia());
		addLinks(new_comment, uriInfo);
		URI location = getUriForSelf(new_comment, uriInfo);

		
        return Response
        		.created(location)
				.entity(new_comment)
				.build();
			
    }

    
    @Path("/{id_comment}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putComment(@PathParam("id_user")long id_user, @PathParam("id_comment")long id_comment, Comment comment) 
    		throws SQLException{
		this.commentService = new CommentUserService(id_user);
		
        return Response
				.status(Status.OK)
				.entity(commentService.updateComment(id_comment, comment.getValue()))
				.build();
			
    }

    
    @Path("/{id_comment}")
    @DELETE
    public Response deleteComment(@PathParam("id_user")long id_user, @PathParam("id_comment")Long id) 
    		throws SQLException{
		this.commentService = new CommentUserService(id_user);
		
        return Response
				.status(Status.OK)
				.entity(commentService.removeComment(id))
				.build();
			
    }

}
