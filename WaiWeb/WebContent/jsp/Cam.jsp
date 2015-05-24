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
					<td>Hinzugef�gt am</td>
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
						<td> x <!--  Hier Thumbnail einf�gen! --></td>
					<td>
		  	    		<form name=camEdit method=POST  action= edit>  
		  	           		<button type="submit" name="action" value="editCam">Editieren</button>
		  	            	<input type="hidden" name="id" value="${cam.id_Cam}">
	                  	</form>	
		  	    	</td>
					<!-- <td>	 
						<form name=camDelete method=POST  action= edit>  
		  	            	<button type="submit" name="action" value="deleteCam">L�schen</button>
		  	            	<input type="hidden" name="id" value="${cam.id_Cam}">
		  	            </form>	
		  	    	</td>
		  	    	-->
					</tr>
				</c:forEach>	
			</tbody>
		</table>
		<br style="clear: both"> <br>
		<div>
			<form name=camAdd method=GET  action= edit>  
		  	 	<button type="submit" name="action" value="addCam">Cam hinzuf�gen</button>
		  		<input type="hidden" name="id" value=add>
		 	</form>	
		</div>
	</body>
</html>
