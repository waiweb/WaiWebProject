<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Cam hinzufuegen </title>
    <!--  Hier die CSS Referenz angeben: -->
    <link href="css/userEdit.css" type="text/css" rel="stylesheet">
 	<link href="css/Buttons.css"  type="text/css" rel="stylesheet"> 
    <link rel="stylesheet" href="css/tabular.css" type="text/css" >  
  	
  </head>  
  <body>	
  		<form name=addCam method=POST  action= edit>  
		<table class="beispiel" cellspacing="0" cellpadding="0"> 
		<caption>Neue Cam hinzufuegen</caption>
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
			<button class="btn" type="submit" name="action" value="addNewCam"> Cam hinzufuegen</button>
			<input type="hidden" name="id" value="add">
		</div>
	  	</form> 
	  			<br style="clear: both">
	  	<form name=back method=GET  action= edit>
			<div class="rechts">
		   		<button class="btn" type="submit" name="action" value="back"> Zurueck</button>	
		  		<input type="hidden" name="id" value="back">
		  	</div>
	  	</form>
  </body>
</html>
