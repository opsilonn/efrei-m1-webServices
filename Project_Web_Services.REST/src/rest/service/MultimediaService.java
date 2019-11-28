package rest.service;

import java.util.ArrayList;
import java.util.List;

import rest.model.Multimedia;

public class MultimediaService {
	
	public List<Multimedia> filtre (List<Multimedia> filtrage, String filtre){
		
		List<Multimedia> result = new ArrayList<Multimedia>();
		
		for(int i=0; i<filtrage.size(); i++){
			if(filtrage.get(i).getTitle().matches("(.*)" + filtre + "(.*)")){
				result.add(filtrage.get(i));
			}
		}
		
		return result;
	}
	
}
