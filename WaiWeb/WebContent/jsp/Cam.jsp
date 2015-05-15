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
			
				
				<c:forEach var="cam" items="${cams}">		<!--users ist die Liste die mir vom servlet gegeben wird -->
					<tr>
						<td><c:out value="${cam.camname}"/></td>			
					 </tr>
				</c:forEach>
				
			</tbody>
		</table>
	</body>
	</html>