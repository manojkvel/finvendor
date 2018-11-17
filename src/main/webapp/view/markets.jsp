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
					<h5>Index Performance</h5>
				</div>
				<section>
					<div class="container-fluid tabulative-container" id="consumer_market">
						<div class="row">
							<div class="col-xs-12 col-md-3">
								<div id="sidebar-panel">
									<div class="sidebar-heading">
										<span>Reset filters</span>
									</div>
									<div class="widget-group">
										<div class='widget widget-panel-default'>
											<div class='widget-panel-heading'>
												<h3>Indices</h3>
											</div>
											<div class='widget-panel-body'>
												<div class="widget_search">
													<input type="text" id="search_market_index" onkeyup="getMarketIndexListByName()" placeholder="Search Indices" />
												</div>
												<div id="search_by_market_index">
													<ul id="search_by_market_index_ul">

													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-md-9">
								<div class="content-panel">
									<div class="tab-content">
										<div id="market_data_all" class="tab-pane">
											<div class="container-fluid market_details">
												<div class="row">
													<div class="col-xs-12 col-sm-4">
														<div class="company_profile_details">
															<div class="profile_details company_details">
																<span class='index_name'>Today's Market Snapshot</span>
															</div>
															<div class="profile_details company_details">
																<span class="price_date">NA</span>
															</div>
														</div>
													</div>
													<div class="col-xs-12 col-sm-3">&nbsp;</div>
													<div class="col-xs-12 col-sm-5">
														<div id="market_index_analytics">
															<table>
																<thead>
																	<th>Gainers</th>
																	<th>Losers</th>
																	<th>Unchanged</th>
																</thead>
																<tr>
																	<td class="center success">0</td>
																	<td class="center danger">0</td>
																	<td class="center neutral">0</td>
																</tr>
															</table>
														</div>
													</div>
												</div>
											</div>
											<div class="hd">
												
											</div>
											<div class="bd">
												<div class="container-fluid market_details">
													<div class="row">
														<div class="col-xs-12 col-sm-4">
															<div id="market_index_winners">
																<h3>Today's Winners</h3>
																<table>
																	<thead>
																		<tr>
																			<th style="width:20%;">
																				<p class="large_font">
																					Company Name
																				</p>
																			</th>
																			<th style="width:7%;">
																				<p class="large_font">
																					CMP
																				</p>
																			</th>
																			<th style="width:10%;">
																				<p class="large_font">
																					% Change
																				</p>
																			</th>
																		</tr>
																	</thead>
																	<tbody></tbody>
																	<tfoot></tfoot>
																</table>
															</div>
														</div>
														<div class="col-xs-12 col-sm-4">
															<div id="market_index_loosers">
																<h3>Today's Losers</h3>
																<table>
																	<thead>
																		<tr>
																			<th style="width:20%;">
																				<p class="large_font">
																					Company Name
																				</p>
																			</th>
																			<th style="width:7%;">
																				<p class="large_font">
																					CMP
																				</p>
																			</th>
																			<th style="width:10%;">
																				<p class="large_font">
																					% Change
																				</p>
																			</th>
																		</tr>
																	</thead>
																	<tbody></tbody>
																	<tfoot></tfoot>
																</table>
															</div>
														</div>
														<div class="col-xs-12 col-sm-4">
															<div id="market_index_active">
																<h3>Most Active Today (By Total Trade Volume)</h3>
																<table>
																	<thead>
																		<tr>
																			<th style="width:20%;">
																				<p class="large_font">
																					Company Name
																				</p>
																			</th>
																			<th style="width:7%;">
																				<p class="large_font">
																					CMP
																				</p>
																			</th>
																			<th style="width:10%;">
																				<p class="large_font">
																					Volume
																				</p>
																			</th>
																		</tr>
																	</thead>
																	<tbody></tbody>
																	<tfoot></tfoot>
																</table>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-xs-12 col-sm-4">
															<div id="market_index_52wHigh">
																<h3>52 Weeks High</h3>
																<table>
																	<thead>
																		<tr>
																			<th style="width:20%;">
																				<p class="large_font">
																					Company Name
																				</p>
																			</th>
																			<th style="width:7%;">
																				<p class="large_font">
																					CMP
																				</p>
																			</th>
																		</tr>
																	</thead>
																	<tbody></tbody>
																	<tfoot></tfoot>
																</table>
															</div>
														</div>
														<div class="col-xs-12 col-sm-4">
															<div id="market_index_52wLow">
																<h3>52 Weeks Low</h3>
																<table>
																	<thead>
																		<tr>
																			<th style="width:20%;">
																				<p class="large_font">
																					Company Name
																				</p>
																			</th>
																			<th style="width:7%;">
																				<p class="large_font">
																					CMP
																				</p>
																			</th>
																		</tr>
																	</thead>
																	<tbody></tbody>
																	<tfoot></tfoot>
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div id='progressLoader'>
												<jsp:include page="common/progressLoader.jsp"></jsp:include>
											</div>
										</div>



										<div id="market_data_see_all" class="tab-pane">
											<div class="back_btn">
												<a href="javascript:void(0);"><span class="fa fa-angle-double-left"></span></a>
												<span class="title"></span>
											</div>
											<div class="hd">
												<!--<h3>Constituents</h3>-->
												<div class="max_per_page">
													<span>Records Per Page </span>
													<select>
														<option value='5'>5</option>
														<option value='10'>10</option>
														<option value='30'>30</option>
														<option selected="selected" value='50'>50</option>
														<option value='100'>100</option>
													</select>

													<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
												</div>
											</div>
											<div class="bd">
												<table id="market_see_all_data">
													<thead>
														<tr>
															<th style="width:20%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="companyName">Company Name <i class="fa fa-sort"></i></a>
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
																	<a href="javascript:void(0)" data-id="percentChange">% Change <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:16%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="volume">Volume <i class="fa fa-sort"></i></a>
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

										<div id="market_data_output" class="tab-pane">
												<div class="container-fluid market_details">
													<div class="row">
														<div class="col-xs-12 col-sm-4">
															<div class="company_profile_details">
																<div class="profile_details company_details">
																	<span class='index_name'>Index Name</span>
																</div>
																<div class="profile_details company_details">
																	<span class='last_cmp'>CMP (Last change)</span>
																</div>
																<div class="profile_details company_details">
																	<span class="price_date">NA</span>
																</div>
															</div>
															<br />
															<div class="md_p" id="day_open_value">
																<div class="fl">Day open</div>
																<div class="fr">-</div>
															</div>
															<div class="md_p" id="day_h_l_value">
																<div class="fl">Day High/Low</div>
																<div class="fr">-</div>
															</div>
															<div class="md_p" id="pe_value">
																<div class="fl">P/E</div>
																<div class="fr">NA</div>
															</div>
															<div class="md_p" id="pb_value">
																<div class="fl">P/B</div>
																<div class="fr">NA</div>
															</div>
															<div class="md_p" id="div_yield_value">
																<div class="fl">Div Yield (%)</div>
																<div class="fr">NA</div>
															</div>
														</div>
														<div class="col-xs-12 col-sm-3">&nbsp;</div>
														<div class="col-xs-12 col-sm-5">
															<div id="market_index_analytics">
																<table>
																	<thead>
																		<th>Gainers</th>
																		<th>Losers</th>
																		<th>Unchanged</th>
																	</thead>
																	<tr>
																		<td class="center success">0</td>
																		<td class="center danger">0</td>
																		<td class="center neutral">0</td>
																	</tr>
																</table>
															</div>
														</div>
													</div>
												</div>
											<div class="hd">
												<h3>Constituents</h3>
												<div class="max_per_page">
													<span>Records Per Page </span>
													<select>
														<option value='5'>5</option>
														<option value='10'>10</option>
														<option value='30'>30</option>
														<option selected="selected" value='50'>50</option>
														<option value='100'>100</option>
													</select>

													<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
												</div>
											</div>
											<div class="bd">
												<table id="market_index_data">
													<thead>
														<tr>
															<th style="width:20%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="companyName">Company Name <i class="fa fa-sort"></i></a>
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
																	<a href="javascript:void(0)" data-id="percentChange">% Change <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:16%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="volume">Volume <i class="fa fa-sort"></i></a>
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
	<script type="text/javascript">

    	function getMarketIndexListByName() {
    		var input, filter, ul, li, span, i;
		    input = document.getElementById("search_market_index");
		    filter = input.value.toUpperCase();
		    ul = document.getElementById("search_by_market_index_ul");
		    
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