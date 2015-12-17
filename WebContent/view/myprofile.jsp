<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>finvendor</title>
<link href="<%=request.getContextPath() %>/resources/css/singleasset.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/resources/css/style-II.css" type="text/css" rel="stylesheet" />

<link href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/resources/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/resources/css/reset.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="top_header">
		<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	</div>
	<div class="wrapper">
	<div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.MarketAggregators.MARKETAGGREGATORS%>">My Profile</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">View</div></div>
	<div class="row-fluid">
		<div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
								<div class="caption">
									<i class="icon-reorder"></i> My Profile - <span class="step-title">Step 1 of 4</span>
								</div>
							</div>
							<div class="portlet-body form">
								<form action="#" class="form-horizontal" id="submit_form" method="post">
									 <div class="form-wizard">
							<div class="navbar">
								<div class="navbar-inner">
									<ul class="row-fluid">
										<li class="span3">
											<a href="tab1"   class="step">
											<span class="number">1</span>
											<span class="desc"><i class="icon-ok"></i>Company Support Details</span>   
											</a>
										</li>
										<li class="span3">
											<a href="tab2"  class="step">
											<span class="number">2</span>
											<span class="desc"><i class="icon-ok"></i>Data Coverage</span>   
											</a>
										</li>
						                <li class="span3">
											<a href="tab3"   class="step">
											<span class="number">3</span>
												<span class="desc"><i class="icon-ok"></i>Support Details</span>   
											</a> 
										</li>
										<li class="span3">
											<a href="tab4"   class="step">
											<span class="number">4</span>
												<span class="desc"><i class="icon-ok"></i>Award Details</span>   
											</a> 
										</li>
										 
									</ul>
								</div>
							</div>
							<div id="bar" class="progress progress-success progress-striped">
								<div class="bar"></div>
							</div>
						<div class="tab-content" style="background-color: white;">
							<div class="tab-pane active" id="tab1">
								<h3 class="block"> Company Support Details  </h3>
								<div class="control-group">
									<label class="control-label">First Name<span class="required">*</span></label>
									<div class="controls">
										<input type="text" id="orgname" placeholder="First Name" name="orgname" class="m-wrap large"   />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Last Name<span class="required">*</span></label>
									<div class="controls">
										<input type="text" id="orgname" placeholder="Last Name" name="orgname" class="m-wrap large" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Designation<span class="required">*</span></label>
									<div class="controls">
										<input type="text" id="orgname" placeholder="Designation" name="orgname" class="m-wrap large"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Company<span class="required">*</span></label>
									<div class="controls">
										<input type="text" id="orgname" placeholder="Company" name="orgname" class="m-wrap large" />
									</div>
								</div>
									<div class="form-actions clearfix">
									<div class="se">
									<input type="hidden" value="${tab1value}" id="userid"/>
										<input type="submit" value="Save" class="input" style="padding: 6px 33px 2px 33px;"/>
										<a  onclick="continuetab('${tab1value}');"   class="btn blue button-next" class="step" style="padding: 8px 29px 2px 20px; font-family:Raleway,sans-serif; font-size: 14px; ">
										Continue <i class="m-icon-swapright m-icon-white"></i>
										</a>
									</div>
									</div>
									
								</div>
									<div class="tab-pane" id="tab2">
									   <h3 class="block"> Company Support Details  </h3>
								<div class="control-group">
									<label class="control-label">First Name<span class="required">*</span></label>
									<div class="controls">
										<input type="text" id="orgname" placeholder="First Name" name="orgname" class="m-wrap large"   />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Last Name<span class="required">*</span></label>
									<div class="controls">
										<input type="text" id="orgname" placeholder="Last Name" name="orgname" class="m-wrap large" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Designation<span class="required">*</span></label>
									<div class="controls">
										<input type="text" id="orgname" placeholder="Designation" name="orgname" class="m-wrap large"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">Company<span class="required">*</span></label>
									<div class="controls">
										<input type="text" id="orgname" placeholder="Company" name="orgname" class="m-wrap large" />
									</div>
								</div>
									<div class="form-actions clearfix">
										<a href="#tab2" class="btn blue button-next" data-toggle="tab" class="step">
										Continue <i class="m-icon-swapright m-icon-white"></i>
										</a>
									</div>
									</div>
					            <div class="tab-pane" id="tab4">
									<h3 class="block">Confirm your Organization</h3>
									<h4 class="form-section">Organization Type</h4>
									<div class="control-group">
										<label class="control-label">Organization Type:</label>
										<div class="controls">
											<span class="text display-value" data-display="orgtype"></span>
										</div>
									</div>
									
									<div class="control-group" id="displayMemberType">
										<label class="control-label">Member Type:</label>
										<div class="controls">
											<span class="text display-value" data-display="membertype"></span>
										</div>
									</div>
									
									<div class="control-group" id="displayParentOrganization">
										<label class="control-label">Parent Organization:</label>
										<div class="controls">
											<span class="text display-value" data-display="banktype"></span>
										</div>
									</div>
									
									<h4 class="form-section">Organization Details</h4>
									<div class="control-group">
										<label class="control-label">Organization Name:</label>
										<div class="controls">
											<span class="text display-value" data-display="orgname"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Website:</label>
										<div class="controls">
											<span class="text display-value" data-display="website"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Address 1:</label>
										<div class="controls">
											<span class="text display-value" data-display="address1"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Address 2:</label>
										<div class="controls">
											<span class="text display-value" data-display="address2"></span>
										</div>
									</div>
									<div class="form-actions clearfix">
										<a href="#tab2" class="btn button-previous" data-toggle="tab" class="step">
										<i class="m-icon-swapleft"></i> Back 
										</a>
										<a href="javascript:saveOraganization();" class="btn blue button-submit" id="submitButton">
										Submit <i class="m-icon-swapright m-icon-white"></i>
										</a>
										<a class="btn blue" onclick="cancelPopup();">Cancel</a>
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
        <div class="row-fluid no-space-steps margin-bottom-40"></div>
    </div>
</div>
	<div class="footer_area">
	  <jsp:include page="common/dashboardfooter.jsp" ></jsp:include>
	</div>
	<script type="text/javascript">
	function continuetab(tabname){
		alert('going tab--:' + tabname);
		   if(tabname != '' && tabname.length > 0 && tabname.match("tab1")){
			   alert('tab value---:' + tabname);
			   $("#tab1").slideDown("slow");
			   $("#tab2").slideDown("slow");
		   }
	}
	</script>
</body>
</html>
