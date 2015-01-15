<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	
	<form action="registerAct" method="post">
		<h1>Registration</h1>
		<p><tt><s:fielderror name="error"/></tt></p>
	
		<p>Last Name: <input type="text" name="sib.lastName" value="${sib.lastName}" placeholder="Last Name" required></p>
		<p>First Name: <input type="text" name="sib.firstName" value="${sib.firstName}" placeholder="First Name" required></p>
		<p>Middle Initial: <input type="text" name="sib.middleInitial" value="${sib.middleInitial}"placeholder="Middle Initial" required></p>
		
		<p>Address: <input type="text" name="sib.street" value="${sib.street}"placeholder="Street" required> <input type="text" name="sib.barangay" value="${sib.barangay}"placeholder="Barangay" required> 
		<input type="text" name="sib.city" value="${sib.city}"placeholder="City" required> <input type="text" name="sib.province" value="${sib.province}"placeholder="Province" required></p>
		
		<p>Year Level: 
			<select name="sib.yearLevel">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
			</select>
		</p>
		
		<p>Course ID: 
			<select name="sib.courseID">
				<option>BSCS-SE</option>
				<option>BS-GD</option>
				<option>BS-IT(WD)</option>
				<option>BS-BA(MA)</option>
				<option>BS-BA(FM)</option>
				<option>BS-A</option>
				<option>BA-MMA</option>
				<option>BA-FD</option>
			</select>
		</p>
		
		<p>Student ID: <input type="text" name="sib.studentID" value="${sib.studentID}"placeholder="Student ID" required></p>
		<p>Password: <input type="password" name="sib.password" placeholder="********" required></p>
		<p>Confirm Password: <input type="password" name="sib.confirmPassword" placeholder="********" required></p>
		
		<img id="cptchaimg" class="LBD_CaptchaDiv" src="<c:url value="simpleCaptcha.png"/>"/>
					<br/>
		<input type="text" class="form-control" id="cptans" name="sib.answer" placeholder="Captcha Image">
		
		<input type="submit" name="Submit" value="Submit">
	</form>
	
</body>
</html>