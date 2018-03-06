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
	
	<div class="container">
		<div class="col-xs-12 col-sm-9">
			<div class="fv_body">
				<div id="dashboard_report">
					
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-3">
			Ads
		</div>
	</div>

    <jsp:include page="common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dashboardreport.js"></script>
	<script type="text/javascript">
		getDashboardResearchReportLoad();
	</script>
</body>
</html>