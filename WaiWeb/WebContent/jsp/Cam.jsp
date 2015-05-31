<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Cam-Liste</title>     
    <link rel="stylesheet" href="css/tabular.css" type="text/css" >
    <link href="css/userEdit.css" type="text/css" rel="stylesheet"> 
    <link href="css/Buttons.css"  type="text/css" rel="stylesheet">
  </head>
	<body>
		<table class="beispiel" cellspacing="0" cellpadding="0"> 
		<caption>Liste der Kameras</caption>
		<thead><tr>
		<th>Cam Name</th>
		<th>URL</th>
		<th>Hinzgefuegt am</th>
		<th>Verzeichnis</th>
		<th>Kommentar</th>
		<th>Bilder</th>
		</tr>
			<c:forEach var="cam" items="${cams}">
				<tr>
					<td><c:out value="${cam.camname}"/></td>	
					<td><c:out value="${cam.url}"/></td>
					<td><c:out value="${cam.timeOfCreation}"/></td>	
					<td><c:out value="${cam.pathOriginalImageDirectory}"/></td>
					<td><c:out value="${cam.kommentar}"/></td>
					<!-- TODO: Cams Thumbnails hier einbinden! -->
					<td>
						<form name=imageShow method=POST  action= edit>  
							<button type="submit" name="action" value="showImages"><img src="<%=request.getContextPath()%><c:out value="${cam.getPathOriginalImageDirectory()}"/>"/></button>
							<input type="hidden" name="id" value="${cam.id_Cam}">
						</form>
					</td>
					<td>
		  	    		<form name=camEdit method=POST  action= edit>  
		  	           		<button type="submit" name="action" value="editCam">Editieren</button>
		  	            	<input type="hidden" name="id" value="${cam.id_Cam}">
	                  	</form>	
		  	    	</td>
				</tr>
				</c:forEach>
		</tbody>
	</table>
		<br style="clear: both">
			<div class="links">
				<form name=camAdd method=GET  action= edit>  
			  	 	<button class="btn" type="submit" name="action" value="addCam">Cam hinzufuegen</button>
			 	</form>	
			</div>
			<div class="rechts">
			<form name=back method=GET  action= edit>
		   		<button class="btn" type="submit" name="action" value="back"> Zurueck</button>	
			</form>
			</div>
	</body>
</html>
