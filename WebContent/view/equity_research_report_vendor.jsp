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
						</div>
						<div class='widget-group'>
							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Geography</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<ul>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>India</span>
												</div>
												<div class='col-xs-3'>
													<input type='radio' name='geography_type' data-name='India' data-section='' data-value='1' id='filled-in-box-geo-india' />
													<label for='geo-india'></label>
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>UK</span>
												</div>
												<div class='col-xs-3'>
													<input type='radio' name='geography_type' data-name='UK' data-section='' data-value='2' id='filled-in-box-geo-uk' />
													<label for='geo-uk'></label>
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>US</span>
												</div>
												<div class='col-xs-3'>
													<input type='radio' name='geography_type' data-name='US' data-section='' data-value='3' id='filled-in-box-smallcap-geo-us' />
													<label for='geo-us'></label>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>M-Cap</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<ul>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>All</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='All' data-section='' data-value='0' id='filled-in-box-all' />
													<label for='all'></label>
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Large Cap: > $5Bn</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Large Cap' data-section='' data-value='1' id='filled-in-box-largecap' />
													<label for='largecap'></label>
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Mid Cap: $1Bn < & < $5Bn</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Mid Cap' data-section='' data-value='2' id='filled-in-box-midcap' />
													<label for='midcap'></label>
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Small Cap: $300M < & < $1Bn</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Small Cap' data-section='' data-value='3' id='filled-in-box-smallcap' />
													<label for='smallcap'></label>
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Micro Cap: $50M < & < $300M</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Micro Cap' data-section='' data-value='4' id='filled-in-box-microcap' />
													<label for='microcap'></label>
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Nano Cap: < $50M</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Nano Cap' data-section='' data-value='5' id='filled-in-box-nanocap' />
													<label for='nanocap'></label>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Style</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<ul>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>All</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='All' data-section='' data-value='all' id='filled-in-box-all' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Value</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Value' data-section='' data-value='value' id='filled-in-box-value' />

												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Growth</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Growth' data-section='' data-value='growth' id='filled-in-box-growth' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Income</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Income' data-section='' data-value='income' id='filled-in-box-income' />
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Analyst Type</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<ul>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Broker</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Broker' data-section='' data-value='broker' id='filled-in-box-broker' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Independent</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Independent' data-section='' data-value='independent' id='filled-in-box-independent' />

												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Others</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Others' data-section='' data-value='others' id='filled-in-box-others' />
												</div>
											</div>
										</li>
									</ul>
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
									<ul id="search_broker_ul">
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Capital Market</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Capital Market' data-section='' data-value='capital-market' id='filled-in-box-capital-market' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Zerodha</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Zerodha' data-section='' data-value='zerodha' id='filled-in-box-zerodha' />

												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>HDFC Sec.</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='HDFC Sec.' data-section='' data-value='hdfc-sec' id='filled-in-box-hdfc-sec' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>ICICI Sec.</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='ICICI Sec' data-section='' data-value='icici-sec' id='filled-in-box-icici-sec' />
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						    <div class='widget widget-panel-default'>
						    	<div class='widget-panel-heading'>
						    		<h3>Broker/Analyst YR of InCorp</h3>
						    		<span class="fa fa-chevron-down"></span>
						    	</div>
						    	<div class='widget-panel-body'>
						    		<ul>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span><= 3 Yrs</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='<= 3 Yrs' data-section='' data-value='lessthan3yrs' id='filled-in-box-lessthan3yrs' />
						    					</div>
						    				</div>
						    			</li>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>3 - 5 Yrs</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='3 - 5 Yrs' data-section='' data-value='between3and5yrs' id='filled-in-box-between3and5yrs' />
						    					</div>
						    				</div>
						    			</li>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>5 - 10 Yrs</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='5 - 10 Yrs' data-section='' data-value='between5and10yrs' id='filled-in-box-between5and10yrs' />
						    					</div>
						    				</div>
						    			</li>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>> 10 Yrs</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='> 10 Yrs' data-section='' data-value='morethan10yrs' id='filled-in-box-morethan10yrs' />
						    					</div>
						    				</div>
						    			</li>
						    		</ul>
						    	</div>
						    </div>
						    <div class='widget widget-panel-default'>
						    	<div class='widget-panel-heading'>
						    		<h3>Broker Rank</h3>
						    		<span class="fa fa-chevron-down"></span>
						    	</div>
						    	<div class='widget-panel-body'>
						    		<ul>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>5 star (Success rate > 80%)</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='5 star' data-section='' data-value='5star' id='filled-in-box-5star' />
						    					</div>
						    				</div>
						    			</li>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>4 star (Success rate >= 65% & < 80%)</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='4 star' data-section='' data-value='4star' id='filled-in-box-4star' />
						    					</div>
						    				</div>
						    			</li>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>3 star (Success rate >= 50% & < 65%)</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='3 star' data-section='' data-value='3star' id='filled-in-box-3star' />
						    					</div>
						    				</div>
						    			</li>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>2 star (Success rate >= 40% & < 50%)</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='2 star' data-section='' data-value='2star' id='filled-in-box-2star' />
						    					</div>
						    				</div>
						    			</li>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>1 star (Success rate < 40%)</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='1 star' data-section='' data-value='1star' id='filled-in-box-1star' />
						    					</div>
						    				</div>
						    			</li>
						    		</ul>
						    	</div>
						    </div>

							<div class='widget widget-panel-default'>
								<div class='widget-panel-heading'>
									<h3>Recomm. Type</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<ul>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Buy</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Buy' data-section='' data-value='buy' id='filled-in-box-buy' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Sell</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Sell' data-section='' data-value='sell' id='filled-in-box-sell' />

												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Accumulate</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Accumulate' data-section='' data-value='accumulate' id='filled-in-box-accumulate' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Overweight</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Overweight' data-section='' data-value='overweight' id='filled-in-box-overweight' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Underweight</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Underweight' data-section='' data-value='underweight' id='filled-in-box-underweight' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Reduce</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Reduce' data-section='' data-value='reduce' id='filled-in-box-reduce' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Neutral</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Neutral' data-section='' data-value='neutral' id='filled-in-box-neutral' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Hold</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Hold' data-section='' data-value='hold' id='filled-in-box-hold' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Bullish</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Bullish' data-section='' data-value='bullish' id='filled-in-box-bullish' />
												</div>
											</div>
										</li>
										<li>
											<div class='row'>
												<div class='col-xs-9'>
													<span>Bearish</span>
												</div>
												<div class='col-xs-3'>
													<input type='checkbox' data-name='Bearish' data-section='' data-value='bearish' id='filled-in-box-bearish' />
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						    <div class='widget widget-panel-default'>
						    	<div class='widget-panel-heading'>
						    		<h3>Others</h3>
						    		<span class="fa fa-chevron-down"></span>
						    	</div>
						    	<div class='widget-panel-body'>
						    		<ul>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>Award Winning Analyst</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='Award winning Analyst' data-section='' data-value='awardwinninganalyst' id='filled-in-box-awardwinninganalyst' />
						    					</div>
						    				</div>
						    			</li>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>Research Reports by CFA</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='Research Reports by CFA' data-section='' data-value='researchreportsbyCFA' id='filled-in-box-researchreportsbyCFA' />
						    					</div>
						    				</div>
						    			</li>
						    			<li>
						    				<div class='row'>
						    					<div class='col-xs-9'>
						    						<span>Upside</span>
						    					</div>
						    					<div class='col-xs-3'>
						    						<input type='checkbox' data-name='Upside' data-section='' data-value='upside' id='filled-in-box-upside' />
						    					</div>
						    				</div>
						    			</li>
						    		</ul>
						    	</div>
						    </div>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-md-9">
					<div class="content-panel">
						<div id="fv_equity_research_report_vendor_search" class="section">
							<table id='broker_table'>
								<thead>
									<tr>
										<th style="width:20%;">
											<p class="large_font">
												<a href="javascript:void(0)">Company <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Style <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">M-Cap <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Sector <i class="fa fa-sort"></i></a>
											</p>
										</th>
										<th style="width:20%;">
											<p class="large_font">
												<a href="javascript:void(0)">Broker <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Since <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Awarded <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Researched by CFA <i class="fa fa-sort"></i></a>
											</p>
										</th>
										<th style="width:15%;">
											<p class="large_font">
												<a href="javascript:void(0)">Broker Rank <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Large Cap <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Mid Cap <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Small Cap <i class="fa fa-sort"></i></a>
											</p>
										</th>
										<th style="width:15%;">

											<p class="large_font">
												<a href="javascript:void(0)">CMP <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Price Date <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">P/E <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">3 YR PAT Growth <i class="fa fa-sort"></i></a>
											</p>
										</th>
										<th style="width:15%;">
											<p class="large_font">
												<a href="javascript:void(0)">Recom. Type <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Tgt Price <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Price Recom. <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Upside <i class="fa fa-sort"></i></a>
											</p>
										</th>
										<th style="width:15%;">
											<p class="large_font">
												<a href="javascript:void(0)">Report <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Research Date <i class="fa fa-sort"></i></a>
											</p>
											<p class="small_font">
												<a href="javascript:void(0)">Analyst Name <i class="fa fa-sort"></i></a>
											</p>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="6">
											<jsp:include page="common/progressLoader.jsp"></jsp:include>
										</td>
									</tr>
									<!--<tr>
										<td>Zee Learn Limited</td>
										<td>Axis Direct</td>
										<td>5</td>
										<td>41.45</td>
										<td>67.35</td>
										<td>
											<div style="display:inline-flex">
												<a href="#" rel="nofollow" data-toggle="tooltip" title="Free login to see report pdf required"><i class="fa fa-file-pdf-o"></i></a>
												<a href="#" target="" data-toggle="tooltip" title="Go to report post"><i class="fa fa-file"></i></a>
											</div>
										</td>
									</tr>
									<tr>
										<td>RIL</td>
										<td>Angel Broking</td>
										<td>1</td>
										<td>1562</td>
										<td>2200</td>
										<td>
											<div style="display:inline-flex">
												<a href="#" rel="nofollow" data-toggle="tooltip" title="Free login to see report pdf required"><i class="fa fa-file-pdf-o"></i></a>
												<a href="#" target="" data-toggle="tooltip" title="Go to report post"><i class="fa fa-file"></i></a>
											</div>
										</td>
									</tr>
									<tr>
										<td>Zee Learn Limited</td>
										<td>Axis Direct</td>
										<td>5</td>
										<td>41.45</td>
										<td>67.35</td>
										<td>
											<div style="display:inline-flex">
												<a href="#" rel="nofollow" data-toggle="tooltip" title="Free login to see report pdf required"><i class="fa fa-file-pdf-o"></i></a>
												<a href="#" target="" data-toggle="tooltip" title="Go to report post"><i class="fa fa-file"></i></a>
											</div>
										</td>
									</tr>
									<tr>
										<td>RIL</td>
										<td>Angel Broking</td>
										<td>1</td>
										<td>1562</td>
										<td>2200</td>
										<td>
											<div style="display:inline-flex">
												<a href="#" rel="nofollow" data-toggle="tooltip" title="Free login to see report pdf required"><i class="fa fa-file-pdf-o"></i></a>
												<a href="#" target="" data-toggle="tooltip" title="Go to report post"><i class="fa fa-file"></i></a>
											</div>
										</td>
									</tr>
									<tr>
										<td>Zee Learn Limited</td>
										<td>Axis Direct</td>
										<td>5</td>
										<td>41.45</td>
										<td>67.35</td>
										<td>
											<div style="display:inline-flex">
												<a href="#" rel="nofollow" data-toggle="tooltip" title="Free login to see report pdf required"><i class="fa fa-file-pdf-o"></i></a>
												<a href="#" target="" data-toggle="tooltip" title="Go to report post"><i class="fa fa-file"></i></a>
											</div>
										</td>
									</tr>
									<tr>
										<td>RIL</td>
										<td>Angel Broking</td>
										<td>1</td>
										<td>1562</td>
										<td>2200</td>
										<td>
											<div style="display:inline-flex">
												<a href="#" rel="nofollow" data-toggle="tooltip" title="Free login to see report pdf required"><i class="fa fa-file-pdf-o"></i></a>
												<a href="#" target="" data-toggle="tooltip" title="Go to report post"><i class="fa fa-file"></i></a>
											</div>
										</td>
									</tr>
									<tr>
										<td>Zee Learn Limited</td>
										<td>Axis Direct</td>
										<td>5</td>
										<td>41.45</td>
										<td>67.35</td>
										<td>
											<div style="display:inline-flex">
												<a href="#" rel="nofollow" data-toggle="tooltip" title="Free login to see report pdf required"><i class="fa fa-file-pdf-o"></i></a>
												<a href="#" target="" data-toggle="tooltip" title="Go to report post"><i class="fa fa-file"></i></a>
											</div>
										</td>
									</tr>
									<tr>
										<td>RIL</td>
										<td>Angel Broking</td>
										<td>1</td>
										<td>1562</td>
										<td>2200</td>
										<td>
											<div style="display:inline-flex">
												<a href="#" rel="nofollow" data-toggle="tooltip" title="Free login to see report pdf required"><i class="fa fa-file-pdf-o"></i></a>
												<a href="#" target="" data-toggle="tooltip" title="Go to report post"><i class="fa fa-file"></i></a>
											</div>
										</td>
									</tr>-->
								</tbody>
							</table>
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
		    ul = document.getElementById("search_broker_ul");
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