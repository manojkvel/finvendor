<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<!DOCTYPE html>
<head>
	<title>Manage User Subscriptions - Finvendor</title>
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
			
	<div class="container-fluid" id="manage_user_subscriptions">
		<div class="inner_breadcrumb">
			<h5>Manage User Subscriptions</h5>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="manage_user_subscriptions_content">
					<div class="subscriptions_header">
						<ul>
							<li>
								<button type="button" class="btn approveBtn" title="Approve" disabled  data-toggle="modal" data-target="#approveUserSubscription">Approve</button>
								<button type="button" class="btn rejectBtn" title="Reject" disabled  data-toggle="modal" data-target="#rejectUserSubscription">Reject</button>
							</li>
							<li>
								<div id="user_subscriptions_search">
									<form action="#" class="elastic-search desktop_variant" method="GET">
										<div class="input-group">
											<input id="mangerUserSubscriptionsSearchBox" name="mangerUserSubscriptionsSearchBox" class="form-control" autocomplete="off" name="searchKeyword" type="text" placeholder="Search by User Id"  onkeyup="getUserById()">
										</div>
									</form>
								</div>
							</li>
							<li>
								<label>From</label>
								<input type="text" placeholder="DD/MM/YYYY" id="transactionDateFrom" name="transactionDateFrom" />
								<label>To</label>
								<input type="text" placeholder="DD/MM/YYYY" id="transactionDateTo" name="transactionDateTo" />
							</li>
							<li>
								<div class="max_per_page">
									<span>Records Per Page </span>
									<select>
										<option value='5'>5</option>
										<option value='10' selected>10</option>
										<option value='30'>30</option>
										<option value='50'>50</option>
										<option value='100'>100</option>
									</select>

									<span id='total_records_count' style="padding-left: 30px;font-weight:bold;font-size: 13px;"></span>
								</div>
							</li>
						</ul>
					</div>
					<div id="user_subscriptions_container">
						
						<table id='user_subscriptions_table'>
							<thead>
								<tr>
									<th style="width:20%;">
										<p class="normal_font">
											User Id
										</p>
									</th>
									<th style="width:20%;">
										<p class="normal_font">
											Subscription Type
										</p>
									</th>
									<th style="width:15%;">
										<p class="normal_font">
											Payment Mode
										</p>
									</th>
									<th style="width:15%;">
										<p class="normal_font">
											Transaction Date
										</p>
									</th>
									<th style="width:15%;">
										<p class="normal_font">
											Transaction Ref Number
										</p>
									</th>
									<th style="width:15%;">
										<p class="normal_font">
											Bank Name
										</p>
									</th>
									<th style="width:15%;">
										<p class="normal_font">
											Bank Holder Name
										</p>
									</th>
									<th style="width:15%;">
										<p class="normal_font">
											Amount
										</p>
									</th>
									<th style="width:15%;">
										<p class="normal_font">
											STATUS
										</p>
									</th>
									<th style="width:15%;">
										<p class="normal_font">
											<input name="selectAll" data-id="selectAll" class="submit-button" type="checkbox" />
										</p>
									</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="9">
										<p>There is no matching records found.</p>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
			<!--<div class="col-xs-12 col-sm-3">
				<jsp:include page="common/inner_sidebar.jsp"></jsp:include>
			</div>-->
		</div>
	</div>

    <!-- Modal -->
  <div class="modal fade" id="addToWatchlist" role="dialog">
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

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/manage_user_subscriptions.js"></script>

	<script type="text/javascript">
		$( function() {
			$( "#transactionDateFrom" ).datepicker({
				dateFormat: 'dd/mm/yy',
				maxDate: "0D"
			});

			$( "#transactionDateTo" ).datepicker({
				dateFormat: 'dd/mm/yy',
				maxDate: "0D"
			});
		});

    	function getUserById() {
    		var input, filter, tbody, tr, td, i;
		    input = document.getElementById("mangerUserSubscriptionsSearchBox");
		    filter = input.value.toUpperCase();
		    tr = jQuery("#user_subscriptions_table tbody tr");
		    for (i = 0; i < tr.length; i++) {
		        td = tr[i].getElementsByTagName("td")[0];
		        if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
		            tr[i].style.display = "";
		        } else {
		            tr[i].style.display = "none";
		        }
		    }

		    if(!jQuery("#user_subscriptions_table tbody tr").is(':visible')) {
		    	$("tfoot").show();
		    } else {
		    	$("tfoot").hide();
		    }
    	}
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