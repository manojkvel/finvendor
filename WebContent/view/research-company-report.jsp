<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<c:set var="researchreport" value="${researchreport}"></c:set>
<!DOCTYPE html>
<head>
	<title>Research Company Report</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<style type="text/css">
		.fv_body {
			background: #fff;
			padding: 10px;
		}

		#dashboard_report {
			min-height: 200px;
		}
	</style>
</head>
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	
	<div class="container dashboard_report">
		<div class="col-xs-12 col-sm-9">
			<div class="fv_body">
				<div>
					<div class="hd">
						<div class="row">
							<span class="companyName">HSIL Limited</span>
							<span class="currentMarketPrice">428.00</span>
							<div>
								<span data-toggle="tooltip" data-placement="top" title="" data-original-title="16 Feb, 2018 at 12:00 AM">16 Feb 2018</span>
							</div>
						</div>
					</div>
					<div class="bd">
						<div class="row">
							<div class="reportContainer">
								<div class="reportDetails">
									<div class="reportTitle">
										<span class="recommType success">BUY:</span> HSIL Limited
										<div class="brokerName">by amit vendor</div>
									</div>
									<div class="targetPrice">Target: 510</div>
									<div class="upside">Upside: 19.2%</div>

									<div class="dwnldReport">
										<a href="/system/api/downloadResearchReports?reportFileName='blahblah'">Download Full Report</a>
									</div>
								</div>
								<span class="brokerName">amit vendor</span>
								<div class="reportDesc">
									<p>
										HSIL Limited (HSIL) is an Indian company, which offers sanitaryware products, faucets and glass bottles. The companys segments include building products division, packaging products division and others division (retail, consumer, pipes, caps and closures, e tc.) The companys ~46% re venue comes from building products division, ~43% from HSIL Limited (HSIL) is an Indian company, which offers sanitaryware products, faucets and glass bottles. The companys segments include building products division, packaging products division and others division (retail, consumer, pipes, caps and closures, e tc.) The companys ~46% re venue comes from building products division, ~43% from
										HSIL Limited (HSIL) is an Indian company, which offers sanitaryware products, faucets and glass bottles. The companys segments include building products division, packaging products division and others division (retail, consumer, pipes, caps and closures, e tc.) The companys ~46% re venue comes from building products division, ~43% from HSIL Limited (HSIL) is an Indian company, which offers sanitaryware products, faucets and glass bottles. The companys segments include building products division, packaging products division and others division (retail, consumer, pipes, caps and closures, e tc.) The companys ~46% re venue comes from building products division, ~43% from
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--<div id="dashboard_report">
					
				</div>-->
			</div>
		</div>
		<div class="col-xs-12 col-sm-3">
			Ads
		</div>
	</div>

    <jsp:include page="common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dashboardreport.js"></script>
	<script type="text/javascript">
		getDashboardResearchReportLoad(<%= request.getParameter("id") %>);
	</script>
</body>
</html>