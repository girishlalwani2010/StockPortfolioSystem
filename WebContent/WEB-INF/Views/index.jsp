<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8" />
<title>Stock Portfolio System</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="css/layout.css" type="text/css"
	media="screen" />
<link rel="stylesheet" href="css/jquery.autocomplete.css"
	type="text/css" media="screen" />
<!--[if lt IE 9]>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen" />
	
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/hideshow.js" type="text/javascript"></script>
<script src="js/jquery.tablesorter.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.equalHeight.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery.jqplot.js"></script>
<script type="text/javascript" src="js/jqplot.cursor.js"></script>
<script type="text/javascript" src="js/jqplot.dateAxisRenderer.js"></script>


<script type="text/javascript" src="js/jquery.autocomplete.js"></script>

<script type="text/javascript">
<!--
	var timer1 = null;
	var timer = null;
	var companyName = "";
	var timer2 = null;
	var timer3 = null;
	// for refreshing company details
	function refreshCompanyDetails() {

		if (companyName != null && companyName != "") {

			var frameObj = document.getElementById('searchFrame');

			frameObj.src = 'Search.do?companyName=' + companyName;

			var i = 0;

		}

	}

	// to refresh user portfolio 

	function refreshUserPortfolio() {

		var frameObj = document.getElementById('portfolioFrame');

		frameObj.src = 'myportfolio.do';

		var i = 0;

	}

	//after pressing the search button

	function goTo(url) {
		if (timer != null) {
			clearInterval(timer);

		}

		if ($("#companyName").val() != null) {

			var name = $("#companyName").val();
			if (name == null || name == "" || name == 'Enter the company Name')
				alert('please enter the company name');
			else {

				var frameObj = document.getElementById('searchFrame');
				companyName = name;

				frameObj.src = 'Search.do?companyName=' + name;
				$(".tab_container").show();
				$(".portfolio").hide();
				$(".chart1").hide();
				$(".graph").hide();
				$(".liveGraph").hide();
				timer = setInterval(refreshCompanyDetails, 600000);

			}
		}
	}

	// after clicking on my portfolio button
	function portfolioTo(url) {
		if (timer2 != null) {
			clearInterval(timer2);

		}

		var frameObj = document.getElementById('portfolioFrame');

		frameObj.src = 'myportfolio.do';
		$(".portfolio").show();
		$(".tab_container").hide();
		$(".chart1").hide();
		$(".graph").hide();
		$(".liveGraph").hide();
		timer2 = setInterval(refreshUserPortfolio, 600000);

	}

	function graphTo(url) {
		var frameObj = document.getElementById('infoGraphFrame');
		frameObj.src = 'graph.do';
		$(".graph").show();
		$(".portfolio").hide();
		$(".tab_container").hide();
		$(".liveGraph").hide();
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".tablesorter").tablesorter();
	});
	$(document).ready(function() {

		//When page loads...
		$(".tab_content").hide(); //Hide all content
		$("ul.tabs li:first").addClass("active").show(); //Activate first tab
		$(".tab_content:first").show(); //Show first tab content

		//On Click Event
		$("ul.tabs li").click(function() {

			$("ul.tabs li").removeClass("active"); //Remove any "active" class
			$(this).addClass("active"); //Add "active" class to selected tab
			$(".tab_content").hide(); //Hide all tab content

			var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
			$(activeTab).fadeIn(); //Fade in the active ID content
			return false;
		});

	});
</script>
<script type="text/javascript">
	$(function() {
		$('.column').equalHeight();
	});

	var tickerlist;
	$(function() {
		$.ajax({
			type : "post",
			url : "autocomplete.do",
			async : true,
			success : function(msg) {

				var tickers = msg;
				tickerlist = tickers.split(",");

				$("#companyName").autocomplete(tickerlist);
			}
		});
	});
</script>

</head>


