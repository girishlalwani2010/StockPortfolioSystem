<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" href="css/layout.css" type="text/css"
	media="screen" />
<title>Insert title here</title>
<script type="text/javascript">
function CheckDecimal(evt,inputObj)  
{  
 var inputtxt = String.fromCharCode(evt.which);
 if(inputtxt == '\b')
	 return;
 
 var numbers =/^[+-]?\d+?$/;  
 if(inputtxt.match(numbers))   
  {  
   return true;  
  }  
 else  
  {  
  alert('please enter the valid percentage format');
  inputObj.value = "";
  return false;   
  }  
}  


</script>

</head>
<body>

	<header>
	<h3 class="tabs_involved">
		<i>&nbsp;&nbsp;User Portfolio</i>
	</h3>

	</header>

	<div id="tab2" class="tab_content" height="400">
		<table class="tablesorter" cellspacing="0">
			<thead>
				<tr>


					<th>CompanyID</th>
					<th>TrackedPrice</th>
					<th>CurrentPrice</th>
					<th>SetPercentageVariation</th>


				</tr>
			</thead>
			<tbody>
				<c:forEach items="${portfolio}" var="temp" varStatus="status">

					<form action="alert.do" method="post">
						<tr>
							<td>${temp.portfolioCompoundKey.companyId}</td>
							<td>${temp.trackedPrice}</td>
							<td>${prices[status.count -1]}</td>

							<td><input type="text" name="PercentageVariation"
								id="PercentageVariation"
								value="${fn:substring((temp.percentageChange / temp.trackedPrice) * 100,0,5)}"
								onKeydown="CheckDecimal(event,this);"></td>
							<td><input type="hidden" id="companyId" name="companyId"
								value="${temp.portfolioCompoundKey.companyId}" /> <input
								type="hidden" id="emailStr" name="emailStr" value=${emailStr } />
								<input type="hidden" id="trackedPrice" name="trackedPrice"
								value="${temp.trackedPrice}" />
							</td>
							<td><a
								href="deleteFromPortfolio.do?emailId=${emailStr}&companyId=${temp.portfolioCompoundKey.companyId}">delete</a>
							</td>
							<td><a
								href="removeAlert.do?companyId=${temp.portfolioCompoundKey.companyId}">removeAlert</a>
							</td>
							<td><input type="submit" value="setAlert" />
							</td>
						</tr>

					</form>

				</c:forEach>





			</tbody>
		</table>
	</div>


</body>
</html>