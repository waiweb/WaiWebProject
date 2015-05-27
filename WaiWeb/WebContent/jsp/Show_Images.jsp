<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Cam Images </title>
    <link href="css/userEdit.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/css/jsDatePick_ltr.min.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jsDatePick.min.1.3.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/moment.min.js"></script> 
    <script src="<%=request.getContextPath()%>/js/combodate.js"></script>
    <script type="text/javascript">
	window.onload = function(){
		new JsDatePick({
			useMode:2,
			target:"inputField",
			dateFormat:"%d-%M-%Y"
		});
		new JsDatePick({
			useMode:2,
			target:"inputField2",
			dateFormat:"%d-%M-%Y"
		});
	};
</script>
    <script>
$(function(){
    $('#time').combodate({
        firstItem: 'name', //show 'hour' and 'minute' string at first item of dropdown
        minuteStep: 5
    });  
    $('#time2').combodate({
        firstItem: 'name', //show 'hour' and 'minute' string at first item of dropdown
        minuteStep: 5
    });  
});
</script>


  </head>  
  <body>
  	<font size=5> Cam Images</font> <br><br>	
   
  
  		<form name=getImages method=GET  action= edit> 
		<table border="1">
			<tbody>
				<tr>   	
			   		<td>Aktuelle Bilder für: <c:out value="${cam.camname}"/></td>
			 	</tr>		
			 	<tr>   	
			 	</tr>
			</tbody>	
			
		</table>

<br style="clear: both">
   	<div class="rechts">
   	 <font size=4> Von</font>
		<table border="1">
			<tbody>
			<tr>
			 
                <td> <input type="text" size="12" id="inputField" name="inputField"  /><img src="<%=request.getContextPath()%>/gifs/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></td>
                <td><input type="text" id="time" data-format="HH:mm" data-template="HH : mm" name="datetime"></td>
				</tr>	
			</tbody>
		</table>
		</div> 
	
		<div class="rechts">
		<font size=4> Bis</font>
		<table border="1">
			<tbody>
			<tr>
			   <td> <input type="text" size="12" id="inputField2" name="inputField2"/><img src="<%=request.getContextPath()%>/gifs/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></td>
			   <td><input type="text" id="time2" data-format="HH:mm" data-template="HH : mm" name="datetime"></td>
				</tr>	
			</tbody>	
					
		</table>
			</div> 
			
		<br style="clear: both"><br><br>
		<table border="1">
			<tbody>
				<tr>
					<td><a href="<%=request.getContextPath()%>/camimages/test.jpg"><img width="250" height="250" src="<%=request.getContextPath()%>/camimages/test.jpg"/></a><br><br>Name / Timestamp</td>	
				</tr>	
			</tbody>				
		</table>
	
	  	<br style="clear: both">
			<div class="rechts">
		   		<button type="submit" name="action" value="back"> Zurück</button>	
		  		<input type="hidden" name="id" value="back">
		  	</div>
		  	<div class="rechts">
		   		<button type="submit" name="action" value="refresh"> Aktualisieren</button>	
		  		<input type="hidden" name="id" value="refresh">
		  	</div>
	  	</form> 
  </body>
</html>