<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" isELIgnored="false"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Activity Stream</title>


</head>
<body>
	
<!-- create a form which will have textboxes for Sender Name and Message content along with a Send 
Submit button. Handle errors like empty fields -->
	
<center>
	<h1>Send Your Message here</h1>
		<form action="sendMessage" >
			<table style="width:100">
			
			<c:if test = "${!empty errormsg}">
			<b>${errormsg}</b>
			</c:if>
				<tr>
					<td>Sender Name:</td>
					<td><input type="text" name="sender"></td>

				</tr>

				<tr>
					<td>Message:</td>
					<td><input type="text" name="message" ></td>

				</tr>
				
				<tr>
					
					<td><input type="submit" name="Send" value = "send"></td>

				</tr>
				
			</table>
		</form>



	
	<c:if test="${!empty list}">
	<h1>Circle Messages</h1>
<!-- display all existing messages in a tabular structure with Sender Name, Posted Date and Message -->		
	<table name="users" border="1.0" width="75" cellpadding="1.5" align="center">
		<tr>
			<th>Sender</th>
			<th>Message</th>
			<th>PostedDate</th>
			
		</tr>
		<c:forEach items="${list}" var="data">
			<tr>
				<td>${data.senderName}</td>
				<td>${data.message}</td>
				<td>${data.postedDate}</td>
				
			</tr>
		</c:forEach>
	</table>
</c:if>
</center>

	


</body>
</html>