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
<title>Metrics - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<section>
		<div class="container-fluid" id="metrics_dashboard">
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
									<h3>Search by Page</h3>
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
									<h3>Search by IP Addres</h3>
									<span class="fa fa-chevron-down"></span>
								</div>
								<div class='widget-panel-body'>
									<div id="search_by_marketcapital">
										<ul>
											
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-md-9">
					<div class="row">
						<div class="col-xs-12 col-md-8">
							<div class="card">
								<div class="card-hd">
									<h5>Dashboard</h5>
								</div>
								<div class="card-bd">
									<div id="global_dashboard">
										<canvas id="myChart" width="250" height="250"></canvas>
									</div>
								</div>
							</div>
							<div class="card">
								<div class="card-hd">
									<h5>Home Page Report</h5>
								</div>
								<div class="card-bd">

								</div>
							</div>
							<div class="card">
								<div class="card-hd">
									<h5>Equity Search Report</h5>
								</div>
								<div class="card-bd">
									
								</div>
							</div>
							<div class="card">
								<div class="card-hd">
									<h5>Download Report</h5>
								</div>
								<div class="card-bd">
									
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-md-4">
							<div class="card">
								<div class="card-hd">
									<h5>Users</h5>
								</div>
								<div class="card-bd">
									<div class="col-xs-6">
										<h4></h4>
										<p>Unique Users</p>
									</div>
									<div class="col-xs-6">
										<h4></h4>
										<p>Total Users</p>
									</div>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/metrics_dashboard.js"></script>
</body>
</html>