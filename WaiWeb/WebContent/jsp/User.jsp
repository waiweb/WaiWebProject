<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>      
    <title>Auswahlseite</title>     
  </head>
	<body>

		<table align="left" border="1">
	  		<tbody>
		  		<tr>					
					<td>User Name</td>
					<td>User Rechte</td>
					<td>User Kommentar</td>
					<td>User Seit</td>
					<td>User Editieren</td>
					
					
				</tr>
			
				
				<c:forEach var="user" items="${users}">		<!--users ist die Liste die mir vom servlet gegeben wird -->
					<tr>
						<td><c:out value="${user.username}"/></td>	
						<td><c:out value="${user.rechte}"/></td>
						<td><c:out value="${user.kommentar}"/></td>	
						<td><c:out value="${user.timeOfCreation}"/></td>
					<!--  	<td><a href="edit?action=edit&id=${book.id}">Ändern</a></td> -->
					<td>	 <form name=checkLogin method=POST  action= edit>  
		  	            		<!--  <input type=submit  name=action value="Editieren">--> 
		  	            		  <button type="submit" name="action" value="Submit">Editieren</button>
		  	            		  	<input type="hidden" name="id" value="${user.rechte}">
	                        	</form>	
	                        	
	                        	</td>	
					 </tr>
				</c:forEach>
				
			</tbody>
		</table>
	
	</body>
	</html>