<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld" %>
<%@page import="com.finvendor.util.RequestConstans"%>
<c:set var="zerovalue" value="<%=RequestConstans.ConstantValues.ZERO_VALUE %>"> </c:set>
<c:set var="marketAggregators" value="<%=RequestConstans.MarketAggregators.MULTI_ASSET_CLASS_SEARCH_RESULT %>"> </c:set>
<c:set var="researchReportProviders" value="<%=RequestConstans.ResearchReportProviders.MULTI_ASSET_CLASS_SEARCH_RESULT%>"> </c:set>
<c:set var="financialAnalytics" value="<%=RequestConstans.FinancialAnalyticsApplication.MULTI_ASSET_CLASS_SEARCH_RESULT%>"> </c:set>
<c:set var="tradingApplication" value="<%=RequestConstans.TradingApplication.MULTI_ASSET_CLASS_SEARCH_RESULT%>"> </c:set>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>finvendor</title>
    <style type="">
    tbody:before {
	    content: "-";
	    display: block;
	    line-height: 2em;
	    color: transparent;
	}
    </style>
</head>
<body>
<jsp:include page="common/dashboardheader.jsp"></jsp:include>
<div class="container">
	<div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.MarketAggregators.MARKETAGGREGATORS%>?RaYUnA=${l:encrypt(username)}">Market Data Vendors (Aggregators) Directory ></a> </div>  <div class="text_arw">Result</div></div>
	<div class="right_nav_area_1">
	<div class="right_nav_1">
	 <ul>
	  <!-- 
	 <li><a href="#">INDEX</a></li>
	 <li style="padding-left: 880px;"><a class="#" data-toggle="modal" href="#normalModal">Modify Search</a></li>
	  -->
	 <li style="padding-left: 880px;"><a class="#" onclick="window.history.back()">Modify Search</a></li> 
	 </ul>
	</div>
	<div id="normalModal" class="modal fade">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title btpopup">Single Asset Search</h4>
		      </div>
		      <div class="modal-body">
		         <input type="file" name="fileUpload" class="m-wrap largefileuploadconspopup" id="fileUpload">&nbsp;&nbsp;&nbsp;&nbsp;<a class="fileupload btnpopup btn-default">Upload</a>
		         <div id="selectedFiles"></div>
		         <div><br></div>
		         <a class="btnpopup btn-default" onclick="loadCheckBoxes();">Remove</a> 
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div> 
		  </div> 
	</div>
	</div>
	<div class="portlet-body">
	
		<table class="table table-striped table-borderedsearch table-hover table-full-width" id="sample_1">
		<c:if test="${result eq marketAggregators}">
			<thead style="background-color: #7BCCA5; color:#FFF;">
					<tr>
						<th>Vendor Name</th>
						<th>Asset Class</th>
						<th>Coverage Region</th>
						<th>Coverage Country</th>
						<th>Coverage Exchange</th>
						<th>Cost</th>
					</tr>
				</thead>
				 
				<tbody>
				<c:choose>
		          <c:when test="${not empty marketDataAggregatorsVendorSearchs}">
		          
		          <c:forEach var="marketDataAggregatorsVendorSearch" items="${marketDataAggregatorsVendorSearchs}">
		          <tr>
		          
						<td><div>${marketDataAggregatorsVendorSearch.vendor}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.assetClass}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.dataCoverageRegion}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.dataCoverageCountry}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.dataCoverageExchange}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.dataAcquisitionCostRange}</div></td>
					</tr>
					</c:forEach>
					
					</c:when>
					<c:otherwise>
				    <br/><br/>
				        <div><br/>
				      		<h2 align="center" ><span style="color: #FF2B2B; font-size:18px; font-family:open_sansregular;">
				      		No records were found that match the specified search criteria or<i> Mandatory fields are not selected</i> </span></h2>
				      </div>
					 </c:otherwise>
					</c:choose>
				</tbody>
			</c:if>
			<c:if test="${result eq researchReportProviders}">
			<thead style="background-color: #7BCCA5; color:#FFF;">
					<tr>
					
						<th>Vendor Name</th>
						<th>Research Area</th>
						<th>Analyst Awards</th>
						<th>Analyst Name</th>
						<th>Coverage Country</th>
						<th>Coverage Region</th>
						
					</tr>
				</thead>
				 
				<tbody>
				<c:choose>
		          <c:when test="${not empty marketDataAggregatorsVendorSearchs}">
		          
		          <c:forEach var="marketDataAggregatorsVendorSearch" items="${marketDataAggregatorsVendorSearchs}">
		          <tr>
		          	
						<td><div>${marketDataAggregatorsVendorSearch.vendor}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.researchArea}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.analystAwards}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.analystName}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.countryName}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.region}</div></td>
						
					</tr>
					</c:forEach>
					
					</c:when>
					<c:otherwise>
				    <br/><br/>
				        <div><br/>
				      		<h2 align="center" ><span style="color: #FF2B2B; font-size:18px; font-family:open_sansregular;">
				      		No records were found that match the specified search criteria or<i> Mandatory fields are not selected</i> </span></h2>
				      </div>
					 </c:otherwise>
					</c:choose>
				</tbody>
			</c:if>	
			<c:if test="${result eq financialAnalytics}">
			<thead style="background-color: #7BCCA5; color:#FFF;">
					<tr>
					
						<th>Vendor Name</th>
						<th>Analytics Solutions Type</th>
						<th>Suitability</th>
						<th>Accessibility</th>
						<th>Coverage Country</th>
						<th>Coverage Region</th>
						
					</tr>
				</thead>
				 
				<tbody>
				<c:choose>
		          <c:when test="${not empty marketDataAggregatorsVendorSearchs}">
		          
		          <c:forEach var="marketDataAggregatorsVendorSearch" items="${marketDataAggregatorsVendorSearchs}">
		          <tr>
		          
						<td><div>${marketDataAggregatorsVendorSearch.vendor}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.analyticssolutionsType}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.suitability}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.accessibility}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.dataCoverageCountry}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.dataCoverageExchange}</div></td>
					</tr>
					</c:forEach>
					
					</c:when>
					<c:otherwise>
				    <br/><br/>
				        <div><br/>
				      		<h2 align="center" ><span style="color: #FF2B2B; font-size:18px; font-family:open_sansregular;">
				      		No records were found that match the specified search criteria or<i> Mandatory fields are not selected</i> </span></h2>
				      </div>
					 </c:otherwise>
					</c:choose>
				</tbody>
			</c:if>	
			<c:if test="${result eq tradingApplication}">
			<thead style="background-color: #7BCCA5; color:#FFF;">
					<tr>
					
						<th>Vendor Name</th>
						<th>Asset Class</th>
						<th>Suitability</th>
						<th>Accessibility</th>
						<th>Order Type</th>
						<th>Coverage Region</th>
						<th>Coverage Country</th>
					</tr>
				</thead>
				 
				<tbody>
				<c:choose>
		          <c:when test="${not empty marketDataAggregatorsVendorSearchs}">
		          
		          <c:forEach var="marketDataAggregatorsVendorSearch" items="${marketDataAggregatorsVendorSearchs}">
		          <tr>
		          
						<td><div>${marketDataAggregatorsVendorSearch.vendor}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.assetClass}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.accessibility}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.suitability}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.orderType}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.coverageRegion}</div></td>
						<td><div>${marketDataAggregatorsVendorSearch.coverageCountry}</div></td>
					</tr>
					</c:forEach>
					
					</c:when>
					<c:otherwise>
				    <br/><br/>
				        <div><br/>
				      		<h2 align="center" ><span style="color: #FF2B2B; font-size:18px; font-family:open_sansregular;">
				      		No records were found that match the specified search criteria or<i> Mandatory fields are not selected</i> </span></h2>
				      </div>
					 </c:otherwise>
					</c:choose>
				</tbody>
			</c:if>	
				
		</table>
	</div>
</div>

<jsp:include page="common/footer.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/resources/js/popup.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/jquery-min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/modernizr.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/bootstrap-min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/singleasset/js/script.js"></script>
</body>
</html>