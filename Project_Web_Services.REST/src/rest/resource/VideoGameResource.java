package rest.resource;

import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import rest.model.VideoGame;
import rest.service.VideoGameService;


@Path("/videoGames")
@Produces(MediaType.APPLICATION_JSON)
public class VideoGameResource {
    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    
    @Context
    Request request;
    
    VideoGameService videoGameService;
    
    
	private void addLinks(VideoGame videoGame)
	{
		this.uriInfo.getBaseUriBuilder();
		
		videoGame.addLink(
				"uploader",
				this.uriInfo.getBaseUriBuilder().toString()
					+ "users/"
					+ Long.toString( videoGame.getID_uploader() ) 
				);
	}
    
    
    /** Returns all the videoGame rows from the database
     * 
     * @return All the videoGame rows from the database
     * @throws SQLException
     */
    @GET
    public Response getVideoGames()throws SQLException
    {
		this.videoGameService = new VideoGameService();
		
		return Response.status(Status.OK)
				.entity((videoGameService.getAllVideoGames()))
				.build();

    }


    /** Returns a given videoGame from the database
     * 
     * @param id ID of the videoGame we are returning
     * @return a specific videoGame row
     * @throws SQLException
     */
    @Path("/{videoGame_id}")
    @GET
    public Response getVideoGame(@PathParam("videoGame_id") long id) 
    		throws SQLException
    {
		this.videoGameService = new VideoGameService();
		
		// We add the links to all foreign references
		VideoGame videoGame = videoGameService.getVideoGame(id);
		addLinks(videoGame);
		
        return Response.status(Status.OK)
				.entity(videoGameService.getVideoGame(id))
				.build();
    }


    /** Displays the number of rows inside the table videoGame
     * 
     * @return the number of rows inside the table videoGame
     * @throws SQLException
     */
    @GET
    @Path("/count")
    public Response getCount() 
    		throws SQLException
    {
		this.videoGameService = new VideoGameService();
		
		
        return Response.status(Status.OK)
        		.entity(videoGameService.getVideoGameCount())
        		.build();
    }

    
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response postUser(User user) 
//    		throws SQLException {
//		this.userService = new UserService();
//		
//		User new_user = userService.addUser(user);
//		URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(new_user.getId_user())).build();
//		
//		
//		return Response.created(location)
//				.entity(new_user)
//				.build();
//    }
//
//
//    @Path("/{user_id}")
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response putUser(@PathParam("user_id")Long id, @FormParam("e_psw")String existing_password, @FormParam("n_psw")String new_password, @FormParam("mail")String email)
//    		throws SQLException {
//		this.userService = new UserService();
//		
//		
//		return Response.status(Status.OK) 
//				.entity(userService.updateUser(id, existing_password, new_password, email))
//				.build();
//    }
//
//    
//    @Path("/{id_user}")
//    @DELETE
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public Response deleteVideoGame(@PathParam("id_videoGame")Long id)
//    		throws SQLException {
//		this.videoGameService = new VideoGameService();
//		
//		
//		return Response.status(Status.OK) 
//				.entity(videoGameService.removeVideoGame(id))
//				.build();
//    } 
}
