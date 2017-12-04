<<<<<<< HEAD
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<html>
	<head>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/superfish.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/tab.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/dataTables.jqueryui.min.css" rel="stylesheet"/>
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/superfish.js"></script>		
		<script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/dataTables.jqueryui.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/finvendorCommon.js"></script>
		
		<script>
			
			( function( $ ) {
								
				<c:choose>
					<c:when test="${nav == 'Manage Account'}">
						$(document).ready(function() {
						    $('#userAccountDetails').DataTable();
						} );
					</c:when>
					<c:when test="${nav != null && subNav != null}">
						$(document).ready(function() {
						    $('#${nav}${subNav}').DataTable();
						} );
					</c:when>
				</c:choose>
				
				
			} )( jQuery );
			
			function changeUserAccountStatus(enable) {
				var atLeastOneIsChecked = $('input:checkbox').is(':checked');
				if(!atLeastOneIsChecked) {
					alert("Please select User Account");
					return false;
				}
				var userId = $("input[type='checkbox']:checked").val()
				document.location.href = '/adminUpdateUserAccountStatus?userId=' + userId + "&enable=" + enable;
			}
			
			function resetPassword() {
				var atLeastOneIsChecked = $('input:checkbox').is(':checked');
				if(!atLeastOneIsChecked) {
					alert("Please select User Account");
					return false;
				}
				var userId = $("input[type='checkbox']:checked").val()
				document.location.href = '/adminResetPassword?userId=' + userId;
			}
			
			function deleteReferenceDataRow(nav, subNav, tableKeyName, tableKey) {
				if (confirm('Do you want to delete row with ' + tableKeyName + ' value ' + tableKey)) {
					document.location.href = '/adminDeleteReferenceDataRow?nav=' + nav + "&subNav=" + subNav + "&tableKeyName=" + tableKeyName + "&tableKey=" + tableKey;
				}
			}
			
			function editReferenceDataRow(nav, subNav, tableKey) {
				document.location.href = '/adminEditReferenceDataRow?nav=' + nav + "&subNav=" + subNav + "&tableKey=" + tableKey;
			}
			
			function addReferenceDataRow(nav, subNav) {
				document.location.href = '/adminAddReferenceDataRow?nav=' + nav + "&subNav=" + subNav;
			}
					
		</script>		
	</head>
	<body>
		<jsp:include page="common/head.jsp"></jsp:include>
		<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
		<div class="container">
		<div class="inner-content">
		<div class="inner-left-wrap" id="welcomeAdmin"><span style="color:red">Welcome to Admin Dashboard</span></div>
			<c:choose>
				<c:when test="${subNav != null}">
					<div class="inner-left-wrap" id="${finven:replaceCharacter(nav, ' ', '_')}_${finven:replaceCharacter(subNav, ' ', '_')}">					
				</c:when>
				<c:otherwise>
					<div class="inner-left-wrap" id="${finven:replaceCharacter(nav, ' ', '_')}">
				</c:otherwise>
			</c:choose>				    
		    <div><b><label class="errorMessage" style="color:red">${lastActionError}</label></b></div>
		    <div><b><label class="errorMessage" style="color:green">${lastActionStatus}</label></b></div>
			<br>
			<c:if test="${status == 'true'}"><script>alert('Account Added Successfully')</script></c:if>
			<c:if test="${status == 'false'}"><script>alert('Error Adding Account')</script></c:if>
			<c:choose>
				<c:when test="${nav == 'Add Account'}">
					<form action="${pageContext.request.contextPath}/adminAddAcount" method="post">
						<table id="userAccountDetails" class="display" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>User Name</th>
									<th>Password</th>
									<th>Email</th>
									<th>Company</th>
									<th>Company Type</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input name="userName" type="text"/></td>
									<td><input name="password" type="text"/></td>
									<td><input name="email" type="text"/></td>
									<td><input name="company" type="text"/></td>
									<td><input name="companyType" type="text" value="Data Aggregator,Trading Application,Analytics Application,Research Report"/></td>			      		
								</tr>
							</tbody>
						</table>
						<br>
						<div align="center">
							<button type="submit">Add Account</button>
						</div>
					</form>
				</c:when>
				<c:when test="${nav == 'Manage Account'}">
					<table id="userAccountDetails" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th></th>
								<th>User Id</th>
								<th>Email</th>
								<th>Role</th>
								<th>Active</th>
								<th>Last Login</th>
								<th>Registration Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="user">
								<tr>
									<td><input name="useridcheck" type="checkbox" value="${user.userName}"/></td>
									<td><a href="/adminUserSummaryProfile?nav=Manage Account&userName=${user.userName}">${user.userName}</a></td>
									<td><a href="/adminUserLogin?userName=${user.userName}">${user.email}</a></td>
									<td>${finven:getRole(user.userRoles)}</td>
									<td>
										<c:choose>
											<c:when test="${user.enabled}">Yes</c:when>
											<c:otherwise>No</c:otherwise>
										</c:choose>
									</td>
									<td>${user.lastLogin}</td>	
									<td>${user.registrationDate}</td>				      		
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br>
					<div align="center">
						<button onClick="changeUserAccountStatus(true)">Enable</button>&nbsp;&nbsp;
						<button onClick="changeUserAccountStatus(false)">Disable</button>&nbsp;&nbsp;
						<button onClick="resetPassword()">Reset Password</button>
					</div>
				</c:when>
				<c:otherwise>
					<div align="right">
						<c:if test="${refData != null}">
							<button onClick="addReferenceDataRow('${nav}', '${subNav}')">Add</button>
						</c:if>
					</div>
					<table id="${nav}${subNav}" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<c:forTokens items="${refData.columnNames}" delims="," var="column" varStatus="columnCounter">
									<c:if test="${columnCounter.count == 1}">
										<c:set var="tableKeyName" value="${column}" scope="page"/>
									</c:if>
									<th>${column}</th>								
								</c:forTokens>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${referenceTableData}" var="tableDataRow">
								<tr>
									<c:forEach items="${tableDataRow}" var="columnRow" varStatus="columnCounter">
										<c:if test="${columnCounter.count == 1}">
											<c:set var="tableKey" value="${columnRow}" scope="page"/>
										</c:if>
										<c:choose>
											<c:when test="${columnRow != null}">
												<td>${columnRow}</td>
											</c:when>
											<c:otherwise>
												<td>&nbsp;</td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<td align="center">
										<a href="#" onClick="editReferenceDataRow('${nav}', '${subNav}', '${tableKey}')""><img src="${pageContext.request.contextPath}/resources/images/edit.png" alt="Edit" title="Edit"/></a>&nbsp;
										<a href="#" onClick="deleteReferenceDataRow('${nav}', '${subNav}', '${tableKeyName}', '${tableKey}')"><img src="${pageContext.request.contextPath}/resources/images/delete.png" alt="Delete" title="Delete"/></a>
									</td>
								</tr>																
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		</div>
		<jsp:include page="adminMenu.jsp"></jsp:include>
		</div>
						
		<jsp:include page="common/footer.jsp"></jsp:include>
	</body>
