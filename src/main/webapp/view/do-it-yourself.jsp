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
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-md-9">
					<div class="content-panel">
						<div id="fv_custom_screener_search" class="section">
							<div class="max_per_page">
								<span>Records Per Page </span>
								<select>
									<option value='5'>5</option>
									<option value='10' selected>10</option>
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
													<a href="javascript:void(0)" data-id="company_name">
														Company <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="industry">
														Industry <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="mcap">
														Market Capitalisation <i class="fa fa-sort"></i>
													</a>
												</p>
											</th>
											<th style="width:20%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="pe">
														P/E <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="pb">
														P/BV <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="divYield">
														Dividend Yield <i class="fa fa-sort"></i>
													</a>
												</p>
											</th>
											<th style="width:15%;">
												<p class="large_font" style="font-size: 16px;">
													<a href="javascript:void(0)" data-id="operatingProfitMargin">
														Operating Profit Margin <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="debtToEquityRatio">
														D/E <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="currentRatio">
														Current Ratio <i class="fa fa-sort"></i>
													</a>
												</p>
											</th>
											<th style="width:15%;">

												<p class="large_font">
													<a href="javascript:void(0)" data-id="roeInPercentage">
														ROE(Avg 3Y) <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="returnOnAssetInPercentage">
														ROA <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="rotcInPercentage">
														ROTC <i class="fa fa-sort"></i>
													</a>
												</p>
											</th>
											<th style="width:15%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="epsGrowthInPercentage">
														EPS Growth(Avg 3Y) <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="patGrowthInPercentage">
														PAT Growth(Avg 3Y) <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="revenueGrowthInPercentage">
														Revenue Growth(Avg 3Y) <i class="fa fa-sort"></i>
													</a>
												</p>
											</th>
											<th style="width:15%;">
												<p class="large_font">
													<a href="javascript:void(0)" data-id="netOperatingCashFlow">
														Net OP Cash Flow <i class="fa fa-sort"></i>
													</a>
												</p>
												<p class="small_font">
													<a href="javascript:void(0)" data-id="totalFreeCashFlow">
														Total Free Cash Flow <i class="fa fa-sort"></i>
													</a>
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
    <script type='text/javascript'>
    </script>

	<script
	src=" http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>


	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/10.6.1/css/bootstrap-slider.min.css">
	
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/10.6.1/bootstrap-slider.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/do_it_yourself.js"></script>

</body>
</html>