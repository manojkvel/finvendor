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
<title>Equity Research Analytics - Finvendor</title>
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

		<c:choose>
			<c:when test="${sessionScope.loggedInUser != null  && sessionScope.loggedInRole=='ROLE_ADMIN'}">

				<div class="inner_breadcrumb" style="margin-left: 15px;">
					<h5>Equity Research Analytics</h5>
				</div>
				<section>
					<div class="container-fluid" id="consumer_analytics">
						<div class="row">
							<div class="col-xs-12 col-md-3">
								<div id="sidebar-panel">
									<div class="sidebar-heading">
										<span>Reset filters</span>
									</div>
									<ul class="nav nav-tabs">
										<li class="active"><a data-toggle="tab" href="#d_breach_tab">D Breach</a></li>
										<li><a data-toggle="tab" href="#rf_breach_tab">RF Breach</a></li>
									</ul>
								</div>
							</div>
							<div class="col-xs-12 col-md-9">
								<div class="content-panel">
									<div class="tab-content">
										<div id="d_breach_tab" class="tab-pane fade in active">
											<div class="breach_hd">
												<div class="breach_level" style="float: left;">
													<span>Breach Level </span>
													<select style="width: 50px;margin-left: 5px;">
														<option value='all'>All</option>
														<option value='y'>Yes</option>
														<option value='n'>No</option>
													</select>
												</div>
												<div class="max_per_page">
													<span>Records Per Page </span>
													<select>
														<option value='5'>5</option>
														<option value='1'>1</option>
														<option value='2'>2</option>
														<option value='10'>10</option>
														<option value='30'>30</option>
														<option value='50'>50</option>
														<option value='100'>100</option>
													</select>

													<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
												</div>
											</div>
											<div class="analytics_content">
												<table id='equity_analytics_table'>
													<thead>
														<tr>
															<th style="width:20%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="user_id">User ID <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:20%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="regist_date">Regis. Date <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:15%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="last_login">Last Login <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:15%;">

																<p class="large_font">
																	<a href="javascript:void(0)" data-id="ip_address">IP Address <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:15%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="recommType">Report Download(D Count) <i class="fa fa-sort"></i></a>
																</p>
															</th>
															<th style="width:15%;">
																<p class="large_font">
																	<a href="javascript:void(0)" data-id="report">D Breach <i class="fa fa-sort"></i></a>
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
										<div id="rf_breach_tab" class="tab-pane fade">
											
											<div class="breach_hd">
												<div class="breach_level" style="float: left;">
													<span>Breach Level </span>
													<select style="width: 50px;margin-left: 5px;">
														<option value='all'>All</option>
														<option value='y'>Yes</option>
														<option value='n'>No</option>
													</select>
												</div>
												<div class="max_per_page">
													<span>Records Per Page </span>
													<select>
														<option value='5'>5</option>
														<option value='1'>1</option>
														<option value='2'>2</option>
														<option value='10'>10</option>
														<option value='30'>30</option>
														<option value='50'>50</option>
														<option value='100'>100</option>
													</select>

													<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
												</div>
											</div>
											<table id='equity_analytics_table'>
												<thead>
													<tr>
														<th style="width:20%;">
															<p class="large_font">
																<a href="javascript:void(0)" data-id="user_id">User ID <i class="fa fa-sort"></i></a>
															</p>
														</th>
														<th style="width:20%;">
															<p class="large_font">
																<a href="javascript:void(0)" data-id="regist_date">Regis. Date <i class="fa fa-sort"></i></a>
															</p>
														</th>
														<th style="width:15%;">
															<p class="large_font">
																<a href="javascript:void(0)" data-id="last_login">Last Login <i class="fa fa-sort"></i></a>
															</p>
														</th>
														<th style="width:15%;">

															<p class="large_font">
																<a href="javascript:void(0)" data-id="ip_address">IP Address <i class="fa fa-sort"></i></a>
															</p>
														</th>
														<th style="width:15%;">
															<p class="large_font">
																<a href="javascript:void(0)" data-id="recommType">Report Filter(RF Count) <i class="fa fa-sort"></i></a>
															</p>
														</th>
														<th style="width:15%;">
															<p class="large_font">
																<a href="javascript:void(0)" data-id="report">RF Breach <i class="fa fa-sort"></i></a>
															</p>
														</th>
													</tr>
												</thead>
												<tbody>	
												</tbody>
											</table>
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
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
					window.location.href = '/';
				</script>
			</c:otherwise>
		</c:choose>

	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/consumer_analytics.js"></script>
</body>
</html>