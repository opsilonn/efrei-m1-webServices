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

import rest.resources.util.JSON_response;
import rest.service.RateService;




@Path("/users/{id_user}/rates")
@Produces(MediaType.APPLICATION_JSON)
public class RateResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    RateService rateService;
    
    
    
    
    @GET
    public JSON_response getRates(@PathParam("id_user")long id_user) {
    	try{
    		this.rateService = new RateService(id_user);
    		return new JSON_response(rateService.getAllRates());
	   	}
	   	catch(Exception e){
	   		System.out.println("ERROR WHILE GETTING ALL THE RATES\n");
    		return new JSON_response(e);
	   	}
    }


    @Path("{rate_id}")
    @GET
    public JSON_response getRate(@PathParam("id_user")long id_user, @PathParam("rate_id") long id) {
    	try{
    		this.rateService = new RateService(id_user);
            return new JSON_response(rateService.getRate(id));
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
    		this.rateService = new RateService(id_user);
            return new JSON_response(rateService.getRateCount());
	   	}
	   	catch(Exception e){
	   		System.out.println("ERROR WHILE GETTING THE RATE COUNT\n");
    		return new JSON_response(e);
	   	}
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public JSON_response postRate(@PathParam("id_user")long id_user, @FormParam("value")int value, @FormParam("id_m")long id_multimedia) {
    	try {
    		this.rateService = new RateService(id_user);
			return new JSON_response(rateService.addRate(value, id_multimedia));
			
		} catch (Exception e) {
    		System.out.println("ERROR WHILE ADDING NEW RATE\n");
    		
    		return new JSON_response(e);
		}
    }

    
    @Path("/{id_rate}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public JSON_response putRate(@PathParam("id_user")long id_user, @PathParam("id_rate")Long id, @FormParam("value")int value) {
    	try {
    		this.rateService = new RateService(id_user);
    		return new JSON_response(rateService.updateRate(id, value));
			
		} catch (Exception e) {
    		System.out.println("ERROR WHILE UPDATING NEW RATE\n");
    		
    		return new JSON_response(e);
		}
    }

    
    @Path("/{id_rate}")
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public JSON_response deleteRate(@PathParam("id_user")long id_user, @PathParam("id_rate")Long id) {
    	try {
    		this.rateService = new RateService(id_user);
    		return new JSON_response(rateService.removeRate(id));
			
		} catch (Exception e) {
    		System.out.println("ERROR WHILE DELETING NEW RATE\n");
    		
    		return new JSON_response(e);
		}
    }

}
