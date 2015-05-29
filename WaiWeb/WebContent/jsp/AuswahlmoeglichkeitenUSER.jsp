<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>      
    <title>Auswahlseite</title>     
    	      	<link rel="stylesheet" href="css/menu_styles.css" type="text/css" >
    			<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
   				<script src="js/menu_script.js"></script>
  </head>
	<body>
		<%	//Zugriff Ueberpruefen:
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
	

	
	
	<div id='cssmenu'>
	<ul>

         <li><a href="auswahl?action=cam">Liste der Cams</a> </li>
 		
			<form name=checkLogin method=POST action=login>  
		  	<input type=submit name=action value="Logout">  <!--  was soll beim logout passieren -->
	  
      </ul>

</div>
	
	</body>
</html>    
  	          
