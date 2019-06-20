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
<title>Sector Research Report - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
</head>
<body id="sector-research">
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
									<h3>Sector Type</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div class="widget_search">
										<input type="text" id="search_sector_type" onkeyup="getSectorTypeByName()" placeholder="Search sector type" />
									</div>
									<div id="search_by_sector_type">
										<ul id="search_by_sector_type_ul">
											
										</ul>
									</div>
								</div>
							</div>

							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Sector SubType</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div class="widget_search">
										<input type="text" id="search_sector_subtype" onkeyup="getSectorSubTypeByName()" placeholder="Search sector subtype" />
									</div>
									<div id="search_by_sector_subtype">
										<ul id="search_by_sector_subtype_ul">
											
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
									<h3>Researched By</h3>
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
									<h3>Report Tone</h3>
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
									<h3>Report Frequency</h3>
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
							<div class="broker_content">
								<table id='broker_table'>
									<thead>
										<tr>
											<th style="width:30%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="sectorSubtype">Sector SubType <i class="fa fa-sort"></i></a>
												</p>
												<p class="small_font">
													Sector Type
												</p>
											</th>
											<th style="width:20%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="researchedBy">Researched By <i class="fa fa-sort"></i></a>
												</p>
												<p class="small_font">
													Analyst Type
												</p>
											</th>
											<th style="width:25%;">
												<p class="large_font" style="font-size: 16px;">
													Report Tone
												</p>
												<p class="small_font">
													Report Frequency
												</p>
											</th>
											<th style="width:25%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="reportName">Report <i class="fa fa-sort"></i></a>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sector-research.js"></script>
    

    <script type="text/javascript">
    	$("#sidebar-panel .widget-panel-heading").on('click', function(e) {
    		$(e.currentTarget).parent().find('.widget-panel-body').slideToggle();
    	});

    	

    	function getSectorTypeByName() {
    		var input, filter, ul, li, span, i;
		    input = document.getElementById("search_sector_type");
		    filter = input.value.toUpperCase();
		    ul = document.getElementById("search_by_sector_type_ul");
		    
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

    	function getSectorSubTypeByName() {
    		var input, filter, ul, li, span, i;
		    input = document.getElementById("search_sector_subtype");
		    filter = input.value.toUpperCase();
		    ul = document.getElementById("search_by_sector_subtype_ul");
		    
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