<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/layout.css" type="text/css" media="screen" />
<script src="js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">

function  addToPortfolio(companyName,currentPrice)
   { 
	   $.ajax({
	 		async: true,
	 		url : "portfolio.do?companyName="+companyName+"&currentPrice="+currentPrice, 
	 		contentType: "text/html",
	 		type : "GET",
	 		dataType : "text",		
	 		success: function(data){
	 		 
	 			alert(data);
	 		}});
	   
   }
   
 </script>
</head>
<body>

         <header><h3 class="tabs_involved"><i>&nbsp;&nbsp;Company Details</i></h3>
		<table>
   			<td><li><a href="javascript:parent.goTo();"><i>see details</i></a></li></td>
    		<%-- <td><li><a href="javascript:parent.seeGraph('${companyDetails.companyId}');"><i>see graph</i></a></li></td> --%>
            </table>	
		</header>
       
			<div id="tab1" class="tab_content" height="140">



<table class="tablesorter" cellspacing="0"> 
			<thead> 
				<tr> 
   					
    			 
    				<th>Company ID</th> 
    				<th>Company Name</th>
    				
    				<th>Current Price</th> 
    				<th>Total Shares</th> 
				    <th></th>
				</tr> 
			</thead> 
			<tbody> 
				<tr> 
   				
    				<td>${companyDetails.companyId}</td> 
    				<td>${companyDetails.companyName}</td> 
    				
    				<td>${currentPrice}</td>
    				<td>${companyDetails.totalShares}</td>
    			
    				<c:if test="${not empty sessionScope.emailStr}">
				    <td><input type="button" value="add To Portfolio" name="add To Portfolio" onclick="addToPortfolio('${companyDetails.companyName}','${currentPrice}');"></td>
				   </c:if>
				</tr> 
			
			</tbody> 
			</table>

</div>



</body>
</html>