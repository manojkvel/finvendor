<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>
<%@page import="com.finvendor.util.RequestConstans"%>	
<div class="footer-container">
	<div class="container">
		<div class="footer" id="footer">
			<div class="footer-left-wrap">
				<ul>
					<li><a href="${finVen:resolveContextPath(pageContext.request.contextPath)}"><font style="font-weight:bold"><b>HOME</b></font></a></li>
					<li><a href="#"><font style="font-weight:bold"><b>ABOUT</b></font></a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS"><font style="font-weight:bold"><b>SOLUTIONS</b></font></a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES"><font style="font-weight:bold"><b>SERVICES</b></font></a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES"><font style="font-weight:bold"><b>RESOURCES</b></font></a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=CONTACT"><font style="font-weight:bold"><b>CONTACT</b></font></a></li>
				</ul>
				<ul class="send-ul">
					<li><font style="font-weight:bold">© 2015 Fin Vendor. All rights reserved.</font></li>
					<li><a href="<%=request.getContextPath()%>/sitemap"><font style="font-weight:bold"><b>Sitemap</b></font></a></li>
					<li><a href="<%=request.getContextPath()%>/privacy"><font style="font-weight:bold"><b>Privacy Policy</b></font></a></li>
					<li><a href="#"><font style="font-weight:bold"><b>Advertise</b></font></a></li>
					<li><a href="<%=request.getContextPath()%>/disclaimer"><font style="font-weight:bold"><b>Disclaimer</b></font></a></li>
				</ul>
			</div>
			<div class="footer-right-wrap" id="footer-right-wrap">
				<p><font style="font-weight:bold"><b>CONNECT WITH US:</b></font></p>
				<ul>
					<li><a href="https://www.facebook.com/finvendor" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/a1.png" alt="Facebook" title=""/></a></li>
					<li><a href="https://www.linkedin.com/company/finvendor" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/a2.png" alt="Linkedin" title=""/></a></li>
					<li><a href="https://twitter.com/FinVendor" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/a3.png" alt="Twitter" title=""/></a></li>
					<li><a href="https://www.youtube.com/channel/UC5vFoU--AH3E8sB5gUkmVqw" target="_blank"><img src="${pageContext.request.contextPath}/resources/images/a4.png" alt="You Tube" title="" /></a></li>
					<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/a5.png" alt="" title="" /></a></li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
		<script src="${pageContext.request.contextPath}/resources/js/CreateHTML5Elements.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jqueryvalidation/jquery.validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jqueryvalidation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jqueryvalidation/additional-methods.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jqueryvalidation/additional-methods.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.tabs.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/jquery.bxslider.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/userregister.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/base64.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/form-wizard.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/resources/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/resources/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/resources/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/additional-methods.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.bootstrap.wizard.min.js"></script>
	<!-- Basic Jquery plugins end here-->
	
	<!-- add to table plugins start-->
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
	<!-- add to table plugins end here-->
	
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
		$(document).ready(function() {
			$('.slider2').bxSlider({
				slideWidth : 740,
				minSlides : 1,
				maxSlides : 1,
				slideMargin : 15,
				auto : true,
			});
		});
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/superfish.js"></script>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jssor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jssor.slider.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/marketaggregators.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/loginmain.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/loginmodernizr.js"></script>
	<script>
		jQuery(document)
				.ready(function($) {
							var options = {
								$AutoPlay : true,
								$AutoPlaySteps : 1,
								$AutoPlayInterval : 4000,
								$PauseOnHover : 1,
								$ArrowKeyNavigation : true,
								$SlideDuration : 500,
								$MinDragOffsetToSlide : 20,
								$SlideSpacing : 5,
								$DisplayPieces : 1,
								$ParkingPosition : 0,
								$UISearchMode : 1,
								$PlayOrientation : 1,
								$DragOrientation : 3,
								$ThumbnailNavigatorOptions : {
									$Class : $JssorThumbnailNavigator$,
									$ChanceToShow : 2,
									$ActionMode : 1,
									$AutoCenter : 3,
									$Lanes : 1,
									$SpacingX : 0,
									$SpacingY : 0,
									$DisplayPieces : 5,
									$ParkingPosition : 0,
									$Orientation : 1,
									$DisableDrag : true
								}
							};
							var jssor_slider2 = new $JssorSlider$(
									"slider2_container", options);
							function ScaleSlider() {
								var parentWidth = jssor_slider2.$Elmt.parentNode.clientWidth;
								if (parentWidth) {
									var sliderWidth = parentWidth;
									sliderWidth = Math.min(sliderWidth, 1000);
									jssor_slider2.$ScaleWidth(sliderWidth);
								} else
									window.setTimeout(ScaleSlider, 30);
							}
							ScaleSlider();
							$(window).bind("load", ScaleSlider);
							$(window).bind("resize", ScaleSlider);
							$(window).bind("orientationchange", ScaleSlider);
						});
		
		function newConfirmMsg(buttonId, buttonNames){
			var def = $.Deferred();
			
			var index;
			var theButtons = {};
			
			for (index = 0; index<buttonNames.length; ++index) {
				if(buttonNames[index] == "Yes") {
					theButtons["Yes"] = function() { 
					def.resolve(true);
					$( this ).dialog( "destroy" );
					};	
				}else if(buttonNames[index] == "No") {
					theButtons["No"] = function() { 
					def.resolve(false);
					$( this ).dialog( "destroy" );
					};
				}else if(buttonNames[index] == "Cancel") {
					theButtons["Cancel"] = function() { 
					def.resolve(false);
					$( this ).dialog( "destroy" );
					};
				}else if(buttonNames[index] == "OK") {
					theButtons["OK"] = function() { 
						def.resolve(true);
						$( this ).dialog( "destroy" );
						};	
					}
			}
    
			$("#"+buttonId).dialog({
				title: 'Confirm', 
				autoOpen: true,
				resizable: false,
			    modal: true,
			    draggable: false,
			    position: ['center','middle'],
				buttons: theButtons
			});
	
			return def.promise();
		}
		
		// History back button--:
		<%--    if (window.history && window.history.pushState) {
		 	    $(window).on('popstate', function() {
		 	      var hashLocation = location.hash;
		 	      var hashSplit = hashLocation.split("#!/");
		 	      var hashName = hashSplit[1];

		 	      if (hashName !== '') {
		 	        var hash = window.location.hashName;
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
			});  --%>
		 
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
