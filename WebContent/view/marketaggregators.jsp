<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
	<title>Fin Vendor | Vendor</title>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	<div class="container">  
    <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}"> Market Data Aggregators (MA) Vendor</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
    <br>
        <div class="control-group">
        	
			<label class="control-labelappstra" ><input type="checkbox" name="savesearch" id="savesearch" value="savesearch" >Search Using Saved Searches
			
			
			<select name="assetclass" style="visibility: hidden;" onchange="loadSecurityTypes(this.value);" id="saveSearchList">
																     <option value ="-SELECT-" selected="selected"> -SELECT- </option>
				     	 											 <option>Saved Searches1</option>
															     	 <option>Saved Searches2</option>
																     <option>Saved Searches3</option>
																     <option>Saved Searches4</option>
																     <option>Saved Searches5</option>
																 </select>
			</label>
			
		</div>
        <div class="control-groupcheckingforradiobuttons">
        	<input  type="radio" id="singleform" name="radios" checked="checked">
			<label class="control-label" style="margin-left: 21px; margin-top: -15px;">Search Vendor for Single MA Type<span class="required">*</span></label>
			<input  type="radio" name="radios" id="multiform" style="margin-left: 371px; margin-top: -40px;" >
			<label class="control-label" style="margin-left: 393px; margin-top: -35px;">Search Vendor for Multiple MA Type<span class="required">*</span></label>
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
    <jsp:include page="common/footer.jsp"></jsp:include>
  
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
