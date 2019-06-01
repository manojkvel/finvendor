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
<title>Do It Yourself - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

	<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"  media="screen">
	<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

	<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<section>
		<div class="container-fluid" id="do_it_yourself">
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
									<h3>Market Capitalisation</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div id="search_by_mcap" class="ui-content">
									      <div data-role="rangeslider">
									        <label for="price-min">Price:</label>
									        <input type="range" name="price-min" id="price-min" value="200" min="0" max="1000">
									        <label for="price-max">Price:</label>
									        <input type="range" name="price-max" id="price-max" value="800" min="0" max="1000">
									      </div>
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
											<th style="width:20%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="company">Company <i class="fa fa-sort"></i></a>
												</p>
											</th>
											<th style="width:20%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="broker">Broker <i class="fa fa-sort"></i></a>
												</p>
											</th>
											<th style="width:15%;">
												<p class="large_font" style="font-size: 16px;">
													Broker Rank
												</p>
											</th>
											<th style="width:15%;">

												<p class="large_font">
													<a href="javascript:void(0)" data-id="cmp">CMP <i class="fa fa-sort"></i></a>
												</p>
											</th>
											<th style="width:15%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="recommType" title="Recommendation Type">Recomm Type <i class="fa fa-sort"></i></a>
												</p>
											</th>
											<th style="width:15%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="report">Report <i class="fa fa-sort"></i></a>
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


    </script>

    <script type="text/javascript">
    	/*$("#sidebar-panel .widget-panel-heading").on('click', function(e) {
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
    	}*/
    </script>
</body>
</html>