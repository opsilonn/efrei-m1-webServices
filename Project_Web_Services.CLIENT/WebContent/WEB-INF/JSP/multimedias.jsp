<html lang="en">
    <head>
        <!-- Changing the title of the page -->
        <title> <%=Constants.NAME_PAGE_HOME%> </title>
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



            <!-- We always add LAST the Footer -->
            <%@include file="footer.jsp" %>            
    </body>
</html>