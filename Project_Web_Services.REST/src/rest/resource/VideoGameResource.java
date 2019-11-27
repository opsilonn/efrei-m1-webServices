package rest.resource;

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
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import rest.model.Comment;
import rest.model.Rate;
import rest.model.VideoGame;
import rest.service.VideoGameService;


@Path("/videoGames")
@Produces(MediaType.APPLICATION_JSON)
public class VideoGameResource
{
    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    
    @Context
    Request request;
    
    VideoGameService videoGameService;
    
	
	/** We add links related to the {@VideoGame}
	 * 
	 * @param videoGame Current {@VideoGame} of which we want to give the associated links
	 */
	private void addLinks(VideoGame videoGame)
	{
		this.uriInfo.getBaseUriBuilder();
		
		// We add a link to the page of this very {@VideoGame}
		videoGame.addLink(
				"self",
				this.uriInfo.getBaseUriBuilder()
				.path(VideoGameResource.class)
				.path(String.valueOf(videoGame.getId_videoGame()))
				.build()
				.toString()
				);

		
		// We add a link to the page of the {@User} that uploaded this {@VideoGame}
		videoGame.addLink(
				"uploader",
				this.uriInfo.getBaseUriBuilder().toString()
					+ "users/"
					+ Long.toString( videoGame.getID_uploader() ) 
				);
	}
    
    
	
	
    /** Returns all the {@VideoGame} rows from the database
     * 
     * @return All the {@VideoGame} rows from the database
     * @throws SQLException
     */
    @GET
    public Response getVideoGames(@QueryParam("start")int start, @QueryParam("end")int end, @QueryParam("filtre")String filtre)throws SQLException
    {
		this.videoGameService = new VideoGameService();

		// We add the links to all foreign references
		List<VideoGame> videoGames;
		List<VideoGame> result;
		
		System.out.println(start);
		System.out.println(end);
		
		
		if(filtre != null){
			videoGames = videoGameService.filtre(filtre);
		}else{
			videoGames = videoGameService.getAllVideoGames();
		}
		
		if(start >=0 && end>0 && end>=start && end<=videoGames.size()){
			 result = videoGames.subList(start, end);
		 }else{
			 result = videoGames;
		 }
		
		for(VideoGame videoGame : result)
		{
			addLinks(videoGame);
		}
		
		return Response.status(Status.OK)
				.entity(result)
				.build();
    }

    
    

    /** Returns a given {@VideoGame} from the database
     * 
     * @param id ID of the {@VideoGame} we are returning
     * @return a specific {@VideoGame} row
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
				.entity(videoGame)
				.build();
    }

    @Path("/{videoGame_id}/rates")
    @GET
    public Response GetRate(@PathParam("videoGame_id") long id) throws SQLException{
    	this.videoGameService = new VideoGameService();
    	
    	List<Rate> result = videoGameService.getRate(id);
    	
    	return Response.status(Status.OK).entity(result).build();
    }
    
    @Path("/{videoGame_id}/comment")
    @GET
    public Response GetComment(@PathParam("videoGame_id") long id) throws SQLException{
    	this.videoGameService = new VideoGameService();
    	
    	List<Comment> result = videoGameService.getComment(id);
    	
    	return Response.status(Status.OK).entity(result).build();
    }

    /** Displays the number of rows inside the table {@VideoGame}
     * 
     * @return the number of rows inside the table {@VideoGame}
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

    
    
 /** Creates a new instance of the table {@VideoGame}
  * 
  * @param film Instance to add to the database
  * @return
  * @throws SQLException
  */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postVideoGame(VideoGame videoGame) 
    		throws SQLException
    {
		this.videoGameService = new VideoGameService();
		VideoGame new_videoGame = videoGameService.addVideoGame(videoGame);
		
		addLinks(new_videoGame);
		 
		return Response
				.status(Status.OK)
				.entity(new_videoGame)
				.build();
    }


	@Path("/{id_videoGame}")
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUser(@PathParam("id_videoGame")Long id, VideoGame videoGame)
    		throws SQLException {
		this.videoGameService = new VideoGameService();
		
		
		return Response.status(Status.OK) 
				.entity(videoGameService.updateVideoGame(id, videoGame))
				.build();
    }

    
	@Path("/{id_videoGame}")
    @DELETE
    public Response deleteVideoGame(@PathParam("id_videoGame")Long id)
    		throws SQLException {
		this.videoGameService = new VideoGameService();
		
		
		return Response.status(Status.OK) 
				.entity(videoGameService.removeVideoGame(id))
				.build();
    } 
}
