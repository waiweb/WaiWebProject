<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>User Konfiguration </title>
    <!--  Hier die CSS Referenz angeben: -->
    <link href="css/userEdit.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="css/tabular.css" type="text/css" > 
 	<link rel="stylesheet" href="css/Buttons.css" type="text/css" >
  </head> 
  <body>
  		
  		<form name=addUser method=POST  action= edit>  
		<table class="beispiel" cellspacing="0" cellpadding="0"> 
			<caption>Neuen User hinzufÃ¼gen </caption>
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
		
		
		<br style="clear: both">
		
		<div class="links"> 
			
			<button class="btn" type="submit" name="action" value="addNewUser"> User hinzufÃ¼gen</button>
			<input type="hidden" name="id" value="add">
				
		</div>
		</form>
		
		<br style="clear: both">
		
		<form name=back method=GET  action= edit>
			<div class="links">
	 			
		   		<button class="btn" type="submit" name="action" value="back"> ZurÃ¼ck</button>	
		  		<input type="hidden" name="id" value="back">
				
		  	</div>
	 </form>
  </body>
</html>
