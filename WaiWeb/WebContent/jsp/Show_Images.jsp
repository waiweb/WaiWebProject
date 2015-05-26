<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Cam Images </title>
    <link href="css/userEdit.css" type="text/css" rel="stylesheet">
  </head>  
  <body>
  	<font size=5> Cam Images</font> <br><br>	
  		<form name=getImages method=GET  action= edit> 
		<table border="1">
			<tbody>
				<tr>   	
			   		<td>Aktuelle Bilder f�r: <c:out value="${cam.camname}"/></td>
			 	</tr>		
			 	<tr>   	
			   		<td>Zeitrahmen: Von [yy:mm:dd, hh:mm:ss] bis [yy:mm:dd, hh:mm:ss] </td>
			 	</tr>
			</tbody>	
					
		</table>
		<br style="clear: both">
		<table border="1">
			<tbody>
				<tr>
					<!-- Hier alle, bzw. einen Teil der Bilder der Cam anzeigen! -->
					<td><a href="<%=request.getContextPath()%>/camimages/test.jpg"><img width="250" height="250" src="<%=request.getContextPath()%>/camimages/test.jpg"/></a>
					<br><br>Name / Timestamp</td>	
				</tr>	
			</tbody>				
		</table>
	  	<br style="clear: both">
			<div class="rechts">
		   		<button type="submit" name="action" value="back"> Zur�ck</button>	
		  		<input type="hidden" name="id" value="back">
		  	</div>
	  	</form> 
  </body>
</html>