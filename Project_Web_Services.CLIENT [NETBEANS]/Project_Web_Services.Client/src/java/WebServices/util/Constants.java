package WebServices.util;

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
	
	public static final String PATH_UTIL_NAVBAR = "JSP/navbar.jsp";
	public static final String PATH_UTIL_FOOTER= "JSP/footer.jsp";
        
	public static final String PATH_PAGE_HOME = "home.jsp";
	public static final String PATH_PAGE_LOGIN = "login.jsp";
	public static final String PATH_PAGE_LOGOUT = "logout.jsp";
	

	// Logo
	public static final String PATH_LOGO = "pictures/logo.png";
	

    // Error Messages
    public static final String ERR_MESSAGE_INVALID = "Connection failed ! Verify your credentials and try again !";


    // Form Login fields's names
    public static final String FORM_LOGIN_USERNAME = "Username";
    public static final String FORM_LOGIN_PASSWORD = "Password";

}
