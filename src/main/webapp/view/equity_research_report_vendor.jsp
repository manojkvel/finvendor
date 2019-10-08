<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0"
	prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans"
	var="requestConstants" />
<html>
<head>
<title>Equity Research Report Vendor - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<section>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-md-3">
					<div id="sidebar-panel">
						<div class="sidebar-heading">
							<h2>Filters</h2>
							<span>Reset filters</span>
						</div>
						<div class='widget-group'>
							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Geography</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div id="search_by_geo">
										<ul>
											
										</ul>
									</div>
								</div>
							</div>
							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Market Capitalisation</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div id="search_by_marketcapital">
										<ul>
											
										</ul>
									</div>
								</div>
							</div>
							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Style</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div id="search_by_style">
										<ul>
											
										</ul>
									</div>
								</div>
							</div>
							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Industry</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div id="search_by_industry">
										<ul>
											
										</ul>
									</div>
								</div>
							</div>
							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Analyst Type</h3>
									<span class="fa fa-chevron-down"></span>
								</div>

								<div class='widget-panel-body'>
									<div id="search_by_analystType">
										<ul>
											
										</ul>
									</div>
								</div>
							</div>
							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Research Broker/Analyst</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div class="widget_search">
										<input type="text" id="search_broker" onkeyup="getBrokerListByName()" placeholder="Search broker/analyst" />
									</div>
									<div id="search_by_researchbroker">
										<ul id="search_by_researchbroker_ul">
											
										</ul>
									</div>
								</div>
							</div>
						    <div class='widget widget-panel-default'>
						    	<div class='widget-panel-heading'>
						    		<h3>Research Date</h3>
						    		<span class="fa fa-chevron-down"></span>
						    	</div>
						    	<div class='widget-panel-body'>
						    		<div id="search_by_researchDate">
										<ul>
											
										</ul>
									</div>
						    	</div>
						    </div>
						    <div class='widget widget-panel-default'>
						    	<div class='widget-panel-heading'>
						    		<h3>Stock Return <!--<a href="javascript:void(0);" data-toggle='tooltip' title='This filter option works with "M-Cap". e.g. If you selected M-Cap as "Large Cap" and further if you select broker rank filter as "5 star", then you will get results for those Brokers having "5 star" rank in "Large Cap" equity research category.'>(?)</a>--></h3>
						    		<span class="fa fa-chevron-down"></span>
						    	</div>
						    	<div class='widget-panel-body'>
						    		<div id="search_by_broker_rank">
										<ul>
											
										</ul>
									</div>
						    	</div>
						    </div>

							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Recommendation Type</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div id="search_by_recommType">
										<ul>
											
										</ul>
									</div>
								</div>
							</div>

							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Upside Potential (%)</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div id="search_by_upside">
										<ul>
											
										</ul>
									</div>
								</div>
							</div>

						    <div class='widget widget-panel-default'>
						    	<div class='widget-panel-heading'>
						    		<h3>Others</h3>
						    		<span class="fa fa-chevron-down"></span>
						    	</div>
						    	<div class='widget-panel-body'>
						    		<div id="search_by_others">
										<ul>
											
										</ul>
									</div>
						    	</div>
						    </div>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-md-9">
					<div class="content-panel">
						<div id="fv_equity_research_report_vendor_search" class="section">
							<div class="page_header">
								<div class="col-xs-12 col-md-7">
									<div class="title">
										<h5>Research Analyst's Recommendations</h5>
										<p>Screen the stocks based on reputed brokers recommendations.</p>
									</div>
								</div>
								<div class="col-xs-12 col-md-5">
									<div class="max_per_page">
										<span>Records Per Page </span>
										<select>
											<option value='5'>5</option>
											<option value='10'>10</option>
											<option value='30'>30</option>
											<option value='50'>50</option>
											<option value='100'>100</option>
										</select>

										<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
									</div>
								</div>
							</div>
							<div class="broker_content">
								<table id='broker_table'>
									<thead>
										<tr>
											<th style="width:20%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="company">Company <i class="fa fa-sort"></i></a>
												</p>
												<p class="small_font">
													Style
												</p>
												<p class="small_font">
													Market Capitalisation
												</p>
												<p class="small_font">
													Industry
												</p>
											</th>
											<th style="width:20%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="broker">Broker <i class="fa fa-sort"></i></a>
												</p>
												<p class="small_font">
													Since
												</p>
												<p class="small_font">
													Awarded
												</p>
												<p class="small_font">
													Researched by CFA
												</p>
											</th>
											<th style="width:15%;">
												<p class="large_font" style="font-size: 16px;">
													Broker Rank
												</p>
												<p class="small_font">
													Large Cap
												</p>
												<p class="small_font">
													Mid Cap
												</p>
												<p class="small_font">
													Small Cap
												</p>
											</th>
											<th style="width:15%;">

												<p class="large_font">
													<a href="javascript:void(0)" data-id="cmp">CMP <i class="fa fa-sort"></i></a>
												</p>
												<p class="small_font">
													Price Date
												</p>
												<p class="small_font">
													P/E
												</p>
												<p class="small_font">
													3 YR EPS Growth
												</p>
											</th>
											<th style="width:15%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="recommType" title="Recommendation Type">Recomm Type <i class="fa fa-sort"></i></a>
												</p>
												<p class="small_font">
													Target Price
												</p>
												<p class="small_font">
													Price@Recomm
												</p>
												<p class="small_font">
													Upside Potential
												</p>
											</th>
											<th style="width:15%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="report">Report <i class="fa fa-sort"></i></a>
												</p>
												<p class="small_font">
													Research Date
												</p>
												<p class="small_font">
													Analyst Name
												</p>
											</th>
										</tr>
									</thead>
									<tbody>	
									</tbody>
								</table>
							</div>
							<div id='progressLoader'>
								<jsp:include page="common/progressLoader.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
	   <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/equitysearch.js"></script>
    <script type='text/javascript'>
    	$('#myofferings1').on('click', function() {
    		$('#tab1').show();
    		$('#tab2').hide();
    	});

    	$('#myofferings2').on('click', function() {
    		$('#tab2').show();
    		$('#tab1').hide();
    	});


		$("#fv_equity_research_report_vendor .action_btn .save").on("click", function() {
			$('#fv_equity_research_report_vendor_search').show();
			$("#fv_equity_research_report_vendor .header p").slideToggle();
			$("#fv_equity_research_report_vendor .body").slideToggle();
			$("#fv_equity_research_report_vendor .header h3 span").toggleClass("fa-chevron-up");
			$("#fv_equity_research_report_vendor .header h3 span").toggleClass("fa-chevron-down");
		});

		$("#fv_equity_research_report_vendor .header p").on("click", function() {
			if($('#fv_equity_research_report_vendor_search').is(':visible')) {
				$("#fv_equity_research_report_vendor .header p").slideToggle();
				$("#fv_equity_research_report_vendor .body").slideToggle();
				$("#fv_equity_research_report_vendor .header h3 span").toggleClass("fa-chevron-up");
				$("#fv_equity_research_report_vendor .header h3 span").toggleClass("fa-chevron-down");
			}
		});

    </script>

    <script type="text/javascript">
    	$("#sidebar-panel .widget-panel-heading").on('click', function(e) {
    		$(e.currentTarget).parent().find('.widget-panel-body').slideToggle();
    	});

    	function getBrokerListByName() {
    		var input, filter, ul, li, span, i;
		    input = document.getElementById("search_broker");
		    filter = input.value.toUpperCase();
		    ul = document.getElementById("search_by_researchbroker_ul");
		    
		    li = ul.getElementsByTagName("li");
		    for (i = 0; i < li.length; i++) {
		        span = li[i].getElementsByTagName("span")[0];
		        if (span.innerHTML.toUpperCase().indexOf(filter) > -1) {
		            li[i].style.display = "";
		        } else {
		            li[i].style.display = "none";

		        }
		    }
    	}
    </script>
</body>
</html>