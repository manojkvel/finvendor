<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>
<%@page import="com.finvendor.util.RequestConstans"%>	
	<div class="wrapper">
		<div class="footer_area_left">
		<div class="right_nav_area_2">
		<div class="right_nav_2">
		 <ul>
		 	<li><a href="${finVen:resolveContextPath(pageContext.request.contextPath)}"><b>HOME</b></a></li>
			<li><a href="#"><b>ABOUT</b></a></li>
			<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS"><b>SOLUTIONS</b></a></li>
			<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES"><b>SERVICES</b></a></li>
			<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES"><b>RESOURCES</b></a></li>
			<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=CONTACT"><b>CONTACT</b></a></li>
		 </ul>
		</div></div>
		<br/>
		© 2015 Fin Vendor. All rights reserved.
		<br/>
		<div class="right_nav_area_2">
			<div class="right_nav_2">
			 <ul>
			  <li><a href="#">Sitemap</a></li>
			  <li><a href="#">Privacy Policy</a></li>
			  <li><a href="#">Advertise</a></li>
			  <li><a href="#">Disclaimer</a></li>
			 </ul>
			</div>
		</div>
		<br/>
		A CreativeLeaves Design
		</div>
		<div class="footer_area_right">
		<br/>
		<div class="con_text">CONNECT WITH US:</div>
		
		<div class="con_icon"><a href="#" onclick="window.open('https://www.facebook.com/finvendor','Facebook_FinVendor','width=800,height=600')"><img src="<%=request.getContextPath() %>/resources/images/s1.png" width="35" height="34" /></a></div>
		<div class="con_icon"><a href="#" onclick="window.open('https://www.linkedin.com/company/finvendor','Linkedin_FinVendor','width=800,height=600')"><img src="<%=request.getContextPath() %>/resources/images/s2.png" width="35" height="34" /></a></div>
		<div class="con_icon"><a href="#" onclick="window.open('https://twitter.com/FinVendor','Twitter_FinVendor','width=800,height=600')"><img src="<%=request.getContextPath() %>/resources/images/s3.png" width="35" height="34" /></a></div>
		<div class="con_icon"><a href="#" onclick="window.open('https://www.youtube.com/channel/UC5vFoU--AH3E8sB5gUkmVqw','Youtube_FinVendor','width=800,height=600')"><img src="<%=request.getContextPath() %>/resources/images/s4.png" width="35" height="34" /></a></div>
		<div class="con_icon"><a href="#"><img src="<%=request.getContextPath() %>/resources/images/s5.png" width="35" height="34" /></a></div>
		<br/>
		</div>
	</div>
	<!-- Basic Jquery plugins start-->
	<script src="<%=request.getContextPath() %>/resources/js/form-wizard.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/resources/js/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/resources/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/resources/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/resources/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>  
 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/additional-methods.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.bootstrap.wizard.min.js"></script>
	<!-- Basic Jquery plugins end here-->
	
	<!-- add to table plugins start-->
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
	<!-- add to table plugins end here-->
	
	<!-- Jquery plugins start-->
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
	<!-- Jquery plugins end here-->
	
	<script src="<%=request.getContextPath()%>/resources/js/userregister.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/base64.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/vendor.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/consumer.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/consumerinviteanrfp.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/marketaggregators.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tradingapplicationvendor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/financialanalyticsvendor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/researchreportprovidervendor.js"></script>
	
	
	<!-- menu options plugins start-->
	<script src="<%=request.getContextPath() %>/resources/js/superfish.js"></script>
	<script>
		(function($) {
			$(document).ready(function() {
				var example = $('#example').superfish({});
			});
		})(jQuery);
		(function($) {
			$(document).ready(function() {
				var example1 = $('#example1').superfish({});
			});
		})(jQuery);
	</script>
	<!-- menu options plugins end here-->
	
	<!-- Show loader plugins start-->
	<script type="text/javascript">
	function showLoader(){
		$('#loading').show();
	}

	function hideLoader(){
		$('#loading').fadeOut(1000);
	} 
	
	$( document ).ajaxStart(function() {
        $( "#loading" ).show();
     });

     $( document ). ajaxComplete(function() {
        $( "#loading" ).hide();
     });
    
  // History back button--:
	 <%--   if (window.history && window.history.pushState) {

	 	    $(window).on('popstate', function() {
	 	      var hashLocation = location.hash;
	 	      var hashSplit = hashLocation.split("#!/");
	 	      var hashName = hashSplit[1];

	 	      if (hashName !== '') {
	 	        var hash = window.location.hash;
	 	        if (hash === '') {
	 	         window.location.href = "<%=request.getServletPath()%>/<%=RequestConstans.Login.HOME%>";
	 	        }
	 	      }
	 	    });

	 	   window.history.pushState('forward', null, './#forward');
	 	}   

     jQuery(window).bind('beforeunload', function(e) {
			if (!validNavigation) {
		    	  window.location.href = "<%=request.getServletPath()%>/<%=RequestConstans.Login.HOME%>";
		      }
		}); 
	  --%>
		/// F5 disbled---:
	  <%--  function disableF5(e) { 
			 if ((e.which || e.keyCode) == 116 ) {
				 	e.preventDefault();
				 	window.location.href = "<%=request.getServletPath()%>/<%=RequestConstans.Login.HOME%>"; 
				 } 
		 }  
	  --%>
		// Ajax session time out------
			
			<%-- function ajaxSessionTimeout()
			{
			    window.location = "<%=request.getContextPath()%>/<%=RequestConstans.Login.HOME%>";
			}
	 
			!function( $ )
			{
			    $.ajaxSetup({
			        statusCode: 
			        {
			            //901: ajaxSessionTimeout
			        	500: ajaxSessionTimeout
			        }
			    });
			}(window.jQuery); --%>
     
	</script>
	
	<div id="loading"></div>
	
	<!-- Show loader plugins end here-->
	
	<!-- XLSX plugins start-->
	<!-- <script lang="javascript" src="dist/xlsx.core.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> -->  
	<!-- XLSX plugins end here-->
  
<!-- Date plugins start-->
	 <%-- <script type="text/javascript" src="<%=request.getContextPath() %>/resources/datepick/jquery.1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/datepick/jsDatePick.jquery.min.1.3.js"></script>  
   <script type="text/javascript">
   jQuery(document).ready(function() {
	   new JsDatePick({
			useMode:2,
			target:"inputField",
			dateFormat:"%d-%M-%Y"
		});
	   new JsDatePick({
			useMode:2,
			target:"inputField1",
			dateFormat:"%d-%M-%Y"
		});
   });
</script>   --%>
<!-- Date plugins end here-->
	
