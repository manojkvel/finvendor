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
		<div class="content">
			<p class="page_desc">Get Benefited By Stock Picking/Screening strategies that Well-known global investors have always been using since past several years. See which all stocks are meeting the these investors' criteria(As of TODAY):</p>
		</div>
		<ol>
			<li>
				<div id="kennith_fisher_strategy">
					<div class="strategy_view">
						<h2>Kenneth Fisher</h2>
						<div class="startegy_content">
							<h3>Founder of Fisher Investments, Managing over $100B assets. Ken Fisher's public stock picks outperform the broad U.S. stock market over the past 18 years by an average 4.2% annually</h3>
							<div class="view_btn">
								<!-- Trigger the modal with a button -->
								<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="static" data-keyboard="false">Run Strategy</button>
							</div>
						</div>
					</div>

				</div>
			</li>
			<li>
				<div id="benjamin_graham_strategy">
					<div class="strategy_view">
						<h2>Benjamin Graham</h2>
						<div class="startegy_content">
							<h3>Widely known as the <strong>"Father of value investing"</strong>, Warren buffet leant the value investment from Benjamin graham</h3>
							<div class="view_btn">
								<!-- Trigger the modal with a button -->
								<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="static" data-keyboard="false">Run Strategy</button>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div id="finvendor_pick_strategy">
					<div class="strategy_view">
						<h2>FinVendor Pick</h2>
						<div class="startegy_content">
							<h3>We have reviewed all the well known stock screening strategies and come up with this strategy. We recommend you to use this one.</h3>
							<div class="view_btn">
								<!-- Trigger the modal with a button -->
								<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="static" data-keyboard="false">Run Strategy</button>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div id="marting_zweigs_strategy">
					<div class="strategy_view">
						<h2>Martin Zweig</h2>
						<div class="startegy_content">
							<h3>Believed to have predicted 1987 market crash a day before.In October, the market collapsed, and while the big averages lost a quarter of their value in one day, Zweig’s portfolio rose 8.7 percent and 50% for all of 1987</h3>
							<div class="view_btn">
								<!-- Trigger the modal with a button -->
								<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="static" data-keyboard="false">Run Strategy</button>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div id="james_o_shaughessys_strategy">
					<div class="strategy_view">
						<h2>James O'Shaughnessy</h2>
						<div class="startegy_content">
							<h3>Spent more than 30 years researching equity market returns. His work has brought to the fore the power of what he calls 'fundamental quant' from that he built a fund management business with nearly $7 billion under management.</h3>
							<div class="view_btn">
								<!-- Trigger the modal with a button -->
								<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="static" data-keyboard="false">Run Strategy</button>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div id="joel_greenblatts_strategy">
					<div class="strategy_view">
						<h2>Joel Greenblatt</h2>
						<div class="startegy_content">
							<h3>Introduced an investment strategy of "magic formula investing", Through his firm Gotham Capital, Greenblatt presided over an annualized return of 40% from 1985 to 2006.</h3>
							<div class="view_btn">
								<!-- Trigger the modal with a button -->
								<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-backdrop="static" data-keyboard="false">Run Strategy</button>
							</div>
						</div>
					</div>
				</div>
			</li>
		</ol>
	</div>

	<!-- Modal -->
  <div class="modal fade" id="strategyModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body">
        </div>
        <div class="modal-footer" style="display: none;">
        </div>
      </div>
      
    </div>
  </div>

	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/celebrity_investors_strategies.js"></script>
</body>
</html>