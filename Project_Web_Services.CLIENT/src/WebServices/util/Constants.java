package WebServices.util;

import java.util.ArrayList;
import java.util.List;




public class Constants
{
    public enum EnumMultimedia
    {
      ALL("See all", "multimedias", new ArrayList<>() ),
      BOOK("Book", "books", new ArrayList<>() ),
      FILM("Film", "films", new ArrayList<>() ),
      VIDEOGAME("Video Game", "videoGames", new ArrayList<>() );

       private String name = "";
       private String URL = "";
      private List<String> values;

      // Constructeur
      EnumMultimedia(String name, String URL, List<String> values)
      {
         this.name = name;
         this.URL = URL;
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
      public String getURL() { return URL; }
      public List<String> getValues() { return values; }
    }

    
    

    // The name of the website
    public static final String NAME_WEBSITE = "The Otter Market";

    // The name of the different pages of the website
    public static final String NAME_PAGE_MULTIMEDIAS = "The Otter Market - Homepage";
    public static final String NAME_PAGE_LOGIN = "The Otter Market - Login";
    public static final String NAME_PAGE_LOGOUT = "The Otter Market - :'(";

    public static final String PATH_UTIL_NAVBAR = "navbar.jsp";
    public static final String PATH_UTIL_FOOTER= "JSP/footer.jsp";

    public static final String PATH_PAGE_MULTIMEDIAS = "WEB-INF/JSP/multimedias.jsp";
    public static final String PATH_PAGE_LOGIN = "WEB-INF/JSP/login.jsp";
    public static final String PATH_PAGE_LOGOUT = "WEB-INF/JSP/logout.jsp";


    // Logo
    public static final String PATH_IMAGE_LOGO = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/ad1bd517-b13b-42ee-b64c-4d28236dfb86/d5dh4zm-df969a9b-099a-40de-b5cb-e5101999d7cc.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwic3ViIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsImF1ZCI6WyJ1cm46c2VydmljZTpmaWxlLmRvd25sb2FkIl0sIm9iaiI6W1t7InBhdGgiOiIvZi9hZDFiZDUxNy1iMTNiLTQyZWUtYjY0Yy00ZDI4MjM2ZGZiODYvZDVkaDR6bS1kZjk2OWE5Yi0wOTlhLTQwZGUtYjVjYi1lNTEwMTk5OWQ3Y2MucG5nIn1dXX0.C_3c7rKx0S-X5QFESOc9M8yikJPAiE4VS29iAplhGpY";
    public static final String PATH_IMAGE_LOGOUT ="http://t03.deviantart.net/uZUayyJZk0WoxfKGrI1tZvYDCsw=/300x200/filters:fixed_height(100,100):origin()/pre10/23bc/th/pre/i/2014/149/f/5/ya_no_sea_chillon_by_kol98-d7k81xo.png";

    // Error Messages
    public static final String ERR_MESSAGE_INVALID = "Connection failed ! Verify your credentials and try again !";


    // Form Login fields's names
    public static final String FORM_LOGIN_USERNAME = "Username";
    public static final String FORM_LOGIN_PASSWORD = "Password";
 }
