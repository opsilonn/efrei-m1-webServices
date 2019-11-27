package fr.WebService.Servlets;

import static fr.WebService.util.Constants.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Servlet_Login
 */
@WebServlet(urlPatterns = {"/Servlet_Login"})
public class Servlet_Login extends HttpServlet
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
        // We verify that the user has entered input ( != null )
        if(request.getParameter(FORM_LOGIN_USERNAME) == null || request.getParameter(FORM_LOGIN_PASSWORD) == null)
        {
            request.getRequestDispatcher(PATH_PAGE_LOGIN).forward(request, response);
            return;
        }
        
        
        // Data entered by the user
        String inputUser = request.getParameter(FORM_LOGIN_USERNAME);
        String inputPwd = request.getParameter(FORM_LOGIN_PASSWORD);
        

        // WHAT WE COMPARE WITH FOR THE MOMENT
        String USERNAME = "admin";
        String PASSWORD = "admin";
        
        
        // If the credentials match, we create an ADMIN session
        if(USERNAME.equals(inputUser) && PASSWORD.equals(inputPwd))
        {
            // Setting the session value
            HttpSession session = request.getSession();
            session.setAttribute("role", "admin");
            
            // Redirecting
            response.sendRedirect("employees");
            return;
        }
        
        
        // Since no match was found
        request.setAttribute("errKey", ERR_MESSAGE_INVALID);
        request.getRequestDispatcher(PATH_PAGE_LOGIN).forward(request, response);
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
        return "This servlet is used to check the Login Credentials (Admin ? Employee ?)";
    }// </editor-fold>
}
