<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Auswahlseite</title>     
  </head>
  <body>
  	<table border="1">
  		<tbody>
	  		<tr>
	  			<td>Liste User</td>				
			</tr>
			
			<c:forEach var="user" items="${users}">		<!--users ist die Liste die mir vom servlet gegeben wird -->
				<tr>
					<td><c:out value="${user.NAME}"/></td>					
				 </tr>
			   </c:forEach>
  		    </tbody>
  	  </table>
  	 <br>
  	      <a href="edit?action=add">Einstellungen</a>	<!-- wir brauchen ein Servlet der die verschiedenen  -->
  	       <a href="edit?action=add">Logout</a>			<!-- action auswertet und an die richtige jsp zurückschickt wo die infos dann angezeigt werden -->
  	       
  	   