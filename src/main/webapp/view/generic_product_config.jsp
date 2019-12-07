<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<!DOCTYPE html>
<head>
	<title>Generic Product Configurations - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true" ></jsp:include>

	<c:choose>
			<c:when test="${sessionScope.loggedInUser != null  && sessionScope.loggedInRole=='ROLE_ADMIN'}">
			
		<input type="hidden" name="loggedInUser" value="${sessionScope.loggedInUser.username}" />
	<div class="container-fluid" id="generic_product_config">
		<div class="inner_breadcrumb">
			<h5>Product Configurations</h5>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="content">
				<div id='progressLoader'>
						<jsp:include page="common/progressLoader.jsp"></jsp:include>
					</div>
					<div class="tab">
						<button class="tablinks" onclick="openCity(event, 'general_config')" id="defaultOpen">General Configurations</button>
						<button class="tablinks" onclick="openCity(event, 'finven_reset_user_config')">Reset User</button>
						<button class="tablinks" onclick="openCity(event, 'finven_others_config')">Others</button>
					</div>

					<div id="general_config" class="tabcontent">
						<h3>General Configurations</h3>
						<div class="default_config_data">
							<ul>
								<li>
									<input type="number" name="featureAccessDailyLimit" id="featureAccessDailyLimit_data" readonly="readonly" />
									<label class="default_select">Feature Access Daily Limit</label>
								</li>
								<li>
									<input type="number" name="featureAccessWeeklyLimit" id="featureAccessWeeklyLimit_data"  readonly="readonly" />
									<label class="default_select">Feature Access Weekly Limit</label>
								</li>
								<li>
									<input type="text" name="reminderDays" id="reminderDays_data"  readonly="readonly" />
									<label class="default_select">Reminder Days</label>
								</li>
								<li>
									<input type="number" name="trialPeriodInDays" id="trialPeriodInDays_data"  readonly="readonly" />
									<label class="default_select">Trial Period In Days</label>
								</li>

								<li id='emailEnabled_data_container'>
									<input type="text" name="emailEnabled" id="emailEnabled_data"  readonly="readonly" />
									<label class="default_select">Email Enabled</label>
								</li>
							</ul>
							<p class="action_btn" style="clear:both;">
								<a class="submit_btn edit" data-toggle="tab">Edit</a>
							</p>
						</div>
						<form method="post" id="general_config_form" name="general_config_form">
							<div class="generic_message">
								<div class="alert alert-success"></div>
							</div>
							<ul>
								<li>
									<input type="number" name="featureAccessDailyLimit" id="featureAccessDailyLimit" required />
									<label>Feature Access Daily Limit</label>
								</li>
								<li>
									<input type="number" name="featureAccessWeeklyLimit" id="featureAccessWeeklyLimit" required />
									<label>Feature Access Weekly Limit</label>
								</li>
								<li>
									<input type="text" name="reminderDays" id="reminderDays" required />
									<label>Reminder Days</label>
								</li>
								<li>
									<input type="number" name="trialPeriodInDays" id="trialPeriodInDays" required />
									<label>Trial Period In Days</label>
								</li>

								<li id='emailEnabled_container'>
									<select class="selectpicker" name="emailEnabled" id="emailEnabled">
										<option value="false"> False </option>
										<option value="true"> True </option>
									</select>
									<label class="default_select">Email Enabled</label>
								</li>
							</ul>
							<p class="action_btn" style="clear:both;">
								<a class="submit_btn save" data-toggle="tab">Save</a>
							</p>
						</form>
					</div>

					<div id="finven_reset_user_config" class="tabcontent">
						<h3>Reset User</h3>
						<form method="post" id="reset_user_config_form" name="reset_user_config_form">
							<div class="generic_message">
								<div class="alert alert-success"></div>
							</div>
							<ul>
								<li>
									<input type="text" name="userId" id="userId" required />
									<label>User Name</label>
								</li>

								<li>
									<input type="password" name="userPassword" id="userPassword" required />
									<label>Password</label>
								</li>

								<li>
									<input type="text" name="userEmail" id="userEmail" required />
									<label>User Email</label>
								</li>

								<li>
									<input type="text" name="userCompany" id="userCompany" required />
									<label>Company</label>
								</li>

								
								<li>
									<select class="selectpicker" name="userCompanyType" id="userCompanyType">
										<option value="Individual Investor" id="1" title='Individual Investor' selected="selected">Individual Investor</option>
										<option value="University/Phd Student" id="4" title='University/Phd Student'>University/Phd Student</option>
									</select>
									<label class="default_select">Company Type</label>
								</li>
							</ul>
							<p class="action_btn" style="clear:both;">
								<a class="submit_btn save" data-toggle="tab">Save</a>
							</p>
						</form> 
					</div>

					<div id="finven_others_config" class="tabcontent">
						<h3>Others Settings</h3>
						<p>We will be updating shortly</p>
					</div>
				</div>
			</div>
		</div>
	</div>

    <!-- Modal -->
  <div class="modal fade" id="genericProductModal" role="dialog">
    <div class="modal-dialog">
    
      <div class="alert alert-success alert-dismissible">
      	<button type="button" class="close" data-dismiss="modal">&times;</button>
      	<span></span>
      </div>
      
    </div>
  </div>

       <!-- Modal -->
	  <div class="modal fade" id="approveUserSubscription" role="dialog">
	    <div class="modal-dialog">
	    	<!-- Modal content-->
	    	<div class="modal-content">
	    		<div class="modal-header">
	    			<button type="button" class="close" data-dismiss="modal">&times;</button>
	    			<h3>Are you sure you want to Approve?</h3>
	    		</div>
	    		<div class="modal-body">
	    			<div class="btns">
	    				<ul>
	    					<li><button type="button" class="btn btn-lg btn-primary" data-dismiss="modal">Cancel</button></li>
	    					<li><button type="button" class="btn btn-lg btn-primary submitBtn">Submit</button></li>
	    				</ul>
	    			</div>
	    		</div>
	    	</div>
	    </div>
	  </div>


       <!-- Modal -->
	  <div class="modal fade" id="rejectUserSubscription" role="dialog">
	    <div class="modal-dialog">
	    	<!-- Modal content-->
	    	<div class="modal-content">
	    		<div class="modal-header">
	    			<button type="button" class="close" data-dismiss="modal">&times;</button>
	    			<h3>Are you sure you want to Reject?</h3>
	    		</div>
	    		<div class="modal-body">
	    			<div class="btns">
	    				<ul>
	    					<li><button type="button" class="btn btn-lg btn-primary" data-dismiss="modal">Cancel</button></li>
	    					<li><button type="button" class="btn btn-lg btn-primary submitBtn">Submit</button></li>
	    				</ul>
	    			</div>
	    		</div>
	    	</div>
	    </div>
	  </div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/generic_product_config.js"></script>



    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>


	<script>
function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
</script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
					window.location.href = '/';
				</script>
			</c:otherwise>
		</c:choose>

	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>