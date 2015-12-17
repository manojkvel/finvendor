<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld" %>
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
    <div class="wrapper">  
    <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}"> Market Data Aggregators (MA) Vendor</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
        <div class="control-group">
        	<input type="radio" name="radios"   value="savedsearch" id="savesearch">
			<label class="control-label" style="margin-left: 21px; margin-top: -15px;">Search Using Saved Searches<span class="required">*</span></label>
		</div>
        <div class="control-groupcheckingforradiobuttons">
        	<input  type="radio" id="singleform" name="radios">
			<label class="control-label" style="margin-left: 21px; margin-top: -15px;">Search Vendor for Single MA Type<span class="required">*</span></label>
			<input  type="radio" name="radios" id="multiform" style="margin-left: 371px; margin-top: -49px;">
			<label class="control-label" style="margin-left: 393px; margin-top: -45px;">Search Vendor for Multiple MA Type<span class="required">*</span></label>
		</div>
        <div class="row-fluid">
	   <div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
							</div>
							  <div class="portlet-body form">
							  
							  <!-- Search using saved search starts here -->
							  <div id="savedsearchform">
								<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
										<div class="tab-content" style="background-color: white;">
											<!-- Support coverage start --> 
												<div class="tab-pane active">
												<div><br/></div>
												 <div class="Row">
													<div class="ColumnCommonvendormarket">
														<div class="control-group">
															<label class="control-label">Trade Asset Class<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass">
																     <option value ="-SELECT-" selected="selected"> -SELECT- </option>
				     	 											 <option>Saved Searches1</option>
															     	 <option>Saved Searches2</option>
																     <option>Saved Searches3</option>
																     <option>Saved Searches4</option>
																     <option>Saved Searches5</option>
																 </select>
															</div>
														</div>
													</div>
												</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se" style="padding-left: 150px;">
										<input type="reset" value="Search" class="btn" />
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
										</div>
								</div>
								</form>
								</div>
								<!-- Search using saved search end here -->
								
								<!-- Single Marketing Aggregator Vendor Start here -->
								<div id="searchsingleform">
									<form action="<%=request.getContextPath() %>/singleassetsearchresult?RaYvEmUl=${l:encrypt(username)}" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									  <jsp:include page="marketaggregators/singleassetinfoclass.jsp"></jsp:include>
									</form>
								</div>
								<!-- Single Marketing Aggregator vendor End here -->
								
								<!-- Multi Marketing Aggregator Vendor Start here -->
								<div id="searchmultiform">
									<form action="<%=request.getContextPath() %>/multiassetsearchresult?RaYvEmUl=${l:encrypt(username)}" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									  <jsp:include page="marketaggregators/multiassetinfoclass.jsp"></jsp:include>
									</form>
								</div>
								
								</div>
								<!-- Multi Marketing Aggregator Vendor ends here -->
								
								
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

<!-- Date plugins start-->
		<script>
		  $(function() {
		    $( "#inputField" ).datepicker();
		    $( "#inputField1" ).datepicker();
		  });
	  </script>
	  <script src="//code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js" type="text/javascript"></script>
  
  <!-- Date plugins start-->
  
</body>
</html>
