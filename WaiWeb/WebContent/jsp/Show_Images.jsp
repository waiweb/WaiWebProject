<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Cam Images </title>
    <link href="css/userEdit.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="css/tabular.css" type="text/css" >
   	<link href="css/Buttons.css"  type="text/css" rel="stylesheet">
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
			dateFormat:"%d-%m-%Y"
		});
		new JsDatePick({
			useMode:2,
			target:"inputField2",
			dateFormat:"%d-%m-%Y"
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
  		<form name=getImages method=POST  action= edit> 
		<table class="beispiel2" cellspacing="0" cellpadding="0"> 
		<caption>Cam Images</caption>
			<tbody>
				<tr>   	
			   		<td>Aktuelle Bilder fuer: <c:out value="${cam.camname}"/></td>
			 	</tr>		
			</tbody>	
		</table>

	<br style="clear: both">
   	 <div class="links">
		<table class="beispiel2" cellspacing="0" cellpadding="0"> 
			<caption>Von: (Start)</caption>
			<tbody>
			<tr>
                <td> <input type="text" size="12" id="inputField" name="startDate"  /><img src="<%=request.getContextPath()%>/gifs/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></td>
                <td><input type="text" id="time" data-format="HH:mm" data-template="HH : mm" name="startTime"></td>
				</tr>	
			</tbody>
		</table>
		</div>

		<div class="rechts">
		<table class="beispiel2" cellspacing="0" cellpadding="0"> 
		<caption>Bis: (Ende)</caption>
			<tbody>
			<tr>
			   <td> <input type="text" size="12" id="inputField2" name="endDate"/><img src="<%=request.getContextPath()%>/gifs/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></td>
			   <td><input type="text" id="time2" data-format="HH:mm" data-template="HH : mm" name="endTime"></td>
				</tr>	
			</tbody>	
		</table>
		</div>
		
		<br style="clear: both"><br>
		<table class="beispiel3" cellspacing="0" cellpadding="0">
			<tbody>
			<c:forEach var="path" items="${path}">
				<tr>
					<!--  HIER werden die Image Pfade eingefügt! Über getContextPath(): Zugriff auf WebContent, dann restlichen Path angeben, also /camimages/ID/Year/... -->
					<td><a href="<%=request.getContextPath()%><c:out value="${path.getPath()}.jpg"/>"><img src="<%=request.getContextPath()%><c:out value="${path.getPath()}_thumbnail.jpg"/>"></a><br><br><c:out value="${path.getTimestamp()}"/></td>	
				</tr>
			</c:forEach>	
			</tbody>				
		</table><br>
	  	<div class="links">
		   		<button class="btn" type="submit" name="action" value="refresh"> Aktualisieren</button>	
		  		<input type="hidden" name="id" value="${cam.id_Cam}">
		</div>
		</form>
		<br style="clear: both">
		<div class="rechts">
		<form name=getImages method=GET  action= edit> 
		   		<button class="btn"  type="submit" name="action" value="back"> Zurueck</button>	
		  		<input type="hidden" name="id" value="back">
	  	</form> 
	  	</div>
  </body>
</html>