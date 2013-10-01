<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registration Form</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta charset="UTF-8" />

<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
<script type="text/javascript">
	function matchPassword() {

		var password = document.getElementById("password");
		var confirmpassword = document.getElementById("confirmPassword");
		/* Your validation code. */

		if (password.value != confirmpassword.value) {
			alert('Your password and confirm password do not match. Please type carefully.');
			return false;
		}
	}
	$(function() {

		alert('${registerationError}');
	});
</script>




</head>

<body>
	<form class="box login" action="saveUpdateProfile.do" method="post">
		<fieldset class="boxBody">

			<label>EmailId&nbsp;&nbsp;&nbsp;&nbsp;${registerationError}</label> <input
				type="text"
				pattern="^[a-zA-Z0-9-\_.]+@[a-zA-Z0-9-\_.]+\.[a-zA-Z0-9.]{2,5}$"
				onChange="this.setCustomValidity(this.validity.patternMismatch ? 'please enter the correct email Id' : '');
      if(this.checkValidity()) form.emailId.pattern = this.value;"
				tabindex="1" name="emailId" id="emailId"
				value="${sessionScope.emailStr}" readonly="readonly" /> <label>FirstName</label>
			<input type="text" tabindex="1" max="20" pattern="^[a-zA-Z]{3,20}$"
				onChange="this.setCustomValidity(this.validity.patternMismatch ? 'please enter the alphabets from 3 to 20' : '');
      if(this.checkValidity()) form.firstName.pattern = this.value;"
				name="firstName" id="firstName" /> <label>LastName</label> <input
				type="text" tabindex="1" pattern="^[a-zA-Z]{3,20}$"
				onChange="this.setCustomValidity(this.validity.patternMismatch ? 'please enter the alphabets from 3 to 20' : '');
      if(this.checkValidity()) form.lastName.pattern = this.value;"
				name="lastName" id="lastName" /> <label>Password<i></i> </label> <input
				type="password" tabindex="1" pattern="^[a-zA-Z]{3,20}$"
				onChange="this.setCustomValidity(this.validity.patternMismatch ? 'please enter the alphabets from 3 to 20' : '');
      if(this.checkValidity()) form.password.pattern = this.value;"
				name="password" id="password" /> <label>ConfirmPassword</label> <input
				type="password" tabindex="2" name="confirmPassword"
				id="confirmPassword" onblur="matchPassword()" /> <label>City

			</label> <input type="text" id="city" tabindex="2" pattern="^[a-zA-Z]{3,20}$"
				onChange="this.setCustomValidity(this.validity.patternMismatch ? 'please enter the alphabets from 3 to 20' : '');
      if(this.checkValidity()) form.city.pattern = this.value;" />

		</fieldset>
		<footer> <input type="submit" class="btnRegistration"
			value="Update Profile" tabindex="4"> <%--  <fieldset>
      <tr>
      <td>
       ${registerationError}      
      </td>
      </tr>
      </fieldset> --%> </footer>
	</form>

</body>
</html>