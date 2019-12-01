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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
