<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>

<script type="text/javascript" src="js/gfeedfetcher.js"></script>

<script type="text/javascript" src="js/gajaxticker.js">
	/***********************************************
	 * gAjax RSS Ticker- (c) Dynamic Drive (www.dynamicdrive.com)
	 * Requires "gfeedfetcher.js" class
	 * This notice MUST stay intact for legal use
	 * Visit http://www.dynamicdrive.com/ for full source code
	 ***********************************************/
</script>

<style type="text/css">
.titlefield { /*CSS for RSS title link in general*/
	color: gray;
	font-size: 90%;
}

.labelfield { /*CSS for label field in general*/
	color: gray;
	font-size: 90%;
}

.datefield { /*CSS for date field in general*/
	color: gray;
	font-size: 90%;
}

#example2 { /*Demo 2 main container*/
	/* border: 1px dashed black; */
	padding: 4px;
	background-color: #FFFFFF;
}

#example2 ul { /*Demo 2 UL container*/
	margin: 0;
	padding-left: 18px;
}

#example2 ul li { /*Demo 2 LI that surrounds each entry*/
	margin-bottom: 4px;
}
</style>
</head>
<body>





	<script type="text/javascript">
		var socialfeed = new gfeedrssticker("example2", "example2class", 3000,
				"")
		socialfeed.addFeed("Slashdot",
				"http://ibnlive.in.com/ibnrss/rss/business/business.xml")
		socialfeed.displayoptions("label datetime")
		//show the specified additional fields
		socialfeed.setentrycontainer("li")
		//Display each entry as a DIV
		socialfeed.filterfeed(25, "label")
		//Show 15 entries, sort by label
		socialfeed.entries_per_page(5)
		socialfeed.init() //Always call this last
	</script>


</body>
</html>
