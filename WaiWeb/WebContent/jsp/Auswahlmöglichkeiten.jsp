<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>      
    <title>Auswahlseite</title>     
  </head>
	<body>
		<font size=5> Hauptmenü </font> <br><br>
			<a href="auswahl?action=user">Liste der User</a>  <br>
			<a href="auswahl?action=cam">Liste der Cams</a>  <br>
			<a href="auswahl?action=settings">Einstellungen</a>   <br>
	    <br>
			<form name=checkLogin method=POST action=login>  
		  	<input type=submit name=action value="Logout">  <!--  was soll beim logout passieren -->
	  	</form>	
				<!-- action auswertet und an die richtige jsp zurückschickt wo die infos dann angezeigt werden -->
	</body>
</html>    
  	          