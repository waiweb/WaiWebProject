<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Cam Konfiguration </title>
    <!--  Hier die CSS Referenz angeben: -->
    <link rel="stylesheet" href="css/tabular.css" type="text/css" > 
	<link href="css/userEdit.css" type="text/css" rel="stylesheet"> 
	<link href="css/Buttons.css"  type="text/css" rel="stylesheet">
  </head>  
  <body>
  		<form name=saveCam method=POST  action= edit>  
		<table class="beispiel" cellspacing="0" cellpadding="0" >
			<tbody>
				<caption>Cam bearbeiten</caption>
			<thead>
			</thead>
			 <caption>Konfiguration fuer: <c:out value="${cam.camname}"/></caption>
			<tbody>
				<tr>
					<td>Camname:</td>
					<td><input type="text" name="camname" value="${cam.camname}"></td>		
				</tr>
				<tr>		
					<td>URL:</td>	
					<td><input type="text" name="url" value="${cam.url}"></td>
				</tr>		
				<tr>		
					<td>Kommentar:</td>	
					<td><input type="text" name="kommentar" value="${cam.kommentar}"></td>
				</tr>		
			</tbody>				
		</table>
		<br>
		<div class="links">
			<button class="btn" type="submit" name="action" value="deleteCam"> Cam loeschen</button>
			<input type="hidden" name="id" value="${cam.id_Cam}">
		</div>
		<div class="rechts">
	   		<button class="btn" type="submit" name="action" value="saveCam"> Cam speichern</button>	
	  		<input type="hidden" name="id" value="${cam.id_Cam}">
	  	</div>
	  	</form> 
  </body>
</html>
