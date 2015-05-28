<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>      
    <title>Auswahlseite</title>     
  </head>
	<body>
		<%	//Zugriff überprüfen:
			String user = null, userName = null, sessionID = null;
			if (session.getAttribute("username") == null){
				//Auf Loginseite zurückleiten falls keine Session erstellt wurde!
			    response.sendRedirect(request.getContextPath() + "/master");
			} else {
				user = (String) session.getAttribute("username");
			}
			
			Cookie[] cookies = request.getCookies();
			if (cookies !=null){
				for(Cookie cookie : cookies){
				    if(cookie.getName().equals("username")) 
				    	userName = cookie.getValue();
				    if(cookie.getName().equals("JSESSIONID")) 
				    	sessionID = cookie.getValue();
				}
			}
		%>
		<font size=5> Willkommen <%=userName %>! </font> <br>
		<font size=3> Deine Session ID=<%=sessionID %></font>
		<br> <br>
		
			<a href="auswahl?action=cam">Liste der Cams</a>  <br>
		
	    <br>
			<form name=checkLogin method=POST action=login>  
		  	<input type=submit name=action value="Logout">  <!--  was soll beim logout passieren -->
	  	</form>	
	</body>
</html>    
  	          
