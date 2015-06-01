<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log-Datei anzeigen</title>
</head>
	<link href="css/Buttons.css"  type="text/css" rel="stylesheet">
	<body>
		<form name=backButton method=GET action=settings>
			<button class="btn" type="submit" name="action" value="Back">Zurück</button>
		</form>
		<font size=5> Aktuelle Log-Daten:</font> <br><br>
		
		<!-- Anzeige der Log-File: -->
		<c:forEach var="string" items="${log}">
			<c:out value="${string}"/><br>
		</c:forEach>
	</body>
</html>