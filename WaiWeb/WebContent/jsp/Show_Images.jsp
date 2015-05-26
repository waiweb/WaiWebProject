<%@ page language="java" contentType="text/html" %>
<%@ page import="model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Cam Images </title>
    <link href="css/userEdit.css" type="text/css" rel="stylesheet">
    <script language="JavaScript" src="<%=request.getContextPath()%>/js/ts_picker.js">
</script>

  </head>  
  <body>
  	<font size=5> Cam Images</font> <br><br>	
     <!--  	<div class="rechts">	--> 
  <!-- 	<font size=4> Von</font> 
  		<form  name="startDate" method=GET action=edit>
 <input type="Text" name="timestamp" value="">
 <a href="javascript:show_calendar('document.startDate.timestamp', document.startDate.timestamp.value);"><img src="<%=request.getContextPath()%>/gifs/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
    <input type="hidden" name="startDate1" value="Niat">
  </form>
</div>   --> 
  
  
  <!--   	<font size=4> Bis</font> 
  	<form name="endDate2" method=POST action=edit>
    <input type="Text" name="timestamps" value="">    
<a href="javascript:show_calendar('document.endDate.timestamps', document.endDate.timestamps.value);"><img src="<%=request.getContextPath()%>/gifs/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
  </form>  --> 
  
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
<!--   	<div class="rechts">	 	
  	<font size=4> Von</font> 
  	
 <input type="Text" name="timestamp" value="">
 <a href="javascript:show_calendar('document.startDate.timestamp', document.startDate.timestamp.value);"><img src="<%=request.getContextPath()%>/gifs/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
    <input type="hidden" name="startDate1" value="Niat">
<!--  </div>  -->  
<br style="clear: both">
   	<div class="rechts">
   	 <font size=4> Von</font>
		<table border="1">
			<tbody>
			<tr>
			   <td><input type="Text" name="timestamp" value=""></td>
                <td><a href="javascript:show_calendar('document.getImages.timestamp', document.getImages.timestamp.value);"><img src="<%=request.getContextPath()%>/gifs/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a></td>
				</tr>	
			</tbody>				
		</table>
		</div> 
		
		<div class="rechts">
		<font size=4> Bis</font>
		<table border="1">
			<tbody>
			<tr>
			   <td><input type="Text" name="timestamp2" value=""></td>
                <td><a href="javascript:show_calendar('document.getImages.timestamp2', document.getImages.timestamp2.value);"><img src="<%=request.getContextPath()%>/gifs/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a></td>
				</tr>	
			</tbody>				
		</table>
			</div> 
		
		<br style="clear: both">
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