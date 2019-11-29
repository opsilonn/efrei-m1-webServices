<html lang="en">
    <head>
        <!-- Changing the title of the page -->
        <title> <%=Constants.NAME_PAGE_HOME%> </title>
    </head>


    <body>
            <!-- We always add FIRST the Navbar -->
            <%@include file="navbar.jsp" %>
            
            
            <!-- Display some stuff -->
            Welcome to the home page
            <br>
            <%
	            if(request.getSession().getAttribute("XYZ") != null)
	            {
	                out.println( "Bonjour Monsieur " + request.getSession().getAttribute("XYZ") );
	            }
           %>


            <!-- We always add LAST the Footer -->
            <%@include file="footer.jsp" %>            
    </body>
</html>