=======
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<html>
	<head>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/superfish.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/tab.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/dataTables.jqueryui.min.css" rel="stylesheet"/>
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/superfish.js"></script>		
		<script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/dataTables.jqueryui.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/finvendorCommon.js"></script>
		
		<script>
			
			( function( $ ) {
								
				<c:choose>
					<c:when test="${nav == 'Manage Account'}">
						$(document).ready(function() {
						    $('#userAccountDetails').DataTable();
						} );
					</c:when>
					<c:when test="${nav != null && subNav != null}">
						$(document).ready(function() {
						    $('#${nav}${subNav}').DataTable();
						} );
					</c:when>
				</c:choose>
				
				
			} )( jQuery );
			
			function changeUserAccountStatus(enable) {
				var atLeastOneIsChecked = $('input:checkbox').is(':checked');
				if(!atLeastOneIsChecked) {
					alert("Please select User Account");
					return false;
				}
				var userId = $("input[type='checkbox']:checked").val()
				document.location.href = '/adminUpdateUserAccountStatus?userId=' + userId + "&enable=" + enable;
			}
			
			function resetPassword() {
				var atLeastOneIsChecked = $('input:checkbox').is(':checked');
				if(!atLeastOneIsChecked) {
					alert("Please select User Account");
					return false;
				}
				var userId = $("input[type='checkbox']:checked").val()
				document.location.href = '/adminResetPassword?userId=' + userId;
			}
			
			function deleteReferenceDataRow(nav, subNav, tableKeyName, tableKey) {
				if (confirm('Do you want to delete row with ' + tableKeyName + ' value ' + tableKey)) {
					document.location.href = '/adminDeleteReferenceDataRow?nav=' + nav + "&subNav=" + subNav + "&tableKeyName=" + tableKeyName + "&tableKey=" + tableKey;
				}
			}
			
			function editReferenceDataRow(nav, subNav, tableKey) {
				document.location.href = '/adminEditReferenceDataRow?nav=' + nav + "&subNav=" + subNav + "&tableKey=" + tableKey;
			}
			
			function addReferenceDataRow(nav, subNav) {
				document.location.href = '/adminAddReferenceDataRow?nav=' + nav + "&subNav=" + subNav;
			}
					
		</script>		
	</head>
	<body>
		<jsp:include page="common/head.jsp"></jsp:include>
		<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
		<div class="container">
		<div class="inner-content">
		<div class="inner-left-wrap" id="welcomeAdmin"><span style="color:red">Welcome to Admin Dashboard</span></div>
			<c:choose>
				<c:when test="${subNav != null}">
					<div class="inner-left-wrap" id="${finven:replaceCharacter(nav, ' ', '_')}_${finven:replaceCharacter(subNav, ' ', '_')}">					
				</c:when>
				<c:otherwise>
					<div class="inner-left-wrap" id="${finven:replaceCharacter(nav, ' ', '_')}">
				</c:otherwise>
			</c:choose>				    
		    <div><b><label class="errorMessage" style="color:red">${lastActionError}</label></b></div>
		    <div><b><label class="errorMessage" style="color:green">${lastActionStatus}</label></b></div>
			<br>
			<c:if test="${status == 'true'}"><script>alert('Account Added Successfully')</script></c:if>
			<c:if test="${status == 'false'}"><script>alert('Error Adding Account')</script></c:if>
			<c:choose>
				<c:when test="${nav == 'Add Account'}">
					<form action="${pageContext.request.contextPath}/adminAddAcount" method="post">
						<table id="userAccountDetails" class="display" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>User Name</th>
									<th>Password</th>
									<th>Email</th>
									<th>Company</th>
									<th>Company Type</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input name="userName" type="text"/></td>
									<td><input name="password" type="text"/></td>
									<td><input name="email" type="text"/></td>
									<td><input name="company" type="text"/></td>
									<td><input name="companyType" type="text" value="Data Aggregator,Trading Application,Analytics Application,Research Report"/></td>			      		
								</tr>
							</tbody>
						</table>
						<br>
						<div align="center">
							<button type="submit">Add Account</button>
						</div>
					</form>
				</c:when>
				<c:when test="${nav == 'Manage Account'}">
					<table id="userAccountDetails" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th></th>
								<th>User Id</th>
								<th>Email</th>
								<th>Role</th>
								<th>Active</th>
								<th>Last Login</th>
								<th>Registration Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="user">
								<tr>
									<td><input name="useridcheck" type="checkbox" value="${user.userName}"/></td>
									<td><a href="/adminUserSummaryProfile?nav=Manage Account&userName=${user.userName}">${user.userName}</a></td>
									<td><a href="/adminUserLogin?userName=${user.userName}">${user.email}</a></td>
									<td>${finven:getRole(user.userRoles)}</td>
									<td>
										<c:choose>
											<c:when test="${user.enabled}">Yes</c:when>
											<c:otherwise>No</c:otherwise>
										</c:choose>
									</td>
									<td>${user.lastLogin}</td>	
									<td>${user.registrationDate}</td>				      		
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br>
					<div align="center">
						<button onClick="changeUserAccountStatus(true)">Enable</button>&nbsp;&nbsp;
						<button onClick="changeUserAccountStatus(false)">Disable</button>&nbsp;&nbsp;
						<button onClick="resetPassword()">Reset Password</button>
					</div>
				</c:when>
				<c:otherwise>
					<div align="right">
						<c:if test="${refData != null}">
							<button onClick="addReferenceDataRow('${nav}', '${subNav}')">Add</button>
						</c:if>
					</div>
					<table id="${nav}${subNav}" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<c:forTokens items="${refData.columnNames}" delims="," var="column" varStatus="columnCounter">
									<c:if test="${columnCounter.count == 1}">
										<c:set var="tableKeyName" value="${column}" scope="page"/>
									</c:if>
									<th>${column}</th>								
								</c:forTokens>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${referenceTableData}" var="tableDataRow">
								<tr>
									<c:forEach items="${tableDataRow}" var="columnRow" varStatus="columnCounter">
										<c:if test="${columnCounter.count == 1}">
											<c:set var="tableKey" value="${columnRow}" scope="page"/>
										</c:if>
										<c:choose>
											<c:when test="${columnRow != null}">
												<td>${columnRow}</td>
											</c:when>
											<c:otherwise>
												<td>&nbsp;</td>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<td align="center">
										<a href="#" onClick="editReferenceDataRow('${nav}', '${subNav}', '${tableKey}')""><img src="${pageContext.request.contextPath}/resources/images/edit.png" alt="Edit" title="Edit"/></a>&nbsp;
										<a href="#" onClick="deleteReferenceDataRow('${nav}', '${subNav}', '${tableKeyName}', '${tableKey}')"><img src="${pageContext.request.contextPath}/resources/images/delete.png" alt="Delete" title="Delete"/></a>
									</td>
								</tr>																
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		</div>
		<jsp:include page="adminMenu.jsp"></jsp:include>
		</div>
						
		<jsp:include page="common/footer.jsp"></jsp:include>
	</body>
>>>>>>> origin/master
</html>