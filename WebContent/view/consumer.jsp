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
										${consumer.user.email} | ${telephoneCode} ${telephoneNumber}
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
				<form action="${pageContext.request.contextPath}/updateConsumerProfileCompanyDetails" class="form-horizontal" 
												id="submit_form" method="post" enctype="multipart/form-data">
				<div class="generic_message">
					<div class="alert">
					</div>
				</div>
				<ul>
					<li id="name">
						<div class="single_row">
							<input type="text" name="firstname" class="single_line" id="consumerProfileFirstName" value="${consumer.firstName}" required />
							<label>First Name</label>
						</div>
						<div class="single_row">
							<input type="text" name="lastname" id="consumerProfileLastName" class="single_line" value="${consumer.lastName}" required />
							<label>Last Name</label>
						</div>
					</li>
					<li>
						<input type="text" name="designation" id="consumerProfileDesignation" value="${consumer.designation}" required />
						<label>Designation</label>
					</li>
					<li>
						<input type="text" name="company" id="consumerProfileCompany" value="${consumer.company}" required />
						<label>Company</label>
					</li>
					<li>
						<input type="text" name="companyurl" placeholder="" id="consumerProfileCompanyUrl" class="personal_detail" value="${consumer.companyUrl}" required />
						<label>Company URL</label>
					</li>
					<li>
						<input type="text" name="companyinfo" id="consumerProfileCompanyInfo" value="${consumer.companyInfo}" required />
						<label>Company Information</label>
					</li>
					<li>
						<input type="text" name="consumerProfileCity" id="consumerProfileCity" value="${consumer.city}" required />
						<label>City</label>
					</li>
					<li>
						<input type="text" name="consumerProfileCompanyType" id="consumerProfileCompanyType" value="${displayCompanyType}" readonly="readonly" />
						<label class="default_select">Company Type</label>
					</li>
					<li>
						<select class="selectpicker show-tick" name="consumerProfileCompanySubType" id="consumerProfileCompanySubType" multiple="multiple">
							<c:forEach var="subType" items="${companySubType}">
							<c:choose>
							<c:when test="${consumer.companySubType.id == subType.id}">
							<option value="${subType.id}" selected>${subType.type}</option>
							</c:when>    
							<c:otherwise>
							<option value="${subType.id}">${subType.type}</option>
							</c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
						<label class="default_select">Company SubType</label>
					</li>
				</ul>
				<ul>
					<li>
						<input type="text" name="primaryemail" placeholder="" class="personal_detail" id="consumerProfilePrimaryEmail" value="${consumer.user.email}" readonly="readonly" />
						<label class="default_select">Primary Email</label>
					</li>
					<li>
						<input type="text" name="secondaryemail" id="consumerProfileSecondaryEmail" value="${consumer.secondaryEmail}" required />
						<label>Secondary Email</label>
					</li>
					<li id="phone">
						<div class="single_row">
							<input type="text" name="phonenumber" placeholder="" class="personal_detail  single_line" id="consumerProfilePhoneNumberCode" value="${telephoneCode}" required />
							<label>Country Code</label>
						</div>
						<div class="single_row">
							<input type="text" name="phonenumber" id="consumerProfilePhoneNumber" class="single_line" value="${telephoneNumber}" required />
							<label>Phone Number</label>
						</div>
					</li>
					<li>
						<select class="selectpicker show-tick" name="consumerProfileCountryOfIncorporation" id="consumerProfileCountryOfIncorporation">
							<c:forEach var="countries" items="${countries}">
							<c:choose>
							<c:when test="${consumer.countryOfIncorporation eq countries.country_id}">
							<option value="${countries.country_id}"  selected="${countries.country_id}">${countries.name}</option>
						</c:when>    
						<c:otherwise>
						<option value="${countries.country_id}">${countries.name}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
					</select>
					<label class="default_select">Country of Incorporation</label>
				</li>
				<li>
					<input type="text" name="consumerProfileYearOfIncorporation" id="consumerProfileYearOfIncorporation" value="${consumer.yearOfIncorporation}" required />
					<label class="default_select">Year of Incorporation</label>
				</li>
				<li>
					<input type="file" id="personalvencompanylogo"
					placeholder="Company Logo" name="personalvencompanylogo"
					class="largefileuploadcons"
					onblur="imageValidation();"  required/>
					<label class="default_select">Company Logo</label>
					<font id="invalidfileformat"
					style="bottom: 1px; font-family: 'Open Sans', sans-serif; font-weight: bold; font-size: 12px; position: absolute; color: #B94A48;"></font>
					<div class="selectOptions">e.g. .jpeg, .jpg,
						.png, .gif</div>
						<div id="displayLogo">
							<img src="${pageContext.request.contextPath}/displayCompanyLogo/${consumer.user.userName}" />
							<font id="invalidfileformat"
							style="bottom: 1px; font-family: 'Open Sans', sans-serif; font-weight: bold; font-size: 12px; position: absolute; color: #B94A48;"></font>
						</div>
					</li>
				</ul>
				<p class="action_btn">
					<a class="submit_btn save" data-toggle="tab">Save</a>
					<a class="submit_btn next" data-toggle="tab" href="#tab3">Next</a>
				</p>
				</form>
			</div>
		</div>
			<div class="tab-pane" id="business_needs">My Business Needs</div>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
	<script type="text/javascript">
		progressLoader(false);
	</script>
	
  
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>

</body>
</html>