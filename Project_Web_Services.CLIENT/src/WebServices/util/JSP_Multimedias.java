package WebServices.util;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import rest.model.Multimedia;



public class JSP_Multimedias
{
    /** A method that dynamically adds rows to the table
    *
     * @param jw the JSP Writer used to write the file
     * @param request The request scope used to access request / session / etc ... variables
     * @throws Exception 
     */
    public static void AddAllTableRows(JspWriter jw, HttpServletRequest request) throws Exception
    {
        @SuppressWarnings("unchecked")
		List<Multimedia> multimedias = (ArrayList<Multimedia>)request.getAttribute("multimedias"); 
        

        jw.println("<div class=\"container\" style=\"padding-top: 4vh\">");
        jw.println("	<div class=\"row\">");
        jw.println("		<div class=\"col\">");

        
        jw.println("			<table class=\"table table-striped table-hover\">");
        
        jw.println("				<thead>");
        jw.println("					<tr>");
        jw.println("				    	<th scope=\"col\">Choice</th>");
        jw.println("				    	<th scope=\"col\">TITLE</th>");
        jw.println("				    	<th scope=\"col\">DESCRIPTION</th>");
        jw.println("				    	<th scope=\"col\">LANGUAGE</th>");
        jw.println("				    	<th scope=\"col\">CATEGORY</th>");
        jw.println("				    	<th scope=\"col\">UPLOADER</th>");
        jw.println("				    	<th scope=\"col\">DATE</th>");

        jw.println("					</tr>");
        jw.println("				</thead>");
        

        jw.println("				<tbody>");
        
    	for(Multimedia multimedia : multimedias)
    	{
    		AddTableRow(jw, request, multimedia);
    	}

        jw.println("				</tbody>");
        jw.println("			</table>");

        jw.println("		</div>");
        jw.println("	</div>");
        jw.println("</div>");
    }
    
    
    
    /** A method that dynamically adds rows to the table
    *
     * @param jw the JSP Writer used to write the file
     * @param request The request scope used to access request / session / etc ... variables
     * @param multimedia The multimedia to display
     * @throws Exception 
     */
    public static void AddTableRow(JspWriter jw, HttpServletRequest request, Multimedia multimedia) throws Exception
    {
        jw.println("<tr style=\"cursor: pointer;border-left: 4px solid;border-right: 2px solid;\">");

        jw.println("	<td scope=\"row\">");
        jw.println("		<input type=\"radio\" name=\"radio_employees_v1\" form=\"employee\" value=\"1\">");
        jw.println("	</td>");

        jw.println("	<td>" + multimedia.getTitle() + "</td>");
        jw.println("	<td>" + multimedia.getDescription() + "</td>");
        jw.println("	<td>" + multimedia.getLanguage() + "</td>");
        jw.println("	<td>" + multimedia.getCategory() + "</td>");
        jw.println("	<td>" + multimedia.getID_uploader() + "</td>");
        jw.println("	<td>" + multimedia.getDate_upload() + "</td>");

        jw.println("</tr>");
    }
}
