<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld" %>
<c:set var="databuyers" value="<%=RequestConstans.Vendor.SEARCHDATABUYERS %>"> </c:set>

<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
	<title>Fin Vendor | Vendor</title>
    <link href="<%=request.getContextPath() %>/resources/css/singleasset.css" type="text/css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/resources/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/resources/css/reset.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/resources/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/resources/css/style-II.css" rel="stylesheet" type="text/css"/>
       <!-- Date Picker CSS Starts -->
    <link href="<%=request.getContextPath() %>/resources/datepick/jsDatePick_ltr.min.css" rel="stylesheet" type="text/css"/>
    <!-- Date Picker CSS Ends -->
    <!-- File Upload CSSS -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<div class="top_header">
			<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
		</div>
		<div><br/></div>
    <div class="wrapper">  
        <div class="row-fluid">
					<div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
							</div>
							<div class="portlet-body form">
								<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
									<div class="Rowtable">
										 <%-- <div class="ColumnCommonmyprofile" id="change3"> <div class="lable_header" id="interdiv3"> <a id="ancho3" href="#tab4" class="lable_header" data-toggle="tab" onclick="activeMode('${databuyers}');">Search Data-Buyers</a> </div> </div> --%>
										</div>
										<div class="tab-content" style="background-color: white;">
								 		<div class="tab-pane active" id="tab4">
											<div class="Rowtableinfoval"><font id="databuyerstabsucessmessage" style="font-size: 14px;font-family:Open Sans, sans-serif; font-weight: bold; position: absolute; color: #2AABAB; padding-left: 585px; margin-top: -2px;"></font>
											<!-- <div class="ColumnCommonmyprofile"><div class="lable_header">Search Data-Buyers</div></div> -->
											</div>
											<div><br/> </div>
											<div class="Row">
											<div class="ColumnCommonSingletwo">
											<div class="control-group">
												<label class="control-label">Data-Buyer Name<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="databuyername" placeholder="Data-Buyer Name" name="databuyername" class="m-wrap large" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">Focus-Region<span class="required">*</span></label>
												<div class="controls">
													<select name="focusregion" multiple="multiple" id="focusregion" style="width: 205px; ">
													     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
														     <c:forEach var="regions" items="${regions}">
														    	<option value="${regions.name}">${regions.name}</option>
														 	 </c:forEach>
													</select> 
												  <div class="selectOptions">Choose one or more options</div>
												</div>
											</div>
											</div>
											<div class="ColumnCommonSingletwo">
											<div class="control-group">
												<label class="control-label">Focus-Asset-class<span class="required">*</span></label>
												<div class="controls">
													<select name="focusassetclass" onchange="loadFocusSecurityTypes(this.value);" id="focusassetclass" style="width: 220px;">
													     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
													     <c:forEach var="assetClasses" items="${assetClasses}">
													    	<option value="${assetClasses.description}">${assetClasses.description}</option>
													 	 </c:forEach>
													 </select>
													<!-- <input type="text" id="focusassetclass" placeholder="Focus-Asset-class" name="focusassetclass" class="m-wrap large" /> -->
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">Focus-Security-Type<span class="required">*</span></label>
												<div class="controls">
													 <select name="awardsecuritytype"  id="assetclassVendorloadFocusSecurityTypes" multiple="multiple" style="width: 220px;">
													  	 <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
													     <c:forEach var="assetclassVendorloadFocusSecurityTypes" items="${assetclassVendorloadFocusSecurityTypes}">
														    <option value="${assetclassVendorloadFocusSecurityTypes.securityType.name}">${assetclassVendorloadFocusSecurityTypes.securityType.name}</option>
														  </c:forEach>
													 </select>
													 <div class="selectOptions">Choose one or more options</div>
													 </div>
											</div>
											</div>
											</div>
											
											<div><br/></div>
											<div class="portlet-body">
												
												<table class="table table-striped table-bordered table-hover table-full-width">
													<thead style="background-color:#7BCCA5;">
														<tr>
															<th>Data-Buyer Name</th>
															<th>Focus-Region</th>
															<th>Focus-Asset-class</th>
															<th>Focus-Security-Type</th>
														</tr>
													</thead>
													<tbody>
														<tr>
													   		<td>Fime</td>
															<td>Asia</td>
															<td>FI</td>
															<td>Market</td>
														</tr>
														<tr>
													   		<td>AXA fin</td>
															<td>Asia</td>
															<td>Equities</td>
															<td>Common-Stocks</td>
														</tr>
													</tbody>
												</table>
											</div>
											
											<div><br/></div>
												<div class="form-actions clearfix">
												<div class="se" style="padding-left: 150px;">
													<a class="btn" data-toggle="tab" class="step" onclick="updateVendorSearchDataBuyers();">Search</a>
													<input type="reset" value="Reset" class="btn"/>
												</div>
												</div>
									</div>   
											
									 
									
										</div>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				</div>
				</div>
    </div>
    </div>
    <!-- END CONTAINER -->
    <div class="footer_area">
	  <jsp:include page="common/dashboardfooter.jsp" ></jsp:include>
	</div>
<script type="text/javascript">
window.onload = function(){
	var searchdatabuyers= '<%=RequestConstans.Vendor.SEARCHDATABUYERS %>';
	if(searchdatabuyers != null && searchdatabuyers.length > 0 && searchdatabuyers.match("searchdatabuyers")){
		document.getElementById('change3').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdiv3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('ancho3').style.backgroundColor = '#5CE5E5';
	}
};
</script>
<!-- tab active code end here-->

<!-- popup window plugins start-->
<script src="<%=request.getContextPath() %>/resources/js/popup.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/jquery-min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/modernizr.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/bootstrap-min.js" type="text/javascript"></script>
 <!-- popup window plugins ends-->

<!-- Add to table plugins start-->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
<!-- Add to table plugins end here-->
</body>
</html>
