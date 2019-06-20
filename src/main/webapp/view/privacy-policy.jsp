<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0"
	prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans"
	var="requestConstants" />
<html>
<head>
<title>Privacy Policy - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container" id="privacy_policy">
		<div class="inner_breadcrumb">
			<h5>Privacy Policy</h5>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-md-9">
				<div class="default_template">
					<div class="content">
						<h2>Privacy Policy</h2>
						<p>
							<b>What information do we collect?</b>
						</p>
						<p>We collect information from you when you register on our site.
						</p>
						<p>When registering on our site, as appropriate, you may be asked to enter your: name, e-mail address, mailing address or phone number. You may, however, visit our many areas of our website anonymously.
						</p>
						<br>
						<p>
							<b>What do we use your information for?</b>
						</p>
						<p>
							Any of the information we collect from you may be used in one of the following ways: <br> <span style="display: list-item; display: relative; margin-left: 3em">To
							personalize your experience (your information helps us to better
							respond to your individual needs)</span> <span style="display: list-item; display: relative; margin-left: 3em">To
							improve customer service (your information helps us to more
							effectively respond to your customer service requests and support
							needs)</span> <span style="display: list-item; display: relative; margin-left: 3em">
							To process transactions<br> Your information, whether public
							or private, will not be sold, exchanged, transferred, or given to
							any other company for any reason whatsoever, without your consent,
							other than for the express purpose of delivering the purchased
							product or service requested
								</span> <span style="display: list-item; display: relative; margin-left: 3em">
								To send periodic emails<br> If you decide to opt-in to our
								mailing list, you will receive emails that may include company
								news, updates, related product or service information, etc.<br>
								Note: If at any time you would like to unsubscribe from receiving
								future emails, we include detailed unsubscribe instructions at the
								bottom of each email.
							</span>
						</p>
						<p>
							<b>Do we use cookies?</b>
						</p>
						<p>Yes (Cookies are small files that a site or its service provider transfers to your computers hard drive through your Web browser (if you allow) that enables the sites or service providers systems to recognize your browser and capture and remember certain information
						</p>
						<p>We use cookies to understand and save your preferences for future visits and compile aggregate data about site traffic and site interaction so that we can offer better site experiences and tools in the future.</p>
						<p>
							<b>Do we disclose any information to outside parties?</b>
						</p>
						<p>We do not sell, trade, or otherwise transfer to outside parties your personally identifiable information. This does not include trusted third parties who assist us in operating our website, conducting our business, or servicing you, so long as those parties agree to keep this information confidential. We may also release your information when we believe release is appropriate to comply with the law, enforce our site policies, or protect ours or others rights, property, or safety. However, non-personally identifiable visitor information may be provided to other parties for marketing, advertising, or other uses.</p>
						<p>
							<b>Third party links</b>
						</p>
						<p>Occasionally, at our discretion, we may include or offer third party products or services on our website. These third party sites have separate and independent privacy policies. We therefore have no responsibility or liability for the content and activities of these linked sites. Nonetheless, we seek to protect the integrity of our site and welcome any feedback about these sites.</p>
						<p>
							<b>Childrens Online Privacy Protection Act Compliance</b>
						</p>
						<p>We are in compliance with the requirements of COPPA (Childrens Online Privacy Protection Act), we do not collect any information from anyone under 13 years of age. Our website, products and services are all directed to people who are at least 13 years old or older.</p>
						<p>
							<b>Online Privacy Policy Only</b>
						</p>
						<p>This online privacy policy applies only to information collected through our website and not to information collected offline.
						</p>
						<p>
							<b>Your Consent</b>
						</p>
						<p>By using our site, you consent to our websites privacy policy.
						</p>
						<p>
							<b>Changes to our Privacy Policy</b>
						</p>
						<p>
							If we decide to change our privacy policy, we will post those changes on this page.<br> This policy was last modified on 04/17/2016
						</p>
						<p>
							<b>Contacting Us</b>
						</p>
						<p>
							If there are any questions regarding this privacy policy you may contact us using the information below.<br> <a href="mailto:sales@finvendor.com" target="_top">sales@finvendor.com</a><br>
						</p>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3">
				<jsp:include page="common/inner_sidebar.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>