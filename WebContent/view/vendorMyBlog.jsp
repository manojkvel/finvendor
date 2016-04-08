<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<!DOCTYPE html>
<head>
	<title>Vendor - My Statistics</title>
	<link
	href="<%=request.getContextPath() %>/resources/css/singleasset.css"
	type="text/css" rel="stylesheet" />
<link
	href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath() %>/resources/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/resources/css/reset.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath() %>/resources/css/style-metro.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/resources/css/style-II.css"
	rel="stylesheet" type="text/css" />
<!-- Date Picker CSS Starts -->
<link
	href="<%=request.getContextPath() %>/resources/datepick/jsDatePick_ltr.min.css"
	rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/css/finvendor.css">
</head>	
<body>
	<div class="top_header">
		<jsp:include page="common/dashboardheader.jsp"></jsp:include>
	</div>
	<div class="wrapper">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid service-box">
					<div class="row-fluid">
						<div class="span12">
							<div class="portlet box blue" id="form_wizard_1">
								<div class="portlet-title">
									<div class="Row">
										<div class="ColumnCommonvendortab3" style="padding-left: 40px;">
											<div class="control-group" align="center">
												<H1><font color="black">Coming Soon..</font></H1>
											</div>										
										</div>										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer_area">
		<jsp:include page="common/footer.jsp"></jsp:include>
	</div>		
	<script type="text/javascript">
		$(document).ready(function() {
			changeTabMode();	
		});
	</script>
</body>
</html>