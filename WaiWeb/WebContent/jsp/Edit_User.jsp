<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Konfiguration </title>
    <!--  Hier die CSS Referenz angeben: -->
    <link rel="stylesheet" href="css/tabular.css" type="text/css" > 
	<link href="css/userEdit.css" type="text/css" rel="stylesheet"> 
	<link href="css/Buttons.css"  type="text/css" rel="stylesheet">
  </head>  
  <body>
  
  		<form name=saveUser method=POST  action= edit>  
  		
  		<div class="links">
		<table class="beispiel" cellspacing="0" cellpadding="0"> 
			<tbody>
			<caption>User bearbeiten</caption>
			<thead>
			</thead>
			 <caption>Konfiguration fÃÂ¼r:  <c:out value="${user.username}"/></caption>
					  
				<tr>
					<td>Benutzer:</td>
					<td><input type="text" name="username" value="${user.username}"></td>		
				</tr>
				<tr>		
					<td>Rechte:</td>	
					<td><input type="text" name="rechte" value="${user.rechte}"></td>
				</tr>		
				<tr>		
					<td>Kommentar:</td>	
					<td><input type="text" name="kommentar" value="${user.kommentar}"></td>
				</tr>			
			</tbody>				
		</table>
		</div>


		<div class = "rechts">
		<table class="beispiel" cellspacing="0" cellpadding="0"> 
			<tbody>
			    <tr>   	
			  		<td>Cams fÃÂ¼r:  <c:out value="${user.username}"/></td>
			  	</tr>
				<tr>
					<c:forEach var="checkedCam" items="${checkedCams}">	
					  <tr>
						<td><c:out value="${checkedCam.camname}"/></td>
						<td><input type="checkbox" name="checked" value="${checkedCam.id_Cam}" checked="checked" >	</td>
						<tr>
			    	</c:forEach>
			    	 <c:forEach var="cams" items="${cams}">
			    	  <tr>
						<td><c:out value="${cams.camname}"/></td>
						<td><input type="checkbox" name="checked" value="${cams.id_Cam}" >	</td>
						<tr>
			    	</c:forEach>	
				</tr>
			</tbody>				
		</table>
	
		</div>
		<br style="clear: both">
		<br style="clear: both">
		<br style="clear: both">  
		<div class="links"> 
			<button class="btn" type="submit" name="action" value="deleteUser"> User LÃÂ¶schen</button>
			<input type="hidden" name="id" value="${user.id_User}">
		</div>
		
		
		<br style="clear: both">
		<div class="links">
		
		
			
	   		<button class="btn" type="submit" name="action" value="saveUser"> User Speichern</button>	
	  		<input type="hidden" name="id" value="${user.id_User}">
				 
	  	</div>
	  	</form> 
	  	
		<br style="clear: both">
	  	<div class="links">
	 			<form name=back method=GET  action= edit>
		   		<button class="btn" type="submit" name="action" value="back"> ZurÃ¼ck</button>	
		  		<input type="hidden" name="id" value="back">
				</form>
		  	</div>
	  	
	  	
	  	
  </body>
</html>
