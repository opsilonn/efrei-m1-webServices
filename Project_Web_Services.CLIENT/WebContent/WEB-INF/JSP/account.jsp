<%@page import="rest.model.User"%>

<html lang="en">
    <head>
        <!-- Changing the title of the page -->
        <title> <%=Constants.NAME_PAGE_ACCOUNT%> </title>
    </head>
    

    <body>
            <!-- We always add FIRST the Navbar -->
            <%@include file="navbar.jsp" %>
            
                <%
			    	User user = new User();
			    	if(Constants.IS_CONNECTED(request))
			    	{
			    		user = (User)request.getSession().getAttribute("user");
			    	}
			    	else
			    	{
			    		user.setPseudo("your username");
			    		user.setEmail("your email");
			    	}
			    %>
            
            
            <!-- Display some stuff -->
            <div class="container" style="padding-top: 4vh">
            <div class="row">
                <div class="col">
                    
                    <h1><% out.println(request.getAttribute("title")); %></h1>
                    
                    <hr class="theme"/>
                        
                    
                    <form action="employee" method="POST">
                    <c:choose>
                        
                        <c:when test="${empty employee}">
                            <input type="hidden" name="method" value="POST" />
                        </c:when>
                                
                        <c:otherwise>
                            <input type="hidden" name="method" value="PUT" />                          
                        </c:otherwise>
                                
                    </c:choose>
                        <%--
                        <c:if test="${not empty employee}">value="${employee.last_name}"</c:if>
                        <c:if test="${role != 'admin'}">disabled</c:if>
                        --%> 
                            
                        <%-- USERNAME FIELD --%>
                        <div class="form-group row">
                            <div class="col-sm-1"></div>
                            <label for="Username" class="col-sm-2 col-form-label" style="text-align: right;">Username</label>
                            <div class="col-sm-8">
                                <input
                                type="text"
                                class="form-control"
                                name="Username" id="0"
                                placeholder=<% out.println("\"" + user.getPseudo() + "\""); %>
                                disabled>
                            </div>
                        </div>
                            
                        <%-- EMAIL FIELD --%>
                        <div class="form-group row">
                            <div class="col-sm-1"></div>
                            <label for="Email" class="col-sm-2 col-form-label" style="text-align: right;">Email</label>
                            <div class="col-sm-8">
                                <input
                                type="text"
                                class="form-control"
                                name="Email" id="0"
                                placeholder=<% out.println("\"" + user.getEmail() + "\""); %>
                                disabled>
                            </div>
                        </div>
                	</form>
                    
                </div>
            </div>
        </div>



            <!-- We always add LAST the Footer -->
            <%@include file="footer.jsp" %>            
    </body>
</html>