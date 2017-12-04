<<<<<<< HEAD
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld" %>
<%@page import="com.finvendor.util.RequestConstans"%>
<c:set var="zerovalue" value="<%=RequestConstans.ConstantValues.ZERO_VALUE %>"> </c:set>
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
	<div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.MarketAggregators.MARKETAGGREGATORS%>?RaYUnA=${l:encrypt(username)}">Market Data Vendors (Aggregators) Directory</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Result</div></div>
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
			<thead style="background-color: #7BCCA5; color:#FFF;">
					<tr>
						<th style="width: 180px; padding: 0px 0px 29px 0px;">Vendor Name</th>
						<th>Corp<br/>Action <br/>Covered?</th>
						<th>Exchange <br/>Coverage</th>
						<th>Country<br/>Coverage</th>
						<th>Feed Type</th>
						<th>No. of<br/>Data Attribiutes<br/>Covered</th>
						<th>Cost</th>
						<th>Sample <br/>Data</th>
					</tr>
				</thead>
				 
				<tbody>
				<c:choose>
		          <c:when test="${not empty assetClassDataInfoList}">
		          
		          <c:forEach var="assetClassDataInfoList" items="${assetClassDataInfoList}">
		          <tr>
						<td> <div><c:if test="${assetClassDataInfoList.company == 'JP Morgan Fin Corp'}">
								<img src="<%=request.getContextPath() %>/resources/images/JPMorgan.jpg" width="35%" align="left"></img>
								</c:if>
								<c:if test="${assetClassDataInfoList.company == 'Barclays Inc'}">
								<img src="<%=request.getContextPath() %>/resources/images/Barclays.jpg" width="35%" align="left"></img>
								</c:if>
								<c:if test="${assetClassDataInfoList.company == 'AXA Fin Corp'}">
								<img src="<%=request.getContextPath() %>/resources/images/AXA.jpg" width="35%" align="left"></img>
								</c:if>
								<c:if test="${assetClassDataInfoList.company == 'America Fin Corp'}">
								<img src="<%=request.getContextPath() %>/resources/images/America.jpg" width="35%" align="left"></img>
								</c:if>
								<c:if test="${assetClassDataInfoList.company == 'Elite Fin Corp'}">
								<img src="<%=request.getContextPath() %>/resources/images/Elite.jpg" width="35%" align="left"></img>
								</c:if> <span> ${assetClassDataInfoList.company}</span> </div> </td>
						<td> <div>Yes</div></td>
						<td>  <div> ${assetClassDataInfoList.exchange_id}</div></td>
						<td> <div> ${assetClassDataInfoList.country_id} </div></td>
						<td> <div>Eod<br/>Real Time<br/>Historical</div></td>
						<td> <div> 10</div></td>
						<td> <div>${assetClassDataInfoList.cost_range} </div></td>
						<td> <div>Link</div></td>
					</tr>
					<tr><td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
					</tr>
					</c:forEach>
					
					</c:when>
					<c:otherwise>
				    <br/><br/>
				        <div><br/>
				      		<h2 align="center" ><span style="color: #FF2B2B; font-size:18px; font-family:open_sansregular;">
				      		No records were found that match the specified search criteria</span></h2>
				      </div>
					 </c:otherwise>
					</c:choose>
				</tbody>
		</table>
	</div>
</div>

<jsp:include page="common/footer.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/resources/js/popup.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/jquery-min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/modernizr.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/bootstrap-min.js" type="text/javascript"></script>
</body>
=======
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld" %>
<%@page import="com.finvendor.util.RequestConstans"%>
<c:set var="zerovalue" value="<%=RequestConstans.ConstantValues.ZERO_VALUE %>"> </c:set>
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
	<div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.MarketAggregators.MARKETAGGREGATORS%>?RaYUnA=${l:encrypt(username)}">Market Data Vendors (Aggregators) Directory</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Result</div></div>
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
			<thead style="background-color: #7BCCA5; color:#FFF;">
					<tr>
						<th style="width: 180px; padding: 0px 0px 29px 0px;">Vendor Name</th>
						<th>Corp<br/>Action <br/>Covered?</th>
						<th>Exchange <br/>Coverage</th>
						<th>Country<br/>Coverage</th>
						<th>Feed Type</th>
						<th>No. of<br/>Data Attribiutes<br/>Covered</th>
						<th>Cost</th>
						<th>Sample <br/>Data</th>
					</tr>
				</thead>
				 
				<tbody>
				<c:choose>
		          <c:when test="${not empty assetClassDataInfoList}">
		          
		          <c:forEach var="assetClassDataInfoList" items="${assetClassDataInfoList}">
		          <tr>
						<td> <div><c:if test="${assetClassDataInfoList.company == 'JP Morgan Fin Corp'}">
								<img src="<%=request.getContextPath() %>/resources/images/JPMorgan.jpg" width="35%" align="left"></img>
								</c:if>
								<c:if test="${assetClassDataInfoList.company == 'Barclays Inc'}">
								<img src="<%=request.getContextPath() %>/resources/images/Barclays.jpg" width="35%" align="left"></img>
								</c:if>
								<c:if test="${assetClassDataInfoList.company == 'AXA Fin Corp'}">
								<img src="<%=request.getContextPath() %>/resources/images/AXA.jpg" width="35%" align="left"></img>
								</c:if>
								<c:if test="${assetClassDataInfoList.company == 'America Fin Corp'}">
								<img src="<%=request.getContextPath() %>/resources/images/America.jpg" width="35%" align="left"></img>
								</c:if>
								<c:if test="${assetClassDataInfoList.company == 'Elite Fin Corp'}">
								<img src="<%=request.getContextPath() %>/resources/images/Elite.jpg" width="35%" align="left"></img>
								</c:if> <span> ${assetClassDataInfoList.company}</span> </div> </td>
						<td> <div>Yes</div></td>
						<td>  <div> ${assetClassDataInfoList.exchange_id}</div></td>
						<td> <div> ${assetClassDataInfoList.country_id} </div></td>
						<td> <div>Eod<br/>Real Time<br/>Historical</div></td>
						<td> <div> 10</div></td>
						<td> <div>${assetClassDataInfoList.cost_range} </div></td>
						<td> <div>Link</div></td>
					</tr>
					<tr><td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
						<td style="border: 1px;"><div style="height: 10px;"></div></td>
					</tr>
					</c:forEach>
					
					</c:when>
					<c:otherwise>
				    <br/><br/>
				        <div><br/>
				      		<h2 align="center" ><span style="color: #FF2B2B; font-size:18px; font-family:open_sansregular;">
				      		No records were found that match the specified search criteria</span></h2>
				      </div>
					 </c:otherwise>
					</c:choose>
				</tbody>
		</table>
	</div>
</div>

<jsp:include page="common/footer.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/resources/js/popup.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/jquery-min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/modernizr.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/bootstrap-min.js" type="text/javascript"></script>
</body>
>>>>>>> origin/master
</html>