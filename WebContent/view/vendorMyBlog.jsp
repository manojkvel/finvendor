<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<!DOCTYPE html>
<head>
	<title>Vendor - My Blogs</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
</head>	
<body>
	<div class="container-fluid">
		<div class="row">
		<jsp:include page="common/dashboardheader.jsp"></jsp:include>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
		<section>
			<div class="container">
				<div id="dashboard">
					<h3>Coming Soon</h3>
				</div>
			</div>
		</section>
		<jsp:include page="common/footer.jsp"></jsp:include>
		</div>
	</div>	
	<script type="text/javascript">
		$(document).ready(function() {
			changeTabMode();	
		});
	</script>
</body>
</html>