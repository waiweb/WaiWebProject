<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Cam Konfiguration </title>
    <!--  Hier die CSS Referenz angeben: -->
    <link href="css/userEdit.css" type="text/css" rel="stylesheet">
  </head>  
  <body>
  	<font size=5> Neue Cam hinzufügen </font> <br><br>	
  		<form name=addCam method=POST  action= edit>  
		<table border="1">
			<tbody>
				<tr>
					<td>Camname: </td>
					<td><input type="text" name="camname" value=""></td>		
				</tr>
				<tr>
					<td>URL: </td>
					<td><input type="text" name="url" value=""></td>		
				</tr>		
				<tr>		
					<td>Kommentar: </td>	
					<td><input type="text" name="kommentar" value=""></td>
				</tr>			
			</tbody>				
		</table>
		<br style="clear: both">
		<div class="links"> 
			<button type="submit" name="action" value="addNewCam"> Cam hinzufügen</button>
			<input type="hidden" name="id" value="add">
		</div>
	  	</form> 
	  	<br style="clear: both">
	  	<form name=back method=GET  action= edit>
			<div class="rechts">
		   		<button type="submit" name="action" value="back"> Zurück</button>	
		  		<input type="hidden" name="id" value="back">
		  	</div>
	  	</form>
  </body>
</html>