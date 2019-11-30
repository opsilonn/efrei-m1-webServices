package WebServices.Servlets;

import static WebServices.util.Constants.*;
import static rest.util.REST_Utils.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;




@WebServlet(name = "Servlet_Multimedias", urlPatterns = {"/Servlet_Multimedias"})
public class Servlet_Multimedias extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private List<rest.model.Multimedia> multimedias;

	
	
	
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
        try
        {
            // -----------
            // REST
            // -----------
            System.out.println("DEBUT");

        	// We get the REST service
        	WebTarget service = REST_GetService();
            System.out.println("Service acquis");

    	    // We get the Users (in a string, JSON format)	    
    	    String JSON_Multimedias = service.path("rest/v1").
    				    		path("multimedias").request().
    				    		accept(MediaType.APPLICATION_JSON).
    				    		get(String.class);
            System.out.println("JSON acquis");
    	    
    	    // Convert the String into a list
    	    multimedias = REST_GetListMultimedias(JSON_Multimedias);
        	   
    	    // We put the List into the request scope
            request.setAttribute("multimedias", multimedias);
            System.out.println("FIN");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            request.setAttribute("errKey", "Sorry, no database yet :(");
        }
    	
    	
        request.getRequestDispatcher(PATH_PAGE_MULTIMEDIAS).forward(request, response);
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