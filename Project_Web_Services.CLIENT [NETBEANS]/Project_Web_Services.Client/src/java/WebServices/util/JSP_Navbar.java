/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author Hugues
 */
public class JSP_Navbar
{
    // DROPDOWNS

    
    

    /** A method that dynamically adds Dropdown to the navbar
    *
     * @param jw the JSP Writer used to write the file
     * @throws Exception 
     */
    public static void AddAllDropdowns(JspWriter jw) throws Exception
    {
        for(Constants.Multimedia media : Constants.Multimedia.values())
        {
                AddDropdown(jw, media);
        }
    }


    
    
    /** A method that creates the Dropdown for a given multimedia
    *
     * @param jw the JSP Writer used to write the file
     * @param multimedia The current Multimedia of which we want to create the Dropdown
     * @throws Exception 
     */
    public static void AddDropdown(JspWriter jw, Constants.Multimedia multimedia) throws Exception
    {
        jw.println("<li class=\"nav-item dropdown\" style=\"margin:0 0 0 25px\">");

        jw.println("	<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdownMenuLink\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\" style=\"color:white\">");
        jw.println("	" + multimedia.getName());
        jw.println("	</a>");


        jw.println("	<div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdownMenuLink\">");
        jw.println("		<a class=\"dropdown-item\" href=\"#\">See All</a>");
        jw.println("		<a class=\"dropdown-item\" href=\"#\">Sort by Rates</a>");
        jw.println("		<a class=\"dropdown-item\" href=\"#\">Sort by Comments</a>");

        jw.println("	<div class=\"dropdown-divider\"></div>");



        for(String s : multimedia.getValues())
        {
                jw.println("		<a class=\"dropdown-item\" href=\"#\">" + s + "</a>");
        }


        jw.println("	</div>");

        jw.println("</li>");
    }


    
    
    // BUTTONS

    
    

    /** A method that dynamically adds Buttons to the navbar, whether you are logged in are not
    *
     * @param jw the JSP Writer used to write the file
     * @param r1 The request scope used to access request / session / etc ... variables
     * @throws Exception 
     */
    public static void AddAllButtons(JspWriter jw, HttpServletRequest r1) throws Exception
    {
        Boolean isConnected = (r1.getSession().getAttribute("ID_user") != null);


        if( isConnected )
        {
                AddButton(jw, r1, "My Profile", "#");
                AddButton(jw, r1, "Logout", "#");
        }
        else
        {
                AddButton(jw, r1, "Sign up", "#");
                AddButton(jw, r1, "Log in", "login");	
        }
    }

    
    

    /** A method that creates the Button for a given mission
    *
     * @param jw the JSP Writer used to write the file
     * @param r1 The request scope used to access request / session / etc ... variables
     * @param name Name of the button to add
     * @param path Path to go to when the button is pressed
     * @throws Exception 
     */
    public static void AddButton(JspWriter jw, HttpServletRequest r1, String name, String path) throws Exception
    {
        jw.println("<li class=\"nav-item\" style=\"margin:0 25px 0 0\">");
        jw.println("<a class=\"nav-link\" href=\" "+ path + " \" style=\"color:white\">" + name + "</a>");
        jw.println("</li>");
    }
}
