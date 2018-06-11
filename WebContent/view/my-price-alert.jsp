<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<!DOCTYPE html>
<head>
	<title>My Watchlist - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true" ></jsp:include>
	
	<div class="container" id="my_pricealert">
		<div class="inner-breadcrumb">
			<h5>Price / Research Report Alerts</h5>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<div class="pricealert_header">
					<ul>
						<li>
							<button type="button" class="fa fa-edit editBtn" title="Edit" disabled  data-toggle="modal" data-target="#setPriceAlert"></button>
						</li>
						<li>
							<button type="button" class="fa fa-trash deleteBtn" title="Delete" disabled  data-toggle="modal" data-target="#deletePriceAlert"></button>
						</li>
					</ul>
				</div>
				<div id="pricealert_container">
					
					<table id='pricealert_table'>
						<thead>
							<tr>
								<th style="width:20%;">
									<p class="normal_font">
										Company Name
									</p>
								</th>
								<th style="width:20%;">
									<p class="normal_font">
										Day<br />
										(-ve/+ve)
										<br />
										change
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										Week<br />
										(-ve/+ve)
										<br />
										change
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										Month<br />
										(-ve/+ve)
										<br />
										change
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										No Timeframe<br />
										(-ve/+ve)
										<br />
										change
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										Research Report
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
					</table>
				</div>
			</div>
			<div class="col-xs-12 col-sm-3">
				<div class="ads_space">Keep Watching this space for ads!!</div>
			</div>
		</div>
	</div>

    <jsp:include page="common/footer.jsp"></jsp:include>



       <!-- Modal -->
	  <div class="modal fade" id="deletePriceAlert" role="dialog">
	    <div class="modal-dialog">
	    	<!-- Modal content-->
	    	<div class="modal-content">
	    		<div class="modal-header">
	    			<button type="button" class="close" data-dismiss="modal">&times;</button>
	    			<h3>Are you sure you want to delete from watchlist?</h3>
	    		</div>
	    		<div class="modal-body">
	    			<div class="btns">
	    				<ul>
	    					<li><button type="button" class="btn btn-lg btn-primary" data-dismiss="modal">Cancel</button></li>
	    					<li><button type="button" class="btn btn-lg btn-primary deleteBtn">Delete</button></li>
	    				</ul>
	    			</div>
	    		</div>
	    	</div>
	    </div>
	  </div>

	  <!-- Modal -->
  <div class="modal fade" id="setPriceAlert" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h3>Add an alert for a stock for price and new research reports. Alerts are delivered to your email.</h3>
          <div class="alert alert-success alert-dismissible">
          	<span>This alert box could indicate a successful or positive action.</span>
          </div>
        </div>
        <div class="modal-body">
        	<div class="alert-cards">
	        	<h4>Stock Price <a class="fa fa-info-circle" href="javascript:void(0);" data-toggle='tooltip' title='Configure alerts for price'></a></h4>
	          	<table>
	          		<tr>
	          			<th>&nbsp;</th>
	          			<th>% change (-ve)</th>
	          			<th>% change (+ve)</th>
	          		</tr>
	          		<tr>
	          			<td>Day</td>
	          			<td>
	          				<span>-</span>
	          				<input type="number" name="day_min_price" val="" />
	          				<span>%</span>
	          			</td>
	          			<td>
	          				<span>+</span>
	          				<input type="number" name="day_max_price" val="" />
	          				<span>%</span>
	          			</td>
	          		</tr>
	          		<tr>
	          			<td>Week</td>
	          			<td>
	          				<span>-</span>
	          				<input type="number" name="week_min_price" val="" />
	          				<span>%</span>
	          			</td>
	          			<td>
	          				<span>+</span>
	          				<input type="number" name="week_max_price" val="" />
	          				<span>%</span>
	          			</td>
	          		</tr>
	          		<tr>
	          			<td>Month</td>
	          			<td>
	          				<span>-</span>
	          				<input type="number" name="month_min_price" val="" />
	          				<span>%</span>
	          			</td>
	          			<td>
	          				<span>+</span>
	          				<input type="number" name="month_max_price" val="" />
	          				<span>%</span>
	          			</td>
	          		</tr>
	          		<tr>
	          			<td>No Timeframe</td>
	          			<td>
	          				<span>-</span>
	          				<input type="number" name="noTimeFrame_min_price" val="" />
	          				<span>%</span>
	          			</td>
	          			<td>
	          				<span>+</span>
	          				<input type="number" name="noTimeFrame_max_price" val="" />
	          				<span>%</span>
	          			</td>
	          		</tr>
	          	</table>
          	</div>
          	<div class="alert-cards">
	        	<h4>Research Report <a class="fa fa-info-circle" href="javascript:void(0);" data-toggle='tooltip' title='Configure alerts for research report'></a></h4>
	        	<table class="research_report">
	        		<tr>
	        			<td>
	          				<input type="checkbox" name="alert_new_research_report" val="" /> 
	          			</td>
	        			<td>
	          				Alert New Research Report
	          			</td>
	          		</tr>
	          	</table>
          	</div>
        </div>
        <div class="modal-footer">
          	<button type="button" name="set_alert_btn" class="btn btn-info btn-lg">Set Alert</button>
        </div>
      </div>
      
    </div>
  </div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pricealert.js"></script>
</body>
</html>