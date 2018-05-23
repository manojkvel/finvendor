<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<!DOCTYPE html>
<head>
	<title>My Watchlist - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
</head>
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<div id="my_watchlist">
					<table id='watchlist_table'>
						<thead>
							<tr>
								<th style="width:20%;">
									<p class="normal_font">
										Company Name
									</p>
								</th>
								<th style="width:20%;">
									<p class="normal_font">
										Price When added
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										CMP
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										% Change since added
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										Price Change
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										% Change
									</p>
								</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-xs-12 col-sm-3">
				<div class="ads_space">Keep Watching this space for ads!!</div>
			</div>
		</div>
	</div>

    <jsp:include page="common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/watchlist.js"></script>
</body>
</html>