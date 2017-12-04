<<<<<<< HEAD
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>

<c:set var="consumermyoffermarketdataneeds" value="<%=RequestConstans.Consumer.CONSUMER_MYOFFER_MARKET_DATA_NEEDS %>"> </c:set>
<c:set var="consumermyoffertradingapplicationneeds" value="<%=RequestConstans.Consumer.CONSUMER_MYOFFER_TRADING_APPLICATION_NEEDS %>"> </c:set>
<c:set var="consumermyofferanalyticsapplicationneeds" value="<%=RequestConstans.Consumer.CONSUMER_MYOFFER_ANALYTICS_APPLICATION_NEEDS %>"> </c:set>
<c:set var="consumermyofferresearchreportneeds" value="<%=RequestConstans.Consumer.CONSUMER_MYOFFER_RESEARCH_REPORT_NEEDS %>"> </c:set>


<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
	<title>finvendor</title>
    <!-- Date Picker CSS Ends -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	<div class="container">  
        <div class="row-fluid">
					<div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
							</div>
							<div class="portlet-body form">
								<form action="#" class="form-horizontal" id="submit_form" method="post">
									<div class="form-wizard">
										<div class="Rowtable">
										<div class="ColumnCommonmyofferings" id="changeconsumermarketoffer1"> <div class="lable_header" id="interdivconsumermarketoffer1"> <a id="anchoconsumermarketoffer1" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeCosumerModeMyOffer('${consumermyoffermarketdataneeds}');">Market Data Needs</a> </div></div>
										<div class="ColumnCommonmyofferings" id="changeconsumermarketoffer2"> <div class="lable_header" id="interdivconsumermarketoffer2">  <a id="anchoconsumermarketoffer2" href="#tab2" class="lable_header" data-toggle="tab" onclick="activeCosumerModeMyOffer('${consumermyoffertradingapplicationneeds}');">Trading Application Needs</a> </div> </div>
										<div class="ColumnCommonmyofferings" id="changeconsumermarketoffer3"> <div class="lable_header" id="interdivconsumermarketoffer3">  <a id="anchoconsumermarketoffer3" href="#tab3" class="lable_header" data-toggle="tab" onclick="activeCosumerModeMyOffer('${consumermyofferanalyticsapplicationneeds}');">Analytics Application Needs</a> </div> </div>
										<div class="ColumnCommonmyofferings" id="changeconsumermarketoffer4"> <div class="lable_header" id="interdivconsumermarketoffer4">  <a id="anchoconsumermarketoffer4" href="#tab4" class="lable_header" data-toggle="tab" onclick="activeCosumerModeMyOffer('${consumermyofferresearchreportneeds}');">Research Report Needs</a> </div> </div>
										</div>
										<div class="tab-content" style="background-color: white;">
										<div class="tab-pane active" id="tab1">
												<div><br/> </div>
												<div class="Rowtableinfoval">
												<div class="image-upload" style="margin-left: 252px;">
												    <label for="fileUploadmyoffercoverage" class="control-label-fileupload" >File Upload (excel or csv)<span class="required">*</span> 
												        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath() %>/resources/images/csv.png" style="padding:0px 0px 4px 0px;"/><span style="line-height: 120%;">/</span><img src="<%=request.getContextPath() %>/resources/images/xls.png" style="padding:0px 0px 4px 0px;" /> &nbsp;&nbsp;&nbsp;&nbsp; 
												    </label>
												    <input id="fileUploadmyoffercoverage" type="file" type="hidden"/>
												</div>
												<div class="ColumnCommonray">
												<a class="fileupmyoffercoverage"><span class="lable_headeractions"><img src="<%=request.getContextPath() %>/resources/images/attachment.png"/>Attachment</span></a>
												</div>
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3">
													<div class="control-group">
														<label class="control-label">Subscription Name<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoverageregion" multiple="multiple" id="supportcoverageregion">
														     	<option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Activation Date<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry">
															     <option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpagetab3">
													<div class="control-group">
														<label class="control-label">Expiry Date<span class="required">*</span></label>
														<div class="controls">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime">
															     <option value ="-SELECT-"> -SELECT- </option>
																	<c:forEach var="supports" items="${supports}">
																    	<option value="${supports.name}">${supports.name}</option>
																 	</c:forEach>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Status<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="phonenumber" placeholder="Phone Number" name="phonenumber" class="m-wrap largeval"/>
														</div>
													</div>
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Subscription Name</th>
															<th>Activation Date</th>
															<th>Expiry Date</th>
											                <th>Status</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab2" class="btn button-next" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyoffertradingapplicationneeds}');">Continue</a>
									</div>
									</div>
											</div>
											
												<div class="tab-pane" id="tab2">
														<div><br/> </div>
												<div class="Rowtableinfoval">
												<div class="image-upload" style="margin-left: 252px;">
												    <label for="fileUploadmyoffercoverage" class="control-label-fileupload" >File Upload (excel or csv)<span class="required">*</span> 
												        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath() %>/resources/images/csv.png" style="padding:0px 0px 4px 0px;"/><span style="line-height: 120%;">/</span><img src="<%=request.getContextPath() %>/resources/images/xls.png" style="padding:0px 0px 4px 0px;" /> &nbsp;&nbsp;&nbsp;&nbsp; 
												    </label>
												    <input id="fileUploadmyoffercoverage" type="file" type="hidden"/>
												</div>
												<div class="ColumnCommonray">
												<a class="fileupmyoffercoverage"><span class="lable_headeractions"><img src="<%=request.getContextPath() %>/resources/images/attachment.png"/>Attachment</span></a>
												</div>
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3">
													<div class="control-group">
														<label class="control-label">Subscription Name<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoverageregion" multiple="multiple" id="supportcoverageregion">
														     	<option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Activation Date<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry">
															     <option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpagetab3">
													<div class="control-group">
														<label class="control-label">Expiry Date<span class="required">*</span></label>
														<div class="controls">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime">
															     <option value ="-SELECT-"> -SELECT- </option>
																	<c:forEach var="supports" items="${supports}">
																    	<option value="${supports.name}">${supports.name}</option>
																 	</c:forEach>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Status<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="phonenumber" placeholder="Phone Number" name="phonenumber" class="m-wrap largeval"/>
														</div>
													</div>
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Subscription Name</th>
															<th>Activation Date</th>
															<th>Expiry Date</th>
											                <th>Status</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab1" class="btn button-previous" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyoffermarketdataneeds}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab3" class="btn button-next" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyofferanalyticsapplicationneeds}');">Continue</a>
									</div>
									</div>
									</div>
									
									<div class="tab-pane" id="tab3">
														<div><br/> </div>
												<div class="Rowtableinfoval">
												<div class="image-upload" style="margin-left: 252px;">
												    <label for="fileUploadmyoffercoverage" class="control-label-fileupload" >File Upload (excel or csv)<span class="required">*</span> 
												        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath() %>/resources/images/csv.png" style="padding:0px 0px 4px 0px;"/><span style="line-height: 120%;">/</span><img src="<%=request.getContextPath() %>/resources/images/xls.png" style="padding:0px 0px 4px 0px;" /> &nbsp;&nbsp;&nbsp;&nbsp; 
												    </label>
												    <input id="fileUploadmyoffercoverage" type="file" type="hidden"/>
												</div>
												<div class="ColumnCommonray">
												<a class="fileupmyoffercoverage"><span class="lable_headeractions"><img src="<%=request.getContextPath() %>/resources/images/attachment.png"/>Attachment</span></a>
												</div>
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3">
													<div class="control-group">
														<label class="control-label">Subscription Name<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoverageregion" multiple="multiple" id="supportcoverageregion">
														     	<option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Activation Date<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry">
															     <option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpagetab3">
													<div class="control-group">
														<label class="control-label">Expiry Date<span class="required">*</span></label>
														<div class="controls">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime">
															     <option value ="-SELECT-"> -SELECT- </option>
																	<c:forEach var="supports" items="${supports}">
																    	<option value="${supports.name}">${supports.name}</option>
																 	</c:forEach>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Status<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="phonenumber" placeholder="Phone Number" name="phonenumber" class="m-wrap largeval"/>
														</div>
													</div>
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Subscription Name</th>
															<th>Activation Date</th>
															<th>Expiry Date</th>
											                <th>Status</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab2" class="btn button-previous" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyoffertradingapplicationneeds}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab4" class="btn button-next" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyofferresearchreportneeds}');">Continue</a>
									</div>
									</div>
									</div>
									<div class="tab-pane" id="tab4">
														<div><br/> </div>
												<div class="Rowtableinfoval">
												<div class="image-upload" style="margin-left: 252px;">
												    <label for="fileUploadmyoffercoverage" class="control-label-fileupload" >File Upload (excel or csv)<span class="required">*</span> 
												        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath() %>/resources/images/csv.png" style="padding:0px 0px 4px 0px;"/><span style="line-height: 120%;">/</span><img src="<%=request.getContextPath() %>/resources/images/xls.png" style="padding:0px 0px 4px 0px;" /> &nbsp;&nbsp;&nbsp;&nbsp; 
												    </label>
												    <input id="fileUploadmyoffercoverage" type="file" type="hidden"/>
												</div>
												<div class="ColumnCommonray">
												<a class="fileupmyoffercoverage"><span class="lable_headeractions"><img src="<%=request.getContextPath() %>/resources/images/attachment.png"/>Attachment</span></a>
												</div>
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3">
													<div class="control-group">
														<label class="control-label">Subscription Name<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoverageregion" multiple="multiple" id="supportcoverageregion">
														     	<option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Activation Date<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry">
															     <option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpagetab3">
													<div class="control-group">
														<label class="control-label">Expiry Date<span class="required">*</span></label>
														<div class="controls">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime">
															     <option value ="-SELECT-"> -SELECT- </option>
																	<c:forEach var="supports" items="${supports}">
																    	<option value="${supports.name}">${supports.name}</option>
																 	</c:forEach>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Status<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="phonenumber" placeholder="Phone Number" name="phonenumber" class="m-wrap largeval"/>
														</div>
													</div>
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Subscription Name</th>
															<th>Activation Date</th>
															<th>Expiry Date</th>
											                <th>Status</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab3" class="btn button-previous" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyofferanalyticsapplicationneeds}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
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
    <jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>
