<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Auswahlseite</title>    
		<link rel="stylesheet" href="css/tabular.css" type="text/css" >   
		<link href="css/Buttons.css"  type="text/css" rel="stylesheet">  
		<link href="css/userEdit.css" type="text/css" rel="stylesheet">
  </head>
	<body>
		<table class="beispiel" cellspacing="0" cellpadding="0"> 
	  		<tbody>
	  		<caption>Liste der User</caption>
		  	<thead>	<tr>			
		  			<td>ID</td>		
					<td>User Name</td>
					<td>Rechte</td>
					<td>Kommentar</td>
					<td>Hinzugefuegt am</td>
					<td>User Editieren</td>
				</tr>			
				
				<c:forEach var="user" items="${users}">		<!--users ist die Liste die mir vom servlet gegeben wird -->
					<tr>
						<td><c:out value="${user.id_User}"/></td>	
						<td><c:out value="${user.username}"/></td>	
						<td><c:out value="${user.rechte}"/></td>
						<td><c:out value="${user.kommentar}"/></td>	
						<td><c:out value="${user.timeOfCreation}"/></td>
						<td>	 
						<form name=checkLogin method=POST  action= edit>  
		  	           		<button type="submit" name="action" value="editUser">Editieren</button>
		  	            	<input type="hidden" name="id" value="${user.id_User}">
	                  	</form>	 	
	              	</td>	
	              	</tr>
				</c:forEach>
			</tbody>
		</table>
		<br style="clear: both">
		<div class="links">
			<form name=userAdd method=GET  action= edit>  
		  	 	<button class="btn" type="submit" name="action" value="addUser">User hinzufuegen</button>
		 	</form>	
		</div>
		<div class="rechts">
			<form name=back method=GET  action= edit>
		   		<button class="btn" type="submit" name="action" value="back"> Zurueck</button>	
			</form>
		</div>
	</body>
</html>
