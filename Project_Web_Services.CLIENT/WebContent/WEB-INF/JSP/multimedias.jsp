<%@page import="WebServices.util.JSP_Multimedias"%>


<html lang="en">
    <head>
        <!-- Changing the title of the page -->
        <title> <%=Constants.NAME_PAGE_MULTIMEDIAS%> </title>
    </head>


    <body>
    	<!-- We always add FIRST the Navbar -->
		<%@include file="navbar.jsp" %>
		
		
		<!-- Display some stuff -->
		<div style="text-align:center">
		
			<br><br><br><br>
			
			<h2>Welcome to the home page !</h2>
			
			<br><br><br><br>
			
			<%
				if(request.getSession().getAttribute("XYZ") != null)
				{
				    out.println( "Bonjour Monsieur " + request.getSession().getAttribute("XYZ") + " !");
				}
				else
				{
				    out.println( "...Voulez-vous vous connecter ? siouplé ?" );
				}
			%>
			    
		    <br><br><br><br>
		
		</div>
		
		
		
       <%-- Displaying the Error Message if there is one --%>
       <div style="color:red; text-align: center">
           <label>
               <small>
                <%
                if(request.getAttribute("errKey") != null)
                {
                    out.println( request.getAttribute("errKey") );
                }
                if(request.getAttribute("multimedias") != null)
                {
                    out.println( "MULTIMEDIAS EXISTE" );
                }
                else
                {
                    out.println( "Multimedias n'existe pas" );
                }
                %>
               </small>
           </label>
       </div>
		
		
		
		
		
		
        <% JSP_Multimedias.AddAllTableRows(out, request); %>
		
		<!-- Display some RELEVANT stuff -->
		<div class="container" style="padding-top: 4vh">
            <div class="row">
                <div class="col">
                
                	<table class="table table-striped table-hover">
                	
                        <thead>
                            <tr>
                            	<th scope="col">Choice</th>
                                <th scope="col">TITLE</th>
                                <th scope="col">DESCRIPTION</th>
                                <th scope="col">LANGUAGE</th>
                                <th scope="col">CATEGORY</th>
                                <th scope="col">STATUS</th>
                                <th scope="col">UPLOADER</th>
                                <th scope="col">DATE</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <c:if test="${not empty multimedias}">
                                
                                <c:forEach var="multimedia" items="${multimedias}">
                                    
		                            <tr style="cursor: pointer;border-left: 4px solid;border-right: 2px solid;">
		                                <td scope="row">
		                                	<input type="radio" name="radio_employees_v1" form="employee" value="${employee.id}">
	                                	</td>
		                                <td> ${multimedia.getTitle()} </td>
		                                <td> ${multimedia.first_name} </td>
		                                <td> ${multimedia.home_phone} </td>
		                                <td> ${multimedia.cell_phone} </td>
		                                <td> ${multimedia.work_phone} </td>
		                                <td> ${multimedia.rue} </td>
		                                <td> ${multimedia.code_postal} </td>
		                                <td> ${multimedia.ville} </td>
		                            </tr>
                            
                            	</c:forEach>
                            </c:if>
                        </tbody>
                        
                    </table>

                </div>
            </div>
        </div>
		
		
		
		
		
		
		<!-- We always add LAST the Footer -->
		<%@include file="footer.jsp" %>            
    </body>
</html>