<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Cam-Liste</title>     
  </head>
	<body>
		<font size=5> Liste der Cams </font> <br><br>
		<table align="left" border="1">
	  		<tbody>
		  		<tr>					
					<td>Cam Name</td>
					<td>URL</td>
					<td>Hinzugefügt am</td>
					<td>Verzeichnis</td>
					<td>Kommentar</td>				
				</tr>
				
				<c:forEach var="cam" items="${cams}">
					<tr>
						<td><c:out value="${cam.camname}"/></td>	
						<td><c:out value="${cam.url}"/></td>
						<td><c:out value="${cam.timeOfCreation}"/></td>	
						<td><c:out value="${cam.pathOriginalImageDirectory}"/></td>
						<td><c:out value="${cam.kommentar}"/></td>
					<td>	 
						<form name=checkLogin method=POST  action= edit>  
		  	            	<button type="submit" name="action" value="DeleteCam">Cam löschen</button>
		  	            	<input type="hidden" name="id" value="${user.rechte}">
		  	            </form>	
		  	    	</td>	
					</tr>
				</c:forEach>	
			</tbody>
		</table>
	</body>
</html>
