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
	
	
	<style>		
	
	
	
table {
    border-collapse: collapse;
    width: 100%;
}

th {

background-color: #2aabab;
    color: white;
}

th, td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}
	
		</style>
	
	
	</head>
	<body>
		<jsp:include page="common/head.jsp"></jsp:include>
		<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
		<div class="container">
		<div class="inner-content">
		<div class="inner-left-wrap">Edit Reference Data for Table <b>${refData.tableName}</b></div>	
			<div class="inner-left-wrap">
				<table id="${nav}${subNav}" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Column Name</th>
							<th>Column Type</th>
							<th>Key Type</th>
							<c:if test="${adminAction == 'adminActionEditRefData'}">
								<th>Old Value</th>
							</c:if>
							<th>New Value</th>
						</tr>
					</thead>
					<tbody>					
						<form action="adminUpdateReferenceData" method="post" id="updateReferenceData">
							<c:forEach var="index" begin="0" end="${totalColumns - 1}">
								<tr>
									<td>${columnNames[index]}</td>
									<td>
										${refData.fieldTypeMap[columnNames[index]].columnType}
										<c:if test="${refData.fieldTypeMap[columnNames[index]].columnType == 'VARCHAR'}">
											&nbsp;(${refData.fieldTypeMap[columnNames[index]].columnLength})
										</c:if>
									</td>
									<td>
										<c:choose>
											<c:when test="${refData.fieldTypeMap[columnNames[index]].primaryKey}">
												PRIMARY
											</c:when>
											<c:when test="${refData.fieldTypeMap[columnNames[index]].foreignKey}">
												FOREIGN&nbsp;(${refData.fieldTypeMap[columnNames[index]].foreignKeyTableName})
											</c:when>
										</c:choose>									
									</td>
									<c:if test="${adminAction == 'adminActionEditRefData'}">
										<td>${referenceTableData[index]}</td>
									</c:if>									
									<td>
										<c:choose>
											<c:when test="${refData.fieldTypeMap[columnNames[index]].primaryKey}">
												<c:choose>
													<c:when test="${adminAction == 'adminActionAddRefData'}">
														<c:choose>
															<c:when test="${refData.fieldTypeMap[columnNames[index]].autoIncrement}">
																<input type="text" id="${columnNames[index]}" name="${columnNames[index]}" readonly/>&nbsp;&nbsp;
																<input type="checkbox" id="${columnNames[index]}_check" name="${columnNames[index]}_check" disabled="disabled"/>
															</c:when>
															<c:otherwise>
																<input type="text" id="${columnNames[index]}" name="${columnNames[index]}"/>&nbsp;&nbsp;
																<input type="checkbox" id="${columnNames[index]}_check" name="${columnNames[index]}_check"/>
															</c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
														<input type="text" id="${columnNames[index]}" name="${columnNames[index]}" readonly value="${referenceTableData[index]}"/>&nbsp;&nbsp;
														<input type="checkbox" id="${columnNames[index]}_check" name="${columnNames[index]}_check" checked disabled="disabled"/>											
													</c:otherwise>
												</c:choose>												
											</c:when>
											<c:otherwise>
												<input type="text" id="${columnNames[index]}" name="${columnNames[index]}"/>&nbsp;&nbsp;
												<input type="checkbox" id="${columnNames[index]}_check" name="${columnNames[index]}_check"/>
											</c:otherwise>
										</c:choose>									
									</td>
								</tr>
							</c:forEach>
							<input type="hidden" name="nav" value="${nav}"/>
							<input type="hidden" name="subNav" value="${subNav}"/>
							<input type="hidden" name="adminAction" value="${adminAction}"/>
						</form>
					</tbody>
				</table>
				<br>
				<div align="right">
					<c:choose>
						<c:when test="${adminAction == 'adminActionAddRefData'}">
							<button onClick="$('form#updateReferenceData').submit()">Add</button>
						</c:when>
						<c:otherwise>
							<button onClick="$('form#updateReferenceData').submit()">Update</button>
						</c:otherwise>
					</c:choose>					
				</div>
			</div>
		</div>
		<jsp:include page="adminMenu.jsp"></jsp:include>
		</div>
		<jsp:include page="common/footer.jsp"></jsp:include>	
	</body>
</html>