<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>    
    <title>Cam Konfiguration </title>
    <!--  Hier die CSS Referenz angeben: -->
    <link href="css/userEdit.css" type="text/css" rel="stylesheet">
  </head>  
  <body>
  	<font size=5> Cam bearbeiten </font> <br><br>	
		<table border="1">
			<tbody>
				<tr>   	
			   		<td>Konfiguration für:<c:out value="${cam.camname}"/></td>
			 	</tr>
				<tr>
					<td>Camname:</td>
					<td><input type="text" name="name" value="${cam.camname}"></td>		
				</tr>
				<tr>		
					<td>URL:</td>	
					<td><input type="text" name="url" value="${cam.url}"></td>
				</tr>				
			</tbody>				
		</table>
		<br>
		<div class="links">
		<form name=deleteCam method=POST  action= edit>  
			<button type="submit" name="action" value="deleteCam"> Cam löschen</button>
			<input type="hidden" name="id" value="${cam.id_Cam}">
		</form> 
		</div>
		<div class="rechts">
		<form name=saveCam method=POST  action= edit>  
	   		<button type="submit" name="action" value="saveCam"> Cam speichern</button>	
	  		<input type="hidden" name="id" value="${cam.id_Cam}">
	  	</form> 
	  	</div>
  </body>
</html>