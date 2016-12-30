<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<script>
	(function( $ ) {									
		$( document ).ready(function() {					
			$('#sidelinksAdmin a').click(function(e) {						
				$('.inner-left-wrap').hide();
				var checkElement = $(this).text();
				var divId = $(this).parent().data("div-id");
				if(divId.indexOf("_") > 0){
					var breadcrumbLink = '<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=' + divId.substring(0, divId.indexOf("_")) + '">' + divId.substring(0, divId.indexOf("_")) + '</a></li>';
					checkElement =  '<li class="li-spr">></li>' + breadcrumbLink + '<li class="li-spr">></li><li>' + checkElement + '</li></ul>';
				}else{
					checkElement = '<li class="li-spr">></li><li>' + checkElement + '</li></ul>';
				}
				divId = '#' + divId;
				$(divId).show();
				$('#sidelinksAdmin li').removeClass('li-ico');
				$(this).closest('li').addClass('li-ico');
				$('#breadcrumb_text').html('<ul><li><a href="${finven:resolveContextPath(pageContext.request.contextPath)}">HOME</a></li>' + checkElement);
				checkElement = $(this).next();
				if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
				  $(this).closest('li').removeClass('active');
				  checkElement.slideUp('normal');
				}
				if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
				  $('#sidelinksAdmin ul ul:visible').slideUp('normal');
				  checkElement.slideDown('normal');
				}
			});
		});
	})( jQuery );
</script>
<c:set var="requestMapKey" value="${nav}_${subNav}"/>

	<div class="inner-sidebar-wrap">
		<div class="sidebar-ctn-wrap cnt-ctn-wrap">
 				<div class="head">
   				<h3>Reference Data</h3>
 				</div>
 				<div class="content" id="sidelinksAdmin">
  	 				<ul>
			    	<li data-div-id="ASSET"><a href="#">ASSET</a>
						<ul id="ASSET_ul">
							<li data-div-id="ASSET_ac"><a href="/adminReferenceDataDetails?nav=ASSET&subNav=ac">Asset Class</a></li>
							<li data-div-id="ASSET_sec"><a href="/adminReferenceDataDetails?nav=ASSET&subNav=sec">Security</a></li>
						</ul>
			      	</li>
    					<li data-div-id="GEOGRAPHICAL"><a href="#">GEOGRAPHICAL</a>
				      	<ul id="GEOGRAPHICAL_ul">
				      		<li data-div-id="GEOGRAPHICAL_r"><a href="/adminReferenceDataDetails?nav=GEOGRAPHICAL&subNav=r">Region</a></li>
				      		<li data-div-id="GEOGRAPHICAL_c"><a href="/adminReferenceDataDetails?nav=GEOGRAPHICAL&subNav=c">Country</a></li>
				      		<li data-div-id="GEOGRAPHICAL_e"><a href="/adminReferenceDataDetails?nav=GEOGRAPHICAL&subNav=e">Exchange</a></li>
				      		<li data-div-id="GEOGRAPHICAL_cur"><a href="/adminReferenceDataDetails?nav=GEOGRAPHICAL&subNav=cur">Currency</a></li>
				      	</ul>
     				</li>
     				</li>
    					<li data-div-id="OTHER"><a href="#">OTHER</a>
				      	<ul id="OTHER_ul">
				      		<li data-div-id="OTHER_dm"><a href="/adminReferenceDataDetails?nav=OTHER&subNav=dm">Distribution Mode</a></li>
				      		<li data-div-id="OTHER_supp"><a href="/adminReferenceDataDetails?nav=OTHER&subNav=supp">Support</a></li>
				      		<li data-div-id="OTHER_rsrcharea"><a href="/adminReferenceDataDetails?nav=OTHER&subNav=rsrcharea">Research Area</a></li>
				      		<li data-div-id="OTHER_rsrchsubarea"><a href="/adminReferenceDataDetails?nav=OTHER&subNav=rsrchsubarea">Research Sub Area</a></li>
				      		<li data-div-id="OTHER_analytsolutiotype"><a href="/adminReferenceDataDetails?nav=OTHER&subNav=analytsolutiotype">Analytical Solution Type</a></li>
				      		<li data-div-id="OTHER_analytsolutiosubtype"><a href="/adminReferenceDataDetails?nav=OTHER&subNav=analytsolutiosubtype">Analytical Solution Sub Type</a></li>
				      	</ul>
     				</li>
   				</ul>
 				</div>
 			</div>
		<div class="sidebar-ctn-wrap cnt-ctn-wrap">
					<div class="head">
  					<h3>User Maintenance</h3>
					</div>
 				<div class="content" id="sidelinksAdmin">
   				<ul>
					<li data-div-id="Manage_Account"><a href="/adminViewUserDetails?nav=Manage Account">Manage Account</a></li>
					<li data-div-id="Review_Vendor"><a href="#">Review Vendor</a></li>
					<li data-div-id="Review_Consumer"><a href="#">Review Consumer</a></li>
   				</ul>
 				</div>
		</div>
	</div>
	<div class="clearfix"></div>

<script>
	$('#ASSET_ul').slideUp();
	$('#GEOGRAPHICAL_ul').slideUp();
	$('#OTHER_ul').slideUp();
	<c:choose>
	 	<c:when test="${subNav != null}">
	 		$('li').filter(function() { return $.text([this]) === '${requestConstants.reqParamDescriptionMap[requestMapKey]}'; }).addClass('li-ico');
	 		$('#welcomeAdmin').hide();
	 	</c:when>
	 	<c:when test="${nav != null}">
	 		$( "#sidelinksAdmin li:contains('${nav}')").addClass('li-ico');
	 		$('#welcomeAdmin').hide();
	 	</c:when>
	 </c:choose>
	$('#${nav}_ul').slideDown();			
</script>