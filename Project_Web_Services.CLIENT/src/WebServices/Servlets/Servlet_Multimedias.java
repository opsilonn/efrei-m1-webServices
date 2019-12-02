package WebServices.Servlets;

import static WebServices.util.Constants.*;
import static rest.util.REST_Utils.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import rest.model.*;



@WebServlet(name = "Servlet_Multimedias", urlPatterns = {"/Servlet_Multimedias"})
public class Servlet_Multimedias extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private List<Multimedia> multimedias;
	private WebTarget service;
	
	
	
	
	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {    
    	// We get the REST service
    	service = REST_GetService();
    	

    	// Getting the URI and checking where we are
    	String URI = request.getRequestURI();
    	Boolean isMultimedia = URI.contains("multimedias");
    	Boolean isBook = URI.contains("books");
    	Boolean isFilm = URI.contains("films");
    	Boolean isVideoGame = URI.contains("videoGames");
    	
    	// In the case where none are true, we consider that we should display all
    	// (Trust me on this one)
    	if(!isBook && !isFilm && !isVideoGame)
    	{
    		isMultimedia = true;
    	}
    			
    	
    	// Getting all the corresponding Multimedia pieces
	    multimedias = new ArrayList<rest.model.Multimedia>();
	    if(isMultimedia || isBook)
	    {
		    AddBooks();
	    }
	    if(isMultimedia || isFilm)
	    {
		    AddFilms();
	    }
	    if(isMultimedia || isVideoGame)
	    {
		    AddVideoGames();
	    }
	    
	    // IF YOU WANT TO HAVE AN EMPTY DATABASE :
	    // UNCOMMENT THE FOLLOWING LINE
	    // multimedias.clear();
    	
    	
	    if(multimedias.size() != 0)
	    {
    		// We shuffle the List
	    	Collections.shuffle(multimedias);
	    	
		    // We put the List into the request scope
	        request.setAttribute("multimedias", multimedias);
	    }
	    else
	    {
	    	// We display an error message
            request.setAttribute("errKey", "Sorry, the database seems to be empty...");
	    }
    	
    	
        request.getRequestDispatcher(PATH_PAGE_MULTIMEDIAS).forward(request, response);
    }
    
    
    
    
    /** Add all the {@link Book} from the database to the List of {@Multimedia}
     * 
     */
    private void AddBooks()
    {
    	try
        {
    	    // We get the {@link Book} (in a string, JSON format)	    
    	    String JSON = service.path("rest/v1/books").request().
		    		accept(MediaType.APPLICATION_JSON).
		    		get(String.class); 

    	    // Convert the String into a list
    		List<Book> books = REST_GetListBooks(JSON);
    		for(Book book : books)
    		{
    			multimedias.add(book);
    		}
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }    
    
    
    
    
    /** Add all the {@link Film} from the database to the List of {@Multimedia}
     * 
     */
    private void AddFilms()
    {
    	try
        {
    	    // We get the {@link Films} (in a string, JSON format)	    
    	    String JSON = service.path("rest/v1/films").request().
		    		accept(MediaType.APPLICATION_JSON).
		    		get(String.class); 

    	    // Convert the String into a list
    		List<Film> films = REST_GetListFilms(JSON);
    		for(Film film : films)
    		{
    			multimedias.add(film);
    		}
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    
    
    /** Add all the {@link VideoGame} from the database to the List of {@Multimedia}
     * 
     */
    private void AddVideoGames()
    {
    	try
        {
    	    // We get the {@link Films} (in a string, JSON format)	    
    	    String JSON = service.path("rest/v1/videoGames").request().
		    		accept(MediaType.APPLICATION_JSON).
		    		get(String.class); 

    	    // Convert the String into a list
    		List<VideoGame> videoGames = REST_GetListVideoGames(JSON);
    		for(VideoGame videoGame : videoGames)
    		{
    			multimedias.add(videoGame);
    		}
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    
    
     
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}