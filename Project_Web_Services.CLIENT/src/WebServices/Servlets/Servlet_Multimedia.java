package WebServices.Servlets;

import static rest.util.REST_Book.*;
import static WebServices.util.Constants.PATH_PAGE_MULTIMEDIA;
import static WebServices.util.Constants.PATH_PAGE_MULTIMEDIAS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rest.model.Book;



/**
 * Servlet implementation class Servlet_Multimedia
 */
public class Servlet_Multimedia extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
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
		// Je récupère le 1er livre et je teste des trucs
		
		System.out.println("processing...");	
        request.getRequestDispatcher(PATH_PAGE_MULTIMEDIA).forward(request, response);
    }
    
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("GET");
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("POST");
		processRequest(request, response);
	}
	
	
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "I'm the multimedia servlet : I display most data about a given Multimedia.";
    }// </editor-fold>
}
