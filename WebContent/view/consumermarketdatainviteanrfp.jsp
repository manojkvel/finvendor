<<<<<<< HEAD
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>

<c:set var="consumerwriteanrfp1" value="<%=RequestConstans.Consumer.CONSUMER_WRITE_AN_RFP_TAB1 %>"> </c:set>
<c:set var="consumerrfpresponse1" value="<%=RequestConstans.Consumer.CONSUMER_TRACK_RFP_RESPONSE1 %>"> </c:set>
<c:set var="consumershortlistedvendor1" value="<%=RequestConstans.Consumer.CONSUMER_SHORTLISTED_VENDORS1 %>"> </c:set>
<c:set var="consumerfinalvendor1" value="<%=RequestConstans.Consumer.CONSUMER_FINAL_VENDOR1 %>"> </c:set>

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
										<div class="ColumnCommonmyprofile" id="changeconsumerinvitemarketdataneeds1"> <div class="lable_header" id="interconsumerdivinvitemarketoffer1"> <a id="anchoconsumerinvitemarketoffer1" href="#tab1" class="lable_header" data-toggle="tab"  onclick="consumerMarketDataNeedsInviteRFPModes('${consumerwriteanrfp1}');">write an RFP</a> </div></div>
										<div class="ColumnCommonmyprofile" id="changeconsumerinvitemarketdataneeds2"> <div class="lable_header" id="interconsumerdivinvitemarketoffer2"> <a id="anchoconsumerinvitemarketoffer2" href="#tab2" class="lable_header" data-toggle="tab" onclick="consumerMarketDataNeedsInviteRFPModes('${consumerrfpresponse1}');">Track RFP response</a> </div> </div>
										<div class="ColumnCommonmyprofile" id="changeconsumerinvitemarketdataneeds3"> <div class="lable_header" id="interconsumerdivinvitemarketoffer3"> <a id="anchoconsumerinvitemarketoffer3" href="#tab3" class="lable_header" data-toggle="tab" onclick="consumerMarketDataNeedsInviteRFPModes('${consumershortlistedvendor1}');">Shortlisted vendors</a> </div> </div>
										<div class="ColumnCommonmyprofile" id="changeconsumerinvitemarketdataneeds4"> <div class="lable_header" id="interconsumerdivinvitemarketoffer4"> <a id="anchoconsumerinvitemarketoffer4" href="#tab4" class="lable_header" data-toggle="tab" onclick="consumerMarketDataNeedsInviteRFPModes('${consumerfinalvendor1}');">Final Vendor</a> </div> </div>
									</div>
								<div class="tab-content">
									<div class="tab-pane active" id="tab1">
										<div><br/></div>
										<div class="Rowtableinfoval" >
										   <div class="ColumnCommonmyprofinputsearchlargeicons"> 
										     <div class="control-group">
												<label class="control-label">RFP Title</label>
												<div class="controls">
													<input type="text" id="personalvenlastname" placeholder="Last Name" name="lastname" class="m-wrap large" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" style="padding-top: 24px;">RFP Short Desc</label>
												<div class="controls">
													<input type="text" id="personalvenlastname"  name="lastname" class="m-wraplargetext large" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" style="padding-top: 42px;">RFP Long Desc</label>
												<div class="controls">
													<input type="text" id="personalvenlastname"  name="lastname" class="m-wrapmorelargetext large" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">RFP Questionnaires</label>
											</div>
											<p style="padding-left: 130px;"> 
												1) It is easier to find a Web-based vendor that sells the item I wish to purchase?
												 <input type="text" class="m-wrap large">
											</p>
											<p style="padding-left: 130px;"> 
												 2) I can quickly gather information about products, I wish to purchase from Web-based vendors?
												   <input type="text" class="m-wrap large">
											</p>
											</div>
										 </div>
										 <div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
													<input type="submit" value="Update" class="btn"/>
													<input type="reset" value="Reset" class="btn"/>
													<a  href="#tab2" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMarketDataNeedsInviteRFPModes('${consumerrfpresponse1}');">Next</a>
												</div>
												</div> 
									</div>
									
									<div class="tab-pane" id="tab2">
									<div class="tab-content">
									<div class="tab-pane active" id="tab5">
										<div><br/></div>
										<div class="Rowtableinfoval" >
										   <div class="ColumnCommonmyprofinputsearchlargeicons"> 
										     <div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="sampledistribution">
													<thead style="background-color: #7BCCA5; color:#FFF;">
														<tr>
															<th>RFP Title</th>
															<th>RFP Issue DT</th>
															<th>RFP End DT</th>
															<th>Total Vendor Response</th>
														</tr>
													</thead>
													<tbody>
													   <tr>
															<td>Mobhero Inc</td>
															<td>09/09/2019</td>
															<td>11/11/2021</td>
															<td> <a id="anchoray" href="#tab6" class="lable_headerinviteanrfp" data-toggle="tab"  onclick="consumerInviteRFPModes('${inviteanrfpwriteanrfp1}');">9 Responses</a> </td>
														</tr>
														<tr>
															<td>AXA Fin Corp</td>
															 <td>12/07/2015</td>
															 <td>13/09/2015</td>
															 <td> <a id="anchoray" href="#tab6" class="lable_headerinviteanrfp" data-toggle="tab"  onclick="consumerInviteRFPModes('${inviteanrfpwriteanrfp1}');">4 Responses</a> </td>
														</tr>
														<tr>
															<td>American Fina pvt.ltd</td>
															 <td>12/07/2015</td>
															 <td>13/09/2015</td>
															 <td> <a id="anchoray" href="#tab6" class="lable_headerinviteanrfp" data-toggle="tab"  onclick="consumerInviteRFPModes('${inviteanrfpwriteanrfp1}');">8 Responses</a> </td>
														</tr>
													</tbody>
												</table>
											</div>
											</div>
										 </div>
										 <div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
												<a  href="#tab1" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMarketDataNeedsInviteRFPModes('${consumerwriteanrfp1}');"">Back</a>
													<input type="submit" value="Update" class="btn"/>
													<input type="reset" value="Reset" class="btn"/>
												</div>
												</div> 
							<div><br/></div>
							</div>
							<div class="tab-pane" id="tab6">
									<div><br/></div>
									<div class="Rowtableaction" >
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab12" data-toggle="tab" class="step lable_headeractionbuttons">Review the response</a> </div></div>
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab13" data-toggle="tab" class="step lable_headeractionbuttons" >Ask for more info</a> </div></div>
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab14" data-toggle="tab" class="step lable_headeractionbuttons">Shortlist vendor</a> </div></div>
									</div>
										 <div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width row-selectconsumerinvite">
													<thead style="background-color: #7BCCA5; color:#FFF;">
														<tr>
															<th>RFI Title</th>
															<th>Vendor Name</th>
															<th>Vendor Response</th>
														</tr>
													</thead>
													<tbody>
													   <tr id="1">
															<td>Best Equitity</td>
															<td>JP Morgan</td>
															<td><a>Vendor Document</a></td>
														</tr>
														<tr id="2">
															<td>Best Indies</td>
															<td>AXA Fin Corp</td>
															<td><a>Vendor Document</a></td>
														</tr>
													</tbody>
												</table>
											</div>
											<div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
												<a  href="#tab5" class="btn button-previous" data-toggle="tab" class="step" onclick="activeMode('${databuyers}');">Back</a>
													<input type="submit" value="Update" class="btn"/>
													<input type="reset" value="Reset" class="btn"/>
												</div>
												</div>
											</div>  
										</div>
									</div>

								<div class="tab-pane" id="tab3">
									<div><br/></div>
									<div class="Rowtableaction" >
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab12" data-toggle="tab" class="step lable_headeractionbuttons">Review the response</a> </div></div>
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab13" data-toggle="tab" class="step lable_headeractionbuttons" >Ask for more info</a> </div></div>
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab14" data-toggle="tab" class="step lable_headeractionbuttons">Write Final Feedback</a> </div></div>
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab14" data-toggle="tab" class="step lable_headeractionbuttons">Finalize the vendor</a> </div></div>
									</div>
										 <div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width row-selectconsumerinvite">
													<thead style="background-color: #7BCCA5; color:#FFF;">
														<tr>
															<th>RFI Title</th>
															<th>Vendor Name</th>
															<th>Vendor Response</th>
														</tr>
													</thead>
													<tbody>
													   <tr id="1">
															<td>Best Equitity</td>
															<td>JP Morgan</td>
															<td><a>Vendor Document</a></td>
														</tr>
														<tr id="2">
															<td>Best Indies</td>
															<td>AXA Fin Corp</td>
															<td><a>Vendor Document</a></td>
														</tr>
													</tbody>
												</table>
											</div>
											<div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
												<a  href="#tab5" class="btn button-previous" data-toggle="tab" class="step" onclick="activeMode('${databuyers}');">Back</a>
													<input type="submit" value="Update" class="btn"/>
													<input type="reset" value="Reset" class="btn"/>
												</div>
												</div>
									</div>
									<div class="tab-pane" id="tab4">
											<div><br/></div>
										 <div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width row-selectconsumerinvite">
													<thead style="background-color: #7BCCA5; color:#FFF;">
														<tr>
															<th>RFI Title</th>
															<th>Vendor Name</th>
															<th>Vendor Response</th>
															<th>Final Decision Date</th>
															<th>Feedback</th>
														</tr>
													</thead>
													<tbody>
													   <tr id="1">
															<td>Best Equitity</td>
															<td>JP Morgan</td>
															<td><a>Vendor Document</a></td>
															<td>12/07/2015</td>
															<td>Good Response</td>
														</tr>
														<tr id="2">
															<td>Best Indies</td>
															<td>AXA Fin Corp</td>
															<td><a>Vendor Document</a></td>
															<td>12/07/2015</td>
															<td>Good Response</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
												<a  href="#tab3" class="btn button-previous" data-toggle="tab" class="step" onclick="activeMode('${databuyers}');">Back</a>
													<input type="submit" value="Update" class="btn"/>
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
    <jsp:include page="common/footer.jsp"></jsp:include>
	
