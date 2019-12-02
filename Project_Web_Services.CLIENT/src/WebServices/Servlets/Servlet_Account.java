package WebServices.Servlets;

import static WebServices.util.Constants.*;
import static rest.util.REST_Utils.REST_GetListBooks;
import static rest.util.REST_Utils.REST_GetService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import rest.model.Book;
import rest.model.Multimedia;
import rest.model.User;
import rest.model.util.Link;
import rest.model.util.Timestamp;

/**
 * Servlet implementation class Servlet_Account
 */
@WebServlet(name = "Servlet_Accounts", urlPatterns = {"/Servlet_Accounts"})
public class Servlet_Account extends HttpServlet
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
    	// There is a user logged in  : display its data
       if(IS_CONNECTED(request))
       {
	       	// We get the User and the link to its data
	       	User user = (User)request.getSession().getAttribute("user");
	       	String Href = "";
	       	for(Link link : user.getLinks())
	       	{
	       		if( link.getRel().equals("self") )
	       		{
	       			Href = link.getHref();		
	       		}
	       	}
	       	
	       	// We get the REST service
	       	service = REST_GetService();
	       	System.out.println(Href);
       	
           request.setAttribute("title", "My Account");
       }
   		// There is no user logged in  : create a new one
       else
       {
           request.setAttribute("title", "New Account"); 	   
       }
        
        request.getRequestDispatcher(PATH_PAGE_ACCOUNT).forward(request, response);
        return;
    }
    
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
        processRequest(request, response);
	}

	
	
	/** Adds {@link User} or modifies the current {@link User}
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// Not a user : trying to SIGN IN
		if( !IS_CONNECTED(request) )
		{
			// We get all the inputs
			String inputUsername = request.getParameter(FORM_ACCOUNT_USERNAME);
			String inputPassword = request.getParameter(FORM_ACCOUNT_PASSWORD);
			String inputPasswordVerif = request.getParameter(FORM_ACCOUNT_PASSWORD_VERIF);
			String inputEmail = request.getParameter(FORM_ACCOUNT_EMAIL);

			
			// if the 2 passwords don't match -> TRY AGAIN
			if( !inputPassword.equals(inputPasswordVerif) )
			{ 
	           request.getRequestDispatcher(PATH_PAGE_ACCOUNT).forward(request, response);
	           return;
			}
			// OTHERWISE : we add the new user to the database
			else
			{ 
				// INSERT CODE - AJOUT BDD
		    	User newUser = new User();
		    	newUser.setPseudo(inputUsername);
		    	newUser.setPassword(inputPassword);
		    	newUser.setEmail(inputEmail);
		    	
		    	
				service = REST_GetService();
				/*
				// We get the {@link Book} (in a string, JSON format)	    
	    	    String JSON = service.path("rest/v1/books").request().
			    		accept(MediaType.APPLICATION_JSON).
			    		get(String.class); 
    			*/
				
				
	           request.getRequestDispatcher(PATH_PAGE_MULTIMEDIAS).forward(request, response);
	           return;
			}
		}
		// user seeing its data : WANTS TO BE ABLE TO MODIFY THEM
		else if(request.getSession().getAttribute("modifyUser") == null)
		{
			// Setting the request value to be able to modify data
            request.getSession().setAttribute("modifyUser", true); 
            
            // Change the title
            request.setAttribute("title", "Modifying my Account");
            
            // redirect
            request.getRequestDispatcher(PATH_PAGE_ACCOUNT).forward(request, response);
            return;
		}
		// user modifying its data : WANTS TO APPLY THE CHANGES
		else
		{
			// We get all the inputs
			String inputUsername = request.getParameter(FORM_ACCOUNT_USERNAME);
			String inputEmail = request.getParameter(FORM_ACCOUNT_EMAIL);
			
			
			// We verify that the inputs are valid
			if(inputUsername.length() == 0 || inputEmail.length() == 0)
			{
				// PAS CONTENT
				
				// redirect to the Account page : do it again !
	            request.getRequestDispatcher(PATH_PAGE_ACCOUNT).forward(request, response);
	            return;
			}
			else
			{
				request.getSession().removeAttribute("modifyUser");
				// CONTENT
				
				// redirect to the Home page : well done !
	            request.getRequestDispatcher(PATH_PAGE_MULTIMEDIA).forward(request, response);
	            return;
			}
		}
	}
}