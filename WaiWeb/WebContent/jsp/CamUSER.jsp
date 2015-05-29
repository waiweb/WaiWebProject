<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Cam-Liste</title>  
        <link rel="stylesheet" href="css/tabular.css" type="text/css" > 
        <link href="css/Buttons.css"  type="text/css" rel="stylesheet">  
  </head>
	<body>
		<table class="beispiel" cellspacing="0" cellpadding="0">
			<caption>Liste der Kameras</caption>
	  		<tbody>
		  		<tr>					
					<td>Cam Name</td>
					<td>URL</td>
					<td>Hinzugefügt am</td>
					<td>Verzeichnis</td>
					<td>Kommentar</td>	
					<td>Thumbnail</td>		
				</tr>
				
				<c:forEach var="cam" items="${cams}">
					
					<tr>
						<td><c:out value="${cam.camname}"/></td>	
						<td><c:out value="${cam.url}"/></td>
						<td><c:out value="${cam.timeOfCreation}"/></td>	
						<td><c:out value="${cam.pathOriginalImageDirectory}"/></td>
						<td><c:out value="${cam.kommentar}"/></td>
						<!-- Cams Thumbnails hier einbinden! -->
						<td>
							<form name=imageShow method=POST  action= edit>  
								<button type="submit" name="action" value="showImages"><img width="75" height="75" src="<%=request.getContextPath()%>/camimages/test.jpg"/></button>
								<input type="hidden" name="id" value="${cam.id_Cam}">
							</form>
						</td>
					</tr>
				</c:forEach>	
			</tbody>
		</table>
			<form name=back method=GET  action= edit>
		   		<button class="btn" type="submit" name="action" value="back"> Zurueck</button>	
			</form>
	</body>
</html>