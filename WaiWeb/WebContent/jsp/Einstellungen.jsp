<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Einstellungen</title> 
</head>
	<body>
		<font size=5> Einstellungen</font> <br><br>
			<table border="1">
				<tbody>
				    <tr>   	
				     <td>Anzahl der Thumbnails</td>
				     <td> <!-- TODO: Hier die Anzahl der Thumbnails übergeben! --></td>
				     </tr>						
				</tbody>
			</table> <br>
		<a href="settings?action=deleteDatabase">Gesamte Datenbank zurücksetzen</a>  <br>
		<a href="settings?action=deletePictures">Alle Bilder löschen</a>  <br><br>
	    <form name=backButton method=GET action=settings>
	    	<button type="submit" name="action" value="Back">Zurück</button>	  
	  	</form>	
	</body>
</html>