<body>

	<header id="header">
		<hgroup>
			<h1 class="site_title">

				<label style="color: red; font-weight: bold;"> <c:choose>
						<c:when test="${sessionScope.emailStr!=null}">
							<%-- Welcome<c:out value="${sessionScope.emailStr}"/> --%>
						</c:when>
						<c:otherwise>
			${errMsg}
			</c:otherwise>
					</c:choose> </label>

			</h1>
			<h2 class="section_title">STOCK PORTFOLIO SYSTEM</h2>
			<c:if test="${sessionScope.emailStr==null}">
				<div>
					<div class="btn_view_site">
						<a href="Login.do">Login</a>
					</div>
				</div>
				<div class="btn_view_site">
					<a href="Registration.do">Sign Up</a>
				</div>
			</c:if>

		</hgroup>
	</header>
	<!-- end of header bar -->

	<section id="secondary_bar">
		<div class="user">
			<p>${sessionScope.emailStr}</p>
			<c:if test="${sessionScope.emailStr!=null}">
				<a class="logout_user" href="Logout.do" title="Logout">Logout</a>
			</c:if>
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs">
				<input type="button" value="Home" name="Home"
					onclick="window.location='home.do';">
				<!--   <div class="breadcrumb_divider"></div> -->
				<c:if test="${sessionScope.emailStr!=null}">
					<input type="button" value="MyPortfolio" name="MyPortfolio"
						onclick="portfolioTo()" />
					<!-- <div class="breadcrumb_divider"></div> -->
				</c:if>
				<c:if test="${sessionScope.emailStr!=null}">
					<input type="button" value="UpdateProfile" name="UpdatePortfolio"
						onclick="window.location='updateprofile.do';" />
				</c:if>
				<input type="button" value="Graph" name="Graph" onclick="graphTo()">
			</article>
		</div>
	</section>
	<!-- end of secondary bar -->

	<aside id="sidebar" class="column">
		<!-- <form class="quick_search" method="get" action="Search.do"> -->
		<table><tr><td><input type="text" name="companyName" id="companyName"
			value="Enter the company Name"
			onFocus="if(!this._haschanged){this.value=''};this._haschanged=true;"
			style="width: 100%;" /></td><td><input type="button" name="search" id="search"
			value="search" onclick="goTo()" /></td></tr></table><br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<article class="module width_quarter">

			<header>
				<h3>Recent Market Updates</h3>
			</header>
			<div class="message_list">
				<div class="module_content">
					<jsp:include page="rssFeeds.jsp" />

				</div>
			</div>
			<footer> </footer>
		</article>
		<!-- </form> -->
		<hr />
		<div></div>



	</aside>
	<!-- end of sidebar -->

	<section id="main" class="column">
		<article class="module width_full" border=0>
			<header>
				<h3>Stats</h3>
			</header>
			<div class="module_content">
				<!--<article class="stats_graph">-->
				<!-- 
				<div class="graph" style="display: none;">
					<iframe id="infoGraphFrame" width="100%" height="300"
						src="graph.do"></iframe>
				</div>
 -->
				<article class="module width_3_quarter" border=0>
					<div id="liveGraph" class="liveGraph">
						<table>
							<tr>
								<td><img width="400" height="150" border="0" align="left"
									src="http://marketgraphs.economictimes.indiatimes.com/charting/IndexPriceSummary1.aspx?indexid=2365&amp;page=home&amp;width=305&amp;height=100&amp;xaxis=true&amp;yaxis=true"
									alt="Graph" id="marketgrpah">
								</td>
								<td><img width="400" height="150" border="0" align="left"
									src="http://marketgraphs.economictimes.indiatimes.com/charting/IndexPriceSummary1.aspx?indexid=2369&amp;page=home&amp;width=305&amp;height=100&amp;xaxis=true&amp;yaxis=true"
									alt="Graph" id="marketgrpah">
								</td>
							</tr>

							<tr>
								<td align="center" style="width: 400px;"><i>SENSEX</i>
								</td>
								<td align="center" style="width: 400px;"><i>NIFTY</i>
								</td>
							</tr>
						</table>
					</div>
					<div class="graph" style="display: none;">
						<iframe id="infoGraphFrame" width="104%" height="460"
							frameborder="0" src="graph.do"></iframe>
					</div>

					<div class="tab_container" style="display: none;" width="100%"
						height="180">
						<iframe id="searchFrame" width="100%" height="280" frameborder="0"
							src=""></iframe>



					</div>
					<!-- end of .tab_container -->
					<div class="portfolio" style="display: none;" width="100%">


						<iframe id="portfolioFrame" width="100%" height="300"
							frameborder="0" src=""></iframe>
					</div>


					<div id="loadingdiv" style="display: none;">
						<img src="images/loading.gif" background color="white" />
					</div>
				</article>
				<!-- end of content manager article -->



				<!-- end of messages article -->

				<div class="clear"></div>
				<!-- end of post new article -->

				<!-- 	<article class="module width_full">
					<header>
						<h3>Basic Styles</h3>
					</header>
					<div class="module_content">
						<h1></h1>
					</div>
				</article> -->
				<!-- end of styles article -->
				<div class="spacer"></div>
	</section>


</body>

</html>