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

import rest.model.Rate;
import rest.service.RateService;


@Produces(MediaType.APPLICATION_JSON)
public class RateResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    Request request;
    
    RateService rateService;
    



	private URI getUriForSelf(Rate rate, UriInfo uriInfo) {	
		return uriInfo.getBaseUriBuilder()
				.path(UserResource.class)
				.path(UserResource.class, "getRateResource")
				.path(String.valueOf(rate.getId_rate()))
				.resolveTemplate("id_user", rate.getId_user())
				.build();
	}
    
    
	private URI getUriForParentUser(Rate rate, UriInfo uriInfo) {	
		return uriInfo.getBaseUriBuilder()
				.path(UserResource.class)
				.path(String.valueOf(rate.getId_user()))
				.build();
	}
    
    
	private URI getUriForMultimedia(Rate rate, UriInfo uriInfo)
			throws SQLException {			
		return uriInfo.getBaseUriBuilder()
				.path( rest.model.Multimedia.getChildClass(rate.getId_multimedia()) ) 
				.path( String.valueOf(rest.model.Multimedia.getChildID(rate.getId_multimedia())) )
				.build();
	}


	private void addLinks(Rate rate, UriInfo uriInfo)
			throws SQLException {
		rate.addLink("self", getUriForSelf(rate, uriInfo).toString());
		rate.addLink("author", getUriForParentUser(rate, uriInfo).toString());
		rate.addLink("multimedia", getUriForMultimedia(rate, uriInfo).toString());
	}
    

    
    
    @GET
    public Response getRates(@PathParam("id_user")long id_user, @QueryParam("value")String value,
    		@Context UriInfo uriInfo) 
    		throws SQLException{
   		this.rateService = new RateService(id_user);
		
		if(value != null){
    		
    		int valueInt = Integer.valueOf(value);
    		List<Rate> rates = rateService.getRatesByValue(valueInt);
    		
    		for(Rate rate : rates){
    			addLinks(rate, uriInfo);
    		}
    		
    		
    		return Response
    				.status(Status.OK)
    				.entity(rates)
    				.build();
			
		}
		
		List<Rate> rates = rateService.getAllRates();

		for(Rate rate : rates){
			addLinks(rate, uriInfo);
		}
		
		
		return Response
				.status(Status.OK)
				.entity(rates)
				.build();
    }


    @Path("{rate_id}")
    @GET
    public Response getRate(@PathParam("id_user")long id_user, @PathParam("rate_id") long id,
    		@Context UriInfo uriInfo) 
    		throws SQLException{
		this.rateService = new RateService(id_user);
		
		Rate rate = rateService.getRate(id);
		
		addLinks(rate, uriInfo);
		
		
        return Response
				.status(Status.OK)
				.entity(rate)
				.build();
    }


    @GET
    @Path("count")
    public Response getCount(@PathParam("id_user")long id_user) 
    		throws SQLException{
		this.rateService = new RateService(id_user);
		
		
        return Response
				.status(Status.OK)
				.entity(rateService.getRateCount())
				.build();
	   	
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postRate(@PathParam("id_user")long id_user, Rate rate,
    		@Context UriInfo uriInfo) 
    		throws SQLException{
		this.rateService = new RateService(id_user);
		
		Rate new_rate = rateService.addRate(rate.getValue(), rate.getId_multimedia());
		addLinks(new_rate, uriInfo);
		URI location = getUriForSelf(new_rate, uriInfo);

		
        return Response
        		.created(location)
				.entity(new_rate)
				.build();
			
    }

    
    @Path("/{id_rate}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRate(@PathParam("id_user")long id_user, @PathParam("id_rate")long id_rate, Rate rate) 
    		throws SQLException{
		this.rateService = new RateService(id_user);
		
        return Response
				.status(Status.OK)
				.entity(rateService.updateRate(id_rate, rate.getValue()))
				.build();
			
    }

    
    @Path("/{id_rate}")
    @DELETE
    public Response deleteRate(@PathParam("id_user")long id_user, @PathParam("id_rate")Long id) 
    		throws SQLException{
		this.rateService = new RateService(id_user);
		
        return Response
				.status(Status.OK)
				.entity(rateService.removeRate(id))
				.build();
			
    }

}
