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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery.jqplot.js"></script>
<script type="text/javascript" src="js/jqplot.cursor.js"></script>
<script type="text/javascript" src="js/jqplot.dateAxisRenderer.js"></script>
<!--
    <script type="text/javascript" src="js/syntaxhighlighter/scripts/shCore.min.js"></script>
    <script type="text/javascript" src="js/syntaxhighlighter/scripts/shCore.min.js"></script>
    <link href="css/jquery.jqplot.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/syntaxhighlighter/scripts/shBrushJScript.min.js"></script>

    <script type="text/javascript" src="js/syntaxhighlighter/scripts/shBrushXml.min.js"></script>
    <script type="text/javascript" src="js/plugins/jqplot.highlighter.min.js"></script>
	
	-->
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
		//$(".tab_container").hide();
		//$("#loadingdiv").show();
		var frameObj = document.getElementById('searchFrame');

		frameObj.src = 'Search.do?companyName=' + companyName;

		var i = 0;
		/*var timer1=setInterval(function (){
				i++;
				if(i==1){
					$("#loadingdiv").hide();
					$(".tab_container").show();
					/*if(timer1)
						clearInterval(timer1);
				}
			},2000);
		
		if(i==1)
			clearInterval(timer1);*/

	}

	// to refresh user portfolio 

	function refreshUserPortfolio() {
		// $(".portfolio").hide();
		//$("#loadingdiv").show();
		var frameObj = document.getElementById('portfolioFrame');

		frameObj.src = 'myportfolio.do';

		var i = 0;
		/*var timer3=setInterval(function (){
				i++;
				if(i==1){
					$("#loadingdiv").hide();
					$(".portfolio").show();
					/*if(timer3)
						clearInterval(timer2);
				}
			},2000);
		
		if(i==1)
			clearInterval(timer3);*/

	}

	//after pressing the search button

	function goTo(url) {
		if (timer != null) {
			clearInterval(timer);

		}

		alert('goto : ' + $("#companyName").val());
		var name = $("#companyName").val();
		if (name == null || name == "" || name == 'Enter the company Name')
			alert('please enter the company name');
		else {
			var frameObj = document.getElementById('searchFrame');
			companyName = name;

			frameObj.src = 'Search.do?companyName=' + name;
			$(".tab_container").show();
			$(".portfolio").hide();
			timer = setInterval(refreshCompanyDetails, 60000);

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
		timer2 = setInterval(refreshUserPortfolio, 90000);

	}

	function seeGraph(compId) {

		$.ajax({
			async : true,
			url : "seegraph.do?companyId=" + compId,
			contentType : "text/html",
			type : "GET",
			dataType : "text",
			success : function(data) {
				{

					var fdata = eval(data);
					var plot1 = $.jqplot('chart1', [ fdata ], {

						title : 'Google, Inc.',
						series : [ {
							label : 'Google, Inc.',
							neighborThreshold : -1
						} ],
						axes : {
							xaxis : {
								renderer : $.jqplot.DateAxisRenderer,
								min : 'july 11, 2012 11:00:00',
								tickInterval : '10 days',
								tickOptions : {
									formatString : '%Y/%#m/%#d'
								}
							},
							yaxis : {
								tickOptions : {
									formatString : '$%.2f'
								}
							}
						},
						cursor : {
							show : true,
							zoom : true,
							showTooltip : false
						}
					});

					$('.button-reset').click(function() {
						plot1.resetZoom()
					});
				}
			}
		});
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
			type : "get",
			url : "autocomplete.do",
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
			<a class="logout_user" href="#" title="Logout">Logout</a>
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs">
				<a href="home.do">Home</a>
				<div class="breadcrumb_divider"></div>
				<input type="button" value="MyPortfolio" name="MyPortfolio"
					onclick="portfolioTo()" />
				<div class="breadcrumb_divider"></div>
			</article>
		</div>
	</section>
	<!-- end of secondary bar -->

	<aside id="sidebar" class="column">
		<!-- <form class="quick_search" method="get" action="Search.do"> -->
		<input type="text" name="companyName" id="companyName"
			value="Enter the company Name"
			onFocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
		<input type="button" name="search" id="search" value="search"
			onclick="goTo()" />
		<!-- </form> -->
		<hr />
		<div>
			<table>
				<!--  <tr>
        <td><a href="Search.do?companyName=tataConsultancyServices">TCS</a></td>
        <td><a href="Search.do?companyName=">COMS</a></td>       
        <td><a href=Search.do?companyName=infosys>INFY</a></td>
        <td><a href=Search.do?companyName=ibm>IBM</a></td> 
       </tr> -->
			</table>


		</div>



	</aside>
	<!-- end of sidebar -->

	<section id="main" class="column">
		<article class="module width_full">
			<header>
				<h3>Stats</h3>
			</header>
			<div class="module_content">
				<!--<article class="stats_graph">-->
				<div id="chart1" style="height: 140; width: 520;"></div>
				<!--</article>
				
				-->
				<article class="stats_overview">
					<!-- <img src="images/MarketInfo.jpg" width="308" background color=/> -->
				</article>
				<div class="clear"></div>
			</div>
		</article>
		<!-- end of stats article -->
		<article class="module width_3_quarter" width="1000">


			<div class="tab_container" style="display: none;" width="1000"
				height="180">
				<iframe id="searchFrame" width="1000" height="140" frameborder="0"
					src="Search.do"></iframe>


			</div>
			<!-- end of .tab_container -->
			<div class="portfolio" style="display: none;" width="100%">


				<iframe id="portfolioFrame" width="100%" height="100%"
					frameborder="0" src="myportfolio.do"></iframe>
			</div>




			<div id="loadingdiv" style="display: none;">
				<img src="images/loading.gif" background color="white" />
			</div>
		</article>
		<!-- end of content manager article -->
		<article class="module width_quarter">
			<header>
				<h3>News</h3>
			</header>
			<div class="message_list">
				<div class="module_content">
					<div class="message">
						<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus
							dolor.</p>
						<p>
							<strong>Impetus</strong>
						</p>
					</div>
					<div class="message">
						<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus
							dolor.</p>
						<p>
							<strong>TCS</strong>
						</p>
					</div>
					<div class="message">
						<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus
							dolor.</p>
						<p>
							<strong>Infosys</strong>
						</p>
					</div>
					<div class="message">
						<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus
							dolor.</p>
						<p>
							<strong>HUL</strong>
						</p>
					</div>
					<div class="message">
						<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus
							dolor.</p>
						<p>
							<strong>Tata Motors</strong>
						</p>
					</div>
				</div>
			</div>
			<footer>
				<form class="post_message">
					<input type="text" value="Message" /> <input type="submit"
						class="btn_post_message" value="" />
				</form>
			</footer>
		</article>
		<!-- end of messages article -->

		<div class="clear"></div>
		<!-- end of post new article -->

		<article class="module width_full">
			<header>
				<h3>Basic Styles</h3>
			</header>
			<div class="module_content">
				<h1></h1>
			</div>
		</article>
		<!-- end of styles article -->
		<div class="spacer"></div>
	</section>


</body>

</html>