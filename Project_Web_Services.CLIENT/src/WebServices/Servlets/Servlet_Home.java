/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices.Servlets;

import static WebServices.util.Constants.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import WebServices.util.Todo;



/**
 *
 * @author Hugues
 */
@WebServlet(name = "Servlet_Home", urlPatterns = {"/Servlet_Home"})
public class Servlet_Home extends HttpServlet
{
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
	    ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget service = client.target(getBaseURI());
	
	    // create one todo
	    Todo todo = new Todo("3", "Blabla");
	    System.out.println("AVANT"); 
	    Response responseREST = service.path("rest").path("todos").path(todo.getId()).request(MediaType.APPLICATION_XML).put(Entity.entity(todo,MediaType.APPLICATION_XML),Response.class);
	    System.out.println("APRES"); 
	    
	    // Return code should be 201 == created resource
	    System.out.println(responseREST.getStatus());
	
	    // Get the Todos
	    System.out.println(service.path("rest").path("todos").request().accept(MediaType.TEXT_XML).get(String.class));

            
            
        // request.getRequestDispatcher(PATH_PAGE_HOME).forward(request, response);
    }

    
    
    private static URI getBaseURI()
    {
      return UriBuilder.fromUri("http://localhost:8080/rest.todo").build();
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