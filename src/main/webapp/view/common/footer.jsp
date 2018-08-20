<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<footer>
	<div id="footer" class="footer_wrapper">
		<div id="footer_top">
				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-12">
						<div class="section_one">
							<h3>About Us</h3>
							<p>
								Finvendor strives for democratizing the inventor's community. Finvendor endeavours to help retail and institutional investors make a well-informed investment decisions. <br />Finvendor sources the equity, sector, macroeconomic and debt research reports for Indian markets from the registered Brokers, economists and other sell-side independent research Analysts.
							</p>
							<ul class="social">
								<li><a href="https://www.facebook.com/finvendor"
									target="_blank" class="fa fa-facebook-square" title="Facebook"></a>
								<li>
								<li><a href="https://twitter.com/FinVendor" target="_blank"
									class="fa fa-twitter-square" title="Twitter"></a>
								<li>
								<li><a href="https://www.linkedin.com/company/finvendor"
									target="_blank" class="fa fa-linkedin-square" title="LinkedIn"></a>
								<li>
								<li><a
									href="https://www.youtube.com/channel/UC5vFoU--AH3E8sB5gUkmVqw"
									target="_blank" class="fa  fa-youtube-square" title="YouTube"></a>
								<li>
								<li><a href="#" target="_blank" class="fa fa-rss-square" title="RSS Feed"></a>
								<li>
							</ul>
						</div>
					</div>
					<div class="col-md-5 col-sm-4 col-xs-12">
						<div class="section_two">
							<h3>Quick Links</h3>
							<ul>
								<li><a
									href="${pageContext.request.contextPath}/view/research-reports.jsp">
										<span class="fa fa-chevron-right"></span> Research Reports
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/view/brokers-analysts.jsp">
										<span class="fa fa-chevron-right"></span> Brokers/Analysts
								</a></li>
								<li><a
									href="${finVen:resolveContextPath(pageContext.request.contextPath)}">
										<span class="fa fa-chevron-right"></span> home
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/view/about-us.jsp">
										<span class="fa fa-chevron-right"></span> About Us
								</a></li>
								<li><a
									href="${pageContext.request.contextPath}/view/contact-us.jsp">
										<span class="fa fa-chevron-right"></span> Contact
								</a></li>
							</ul>
						</div>
					</div>
					<div class="col-md-3 col-sm-4 col-xs-12">
						<div class="section_three">
							<h3>Contact Us</h3>
							<p>
								<span class="fa fa-map-marker"></span>
								101, F-Wing, Kailash Gardens, Gauripada, Kalyan(west), Thane, Maharashtra, India, 42130
								<br /><cite>(CIN - U74999MH2016PTC284691)</cite>
							</p>
							<p>
								<span class="fa fa-phone"></span> +65 869 633 21
							</p>
							<p>
								<span class="fa fa-fax"></span> +65 869 633 21
							</p>
							<p>
								<span class="fa fa-envelope"></span> enquiry@finvendor.com
							</p>
						</div>
					</div>
				</div>
			
		</div>
		<div id="footer_bottom">
				<div class="row">
					<div class="col-md-6 col-sm-8">
						<div class="clearfix">
							<div class="pull-left">
								<img class="footer_logo"
									src="${pageContext.request.contextPath}/resources/images/company-logo-footer.png"
									alt="Footer logo">
							</div>
							<div class="copyright_text">Copyright © 2016 MTAR Vendor Consulting Pvt. Ltd.</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-4">
						<div class="clearfix">
							<div class="pull-right xs-pull-left">
								<!-- Header top bar Socials -->
								<div class="pull-right">
									<div class="copyright_socials">
										<ul class="clearfix">
										</ul>
									</div>
								</div>
							</div>
							<div class="pull-right xs-pull-left hidden-sm hidden-xs">
								<ul class="footer_menu heading_font clearfix">
									<li><a
										href="${pageContext.request.contextPath}/view/privacy-policy.jsp">
											Privacy Policy </a></li>
									<li><a href="#"> Advertise </a></li>
									<li><a
										href="${pageContext.request.contextPath}/view/disclaimer.jsp">
											Disclaimer </a></li>
									<li><a href="<%=request.getContextPath()%>/view/sitemap.jsp">Sitemap</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			
		</div>
	</div>
</footer>

<script
	src="${pageContext.request.contextPath}/resources/js/CreateHTML5Elements.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jqueryvalidation/jquery.validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jqueryvalidation/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jqueryvalidation/additional-methods.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jqueryvalidation/additional-methods.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tabs.js"></script>
<script
	src="<%=request.getContextPath() %>/resources/js/jquery.bxslider.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/userregister.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/base64.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/form-wizard.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath() %>/resources/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath() %>/resources/js/jquery-migrate-1.2.1.min.js"
	type="text/javascript"></script>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/additional-methods.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/WebUtil.js"></script>
<!-- Basic Jquery plugins end here-->

<!-- add to table plugins start-->
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
<!-- add to table plugins end here-->

<script src="<%=request.getContextPath()%>/resources/js/userregister.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/base64.js"></script>
<script
	src="<%=request.getContextPath() %>/resources/js/loadingpanel-min.js"></script>
	
<script src="<%=request.getContextPath() %>/resources/js/jquery.marcopolo.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/common.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/consumer.js"></script>
<script
	src="<%=request.getContextPath() %>/resources/js/consumerinviteanrfp.js"></script>



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
			/*$('.slider2').bxSlider({
				slideWidth : 740,
				minSlides : 1,
				maxSlides : 1,
				slideMargin : 15,
				auto : true,
			});*/
		});
	</script>
<script
	src="${pageContext.request.contextPath}/resources/js/superfish.js"></script>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jssor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jssor.slider.js"></script>

<!-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/marketaggregators.js"></script> -->
<script
	src="${pageContext.request.contextPath}/resources/js/loginmain.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/loginmodernizr.js"></script>
<script>
		/*jQuery(document)
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
							$('.slider2').bxSlider({
								slideWidth : 740,
								minSlides : 1,
								maxSlides : 1,
								slideMargin : 15,
								auto : true,
							});
							try{
							var jssor_slider2 = new $JssorSlider$(
									"slider2_container", options);
							}catch(err){
								
							}
							function ScaleSlider() {
							try{	
								var parentWidth = jssor_slider2.$Elmt.parentNode.clientWidth;
								if (parentWidth) {
									var sliderWidth = parentWidth;
									sliderWidth = Math.min(sliderWidth, 1000);
									jssor_slider2.$ScaleWidth(sliderWidth);
								} else
									window.setTimeout(ScaleSlider, 30);
								
							}catch(err){
							}
								
							}
							ScaleSlider();
							$(window).bind("load", ScaleSlider);
							$(window).bind("resize", ScaleSlider);
							$(window).bind("orientationchange", ScaleSlider);
						});*/
		
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
