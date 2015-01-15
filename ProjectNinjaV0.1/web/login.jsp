<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="loginAct" method="post">
		<p><tt><s:fielderror name="error"/></tt></p>
		
		<p>Student ID: <input type="text" name="sb.studentID" placeholder="Username" value="${sb.studentID}"></p>
		<p>Password: <input type="password" name="sb.password" placeholder="********"></p>
		
		<img id="cptchaimg" class="LBD_CaptchaDiv" src="<c:url value="simpleCaptcha.png"/>"/>
		<br/>
		<input type="text" class="form-control" id="cptans" name="sb.answer" value="${sb.answer}" placeholder="Captcha Image">
		
		<input type="submit" name="Submit" value="Submit">
	</form>

</body>
</html>