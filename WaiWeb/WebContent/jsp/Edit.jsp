<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Konfiguration </title>
    <!--  Hier die CSS Referenz angeben: -->
    <link href="css/userEdit.css" type="text/css" rel="stylesheet">
  </head>  
  <body>
  	<font size=5> User bearbeiten </font> <br><br>	
		<table border="1">
			<tbody>
			    <tr>   	
			     <td>Konfiguration f�r:  <c:out value="${user.username}"/></td>
			     </tr>
				<tr>
					<td>Benutzer:</td>
					<td><input type="text" name="author" value="${user.username}"></td>		
				</tr>
				<tr>		
					<td>Rechte:</td>	
					<td><input type="text" name="title" value="${user.rechte}"></td>
				</tr>				
			</tbody>				
		</table>
		<br>
		<div class="links">
		<form name=deleteUser method=POST  action= edit>  
			<button type="submit" name="action" value="deleteUser"> User L�schen</button>
			<input type="hidden" name="id" value="${user.id_User}">
		</form> 
		</div>
		<div class="rechts">
		<form name=saveUser method=POST  action= edit>  
	   		<button type="submit" name="action" value="saveUser"> User Speichern</button>	
	  		<input type="hidden" name="id" value="${user.id_User}">
	  	</form> 
	  	</div>
  </body>
</html>