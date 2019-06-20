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
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true" ></jsp:include>
	
	<div class="container" id="my_watchlist">
		<div class="inner_breadcrumb">
			<h5>My Watchlist</h5>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<div class="watchlist_header">
					<ul>
						<li>
							<div id="watchlist_search">
								<form action="#" class="elastic-search desktop_variant" method="GET">
									<div class="input-group">
										<input id="watchListSearchBox" name="watchListSearchBox" class="form-control" autocomplete="off" name="searchKeyword" type="text" placeholder="Search to add company">
										<div class="input-group-addon" style="width:40px;">
											<span class="fa fa-plus-circle"><input name="watchListSearchBoxSubmit" class="submit-button" type="submit" data-toggle="modal" data-target="#addToWatchlist" /></span>
										</div>
										<div class="input-group-addon close-btn"><span class="glyphicon glyphicon-remove"><a href="javascript:;"></a></span></div>
									</div>
									<div class="suggestions" style="display:none" id="tblSuggestions">
										<div id="tableBodyAutocomplete">
										</div>
									</div>
								</form>
							</div>
						</li>
						<li>
							<button type="button" class="fa fa-trash deleteBtn" title="Delete" disabled  data-toggle="modal" data-target="#deleteWatchlist"></button>
						</li>
					</ul>
				</div>
				<div id="watchlist_container">
					
					<table id='watchlist_table'>
						<thead>
							<tr>
								<th style="width:20%;">
									<p class="normal_font">
										Company Name
									</p>
								</th>
								<th style="width:20%;">
									<p class="normal_font">
										Price When added
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										CMP
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										% Change since added
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										Price Change
									</p>
								</th>
								<th style="width:15%;">
									<p class="normal_font">
										% Change
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
				<jsp:include page="common/inner_sidebar.jsp"></jsp:include>
			</div>
		</div>
	</div>

    <jsp:include page="common/footer.jsp"></jsp:include>

    <!-- Modal -->
  <div class="modal fade" id="addToWatchlist" role="dialog">
    <div class="modal-dialog">
    
      <div class="alert alert-success alert-dismissible">
      	<button type="button" class="close" data-dismiss="modal">&times;</button>
      	<span>This alert box could indicate a successful or positive action.</span>
      </div>
      
    </div>
  </div>

       <!-- Modal -->
	  <div class="modal fade" id="deleteWatchlist" role="dialog">
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

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/watchlist.js"></script>
</body>
</html>