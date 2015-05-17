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
  	<font size=5> Neuen User hinzufügen </font> <br><br>	
  		<form name=saveUser method=POST  action= edit>  
		<table border="1">
			<tbody>
				<tr>
					<td>Username: </td>
					<td><input type="text" name="username" value=""></td>		
				</tr>
				<tr>
					<td>Passwort: </td>
					<td><input type="text" name="passwort" value=""></td>		
				</tr>
				<tr>		
					<td>Rechte: </td>	
					<td><input type="text" name="rechte" value=""></td>
				</tr>		
				<tr>		
					<td>Kommentar: </td>	
					<td><input type="text" name="kommentar" value=""></td>
				</tr>			
			</tbody>				
		</table>
		<br>
		<div class="links"> 
			<button type="submit" name="action" value="addNewUser"> User hinzufügen</button>
			<input type="hidden" name="id" value="add">
		</div>
		<div class="rechts">
	   		<button type="submit" name="action" value="Back"> Zurück</button>	
	  		<input type="hidden" name="id" value="back">
	  	</div>
	  	</form> 
  </body>
</html>