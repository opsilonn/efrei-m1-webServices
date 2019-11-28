package WebServices.Servlets;

import static WebServices.util.Constants.*;

import java.io.IOException;
import java.io.PrintWriter;

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
            String inputPwd = request.getParameter(FORM_LOGIN_PASSWORD);



            // Checking if the inputs correspond to the ADMIN credentials
            String USER = "user";
            String PWD = "user";

            // If true, we create an ADMIN session
            if(USER.equals(inputUser) && PWD.equals(inputPwd))
            {
                // Setting the session value
                request.getSession().setAttribute("ID_user", 1);

                // Redirecting
                response.sendRedirect("home");
                return;
            }


            // Since no match was found
            request.setAttribute("errKey", ERR_MESSAGE_INVALID);
            
            request.getRequestDispatcher(PATH_PAGE_LOGIN).forward(request, response);
            return;
        }
        // If we have a Session (=we are already logged-in) -> go back to the Home page
        else
        {
            // request.getSession().invalidate();
            
            request.getRequestDispatcher(PATH_PAGE_HOME).forward(request, response);
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
        return "This servlet is used to check the Login Credentials";
    }// </editor-fold>
}
