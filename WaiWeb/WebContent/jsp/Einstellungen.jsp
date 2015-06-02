<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Einstellungen</title> 
	<link rel="stylesheet" href="css/menu_styles.css" type="text/css" >
	<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	<script src="js/menu_script.js"></script>
	<link href="css/Buttons.css"  type="text/css" rel="stylesheet">
</head>
	<body>
		<font face="sans-serif" size=5> Einstellungen</font> <br><br>
		<form name=backButton method=GET action=settings>
		<div id='cssmenu'>
		<ul>
		<li><a href="settings?action=deleteDatabase">Gesamte Datenbank zurücksetzen</a></li>
		<li><a href="settings?action=showLog">Logfile anzeigen</a><br></li>	 
	  	</ul>
	  		<button class="btn" type="submit" name="action" value="Back">Zurück</button>
	  	</div>
	  	</form>	
	</body>
</html>
