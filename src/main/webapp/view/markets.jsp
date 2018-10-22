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
<title>Markets - Finvendor</title>
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


				<div class="inner_breadcrumb" style="margin-left: 15px;">
					<h5>Markets</h5>
				</div>
				<section>
					<div class="container-fluid tabulative-container" id="consumer_market">
						<div class="row">
							<div class="col-xs-12 col-md-3">
								<div id="sidebar-panel">
									<div class="sidebar-heading">
										<span>Index Constituients Performance</span>
									</div>
									<ul class="nav nav-tabs">
										<li class="active"><a data-toggle="tab" href="#market_all_stocks">All Stocks</a></li>
										<li><a data-toggle="tab" href="#market_sensex">SENSEX</a></li>
										<li><a data-toggle="tab" href="#market_msci_india_index">MSCI India Index</a></li>
										<li><a data-toggle="tab" href="#market_nifty_50">NIFTY 50</a></li>
										<li><a data-toggle="tab" href="#market_nifty_mid_cap">NIFTY MID CAP</a></li>
										<li><a data-toggle="tab" href="#market_nifty_small_cap">NIFTY SMALL CAP</a></li>
										<li><a data-toggle="tab" href="#market_nifty_500">NIFTY 500</a></li>
									</ul>
								</div>
							</div>
							<div class="col-xs-12 col-md-9">
								<div class="content-panel">
									<div class="tab-content">
										<div id="market_all_stocks" class="tab-pane fade in active">
											<div class="hd">
												
											</div>
											<div class="bd">
												
											</div>
											<div id='progressLoader'>
												<jsp:include page="common/progressLoader.jsp"></jsp:include>
											</div>
										</div>

										<div id="market_sensex" class="tab-pane fade">
											
											<div class="hd">
												<div class="max_per_page">
													<span>Records Per Page </span>
													<select>
														<option value='5'>5</option>
														<option value='1'>10</option>
														<option value='30'>30</option>
														<option value='50'>50</option>
														<option value='100'>100</option>
													</select>

													<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
												</div>
											</div>
											<div class="bd">
												<table>
													<thead>
														<tr>
															<th style="width:20%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="user_id">Company Name <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	High
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	Low
																</p>
															</th>
															<th style="width:8%;">

																<p class="large_font">
																	Close
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Previous Close
																</p>
															</th>
															<th style="width:16%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="recommType">Volume <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w High
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w Low
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Change
																</p>
															</th>
															<th style="width:10%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="report">% Change <i class="fa fa-sort"></i></a>
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

										<div id="market_msci_india_index" class="tab-pane fade">
											
											<div class="hd">
												<div class="max_per_page">
													<span>Records Per Page </span>
													<select>
														<option value='5'>5</option>
														<option value='1'>10</option>
														<option value='30'>30</option>
														<option value='50'>50</option>
														<option value='100'>100</option>
													</select>

													<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
												</div>
											</div>
											<div class="bd">
												<table>
													<thead>
														<tr>
															<th style="width:20%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="user_id">Company Name <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	High
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	Low
																</p>
															</th>
															<th style="width:8%;">

																<p class="large_font">
																	Close
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Previous Close
																</p>
															</th>
															<th style="width:16%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="recommType">Volume <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w High
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w Low
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Change
																</p>
															</th>
															<th style="width:10%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="report">% Change <i class="fa fa-sort"></i></a>
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

										<div id="market_nifty_50" class="tab-pane fade">
											
											<div class="hd">
												<div class="max_per_page">
													<span>Records Per Page </span>
													<select>
														<option value='5'>5</option>
														<option value='1'>10</option>
														<option value='30'>30</option>
														<option value='50'>50</option>
														<option value='100'>100</option>
													</select>

													<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
												</div>
											</div>
											<div class="bd">
												<table>
													<thead>
														<tr>
															<th style="width:20%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="user_id">Company Name <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	High
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	Low
																</p>
															</th>
															<th style="width:8%;">

																<p class="large_font">
																	Close
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Previous Close
																</p>
															</th>
															<th style="width:16%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="recommType">Volume <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w High
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w Low
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Change
																</p>
															</th>
															<th style="width:10%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="report">% Change <i class="fa fa-sort"></i></a>
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

										<div id="market_nifty_mid_cap" class="tab-pane fade">
											
											<div class="hd">
												<div class="max_per_page">
													<span>Records Per Page </span>
													<select>
														<option value='5'>5</option>
														<option value='1'>10</option>
														<option value='30'>30</option>
														<option value='50'>50</option>
														<option value='100'>100</option>
													</select>

													<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
												</div>
											</div>
											<div class="bd">
												<table>
													<thead>
														<tr>
															<th style="width:20%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="user_id">Company Name <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	High
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	Low
																</p>
															</th>
															<th style="width:8%;">

																<p class="large_font">
																	Close
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Previous Close
																</p>
															</th>
															<th style="width:16%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="recommType">Volume <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w High
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w Low
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Change
																</p>
															</th>
															<th style="width:10%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="report">% Change <i class="fa fa-sort"></i></a>
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

										<div id="market_nifty_small_cap" class="tab-pane fade">
											
											<div class="hd">
												<div class="max_per_page">
													<span>Records Per Page </span>
													<select>
														<option value='5'>5</option>
														<option value='1'>10</option>
														<option value='30'>30</option>
														<option value='50'>50</option>
														<option value='100'>100</option>
													</select>

													<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
												</div>
											</div>
											<div class="bd">
												<table>
													<thead>
														<tr>
															<th style="width:20%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="user_id">Company Name <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	High
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	Low
																</p>
															</th>
															<th style="width:8%;">

																<p class="large_font">
																	Close
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Previous Close
																</p>
															</th>
															<th style="width:16%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="recommType">Volume <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w High
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w Low
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Change
																</p>
															</th>
															<th style="width:10%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="report">% Change <i class="fa fa-sort"></i></a>
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

										<div id="market_nifty_500" class="tab-pane fade">
											
											<div class="hd">
												<div class="max_per_page">
													<span>Records Per Page </span>
													<select>
														<option value='5'>5</option>
														<option value='1'>10</option>
														<option value='30'>30</option>
														<option value='50'>50</option>
														<option value='100'>100</option>
													</select>

													<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
												</div>
											</div>
											<div class="bd">
												<table>
													<thead>
														<tr>
															<th style="width:20%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="user_id">Company Name <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	High
																</p>
															</th>
															<th style="width:7%;">
																<p class="large_font">
																	Low
																</p>
															</th>
															<th style="width:8%;">

																<p class="large_font">
																	Close
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Previous Close
																</p>
															</th>
															<th style="width:16%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="recommType">Volume <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w High
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	52w Low
																</p>
															</th>
															<th style="width:8%;">
																<p class="large_font">
																	Change
																</p>
															</th>
															<th style="width:10%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="report">% Change <i class="fa fa-sort"></i></a>
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
					</div>
				</section>

	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/markets.js"></script>
</body>
</html>