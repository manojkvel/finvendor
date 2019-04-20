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
<title>Celebrity Investor's Strategies - Finvendor</title>
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
	<div class="container-fluid" id="celebrity_investors_strategies">
		<div class="inner_breadcrumb">
			<h5>Celebrity Investor's Strategies</h5>
		</div>
		<ol>
			<li>
				<div id="kennith_fisher_strategy">
					
					<div class="startegy_content_table">
						<table>
							<tbody>
								<thead>
									<tr>
										<th>
											Company Name
										</th>
										<th>
											Annual Revenue
										</th>
										<th>
											DE
										</th>
										<th>
											Inflation Rate
										</th>
										<th>
											M cap
										</th>
										<th>
											PSR
										</th>
										<th>
											rnd Expenditure
										</th>
										<th>
											EPS
										</th>
										<th>
											Average Net Profit Margin
										</th>
									</tr>
								</thead>
							</tbody>
						</table>
					</div>
					<div class="strategy_view">
						<h2>Kennith Fisher</h2>
						<div class="startegy_content">
							<h3></h3>
							<div class="view_btn">
								<a href="javascript:void(0)">
									Run Strategy
								</a>
							</div>
						</div>
					</div>

				</div>
			</li>
			<li>
				<div id="benjamin_graham_strategy">

					<div class="startegy_content_table">
						<table>
							<thead>
								<tr>
									<th>
										Company Name
									</th>
									<th>
										Total Debt
									</th>
									<th>
										Current Assets
									</th>
									<th>
										Current Liabilities
									</th>
									<th>
										EPS
									</th>
									<th>
										P/E
									</th>
									<th>
										P/B
									</th>
									<th>
										Div Yield (%)
									</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<div class="strategy_view">
						<h2>Benjamin Graham</h2>
						<div class="startegy_content">
							<h3></h3>
							<div class="view_btn">
								<a href="javascript:void(0)">
									Run Strategy
								</a>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div id="finvendors_pick_strategy">
					<div class="strategy_view">
						<h2>FinVendor's Pick</h2>
						<div class="startegy_content">
							<h3></h3>
							<div class="view_btn">
								<a href="javascript:void(0)">
									Run Strategy
								</a>
							</div>
						</div>
					</div>

					<div class="startegy_content_table">
						<table>
							<thead>
								<tr>
									<th>
										Company Name
									</th>
									<th>
										Total Debt
									</th>
									<th>
										Current Assets
									</th>
									<th>
										Current Liabilities
									</th>
									<th>
										EPS
									</th>
									<th>
										P/E
									</th>
									<th>
										P/B
									</th>
									<th>
										Div Yield (%)
									</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</li>
			<li>
				<div id="marting_zweigs_strategy">
					<div class="strategy_view">
						<h2>Martin Zweig's Strategy</h2>
						<div class="startegy_content">
							<h3>Believed to have predicted 1987 market crash a day before.In October, the market collapsed, and while the big averages lost a quarter of their value in one day, Zweig’s portfolio rose 8.7 percent and 50% for all of 1987</h3>
							<div class="view_btn">
								<a href="javascript:void(0)">
									Run Strategy
								</a>
							</div>
						</div>
					</div>

					<div class="startegy_content_table">
						<table>
							<tbody>
								<thead>
									<tr>
										<th>
											Company Name
										</th>
										<th>
											Annual Revenue
										</th>
										<th>
											DE
										</th>
										<th>
											Inflation Rate
										</th>
										<th>
											M cap
										</th>
										<th>
											PSR
										</th>
										<th>
											rnd Expenditure
										</th>
										<th>
											EPS
										</th>
										<th>
											Average Net Profit Margin
										</th>
									</tr>
								</thead>
							</tbody>
						</table>
					</div>
				</div>
			</li>
			<li>
				<div id="james_o_shaughessys_strategy">
					<div class="strategy_view">
						<h2>James O'Shaughnessy's Strategy</h2>
						<div class="startegy_content">
							<h3>Spent more than 30 years researching equity market returns. His work has brought to the fore the power of what he calls 'fundamental quant' from that he built a fund management business with nearly $7 billion under management.</h3>
							<div class="view_btn">
								<a href="javascript:void(0)">
									Run Strategy
								</a>
							</div>
						</div>
					</div>

					<div class="startegy_content_table">
						<table>
							<thead>
								<tr>
									<th>
										Company Name
									</th>
									<th>
										Total Debt
									</th>
									<th>
										Current Assets
									</th>
									<th>
										Current Liabilities
									</th>
									<th>
										EPS
									</th>
									<th>
										P/E
									</th>
									<th>
										P/B
									</th>
									<th>
										Div Yield (%)
									</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</li>
			<li>
				<div id="joel_greenblatts_strategy">
					<div class="strategy_view">
						<h2>Joel Greenblatt's Strategy</h2>
						<div class="startegy_content">
							<h3>Introduced an investment strategy of "magic formula investing", Through his firm Gotham Capital, Greenblatt presided over an annualized return of 40% from 1985 to 2006.</h3>
							<div class="view_btn">
								<a href="javascript:void(0)">
									Run Strategy
								</a>
							</div>
						</div>
					</div>

					<div class="startegy_content_table">
						<table>
							<tbody>
								<thead>
									<tr>
										<th>
											Company Name
										</th>
										<th>
											Annual Revenue
										</th>
										<th>
											DE
										</th>
										<th>
											Inflation Rate
										</th>
										<th>
											M cap
										</th>
										<th>
											PSR
										</th>
										<th>
											rnd Expenditure
										</th>
										<th>
											EPS
										</th>
										<th>
											Average Net Profit Margin
										</th>
									</tr>
								</thead>
							</tbody>
						</table>
					</div>
				</div>
			</li>
		</ol>
	</div>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/celebrity_investors_strategies.js"></script>
</body>
</html>