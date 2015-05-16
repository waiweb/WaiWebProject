<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Auswahlseite</title>     
  </head>
	<body>
		<font size=5> Liste der User </font> <br><br>
		<table align="left" border="1">
	  		<tbody>
		  		<tr>			
		  			<td>ID</td>		
					<td>User Name</td>
					<td>Rechte</td>
					<td>Kommentar</td>
					<td>Hinzugefügt am</td>
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
	</body>
</html>
