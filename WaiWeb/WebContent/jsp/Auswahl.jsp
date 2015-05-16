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
					<td>User Liste</td>
				</tr>
			
				
				<c:forEach var="user" items="${users}">		<!--users ist die Liste die mir vom servlet gegeben wird -->
					<tr>
						<td><c:out value="${user.username}"/></td>			
					 </tr>
				</c:forEach>
				
			</tbody>
		</table>
	
	
		<table  border="1">
	  		<tbody>
		  		<tr>					
					<td>User Cams</td>
				</tr>
				
				<c:forEach var="cam" items="${cams}">		<!--users ist die Liste die mir vom servlet gegeben wird -->
					<tr>
						<td><c:out value="${cam.camname}"/></td>			
					 </tr>
				</c:forEach>
				
			</tbody>
		</table>
	
		<br>
		     <form name=checkLogin method=POST action=login>  
		  	<input type=submit name=action value="Logout">  <!--  was soll beim logout passieren -->
	  	</form>	
			<a href="edit?action=add">Logout</a>			<!-- action auswertet und an die richtige jsp zurückschickt wo die infos dann angezeigt werden -->
	</body>
</html>    
  	          