</body>
</html>
=======
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>

<c:set var="consumerwriteanrfp1" value="<%=RequestConstans.Consumer.CONSUMER_WRITE_AN_RFP_TAB1 %>"> </c:set>
<c:set var="consumerrfpresponse1" value="<%=RequestConstans.Consumer.CONSUMER_TRACK_RFP_RESPONSE1 %>"> </c:set>
<c:set var="consumershortlistedvendor1" value="<%=RequestConstans.Consumer.CONSUMER_SHORTLISTED_VENDORS1 %>"> </c:set>
<c:set var="consumerfinalvendor1" value="<%=RequestConstans.Consumer.CONSUMER_FINAL_VENDOR1 %>"> </c:set>

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
										<div class="ColumnCommonmyprofile" id="changeconsumerinvitemarketdataneeds1"> <div class="lable_header" id="interconsumerdivinvitemarketoffer1"> <a id="anchoconsumerinvitemarketoffer1" href="#tab1" class="lable_header" data-toggle="tab"  onclick="consumerMarketDataNeedsInviteRFPModes('${consumerwriteanrfp1}');">write an RFP</a> </div></div>
										<div class="ColumnCommonmyprofile" id="changeconsumerinvitemarketdataneeds2"> <div class="lable_header" id="interconsumerdivinvitemarketoffer2"> <a id="anchoconsumerinvitemarketoffer2" href="#tab2" class="lable_header" data-toggle="tab" onclick="consumerMarketDataNeedsInviteRFPModes('${consumerrfpresponse1}');">Track RFP response</a> </div> </div>
										<div class="ColumnCommonmyprofile" id="changeconsumerinvitemarketdataneeds3"> <div class="lable_header" id="interconsumerdivinvitemarketoffer3"> <a id="anchoconsumerinvitemarketoffer3" href="#tab3" class="lable_header" data-toggle="tab" onclick="consumerMarketDataNeedsInviteRFPModes('${consumershortlistedvendor1}');">Shortlisted vendors</a> </div> </div>
										<div class="ColumnCommonmyprofile" id="changeconsumerinvitemarketdataneeds4"> <div class="lable_header" id="interconsumerdivinvitemarketoffer4"> <a id="anchoconsumerinvitemarketoffer4" href="#tab4" class="lable_header" data-toggle="tab" onclick="consumerMarketDataNeedsInviteRFPModes('${consumerfinalvendor1}');">Final Vendor</a> </div> </div>
									</div>
								<div class="tab-content">
									<div class="tab-pane active" id="tab1">
										<div><br/></div>
										<div class="Rowtableinfoval" >
										   <div class="ColumnCommonmyprofinputsearchlargeicons"> 
										     <div class="control-group">
												<label class="control-label">RFP Title</label>
												<div class="controls">
													<input type="text" id="personalvenlastname" placeholder="Last Name" name="lastname" class="m-wrap large" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" style="padding-top: 24px;">RFP Short Desc</label>
												<div class="controls">
													<input type="text" id="personalvenlastname"  name="lastname" class="m-wraplargetext large" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" style="padding-top: 42px;">RFP Long Desc</label>
												<div class="controls">
													<input type="text" id="personalvenlastname"  name="lastname" class="m-wrapmorelargetext large" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">RFP Questionnaires</label>
											</div>
											<p style="padding-left: 130px;"> 
												1) It is easier to find a Web-based vendor that sells the item I wish to purchase?
												 <input type="text" class="m-wrap large">
											</p>
											<p style="padding-left: 130px;"> 
												 2) I can quickly gather information about products, I wish to purchase from Web-based vendors?
												   <input type="text" class="m-wrap large">
											</p>
											</div>
										 </div>
										 <div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
													<input type="submit" value="Update" class="btn"/>
													<input type="reset" value="Reset" class="btn"/>
													<a  href="#tab2" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMarketDataNeedsInviteRFPModes('${consumerrfpresponse1}');">Next</a>
												</div>
												</div> 
									</div>
									
									<div class="tab-pane" id="tab2">
									<div class="tab-content">
									<div class="tab-pane active" id="tab5">
										<div><br/></div>
										<div class="Rowtableinfoval" >
										   <div class="ColumnCommonmyprofinputsearchlargeicons"> 
										     <div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="sampledistribution">
													<thead style="background-color: #7BCCA5; color:#FFF;">
														<tr>
															<th>RFP Title</th>
															<th>RFP Issue DT</th>
															<th>RFP End DT</th>
															<th>Total Vendor Response</th>
														</tr>
													</thead>
													<tbody>
													   <tr>
															<td>Mobhero Inc</td>
															<td>09/09/2019</td>
															<td>11/11/2021</td>
															<td> <a id="anchoray" href="#tab6" class="lable_headerinviteanrfp" data-toggle="tab"  onclick="consumerInviteRFPModes('${inviteanrfpwriteanrfp1}');">9 Responses</a> </td>
														</tr>
														<tr>
															<td>AXA Fin Corp</td>
															 <td>12/07/2015</td>
															 <td>13/09/2015</td>
															 <td> <a id="anchoray" href="#tab6" class="lable_headerinviteanrfp" data-toggle="tab"  onclick="consumerInviteRFPModes('${inviteanrfpwriteanrfp1}');">4 Responses</a> </td>
														</tr>
														<tr>
															<td>American Fina pvt.ltd</td>
															 <td>12/07/2015</td>
															 <td>13/09/2015</td>
															 <td> <a id="anchoray" href="#tab6" class="lable_headerinviteanrfp" data-toggle="tab"  onclick="consumerInviteRFPModes('${inviteanrfpwriteanrfp1}');">8 Responses</a> </td>
														</tr>
													</tbody>
												</table>
											</div>
											</div>
										 </div>
										 <div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
												<a  href="#tab1" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMarketDataNeedsInviteRFPModes('${consumerwriteanrfp1}');"">Back</a>
													<input type="submit" value="Update" class="btn"/>
													<input type="reset" value="Reset" class="btn"/>
												</div>
												</div> 
							<div><br/></div>
							</div>
							<div class="tab-pane" id="tab6">
									<div><br/></div>
									<div class="Rowtableaction" >
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab12" data-toggle="tab" class="step lable_headeractionbuttons">Review the response</a> </div></div>
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab13" data-toggle="tab" class="step lable_headeractionbuttons" >Ask for more info</a> </div></div>
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab14" data-toggle="tab" class="step lable_headeractionbuttons">Shortlist vendor</a> </div></div>
									</div>
										 <div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width row-selectconsumerinvite">
													<thead style="background-color: #7BCCA5; color:#FFF;">
														<tr>
															<th>RFI Title</th>
															<th>Vendor Name</th>
															<th>Vendor Response</th>
														</tr>
													</thead>
													<tbody>
													   <tr id="1">
															<td>Best Equitity</td>
															<td>JP Morgan</td>
															<td><a>Vendor Document</a></td>
														</tr>
														<tr id="2">
															<td>Best Indies</td>
															<td>AXA Fin Corp</td>
															<td><a>Vendor Document</a></td>
														</tr>
													</tbody>
												</table>
											</div>
											<div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
												<a  href="#tab5" class="btn button-previous" data-toggle="tab" class="step" onclick="activeMode('${databuyers}');">Back</a>
													<input type="submit" value="Update" class="btn"/>
													<input type="reset" value="Reset" class="btn"/>
												</div>
												</div>
											</div>  
										</div>
									</div>

								<div class="tab-pane" id="tab3">
									<div><br/></div>
									<div class="Rowtableaction" >
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab12" data-toggle="tab" class="step lable_headeractionbuttons">Review the response</a> </div></div>
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab13" data-toggle="tab" class="step lable_headeractionbuttons" >Ask for more info</a> </div></div>
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab14" data-toggle="tab" class="step lable_headeractionbuttons">Write Final Feedback</a> </div></div>
									<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab14" data-toggle="tab" class="step lable_headeractionbuttons">Finalize the vendor</a> </div></div>
									</div>
										 <div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width row-selectconsumerinvite">
													<thead style="background-color: #7BCCA5; color:#FFF;">
														<tr>
															<th>RFI Title</th>
															<th>Vendor Name</th>
															<th>Vendor Response</th>
														</tr>
													</thead>
													<tbody>
													   <tr id="1">
															<td>Best Equitity</td>
															<td>JP Morgan</td>
															<td><a>Vendor Document</a></td>
														</tr>
														<tr id="2">
															<td>Best Indies</td>
															<td>AXA Fin Corp</td>
															<td><a>Vendor Document</a></td>
														</tr>
													</tbody>
												</table>
											</div>
											<div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
												<a  href="#tab5" class="btn button-previous" data-toggle="tab" class="step" onclick="activeMode('${databuyers}');">Back</a>
													<input type="submit" value="Update" class="btn"/>
													<input type="reset" value="Reset" class="btn"/>
												</div>
												</div>
									</div>
									<div class="tab-pane" id="tab4">
											<div><br/></div>
										 <div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width row-selectconsumerinvite">
													<thead style="background-color: #7BCCA5; color:#FFF;">
														<tr>
															<th>RFI Title</th>
															<th>Vendor Name</th>
															<th>Vendor Response</th>
															<th>Final Decision Date</th>
															<th>Feedback</th>
														</tr>
													</thead>
													<tbody>
													   <tr id="1">
															<td>Best Equitity</td>
															<td>JP Morgan</td>
															<td><a>Vendor Document</a></td>
															<td>12/07/2015</td>
															<td>Good Response</td>
														</tr>
														<tr id="2">
															<td>Best Indies</td>
															<td>AXA Fin Corp</td>
															<td><a>Vendor Document</a></td>
															<td>12/07/2015</td>
															<td>Good Response</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div><br/></div>
												<div class="form-actions clearfix">
												<div class="se">
												<a  href="#tab3" class="btn button-previous" data-toggle="tab" class="step" onclick="activeMode('${databuyers}');">Back</a>
													<input type="submit" value="Update" class="btn"/>
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
    <jsp:include page="common/footer.jsp"></jsp:include>
	
</body>
</html>
>>>>>>> origin/master
