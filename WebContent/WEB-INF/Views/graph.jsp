<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>jQuery UI</title>
<link rel="stylesheet" href="css/jquery.ui.all.css">
<link rel="stylesheet" href="css/jquery.ui.datepicker.css">
<link rel="stylesheet" href="css/jquery.autocomplete.css"
	type="text/css" media="screen" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery.ui.core.js"></script>
<script src="js/jquery.ui.widget.js"></script>
<script src="js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="css/demos.css">
<script type="text/javascript" src="js/jquery.autocomplete.js"></script>

<script src="js/hideshow.js" type="text/javascript"></script>

<script type="text/javascript" src="js/jquery.equalHeight.js"></script>

<script type="text/javascript" src="js/jquery.jqplot.js"></script>
<script type="text/javascript" src="js/jqplot.cursor.js"></script>
<script type="text/javascript" src="js/jqplot.dateAxisRenderer.js"></script>
<script type="text/javascript" src="js/jqplot.highlighter.min.js"></script>




<script>
	var currentDate = "${currentDate}";
	$(function() {
		$("#datepicker1").datepicker();
		$("#datepicker2").datepicker();
	});

	var tickerlist;
	$(function() {
		$.ajax({
			type : "post",
			url : "autocomplete1.do",
			success : function(msg) {
				var tickers = msg;
				tickerlist = tickers.split(",");
				$("#companyName").autocomplete(tickerlist);
			}
		});
	});
</script>

<script type="text/javascript">
	var currentDate = "${currentDate}";
	var noOfDays = "${noOfDays}";
	var tickInterval1;
	var fromDate;
	var formatString1 = null;
	function dayGraph() {

		if ($("#companyName").val() != null) {
			var name = $("#companyName").val();
			if (name == null || name == ""
					|| name == "enter the company's tickersymbol")
				alert('please enter the company ticker symbol');
			else {
				var compId = document.getElementById('companyName').value;
				var fromDate = currentDate;
				var toDate = document.getElementById('datepicker2').value;
				var dayGraph = true;
				formatString1 = '%H:%M:%S';
				tickInterval1 = '59 minutes';
				seeGraph(compId, fromDate, toDate, dayGraph);
			}
		}
	}
	function seeGraph1() {

		;
		if ($("#companyName").val() != null) {
			var name = $("#companyName").val();
			var fromDate = document.getElementById('datepicker1').value;
			var toDate = document.getElementById('datepicker2').value;
			if (name == null || name == ""
					|| name == "enter the company's tickersymbol")
				alert('please enter the company ticker symbol');
			if (fromDate == null || fromDate == "" || fromDate == 'From Date')
				alert('please enter the from date');
			if (toDate == null || toDate == "" || toDate == 'To Date')
				alert('please enter the to date');

			else {
				var compId = document.getElementById('companyName').value;
				var dayGraph = false;
				tickInterval1 = noOfDays / 7;
				formatString1 = '%Y/%#m/%#d';
				seeGraph(compId, fromDate, toDate, dayGraph);
			}
		}
	}

	function seeGraph(compId, fromDate, toDate, dayGraph) {

		document.getElementById('chart1').innerHTML = "", $.ajax({
			async : false,
			url : "seegraph.do",
			data : {
				companyId : compId,
				fromDate : fromDate,
				toDate : toDate,
				dayGraph : dayGraph
			},
			contentType : "text/html",
			type : "GET",
			dataType : "text",
			success : function(data) {
				{
					if (data == "" || data == "];") {
						alert('invalid time or date or tickerSymbol');
						return;
					}

					var fdata = eval(data);
					var plot1 = $.jqplot('chart1', [ fdata ], {

						title : 'Graph of ' + compId,
						series : [ {
							label : 'Google, Inc.',
							neighborThreshold : -1
						} ],
						axes : {
							xaxis : {
								renderer : $.jqplot.DateAxisRenderer,
								min : fromDate,
								tickInterval : tickInterval1,
								tickOptions : {
									formatString : formatString1
								}
							},
							yaxis : {
								tickOptions : {
									formatString : '$%.2f'
								}
							}
						},
						highlighter : {
							show : true,
							sizeAdjust : 7.5
						},
						cursor : {
							show : true,
							zoom : true,
							showTooltip : false
						}

					});

					$('.button-reset').click(function() {
						plot1.resetZoom();
					});
				}
			}
		});
	}
</script>
</head>
<body>

	<div class="demo1">
		<tr>
			<td><i>From Date:</i><input type="text" id="datepicker1"
				value="From Date" style="width: 90px;"
				onFocus="if(!this._haschanged){this.value=''};this._haschanged=true;" />
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><i>To Date:</i><input type="text" id="datepicker2"
				value="To Date" style="width: 90px;"
				onFocus="if(!this._haschanged){this.value=''};this._haschanged=true;" />
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><i>Company Name or TickerSymbol</i><input type="text"
				name="companyName" id="companyName"
				value="enter the company's tickersymbol"
				onFocus="if(!this._haschanged){this.value=''};this._haschanged=true;" />
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><input type="button" value="IntervalGraph" id="view Graph"
				style="width: 90px;" onclick="seeGraph1()" />
			</td>

			<td><i><input type="button" value="Day Graph" id="dayGraph"
					style="width: 75px;" onclick="dayGraph()" /> </i>
			</td>
		</tr>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div id="chart1" style="height: 350px; width: 890px;"></div>




</body>
</html>
