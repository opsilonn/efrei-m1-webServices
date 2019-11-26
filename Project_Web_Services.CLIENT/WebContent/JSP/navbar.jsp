<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<%@page import="fr.WebService.util.Constants"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
	    <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


	    <!-- Do something -->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	</head>
	
	
	<body>
	
		<!-- Define the Navbar -->
		<nav class="navbar navbar-expand-lg navbar-light justify-content-between" style="background-color:cornflowerblue;color:white">
		
			<!-- Name of the Website -->
			  <a class="navbar-brand my-0 mr-md-auto" href="#">
			    <img src="<%=Constants.NAVBAR_IMAGE_PATH%>" width="30" height="30" class="d-inline-block align-top" alt="">
				<b style="color:white"> <%=Constants.NAME_WEBSITE%> </b>
			  </a>
			
			
			<!-- The toggler used when the web browser's horizontal size is small -->
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			
			<!-- The Navbar's content -->			
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
	
				<!-- Dropdowns -->
				<ul class="navbar-nav mr-auto">
					<% AddAllDropdowns(out, request); %>
				</ul>	
				
							
				<!-- Buttons -->
				<ul class="navbar-nav ml-md-auto">
					<% AddAllButtons(out, request); %>
				</ul>
				
				
				
				<!-- Search Bar -->
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
				
			</div>
			
		</nav>
	</body>
</html>




<%!
	// DROPDOWNS


	/** A method that dynamically adds Dropdown to the navbar
	*
	*/
	public void AddAllDropdowns(JspWriter jw, HttpServletRequest r1) throws Exception
	{
		for(Constants.Multimedia media : Constants.Multimedia.values())
		{
			AddDropdown(jw, r1, media);
	  	}
	}


	/** A method that creates the Dropdown for a given multimedia
	*
	*/
	public void AddDropdown(JspWriter jw, HttpServletRequest r1, Constants.Multimedia multimedia) throws Exception
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
	*/
	public void AddAllButtons(JspWriter jw, HttpServletRequest r1) throws Exception
	{
		Boolean isConnected = false;
		
		
		if( isConnected )
		{
			AddButton(jw, r1, "My Profile");
			AddButton(jw, r1, "Logout");
		}
		else
		{
			AddButton(jw, r1, "Sign up");
			AddButton(jw, r1, "Log in");	
		}
	}
	
	
	/** A method that creates the Button for a given mission
	*
	*/
	public void AddButton(JspWriter jw, HttpServletRequest r1, String name) throws Exception
	{
		jw.println("<li class=\"nav-item\" style=\"margin:0 25px 0 0\">");
		jw.println("<a class=\"nav-link\" href=\"#\" style=\"color:white\">" + name + "</a>");
		jw.println("</li>");
	}
%>