=======
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>

<c:set var="consumermyoffermarketdataneeds" value="<%=RequestConstans.Consumer.CONSUMER_MYOFFER_MARKET_DATA_NEEDS %>"> </c:set>
<c:set var="consumermyoffertradingapplicationneeds" value="<%=RequestConstans.Consumer.CONSUMER_MYOFFER_TRADING_APPLICATION_NEEDS %>"> </c:set>
<c:set var="consumermyofferanalyticsapplicationneeds" value="<%=RequestConstans.Consumer.CONSUMER_MYOFFER_ANALYTICS_APPLICATION_NEEDS %>"> </c:set>
<c:set var="consumermyofferresearchreportneeds" value="<%=RequestConstans.Consumer.CONSUMER_MYOFFER_RESEARCH_REPORT_NEEDS %>"> </c:set>


<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
	<title>finvendor</title>
    <!-- Date Picker CSS Ends -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	<div class="container">  
        <div class="row-fluid">
					<div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
							</div>
							<div class="portlet-body form">
								<form action="#" class="form-horizontal" id="submit_form" method="post">
									<div class="form-wizard">
										<div class="Rowtable">
										<div class="ColumnCommonmyofferings" id="changeconsumermarketoffer1"> <div class="lable_header" id="interdivconsumermarketoffer1"> <a id="anchoconsumermarketoffer1" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeCosumerModeMyOffer('${consumermyoffermarketdataneeds}');">Market Data Needs</a> </div></div>
										<div class="ColumnCommonmyofferings" id="changeconsumermarketoffer2"> <div class="lable_header" id="interdivconsumermarketoffer2">  <a id="anchoconsumermarketoffer2" href="#tab2" class="lable_header" data-toggle="tab" onclick="activeCosumerModeMyOffer('${consumermyoffertradingapplicationneeds}');">Trading Application Needs</a> </div> </div>
										<div class="ColumnCommonmyofferings" id="changeconsumermarketoffer3"> <div class="lable_header" id="interdivconsumermarketoffer3">  <a id="anchoconsumermarketoffer3" href="#tab3" class="lable_header" data-toggle="tab" onclick="activeCosumerModeMyOffer('${consumermyofferanalyticsapplicationneeds}');">Analytics Application Needs</a> </div> </div>
										<div class="ColumnCommonmyofferings" id="changeconsumermarketoffer4"> <div class="lable_header" id="interdivconsumermarketoffer4">  <a id="anchoconsumermarketoffer4" href="#tab4" class="lable_header" data-toggle="tab" onclick="activeCosumerModeMyOffer('${consumermyofferresearchreportneeds}');">Research Report Needs</a> </div> </div>
										</div>
										<div class="tab-content" style="background-color: white;">
										<div class="tab-pane active" id="tab1">
												<div><br/> </div>
												<div class="Rowtableinfoval">
												<div class="image-upload" style="margin-left: 252px;">
												    <label for="fileUploadmyoffercoverage" class="control-label-fileupload" >File Upload (excel or csv)<span class="required">*</span> 
												        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath() %>/resources/images/csv.png" style="padding:0px 0px 4px 0px;"/><span style="line-height: 120%;">/</span><img src="<%=request.getContextPath() %>/resources/images/xls.png" style="padding:0px 0px 4px 0px;" /> &nbsp;&nbsp;&nbsp;&nbsp; 
												    </label>
												    <input id="fileUploadmyoffercoverage" type="file" type="hidden"/>
												</div>
												<div class="ColumnCommonray">
												<a class="fileupmyoffercoverage"><span class="lable_headeractions"><img src="<%=request.getContextPath() %>/resources/images/attachment.png"/>Attachment</span></a>
												</div>
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3">
													<div class="control-group">
														<label class="control-label">Subscription Name<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoverageregion" multiple="multiple" id="supportcoverageregion">
														     	<option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Activation Date<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry">
															     <option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpagetab3">
													<div class="control-group">
														<label class="control-label">Expiry Date<span class="required">*</span></label>
														<div class="controls">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime">
															     <option value ="-SELECT-"> -SELECT- </option>
																	<c:forEach var="supports" items="${supports}">
																    	<option value="${supports.name}">${supports.name}</option>
																 	</c:forEach>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Status<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="phonenumber" placeholder="Phone Number" name="phonenumber" class="m-wrap largeval"/>
														</div>
													</div>
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Subscription Name</th>
															<th>Activation Date</th>
															<th>Expiry Date</th>
											                <th>Status</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab2" class="btn button-next" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyoffertradingapplicationneeds}');">Continue</a>
									</div>
									</div>
											</div>
											
												<div class="tab-pane" id="tab2">
														<div><br/> </div>
												<div class="Rowtableinfoval">
												<div class="image-upload" style="margin-left: 252px;">
												    <label for="fileUploadmyoffercoverage" class="control-label-fileupload" >File Upload (excel or csv)<span class="required">*</span> 
												        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath() %>/resources/images/csv.png" style="padding:0px 0px 4px 0px;"/><span style="line-height: 120%;">/</span><img src="<%=request.getContextPath() %>/resources/images/xls.png" style="padding:0px 0px 4px 0px;" /> &nbsp;&nbsp;&nbsp;&nbsp; 
												    </label>
												    <input id="fileUploadmyoffercoverage" type="file" type="hidden"/>
												</div>
												<div class="ColumnCommonray">
												<a class="fileupmyoffercoverage"><span class="lable_headeractions"><img src="<%=request.getContextPath() %>/resources/images/attachment.png"/>Attachment</span></a>
												</div>
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3">
													<div class="control-group">
														<label class="control-label">Subscription Name<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoverageregion" multiple="multiple" id="supportcoverageregion">
														     	<option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Activation Date<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry">
															     <option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpagetab3">
													<div class="control-group">
														<label class="control-label">Expiry Date<span class="required">*</span></label>
														<div class="controls">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime">
															     <option value ="-SELECT-"> -SELECT- </option>
																	<c:forEach var="supports" items="${supports}">
																    	<option value="${supports.name}">${supports.name}</option>
																 	</c:forEach>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Status<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="phonenumber" placeholder="Phone Number" name="phonenumber" class="m-wrap largeval"/>
														</div>
													</div>
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Subscription Name</th>
															<th>Activation Date</th>
															<th>Expiry Date</th>
											                <th>Status</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab1" class="btn button-previous" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyoffermarketdataneeds}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab3" class="btn button-next" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyofferanalyticsapplicationneeds}');">Continue</a>
									</div>
									</div>
									</div>
									
									<div class="tab-pane" id="tab3">
														<div><br/> </div>
												<div class="Rowtableinfoval">
												<div class="image-upload" style="margin-left: 252px;">
												    <label for="fileUploadmyoffercoverage" class="control-label-fileupload" >File Upload (excel or csv)<span class="required">*</span> 
												        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath() %>/resources/images/csv.png" style="padding:0px 0px 4px 0px;"/><span style="line-height: 120%;">/</span><img src="<%=request.getContextPath() %>/resources/images/xls.png" style="padding:0px 0px 4px 0px;" /> &nbsp;&nbsp;&nbsp;&nbsp; 
												    </label>
												    <input id="fileUploadmyoffercoverage" type="file" type="hidden"/>
												</div>
												<div class="ColumnCommonray">
												<a class="fileupmyoffercoverage"><span class="lable_headeractions"><img src="<%=request.getContextPath() %>/resources/images/attachment.png"/>Attachment</span></a>
												</div>
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3">
													<div class="control-group">
														<label class="control-label">Subscription Name<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoverageregion" multiple="multiple" id="supportcoverageregion">
														     	<option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Activation Date<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry">
															     <option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpagetab3">
													<div class="control-group">
														<label class="control-label">Expiry Date<span class="required">*</span></label>
														<div class="controls">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime">
															     <option value ="-SELECT-"> -SELECT- </option>
																	<c:forEach var="supports" items="${supports}">
																    	<option value="${supports.name}">${supports.name}</option>
																 	</c:forEach>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Status<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="phonenumber" placeholder="Phone Number" name="phonenumber" class="m-wrap largeval"/>
														</div>
													</div>
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Subscription Name</th>
															<th>Activation Date</th>
															<th>Expiry Date</th>
											                <th>Status</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab2" class="btn button-previous" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyoffertradingapplicationneeds}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab4" class="btn button-next" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyofferresearchreportneeds}');">Continue</a>
									</div>
									</div>
									</div>
									<div class="tab-pane" id="tab4">
														<div><br/> </div>
												<div class="Rowtableinfoval">
												<div class="image-upload" style="margin-left: 252px;">
												    <label for="fileUploadmyoffercoverage" class="control-label-fileupload" >File Upload (excel or csv)<span class="required">*</span> 
												        &nbsp;&nbsp;&nbsp;<img src="<%=request.getContextPath() %>/resources/images/csv.png" style="padding:0px 0px 4px 0px;"/><span style="line-height: 120%;">/</span><img src="<%=request.getContextPath() %>/resources/images/xls.png" style="padding:0px 0px 4px 0px;" /> &nbsp;&nbsp;&nbsp;&nbsp; 
												    </label>
												    <input id="fileUploadmyoffercoverage" type="file" type="hidden"/>
												</div>
												<div class="ColumnCommonray">
												<a class="fileupmyoffercoverage"><span class="lable_headeractions"><img src="<%=request.getContextPath() %>/resources/images/attachment.png"/>Attachment</span></a>
												</div>
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3">
													<div class="control-group">
														<label class="control-label">Subscription Name<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoverageregion" multiple="multiple" id="supportcoverageregion">
														     	<option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Activation Date<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry">
															     <option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpagetab3">
													<div class="control-group">
														<label class="control-label">Expiry Date<span class="required">*</span></label>
														<div class="controls">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime">
															     <option value ="-SELECT-"> -SELECT- </option>
																	<c:forEach var="supports" items="${supports}">
																    	<option value="${supports.name}">${supports.name}</option>
																 	</c:forEach>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">Status<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="phonenumber" placeholder="Phone Number" name="phonenumber" class="m-wrap largeval"/>
														</div>
													</div>
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Subscription Name</th>
															<th>Activation Date</th>
															<th>Expiry Date</th>
											                <th>Status</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab3" class="btn button-previous" data-toggle="tab" class="step" onclick="activeCosumerModeMyOffer('${consumermyofferanalyticsapplicationneeds}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
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
    <jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>
>>>>>>> origin/master
