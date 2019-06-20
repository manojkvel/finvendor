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
<title>Sitemap - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container" id="sitemap">
		<div class="inner_breadcrumb">
			<h5>Sitemap</h5>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-md-9">
				<div class="default_template">
					<div class="content">
						<div class="row">
							<div class="col-xs-12 col-sm-4">
								<ul>
									<li>
										<a href="${pageContext.request.contextPath}/view/equity_research_report_vendor.jsp?researchReportType=Equity/Company Research">
											<span class="fa fa-chevron-right"></span> Research Analyst's Recommendations
										</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/view/celebrity-investors-strategies.jsp">
											<span class="fa fa-chevron-right"></span> Celebrity Investor's Strategies
										</a>
									</li>
									
									<li>
										<a href="${pageContext.request.contextPath}/view/do-it-yourself.jsp">
											<span class="fa fa-chevron-right"></span> Do It Yourself
										</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/view/sector-research.jsp">
											<span class="fa fa-chevron-right"></span> Sector Screener
										</a>
									</li>
								</ul>
							</div>
							<div class="col-xs-12 col-sm-4">
								<ul class="parent_link">
									<li>
										<a href="${pageContext.request.contextPath}/view/markets.jsp">
											<span class="fa fa-chevron-right"></span> Markets
										</a>
									</li>
								</ul>
							</div>
							<div class="col-xs-12 col-sm-4">
								<ul>
									<li>
										<a href="${pageContext.request.contextPath}/view/brochures.jsp">
											<span class="fa fa-chevron-right"></span> Brochures
										</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/view/white-papers.jsp">
											<span class="fa fa-chevron-right"></span> Whitepapers
										</a>
									</li>

									<li>
										<a href="${pageContext.request.contextPath}/view/blogs.jsp">
											<span class="fa fa-chevron-right"></span> Blogs
										</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/view/case-studies.jsp">
											<span class="fa fa-chevron-right"></span> Case Studies
										</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/view/spotlights.jsp">
											<span class="fa fa-chevron-right"></span> Spotlights
										</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3">
				<jsp:include page="common/inner_sidebar.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>