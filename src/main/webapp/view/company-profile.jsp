<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
	<title>Research Company Profile</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<script type="text/javascript">
		var isinCode = "<%= request.getParameter("isinCode")%>";
		
		if(isinCode != "null") {
			var companyProfileJson = {
				isinCode : isinCode,
			}
			window.localStorage.setItem('companyProfileJson', JSON.stringify(companyProfileJson));
		}
	</script>
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-md-9">
				<div id="company_profile">
					<div id='progressLoader'>
						<jsp:include page="common/progressLoader.jsp"></jsp:include>
					</div>
					<div class="company_profile_details">
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
								<div class="profile_details company_details">
									<span class="price_date">NA</span>
								</div>
							</div>
							<div class="col-xs-12 col-sm-5">
								<div class="profile_details func_details">
									<c:choose>
										<c:when test="${sessionScope.loggedInUser != null  && (sessionScope.loggedInRole=='ROLE_CONSUMER' || sessionScope.loggedInRole=='ROLE_ADMIN')}">
										 	<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#addToWatchlist"><span class="fa fa-eye"></span> Add to Watchlist</button>
										 	<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#setPriceAlert"><span class="fa fa-bell"></span> Set Price Alert</button>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${sessionScope.loggedInUser != null  && (sessionScope.loggedInRole=='ROLE_VENDOR')}">
												 	
												</c:when>
												<c:otherwise>
													<a href="javascript:inner_login('view/company-profile.jsp')">
														<span class="fa fa-eye"></span> Add to Watchlist
													</a>
													<a href="javascript:inner_login('view/company-profile.jsp')">
														<span class="fa fa-bell"></span> Set Price Alert
													</a>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
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

						<div class="container-fluid market_details">
							<div class="row">
								<div class="col-xs-12 col-sm-7">
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="mkt_cap_value">
											<div class="fl">Market Cap (cr)</div>
											<div class="fr">NA</div>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="face_value">
											<div class="fl">Face Value</div>
											<div class="fr">NA</div>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-7">
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="pe_value">
											<div class="fl">P/E (ttm)</div>
											<div class="fr">NA</div>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="eps_value">
											<div class="fl">EPS (ttm)</div>
											<div class="fr">NA</div>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-7">
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="pb_value">
											<div class="fl">P/B</div>
											<div class="fr">NA</div>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="bv_value">
											<div class="fl">BV/share</div>
											<div class="fr">NA</div>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-7">
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="div_yield_value">
											<div class="fl">Div Yield (%)</div>
											<div class="fr">NA</div>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="year_l_h_value">
											<div class="fl">52wk L/H</div>
											<div class="fr">NA</div>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-7">
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="beta_value">
											<div class="fl">BETA</div>
											<div class="fr">NA</div>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="roe_value">
											<div class="fl">ROE (%)</div>
											<div class="fr">NA</div>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-7">
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="revenue_value">
											<div class="fl">Revenue (cr)<sup>*</sup></div>
											<div class="fr">NA</div>
										</div>
									</div>
									<div class="col-xs-12 col-sm-6">
										<div class="md_p" id="pat_value">
											<div class="fl">PAT (cr)<sup>*</sup></div>
											<div class="fr">NA</div>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-7">
									<div class="col-xs-12 col-sm-12">
										<div class="fv_indicators">* indicates recent qtr</div>
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
												<div class="col-xs-4 col-sm-4">
													<div id="total_buy_recomm" class="research_report_score_block">
														<span class='success'>0</span>
														<div class="row">
															<p>Total BUY Recomm.</p>
														</div>
													</div>
												</div>
												<div class="col-xs-4 col-sm-4">
													<div id="total_neutral_recomm" class="research_report_score_block">
														<span class='warning'>0</span>
														<div class="row">
															<p>Total NEUTRAL Recomm.</p>
														</div>
													</div>
												</div>
												<div class="col-xs-4 col-sm-4">
													<div id="total_sell_recomm" class="research_report_score_block">
														<span class='danger'>0</span>
														<div class="row">
															<p>Total SELL Recomm.</p>
														</div>
													</div>
												</div>
											</div>

											<div class="col-xs-12 col-sm-12" style="margin-top: 20px;">
												<div class="col-xs-6 col-sm-6">
													<div id="average_target_price" class="research_report_score_block success">
														<span>0</span>
														<div class="row">
															<p>Average Target Price</p>
														</div>
													</div>
												</div>
												<div class="col-xs-6 col-sm-6">
													<div id="no_of_analyst_report" class="research_report_score_block danger">
														<span>0</span>
														<div class="row">
															<p>No. of Analyst Report</p>
														</div>
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
																<a href="javascript:void(0)" data-id="recommType">Recommendation Type <i class="fa fa-sort"></i></a>
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
			</div>
			<div class="col-xs-12 col-sm-12 col-md-3">
				<jsp:include page="common/inner_sidebar.jsp"></jsp:include>
			</div>
		</div>
	</div>

	<jsp:include page="login.jsp"></jsp:include>
    <jsp:include page="common/footer.jsp"></jsp:include>

    <!-- Modal -->
  <div class="modal fade" id="addToWatchlist" role="dialog">
    <div class="modal-dialog">
    
      <div class="alert alert-success alert-dismissible">
      	<button type="button" class="close" data-dismiss="modal">&times;</button>
      	<span>This alert box could indicate a successful or positive action.</span>
      </div>
      
    </div>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="setPriceAlert" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h3>Add an alert for a stock for price and new research reports. Alerts are delivered to your email.</h3>
          <div class="alert alert-success alert-dismissible">
          	<span>This alert box could indicate a successful or positive action.</span>
          </div>
        </div>
        <div class="modal-body">
        	<div class="alert-cards">
	        	<h4>Stock Price <a class="fa fa-info-circle" href="javascript:void(0);" data-toggle='tooltip' title='Configure alerts for price'></a></h4>
	          	<table>
	          		<tr>
	          			<th>&nbsp;</th>
	          			<th>% change (-ve)</th>
	          			<th>% change (+ve)</th>
	          		</tr>
	          		<tr>
	          			<td>Day</td>
	          			<td>
	          				<span>-</span>
	          				<input type="number" name="day_min_price" val="" />
	          				<span>%</span>
	          			</td>
	          			<td>
	          				<span>+</span>
	          				<input type="number" name="day_max_price" val="" />
	          				<span>%</span>
	          			</td>
	          		</tr>
	          		<tr>
	          			<td>Week</td>
	          			<td>
	          				<span>-</span>
	          				<input type="number" name="week_min_price" val="" />
	          				<span>%</span>
	          			</td>
	          			<td>
	          				<span>+</span>
	          				<input type="number" name="week_max_price" val="" />
	          				<span>%</span>
	          			</td>
	          		</tr>
	          		<tr>
	          			<td>Month</td>
	          			<td>
	          				<span>-</span>
	          				<input type="number" name="month_min_price" val="" />
	          				<span>%</span>
	          			</td>
	          			<td>
	          				<span>+</span>
	          				<input type="number" name="month_max_price" val="" />
	          				<span>%</span>
	          			</td>
	          		</tr>
	          		<tr>
	          			<td>No Timeframe</td>
	          			<td>
	          				<span>-</span>
	          				<input type="number" name="noTimeFrame_min_price" val="" />
	          				<span>%</span>
	          			</td>
	          			<td>
	          				<span>+</span>
	          				<input type="number" name="noTimeFrame_max_price" val="" />
	          				<span>%</span>
	          			</td>
	          		</tr>
	          	</table>
          	</div>
          	<div class="alert-cards">
	        	<h4>Research Report <a class="fa fa-info-circle" href="javascript:void(0);" data-toggle='tooltip' title='Configure alerts for research report'></a></h4>
	        	<table class="research_report">
	        		<tr>
	        			<td>
	          				<input type="checkbox" name="alert_new_research_report" val="" /> 
	          			</td>
	        			<td>
	          				Alert New Research Report
	          			</td>
	          		</tr>
	          	</table>
          	</div>
        </div>
        <div class="modal-footer">
          	<button type="button" name="set_alert_btn" class="btn btn-info btn-lg">Set Alert</button>
        </div>
      </div>
      
    </div>
  </div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/companyprofile.js"></script>
	<script type="text/javascript">
		getCompanyProfileResearchReportLoad();
		$('[data-toggle="tooltip"]').tooltip();
	</script>
</body>
</html>