package fr.WebService.util;

import java.util.ArrayList;
import java.util.List;

public class Constants
{
	public enum Multimedia
	{
	  ALL("See all", new ArrayList<>() ),
	  BOOK("Book", new ArrayList<>() ),
	  FILM("Film", new ArrayList<>() ),
	  VIDEOGAME("Video Game", new ArrayList<>() );

	  private String name = "";
	  private List<String> values;
	   
	  // Constructeur
	  Multimedia(String name, List<String> values)
	  {
	    this.name = name;
	    this.values = values;
	    
	    switch(name)
	    {
	    	case "See all":
	    	break;
	    	
	    	case "Book":
	    	values.add("Author");
	    	values.add("Publisher");
	    	break;

	    	case "Film":
	    	values.add("Director");
	    	values.add("Productor");
	    	values.add("Main Cast");
	    	values.add("Duration");
	    	break;
	    	
	    	case "Video Game":
	    	values.add("Developer");
	    	values.add("Publisher");
	    	break;
	    }
	  }

	  public String getName() { return name; }
	  public List<String> getValues() { return values; }
	}
	
	
	// The name of the website
	public static final String NAME_WEBSITE = "The Otter Market";

	// The name of the different pages of the website
	public static final String NAME_PAGE_HOME = "The Otter Market - Homepage";
	public static final String NAME_PAGE_LOGIN = "The Otter Market - Login";
	public static final String NAME_PAGE_LOGOUT = "The Otter Market - :'(";
	

	// Navbar
	public static final String NAVBAR_IMAGE_PATH = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/ad1bd517-b13b-42ee-b64c-4d28236dfb86/d5dh4zm-df969a9b-099a-40de-b5cb-e5101999d7cc.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwic3ViIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsImF1ZCI6WyJ1cm46c2VydmljZTpmaWxlLmRvd25sb2FkIl0sIm9iaiI6W1t7InBhdGgiOiIvZi9hZDFiZDUxNy1iMTNiLTQyZWUtYjY0Yy00ZDI4MjM2ZGZiODYvZDVkaDR6bS1kZjk2OWE5Yi0wOTlhLTQwZGUtYjVjYi1lNTEwMTk5OWQ3Y2MucG5nIn1dXX0.C_3c7rKx0S-X5QFESOc9M8yikJPAiE4VS29iAplhGpY";
}
