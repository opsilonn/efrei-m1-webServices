package rest.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rest.model.Multimedia;
import rest.model.Rate;
import rest.model.User;
import rest.resource.util.Constants;
import rest.util.DB_web_services;

public class RateService {
	
	private long id_user;
	
	private Map<Long, User> users = DB_web_services.getUsers();
	private Map<Long, Rate> rates = DB_web_services.getRates();
	
	
	public RateService(long id_user)
			throws SQLException
	{
		this.id_user = id_user;
    	DB_web_services db = new DB_web_services();
	    	
    	PreparedStatement ppsm = db.getPreparedStatement(Constants.Rate.getAll);
    	
    	ppsm.setLong(1, this.id_user);
    	
    	ResultSet rs = ppsm.executeQuery();
    	this.rates.clear();
    	
    	while(rs.next()){
    		this.rates.put(rs.getLong("ID_rate"), new Rate(rs.getLong("ID_rate"), rs.getInt("value"), this.users.get(rs.getLong("ID_user")), new Multimedia()));
    	}
	}

	public List<Rate> getAllRates()
	{
    	return new ArrayList<Rate>(this.rates.values());
	}

	public List<Rate> getRatesByValue(int value)
	{
		List<Rate> return_rates = new ArrayList<Rate>();

		for(Map.Entry<Long, Rate> entry : this.rates.entrySet()){
			if(entry.getValue().getValue() == value){
				return_rates.add(entry.getValue());
			}
		}
		
    	return return_rates;
	}
	

	public Rate getRate(long id){
		return this.rates.get(id);
	}
	
	
	public int getRateCount(){
		return this.rates.size();
	}
	
	
	public Rate addRate(int value, long id_multimedia) 
			throws Exception{
		
		DB_web_services db = new DB_web_services();
    	
    	PreparedStatement ppsm = db.getPreparedStatement(Constants.Rate.post);
    	
    	ppsm.setInt(1, value);
    	ppsm.setLong(2, this.id_user);
    	ppsm.setLong(3, id_multimedia);
    	
    	
    	int rs = ppsm.executeUpdate();

    	if(rs == 1){
    		ResultSet generated_id = ppsm.getGeneratedKeys();
    		
    		if(generated_id.next()){
    			ppsm = db.getPreparedStatement(Constants.Rate.getByID);
    			
    			ppsm.setLong(1, generated_id.getLong(1));
    	    	ppsm.setLong(2, this.id_user);
    			
    			ResultSet rs_rate = ppsm.executeQuery();
    	    	
    	    	if(rs_rate.next()){
    	    		Rate rate = new Rate(rs_rate.getLong("ID_rate"), rs_rate.getInt("value"), this.users.get(rs_rate.getLong("ID_user")), new Multimedia());
    	    		this.rates.put(rs_rate.getLong("ID_rate"), rate);
    	    		
    	    		
    	    		return rate;
    	    	}
    		}
    	}

    	
    	return null;
	}
	
	
	public boolean updateRate(long id, int value) 
			throws Exception{

		DB_web_services db = new DB_web_services();
    	

    	PreparedStatement ppsm = db.getPreparedStatement(Constants.Rate.putByID);
        	
    	ppsm.setInt(1, value);
    	ppsm.setLong(2, id);
    	ppsm.setLong(3, this.id_user);

    	
    	int rs = ppsm.executeUpdate();

    	if(rs == 1){
    		Rate rate = getRate(id);
    		
    		rate.setValue(value);
    	}
    	
    	
    	return (rs == 1) ? true : false;
	}
	
	
	public boolean removeRate(long id) 
			throws Exception{
		DB_web_services db = new DB_web_services();

		PreparedStatement ppsm = db.getPreparedStatement(Constants.Rate.deleteByID);

    	ppsm.setLong(1, id);
    	ppsm.setLong(2, this.id_user);
    	
    	int rs = ppsm.executeUpdate();

    	if(rs == 1){
    		this.rates.remove(id);
    	}
    	
    	
    	return (rs == 1) ? true : false;
	}
}
