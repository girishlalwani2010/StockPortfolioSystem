<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Form</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
</head>
<body>
	<form class="box login" action="SubmitLogin.do" method="post">
		<fieldset class="boxBody">
			<label>EmailId</label> <input type="text"
				pattern="^[a-zA-Z0-9-\_.]+@[a-zA-Z0-9-\_.]+\.[a-zA-Z0-9.]{2,5}$"
				tabindex="1" name="emailId" id="emailId" required>
			 <label><a href="ForgetPassword.do" class="rLink" tabindex="5">Forget your password?</a></label> 
			<label>Password</label> 
			<input type="password" tabindex="2" name="password" id="password"
				required>
		</fieldset>
		<footer> <label><input type="submit" class="btnLogin" value="Login"
			tabindex="4"> </footer>
		<fieldset>
			<tr>
				<td>${errMsg}</td>
			</tr>
		</fieldset>
	</form>
	<footer id="main"> </footer>
</body>
</html>