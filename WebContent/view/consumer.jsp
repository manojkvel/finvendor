<!DOCTYPE html> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>Fin Vendor | Consumer</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="common/header.jsp"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container">
		<div id="consumer_profile">
			<ul class="nav nav-tabs">
				<li class="active">
					<a class="consumer_details" href="#consumer_details" data-toggle="tab" >
						Company Details
					</a>
				</li>
				<li>
					<a class="business_needs" href="#business_needs" data-toggle="tab">
						My Business Needs
					</a>
				</li>
			</ul>
			<jsp:include page="common/progressLoader.jsp"></jsp:include>
			<div class="tab-pane" id="consumer_details">
				<div id="top-card">
					<div class="profile-top-card top-card">
						<div class="profile-card vcard">
							<div class="profile-picture">
								<img src="<%=request.getContextPath() %>/getfile/logo" />
							</div>
							<div class="profile-overview">
								<div class="profile-overview-content">
									<h2 class="full-name">
									${consumer.firstName} ${consumer.lastName}
									</h2>
									<h3 class="headline">
										${consumer.designation} at ${consumer.company}
									</h3>
									<h4 class="contacts">
										${consumer.user.email} | ${vendor.telephoneCode} ${vendor.telephone}
									</h4>
									<table class="company-details">
										<tr>
											<th>Comany Url</th>
											<td class="url">
												${consumer.companyUrl}
											</td>
										</tr>
										<tr>
											<th>Comany Information</th>
											<td class="info">
												${consumer.companyInfo}
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
							<input type="text" name="firstname" class="single_line" id="personalvenfirstname" value="${vendor.firstName}" required />
							<label>First Name</label>
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
				</ul>
				<ul>
					<li>
						<input type="text" name="primaryemail" placeholder="" class="personal_detail" id="personalvenprimemail" value="${vendor.user.email}" required />
						<label>Primary Email</label>
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
			</div>
			<div class="tab-pane" id="business_needs">My Business Needs</div>
		</div>
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
	<script type="text/javascript">
		progressLoader(false);
	</script>
</body>
</html>