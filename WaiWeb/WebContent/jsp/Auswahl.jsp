<%@ page language="java" import="model.*" %>
<jsp:useBean id="User" scope="session" class="model.User" />
<jsp:useBean id="Cam" scope="session" class="model.Cam" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


  User.processRequest(request);  hier habe ich einen User 
	if (table.getProcessError() == false && User.getRechte()==(fragen was der int wert ist)){
	  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>Auswahlseite</title>     
  </head>
  <body>
  	<table border="1">
  		<tbody>
	  		<tr>
	  			<td>Liste Cams</td>				
				<td>Liste User</td>
				<
			</tr>
			<c:forEach var="cam" items="${cams}"> cams ist die Liste die mir vom servlet gegeben wird 
				<tr>
					<td><c:out value="${cam.NAME}"/></td>					
				</tr>
			</c:forEach>
			<c:forEach var="user" items="${users}"> users ist die Liste die mir vom servlet gegeben wird 
				<tr>
					<td><c:out value="${user.NAME}"/></td>					
				 </tr>
			   </c:forEach>
  		    </tbody>
  	  </table>
  	 <br>
  	      <a href="edit?action=add">Einstellungen</a>   wir brauchen ein Servlet der die verschiedenen 
  	       <a href="edit?action=add">Logout</a>   action auswertet und an die richtige jsp zurückschickt wo die infos dann angezeigt werden
  	       
  	         } else{ 
  	                Normaler user
  	            }       