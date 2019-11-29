package WebServices.Servlets;

import static WebServices.util.Constants.*;
import static rest.util.REST_Utils.REST_GetList;
import static rest.util.REST_Utils.REST_GetService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import rest.model.User;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Servlet_Login
 */
@WebServlet(name = "Servlet_Login", urlPatterns = {"/Servlet_Login"})
public class Servlet_Login extends HttpServlet
{
    /**
    * 
    */
   private static final long serialVersionUID = -389100878518890328L;



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
        //If we don't have a role in the session, we are in the login page
        if(request.getSession().getAttribute("role") == null)
        {
            // We verify that the user has entered input ( != null )
            if(request.getParameter(FORM_LOGIN_USERNAME) == null
                    || request.getParameter(FORM_LOGIN_PASSWORD) == null)
            {
                request.getRequestDispatcher(PATH_PAGE_LOGIN).forward(request, response);
                return;
            }
            
            
            
            // Data entered by the user
            String inputUser = request.getParameter(FORM_LOGIN_USERNAME);
            // String inputPwd = request.getParameter(FORM_LOGIN_PASSWORD);


            
            // -----------
            // REST
            // -----------
            
            

        	// We get the REST service
        	WebTarget service = REST_GetService();

    	
    	    // We get the Users (in a string, JSON format)	    
    	    String users_String = service.path("rest/v1").
    				    		path("users").request().
    				    		accept(MediaType.APPLICATION_JSON).
    				    		get(String.class);
    	    
    	    
    	    // Convert the String into a list
    	    List<User> listUsers = REST_GetList(users_String);
    	    

    	    
    	    // For all the Users
    	    for(User user : listUsers)
    	    {
    	    	String bddUser = user.getPseudo();
    	    	
                // If true, we create an ADMIN session
                if(bddUser.equals(inputUser))
                {
                    // Setting the session value
                    request.getSession().setAttribute("ID_user", 1);
                    request.getSession().setAttribute("XYZ", bddUser);

                    // Redirecting
                    response.sendRedirect("home");
                    return;
                }
    	    }



            // Since no match was found
            request.setAttribute("errKey", ERR_MESSAGE_INVALID);
            
            response.sendRedirect("login");
            return;
        }
        //If we have a role in the session, we are redirected to the home page
        else
        {
            response.sendRedirect("home");
        	return;	
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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "I'm the login servlet : I log people in.";
    }// </editor-fold>
}
