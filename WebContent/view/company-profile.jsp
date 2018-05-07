<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<c:set var="researchreport" value="${researchreport}"></c:set>
<!DOCTYPE html>
<head>
	<title>Research Company Profile</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
</head>
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-md-9">
				<div id="company_profile">
					<div id='progressLoader'>
						<jsp:include page="common/progressLoader.jsp"></jsp:include>
					</div>
					<div class="container-fluid">
						<div class="col-xs-12 col-sm-7">
							<div class="profile_details company_details">
								<span class='c_name'>Company Name</span>
								<span class='ind_name'>Industry</span>
								<span class='mcap_name'>M-Cap Type</span>
							</div>
							<div class="profile_details company_details">
								<span class='last_cmp'>CMP (Last change)</span>
							</div>
						</div>
						<div class="col-xs-12 col-sm-5">
							<div class="profile_details func_details">
								<span><a href="#">Add to Watchlist</a></span>
								<span><a href="#">Set Price Alert</a></span>
							</div>
						</div>
					</div>


					<div class="container-fluid score_func_details">
						<div class="col-xs-12 col-sm-12">
							<div class="col-xs-12 col-sm-3">
								<div class="score_block success">
									<span>Valuation Score</span>
									<p>9 / 10</p>
								</div>
							</div>
							<div class="col-xs-12 col-sm-3">
								<div class="score_block danger">
									<span>Earnings Score</span>
									<p>3 / 10</p>
								</div>
							</div>
							<div class="col-xs-12 col-sm-3">
								<div class="score_block warning">
									<span >Future Outlook Score</span>
									<p>5 / 10</p>
								</div>
							</div>
							<div class="col-xs-12 col-sm-3">
								<div class="score_block danger">
									<span class="danger">Founder Expertise Score</span>
									<p>2 / 10</p>
								</div>
							</div>
						</div>
					</div>

					<div class="container-fluid">
						<div class="col-xs-12">
							<div class="subheader">
								<nav>
									<ul class="nav nav-tabs">
										<li class="active"><a href="javascript:void(0);" data-tab="summary_content">Summary</a></li>
										<li>
											<a href="javascript:void(0);" data-tab="research_report_content">Research Reports</a>
										</li>
										<li><a href="javascript:void(0);" data-tab="technical_content">Technical</a></li>
										<li><a href="javascript:void(0);" data-tab="calendar_content">Calendar</a></li>
										<li><a href="javascript:void(0);" data-tab="shareholding_content">Shareholding</a></li>
									</ul>
								</nav>
							</div>
							<div class="subheader_content">
								<div id="summary_content">
									<p class="summary">To be available soon</p>
								</div>
								<div id="research_report_content">
									<div class="row">
										<div class="col-xs-12 col-sm-12" style="margin-top: 20px;">
											<div class="col-xs-12 col-sm-4">
												<div class="research_report_score_block">
													<span>Total Buy Recomm.</span>
													<p>
														<span class='success'  data-toggle='tooltip' title='10'>10</span>
													</p>
												</div>
											</div>
											<div class="col-xs-12 col-sm-4">
												<div class="research_report_score_block">
													<span>Total Neutral Recomm.</span>
													<p>
														<span class='warning'  data-toggle='tooltip' title='10'>10</span>
													</p>
												</div>
											</div>
											<div class="col-xs-12 col-sm-4">
												<div class="research_report_score_block">
													<span >Total Sell Recomm.</span>
													<p>
														<span class='danger'  data-toggle='tooltip' title='10'>10</span>
													</p>
												</div>
											</div>
										</div>

										<div class="col-xs-12 col-sm-12" style="margin-top: 20px;">
											<div class="col-xs-12 col-sm-6">
												<div class="research_report_score_block success">
													<span>Average Target Price</span>
													<p>2200</p>
												</div>
											</div>
											<div class="col-xs-12 col-sm-6">
												<div class="research_report_score_block danger">
													<span>No. of Analyst Report</span>
													<p>1</p>
												</div>
											</div>
										</div>
									</div>
									<div class="max_per_page">
										<span>Records Per Page </span>
										<select>
											<option value='5'>5</option>
											<option value='10' selected="selected">10</option>
											<option value='30'>30</option>
											<option value='50'>50</option>
											<option value='100'>100</option>
										</select>

										<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
									</div>
									<table id='broker_table'>
										<thead>
											<tr>
												<th style="width:20%;">
													<p class="large_font">
														<a href="javascript:void(0)" data-id="company">Company <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="style">Style <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="mcap">M-Cap <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="sector">Sector <i class="fa fa-sort"></i></a>
													</p>
												</th>
												<th style="width:20%;">
													<p class="large_font">
														<a href="javascript:void(0)" data-id="broker">Broker <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="since">Since <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="awarded">Awarded <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="researchedByCfa">Researched by CFA <i class="fa fa-sort"></i></a>
													</p>
												</th>
												<th style="width:15%;">
													<p class="large_font">
														<a href="javascript:void(0)">Broker Rank</a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="brokerRank">Large Cap <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="brokerRank">Mid Cap <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="brokerRank">Small Cap <i class="fa fa-sort"></i></a>
													</p>
												</th>
												<th style="width:15%;">

													<p class="large_font">
														<a href="javascript:void(0)" data-id="cmp">CMP <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="priceDate">Price Date <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="pe">P/E <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="_3YrPatGrowth">3 YR PAT Growth <i class="fa fa-sort"></i></a>
													</p>
												</th>
												<th style="width:15%;">
													<p class="large_font">
														<a href="javascript:void(0)" data-id="recommType">Recom. Type <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="targetPrice">Tgt Price <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="priceAtRecomm">Price@Recomm <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="upside">Upside <i class="fa fa-sort"></i></a>
													</p>
												</th>
												<th style="width:15%;">
													<p class="large_font">
														<a href="javascript:void(0)" data-id="report">Report <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="researchDate">Research Date <i class="fa fa-sort"></i></a>
													</p>
													<p class="small_font">
														<a href="javascript:void(0)" data-id="analystName">Analyst Name <i class="fa fa-sort"></i></a>
													</p>
												</th>
											</tr>
										</thead>
										<tbody>	
										</tbody>
									</table>
								</div>
								<div id="technical_content">
								<div>
									To be available soon</div>
								</div>
								<div id="calendar_content">
									To be available soon
								</div>
								<div id="shareholding_content">
									To be available soon
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-3">
				<div class="ads_space">Keep Watching this space for ads!!</div>
			</div>
		</div>
	</div>

    <jsp:include page="common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/companyprofile.js"></script>
	<script type="text/javascript">
		getCompanyProfileResearchReportLoad();
	</script>
</body>
</html>