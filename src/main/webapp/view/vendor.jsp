<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld"%>
<c:set var="personaldetails"
	value="<%=RequestConstans.Vendor.PERSONALDETAILS%>">
</c:set>
<c:set var="supportcoverage"
	value="<%=RequestConstans.Vendor.SUPPORTCOVERAGE%>">
</c:set>
<c:set var="supportdetails"
	value="<%=RequestConstans.Vendor.SUPPORTDETAILS%>">
</c:set>
<c:set var="awarddetails"
	value="<%=RequestConstans.Vendor.AWARDDETAILS%>">
</c:set>
<c:set var="datadistribution"
	value="<%=RequestConstans.Vendor.DATADISTRIBUTION%>">
</c:set>
<c:set var="databuyers"
	value="<%=RequestConstans.Vendor.SEARCHDATABUYERS%>">
</c:set>
<c:set var="myrfp" value="<%=RequestConstans.Vendor.MYRFP%>">
</c:set>

<!DOCTYPE html>
<head>
<title>Fin Vendor | Vendor</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta http-equiv="Pragma" content="no-cache">
<meta name="author" content="" />
<meta name="apple-mobile-web-app-capable" content="yes" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>

    <script type="text/javascript">
    	$(document).ready( function () {
    		getAssetClassList();
    		getSecurityTypeList();
    		getRegionList();
    		getCountryList();
    		getExchangeList();
    		getAnalyticalSolutionTypeList();
    		getAnalyticalSolutionSubTypeList();
    		getResearchAreaList();
    		getResearchSubAreaList();
    	});
    </script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body id="vendor_profile">
	<jsp:include page="common/dashboardheader.jsp"></jsp:include>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid service-box">
					<div class="row-fluid">
						<div class="span12">
							<div class="portlet box blue" id="form_wizard_1">
								<div class="portlet-title"></div>
								<div class="portlet-body form">
									<form action="#" class="form-horizontal" id="submit_form"
										method="post" enctype="multipart/form-data">
										<div class="form-wizard">
											<ul class="nav nav-tabs">
												<li class="active">
													<a class="personal_details" data-toggle="tab" href="#tab1" onclick="activeMode('${personaldetails}')">
														Profile
													</a>
												</li>
												<li>
													<a class="awards_details" data-toggle="tab" href="#tab3">
														Awards
													</a>
												</li>
											</ul>
											
											<div class="tab-content" style="background-color: white;">
												<jsp:include page="common/progressLoader.jsp"></jsp:include>
												<div class="tab-pane active" id="tab1">
													<div id="top-card">
														<div class="profile-top-card top-card">
															<div class="profile-card vcard">
																<div class="profile-picture">
																	<img src="<%=request.getContextPath() %>/getfile/logo" />
																</div>
																<div class="profile-overview">
																	<div class="profile-overview-content">
																		<h2 class="full-name">
																		${vendor.firstName} ${vendor.lastName}
																		</h2>
																		<h3 class="headline">
																			${vendor.designation} at ${vendor.company}
																		</h3>
																		<h4 class="contacts">
																			${vendor.user.email} | ${vendor.telephoneCode} ${vendor.telephone}
																		</h4>
																		<table class="company-details">
																			<tr>
																				<th>Company Url</th>
																				<td class="url">
																					${vendor.companyUrl}
																				</td>
																			</tr>
																			<tr>
																				<th>Company Information</th>
																				<td class="info">
																					${vendor.companyInfo}
																				</td>
																			</tr>
																			<tr>
																				<th>Company Type</th>
																				<td class="info" style="text-transform: capitalize;">
																					${vendor.analystType}
																				</td>
																			</tr>
																		</table>
																	</div>
																</div>
															</div>
														</div>
														<a class="btn" id="edit-details">
															<span class="fa fa-pencil"></span>Edit Details
														</a>
													</div>
													<div id="personal_details" class="custom_form">
														<div class="generic_message">
															<div class="alert">
															</div>
														</div>
														<ul>
															<li id="name">
																<div class="single_row">
																	<input type="text" name="firstname" class="single_line" id="personalvenfirstname" value="${vendor.firstName}" readonly />
																	<label class="default_select">First Name</label>
																</div>
																<div class="single_row">
																	<input type="text" name="lastname" id="personalvenlastname" class="single_line" value="${vendor.lastName}" required />
																	<label>Last Name</label>
																</div>
															</li>
															<li>
																<input type="text" name="designation" id="personalvendesignation" value="${vendor.designation}" required />
																<label>Designation</label>
															</li>
															<li>
																<input type="text" name="company" id="personalvencompany" value="${vendor.company}" required />
																<label>Company</label>
															</li>
															<li>
																<input type="text" name="companyurl" placeholder="" id="personalvencompanyurl" class="personal_detail" value="${vendor.companyUrl}" required />
																<label>Company URL</label>
															</li>
															<li>
																<input type="text" name="companyinfo" id="personalvencompanyinfo" value="${vendor.companyInfo}" required />
																<label>Company Information</label>
															</li>
															<li>
																<input name='companyTypeList' id='companyTypeList' type="hidden" value="${vendor.analystType}" />
																<select class="selectpicker show-tick" name="personal_analyst_type" id="personal_analyst_type">
																	<option value="">Nothing Selected</option>
																	<option value="brokers">Brokers</option>
																	<option value="independent">Independent</option>
																	<option value="others">Others</option>
																</select>
																<label class="default_select">Analyst Type</label>
															</li>
														</ul>
														<ul>
															<li>
																<input type="text" name="primaryemail" placeholder="" class="personal_detail" id="personalvenprimemail" value="${vendor.user.email}" readonly />
																<label class="default_select">Primary Email</label>
															</li>
															<li>
																<input type="text" name="secondaryemail" id="personalvensecemail" value="${vendor.secondaryEmail}" required />
																<label>Secondary Email</label>
															</li>
															<li id="phone">
																<div class="single_row">
																	<input type="text" name="phonenumber" placeholder="" class="personal_detail  single_line" id="personalvenphonenumbercode" value="${vendor.telephoneCode}" required />
																	<label>Country Code</label>
																</div>
																<div class="single_row">
																	<input type="text" name="phonenumber" id="personalvenphonenumber" class="single_line" value="${vendor.telephone}" required />
																	<label>Phone Number</label>
																</div>
															</li>
															<li>
																<select class="selectpicker show-tick" name="personalvencountryofincorp" id="personalvencountryofincorp" onchange="getCountryRegionMapping('personalvencountryofincorp', 'personalvenregionofincorp');">
																	<c:forEach var="countries" items="${countries}">
																	<option value="${countries.country_id}" >${countries.name}</option>
																	</c:forEach>
																</select>
																<label class="default_select">Country of Incorporation</label>
															</li>
															<li>
																<select class="selectpicker show-tick" name="personalvenregionofincorp" id="personalvenregionofincorp" disabled="true">
																</select>
																<label class="default_select">Region of Incorporation</label>
															</li>
															<li>
																<input type="file" id="personalvencompanylogo"
																	placeholder="Company Logo" name="companylogo"
																	class="largefileuploadcons"
																	onblur="imageValidation();" value="${vendor.logoName}" required/>
																<label class="default_select">Company Logo</label>
																	<font id="invalidfileformat"
																		style="bottom: 1px; font-family: 'Open Sans', sans-serif; font-weight: bold; font-size: 12px; position: absolute; color: #B94A48;"></font>
																	<div class="selectOptions">e.g. .jpeg, .jpg,
																		.png, .gif</div>
																		<div id="displayLogo">
																			<img src="<%=request.getContextPath() %>/getfile/logo" />
																			<font id="invalidfileformat"
																			style="bottom: 1px; font-family: 'Open Sans', sans-serif; font-weight: bold; font-size: 12px; position: absolute; color: #B94A48;"></font>
																		</div>
															</li>
														</ul>
														<p class="action_btn">
															<a class="submit_btn save" data-toggle="tab">Save</a>
															<a class="submit_btn next" data-toggle="tab" href="#tab3">Next</a>
														</p>
													</div>
												</div>

												<div class="tab-pane" id="tab3">
													<div id="awards_top_card">
														<div class="awards_info">
														</div>
														<a class="btn" id="edit-details">
															<span class="fa fa-pencil"></span>Add More
														</a>
													</div>
													<div id="award_details" class="custom_form">
														<div class="generic_message">
															<div class="alert"></div>
														</div>
														<ul>
															<li>
																<input type="text" name="awardname" id="awardname"  required />
																<label>Award Name</label>
															</li>
															<li>
																<input type="text" name="awardsponsor" id="awardsponsor"  required />
																<label>Award Sponsor</label>
															</li>
															<li>
																<input type="number" name="awardedyear" id="awardedyear"  required />
																<label>Awarded Year</label>
															</li>
															<li> 
																<input name='companyTypeList' id='companyTypeList' type="hidden" value="${vendor.companyType}" />
																<select class="selectpicker show-tick" name="awardVendorType" id="awardVendorType">
																	<option value="Data Aggregator">Data Aggregator</option>
																	<option value="Trading Application">Trading Application</option>
																	<option value="Analytics Application">Analytics Application</option>
																	<option value="Research Report">Research Report</option>
																</select>
																<label class="default_select">Vendor Type</label>
															</li>
															<li>
																<select class="selectpicker show-tick" name="awardAssetclass" id="awardAssetclass">
																	<c:forEach var="assetClasses" items="${assetClasses}">
																	<option value="${assetClasses.asset_class_id}">${assetClasses.description}</option>
																	</c:forEach>
																</select>
																<label class="default_select">Award Category</label>
															</li>
														</ul>
													<p class="action_btn">
														<a class="submit_btn save" data-toggle="tab">Save</a>
														<a class="submit_btn previous hide" data-toggle="tab" href="#tab1">Previous</a>
													</p>
												</div>
													
												</div>
												<!-- Award Details ENDs -->

												<!-- My RFP start -->

												<div class="tab-pane" id="tab5">
												<div class="clearfix">
													<h1>Coming Soon..<h1>
												</div>
												<%--
													<div class="tab-content">

														<div class="tab-pane active" id="tab8">
															<div>
																<br />
															</div>
															<div class="Rowtableinfoval">
																<div class="ColumnCommonmyprofinputsearch">
																	<select name="rfpcompany"
																		class="m-wrap largevalra inpubackground">
																		<option value="-SELECT-" class="selectvalues">
																			-Select RFP By Company-</option>
																		<option value="-SELECT-">JP Morgan</option>
																		<option value="-SELECT-">AXA Fin Corp</option>
																		<option value="-SELECT-">MAX Bupa Fin Corp</option>
																	</select>
																</div>
																<div class="ColumnCommonmyprofinputsearch">
																	<select name="rfpassetclass"
																		class="m-wrap largevalra inpubackground">
																		<option value="-SELECT-" class="selectvalues">
																			-Select Asset Class-</option>
																		<c:forEach var="assetClasses" items="${assetClasses}">
																			<option value="${assetClasses.asset_class_id}">${assetClasses.description}</option>
																		</c:forEach>
																	</select>
																</div>
																<div class="ColumnCommonmyprofinputsearch">
																	<input type="text" placeholder="Enter RFP Issue DT"
																		class="m-wrap largeval inpubackground" id="inputField"
																		name="rfpissuedate" />
																</div>
																<div class="ColumnCommonmyprofinputsearch">
																	<input type="text" placeholder="Enter RFP End DT"
																		class="m-wrap largeval inpubackground"
																		id="inputField1" name="rfpenddate" />
																</div>
																<div class="ColumnCommonmyprofinputsearch">
																	<div class="se">
																		<a href="#" class="btn buttoncheck">Search</a> <input
																			type="reset" value="Clear" class="btn buttoncheck" />
																	</div>
																</div>
															</div>
															<div>
																<br />
															</div>
															<div class="Rowtableaction">
																<div class="ColumnCommonmyprofileaction">
																	<div class="lable_headeractionbuttons">
																		<a href="#tab12"
																			class="lable_headeractionbuttons button-next"
																			data-toggle="tab" class="step">Submit final
																			Response</a>
																	</div>
																</div>
																<div class="ColumnCommonmyprofileaction">
																	<div class="lable_headeractionbuttons">
																		<a href="#tab11"
																			class="lable_headeractionbuttons button-next"
																			data-toggle="tab" class="step"> Ask for More Info</a>
																	</div>
																</div>
																<div class="ColumnCommonmyprofileaction">
																	<div class="lable_headeractionbuttons">
																		<a href="#tab10"
																			class="lable_headeractionbuttons button-next"
																			data-toggle="tab" class="step">Express an
																			Interest</a>
																	</div>
																</div>
																<div class="ColumnCommonmyprofileaction">
																	<div class="lable_headeractionbuttons">
																		<a href="#tab9"
																			class="lable_headeractionbuttons button-next"
																			data-toggle="tab" class="step">See the
																			Description</a>
																	</div>
																</div>
																<!-- <div class="ColumnCommonmyprofileaction"> <div class="lable_headeractions"> Actions </div> </div> -->
															</div>
															<div class="portlet-body">
																<table
																	class="table table-striped table-bordered table-hover table-full-width"
																	id="sampledistribution">
																	<thead style="background-color: #7BCCA5; color: #FFF;">
																		<tr>
																			<th>RFP By Company</th>
																			<th>RFP Title</th>
																			<th>Asset-Class</th>
																			<th>Region</th>
																			<th>RFP Issue DT</th>
																			<th>RFP End DT</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>JP Morgan</td>
																			<td>Equity Index Constituent Data</td>
																			<td>Equity</td>
																			<td>Asia</td>
																			<td>09/09/2019</td>
																			<td>11/11/2021</td>
																		</tr>
																		<tr>
																			<td>AXA Fin Corp</td>
																			<td>Fixed Income Constituent Data</td>
																			<td>Fixed Income</td>
																			<td>Africa</td>
																			<td>12/07/2015</td>
																			<td>13/09/2015</td>
																		</tr>
																		<tr>
																			<td>American Fina pvt.ltd</td>
																			<td>Indices Constituent Data</td>
																			<td>Indices</td>
																			<td>North America</td>
																			<td>12/07/2015</td>
																			<td>13/09/2015</td>
																		</tr>
																		<tr>
																			<td>MAX Bupa Fin corp ltd</td>
																			<td>Derivates Constituent Data</td>
																			<td>Derivates</td>
																			<td>South America</td>
																			<td>12/07/2015</td>
																			<td>13/09/2015</td>
																		</tr>
																	</tbody>
																</table>
															</div>
															<div>
																<br />
															</div>
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 177px;">
																	<a href="#tab3" class="btn button-previous"
																		data-toggle="tab" class="step"
																		onclick="activeMode('${databuyers}');"
																		style="margin-left: -341px;">Back</a> <input
																		type="submit" value="Update" class="btn"
																		style="margin-left: 295px;" /> <input type="reset"
																		value="Reset" class="btn" />
																</div>
															</div>
														</div>

														<div class="tab-pane" id="tab9">
															<div>
																<br />
															</div>
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">RFP Detailed Content
																	</div>
																</div>
															</div>
															<div>
																<br>
															</div>
															<div class="ramtra">
																<div class="ramtra" style="font-weight: bolder;">Benefits
																	of Investing in Diversified Equity Funds:</div>
																<div>
																	<br />
																</div>
																<div class="ramtra" style="font-weight: bolder;">
																	Stability in Bull and Bear Markets:</div>
																Diversified Equity Funds comprise of all markets cap
																stocks. Large cap stocks due to high end market
																capitalization tend to be stable in bear markets and
																show moderate appreciation in bull markets. Mid and
																small cap stocks respond to market stimulations. While,
																they show higher appreciation in bull markets, their
																depreciation is in sync with the bear markets. The
																differences in the performance of these market caps get
																balanced in the Diversified Equity Funds. In a bear
																market the mid and small cap stocks have a tendency to
																be volatile even if the large cap stocks shows moderate
																depreciation, thereby maintaining a steady balance. Due
																to this stability it allows investors with a varying
																risk appetite to park their investments in these funds.
																<div class="ramtra" style="font-weight: bolder;">Reduces
																	the Need to Diversify:</div>
																Financial planners and advisors keep emphasising about
																the need to diversify your investments. It is said that
																diversification in various asset classes determines the
																return of the portfolio and not the individual funds.
																Investing in Diversified Equity Funds reduces the need
																to diversify your portfolio as you choose an already
																diversified fund depending upon your investing needs and
																risk taking ability. As an investor if you are looking
																for stability in your investments, you could allocate a
																larger portion of your investments in Diversified Equity
																Funds and the remaining in Small and Mid Cap Funds.
																However, If you are an aggressive investor and ready to
																take high risk for long term appreciation then Mid and
																Small Cap Funds could be ideal investments for you.
																<div class="ramtra" style="font-weight: bolder;">
																	A universal Appeal:</div>
																The fund has a component to appeal to all kinds of
																investors: the risk takers, the safe player and the
																flexible investor. It also reduces the need to
																diversify. Hence, as an investor if you like to manage
																your own portfolio then this reduces your need to
																diversify to a certain degree. It provides stability to
																your portfolio along with a return range of moderate to
																high.
															</div>
															<br />
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">Questionnaires</div>
																</div>
															</div>
															<br />
															<div class="ramtra">
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> <a onclick="openWindow();">It is easier to find a
																		Web-based vendor that sells the item I wish to
																		purchase.</a>
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> I can quickly gather information about products and
																	services I wish to purchase from Web-based vendors.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> Web-based vendors deliver orders/services in a more
																	timely manner.
																</p>

															</div>
															<br />
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 60px;">
																	<a href="#tab8" class="btn button-previous"
																		data-toggle="tab" class="step"
																		style="margin-left: -212px;">Back</a> <input
																		type="submit" value="express an interest"
																		class="btn buttoncheck" style="margin-left: 202px;" />
																	<input type="reset" value="ask for more info"
																		class="btn buttoncheck" /> <a href="#tab13"
																		class="btn button-next" data-toggle="tab" class="step">submit
																		final response</a>
																</div>
															</div>
														</div>
														<div class="tab-pane" id="tab10">
															<div>
																<br />
															</div>
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">RFP Detailed Content
																	</div>
																</div>
															</div>
															<div>
																<br>
															</div>
															<div class="ramtra">
																<div class="ramtra" style="font-weight: bolder;">Benefits
																	of Investing in Diversified Equity Funds:</div>
																<div>
																	<br />
																</div>
																<div class="ramtra" style="font-weight: bolder;">
																	Stability in Bull and Bear Markets:</div>
																Diversified Equity Funds comprise of all markets cap
																stocks. Large cap stocks due to high end market
																capitalization tend to be stable in bear markets and
																show moderate appreciation in bull markets. Mid and
																small cap stocks respond to market stimulations. While,
																they show higher appreciation in bull markets, their
																depreciation is in sync with the bear markets. The
																differences in the performance of these market caps get
																balanced in the Diversified Equity Funds. In a bear
																market the mid and small cap stocks have a tendency to
																be volatile even if the large cap stocks shows moderate
																depreciation, thereby maintaining a steady balance. Due
																to this stability it allows investors with a varying
																risk appetite to park their investments in these funds.
																<div class="ramtra" style="font-weight: bolder;">Reduces
																	the Need to Diversify:</div>
																Financial planners and advisors keep emphasising about
																the need to diversify your investments. It is said that
																diversification in various asset classes determines the
																return of the portfolio and not the individual funds.
																Investing in Diversified Equity Funds reduces the need
																to diversify your portfolio as you choose an already
																diversified fund depending upon your investing needs and
																risk taking ability. As an investor if you are looking
																for stability in your investments, you could allocate a
																larger portion of your investments in Diversified Equity
																Funds and the remaining in Small and Mid Cap Funds.
																However, If you are an aggressive investor and ready to
																take high risk for long term appreciation then Mid and
																Small Cap Funds could be ideal investments for you.
																<div class="ramtra" style="font-weight: bolder;">
																	A universal Appeal:</div>
																The fund has a component to appeal to all kinds of
																investors: the risk takers, the safe player and the
																flexible investor. It also reduces the need to
																diversify. Hence, as an investor if you like to manage
																your own portfolio then this reduces your need to
																diversify to a certain degree. It provides stability to
																your portfolio along with a return range of moderate to
																high.
															</div>
															<br />
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">Questionnaires</div>
																</div>
															</div>
															<br />
															<div class="ramtra">
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> It is easier to find a Web-based vendor that sells the
																	item I wish to purchase.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> I can quickly gather information about products and
																	services I wish to purchase from Web-based vendors.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> Web-based vendors deliver orders/services in a more
																	timely manner.
																</p>

															</div>
															<br />
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 60px;">
																	<a href="#tab8" class="btn button-previous"
																		data-toggle="tab" class="step"
																		style="margin-left: -212px;">Back</a> <input
																		type="submit" value="express an interest"
																		class="btn buttoncheck" style="margin-left: 202px;" />
																	<input type="reset" value="ask for more info"
																		class="btn buttoncheck" /> <a href="#tab13"
																		class="btn button-next" data-toggle="tab" class="step">submit
																		final response</a>
																</div>
															</div>
														</div>
														<div class="tab-pane" id="tab11">
															<div>
																<br />
															</div>
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">RFP Detailed Content
																	</div>
																</div>
															</div>
															<div>
																<br>
															</div>
															<div class="ramtra">
																<div class="ramtra" style="font-weight: bolder;">Benefits
																	of Investing in Diversified Equity Funds:</div>
																<div>
																	<br />
																</div>
																<div class="ramtra" style="font-weight: bolder;">
																	Stability in Bull and Bear Markets:</div>
																Diversified Equity Funds comprise of all markets cap
																stocks. Large cap stocks due to high end market
																capitalization tend to be stable in bear markets and
																show moderate appreciation in bull markets. Mid and
																small cap stocks respond to market stimulations. While,
																they show higher appreciation in bull markets, their
																depreciation is in sync with the bear markets. The
																differences in the performance of these market caps get
																balanced in the Diversified Equity Funds. In a bear
																market the mid and small cap stocks have a tendency to
																be volatile even if the large cap stocks shows moderate
																depreciation, thereby maintaining a steady balance. Due
																to this stability it allows investors with a varying
																risk appetite to park their investments in these funds.
																<div class="ramtra" style="font-weight: bolder;">Reduces
																	the Need to Diversify:</div>
																Financial planners and advisors keep emphasising about
																the need to diversify your investments. It is said that
																diversification in various asset classes determines the
																return of the portfolio and not the individual funds.
																Investing in Diversified Equity Funds reduces the need
																to diversify your portfolio as you choose an already
																diversified fund depending upon your investing needs and
																risk taking ability. As an investor if you are looking
																for stability in your investments, you could allocate a
																larger portion of your investments in Diversified Equity
																Funds and the remaining in Small and Mid Cap Funds.
																However, If you are an aggressive investor and ready to
																take high risk for long term appreciation then Mid and
																Small Cap Funds could be ideal investments for you.
																<div class="ramtra" style="font-weight: bolder;">
																	A universal Appeal:</div>
																The fund has a component to appeal to all kinds of
																investors: the risk takers, the safe player and the
																flexible investor. It also reduces the need to
																diversify. Hence, as an investor if you like to manage
																your own portfolio then this reduces your need to
																diversify to a certain degree. It provides stability to
																your portfolio along with a return range of moderate to
																high.
															</div>
															<br />
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">Questionnaires</div>
																</div>
															</div>
															<br />
															<div class="ramtra">
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> It is easier to find a Web-based vendor that sells the
																	item I wish to purchase.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> I can quickly gather information about products and
																	services I wish to purchase from Web-based vendors.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> Web-based vendors deliver orders/services in a more
																	timely manner.
																</p>

															</div>
															<br />
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 60px;">
																	<a href="#tab8" class="btn button-previous"
																		data-toggle="tab" class="step"
																		style="margin-left: -212px;">Back</a> <input
																		type="submit" value="express an interest"
																		class="btn buttoncheck" style="margin-left: 202px;" />
																	<input type="reset" value="ask for more info"
																		class="btn buttoncheck" /> <a href="#tab13"
																		class="btn button-next" data-toggle="tab" class="step">submit
																		final response</a>
																</div>
															</div>
														</div>

														<div class="tab-pane" id="tab12">
															<div>
																<br />
															</div>
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">RFP Detailed Content
																	</div>
																</div>
															</div>
															<div>
																<br>
															</div>
															<div class="ramtra">
																<div class="ramtra" style="font-weight: bolder;">Benefits
																	of Investing in Diversified Equity Funds:</div>
																<div>
																	<br />
																</div>
																<div class="ramtra" style="font-weight: bolder;">
																	Stability in Bull and Bear Markets:</div>
																Diversified Equity Funds comprise of all markets cap
																stocks. Large cap stocks due to high end market
																capitalization tend to be stable in bear markets and
																show moderate appreciation in bull markets. Mid and
																small cap stocks respond to market stimulations. While,
																they show higher appreciation in bull markets, their
																depreciation is in sync with the bear markets. The
																differences in the performance of these market caps get
																balanced in the Diversified Equity Funds. In a bear
																market the mid and small cap stocks have a tendency to
																be volatile even if the large cap stocks shows moderate
																depreciation, thereby maintaining a steady balance. Due
																to this stability it allows investors with a varying
																risk appetite to park their investments in these funds.
																<div class="ramtra" style="font-weight: bolder;">Reduces
																	the Need to Diversify:</div>
																Financial planners and advisors keep emphasising about
																the need to diversify your investments. It is said that
																diversification in various asset classes determines the
																return of the portfolio and not the individual funds.
																Investing in Diversified Equity Funds reduces the need
																to diversify your portfolio as you choose an already
																diversified fund depending upon your investing needs and
																risk taking ability. As an investor if you are looking
																for stability in your investments, you could allocate a
																larger portion of your investments in Diversified Equity
																Funds and the remaining in Small and Mid Cap Funds.
																However, If you are an aggressive investor and ready to
																take high risk for long term appreciation then Mid and
																Small Cap Funds could be ideal investments for you.
																<div class="ramtra" style="font-weight: bolder;">
																	A universal Appeal:</div>
																The fund has a component to appeal to all kinds of
																investors: the risk takers, the safe player and the
																flexible investor. It also reduces the need to
																diversify. Hence, as an investor if you like to manage
																your own portfolio then this reduces your need to
																diversify to a certain degree. It provides stability to
																your portfolio along with a return range of moderate to
																high.
															</div>
															<br />
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">Questionnaires</div>
																</div>
															</div>
															<br />
															<div class="ramtra">
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> It is easier to find a Web-based vendor that sells the
																	item I wish to purchase.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> I can quickly gather information about products and
																	services I wish to purchase from Web-based vendors.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> Web-based vendors deliver orders/services in a more
																	timely manner.
																</p>

															</div>
															<br />
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 60px;">
																	<a href="#tab8" class="btn button-previous"
																		data-toggle="tab" class="step"
																		style="margin-left: -212px;">Back</a> <input
																		type="submit" value="express an interest"
																		class="btn buttoncheck" style="margin-left: 202px;" />
																	<input type="reset" value="ask for more info"
																		class="btn buttoncheck" /> <a href="#tab13"
																		class="btn button-next" data-toggle="tab" class="step">submit
																		final response</a>
																</div>
															</div>
														</div>

														<div class="tab-pane" id="tab13">
															<div>
																<br />
															</div>
															<div class="ramtra">
																<div class="ramtra" style="font-weight: bolder;">Benefits
																	of Investing in Diversified Equity Funds:</div>
															</div>
															<br />
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">Questionnaires</div>
																</div>
															</div>
															<br />
															<div class="ramtra">
																<p>
																	1) It is easier to find a Web-based vendor that sells
																	the item I wish to purchase? <br> <input
																		type="text" class="m-wrap large">
																</p>
																<p>
																	2) I can quickly gather information about products and
																	services I wish to purchase from Web-based vendors? <br>
																	<input type="text" class="m-wrap large">
																</p>
																<p>
																	3) Web-based vendors deliver orders/services in a more
																	timely manner? <br> <input type="text"
																		class="m-wrap large">
																</p>

															</div>
															<br />
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 180px;">
																	<a href="#tab8" class="btn button-previous"
																		data-toggle="tab" class="step"
																		style="margin-left: -212px;">Back</a> <input
																		type="reset" value="submit response"
																		class="btn buttoncheck" />
																</div>
															</div>
														</div>

													</div>

												--%>

												</div>
												<!-- My RFP  ENds-->
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

	<!-- tab active code starts here-->
	<script type="text/javascript">
window.onload = function(){
	var personalDetails= '<%=RequestConstans.Vendor.PERSONALDETAILS%>';
			if (personalDetails != null && personalDetails.length > 0
					&& personalDetails.match("personaldetails")) {
			}
		};
	</script>
	<script>
		progressLoader(false);
		function loadSecurityTypes(assettypeId) {
		 	
				if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
					assettypeId = encode64(assettypeId);
					$.ajax({
						type: 'GET',
						url:  "loadVendorSecurityTypes?RAyuL="+assettypeId,
						cache:false,
						success : function(output){
							document.getElementById("assetClassVendorSecurityMaps").innerHTML = output;		
						},
						error : function(data, textStatus, jqXHR){
							//alert('Error: '+data+':'+textStatus);
						}
					});
				}
			}
	</script>
	<script src="//code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"
		type="text/javascript"></script>

  
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
    
<script src="<%=request.getContextPath() %>/resources/js/vendor.js"></script>

</body>
</html>