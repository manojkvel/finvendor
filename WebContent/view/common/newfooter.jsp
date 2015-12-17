	<%@page import="com.finvendor.util.RequestConstans"%>
	<div class="footer1" id="footer">
			<div class="footer-left-wrap" style="margin: 25px -12px 0px -12px;">
				<ul>
					<li><a href="#">HOME</a></li>
					<li><a href="#">ABOUT</a></li>
					<li><a href="#">SOLUTIONS</a></li>
					<li><a href="#">SERVICES</a></li>
					<li><a href="#">RESOURCES</a></li>
					<li><a href="#">CONTACT</a></li>
				</ul>
				<ul class="send-ul">
					<li>© 2015 Fin Vendor. All rights reserved.</li>
					<li><a href="#">Sitemap</a></li>
					<li><a href="#">Privacy Policy</a></li>
					<li><a href="#">Advertise</a></li>
					<li><a href="#">Disclaimer</a></li>
				</ul>
				<!-- <ul>
					<li>A <a href="#">CreativeLeaves</a> Design
					</li>
				</ul> -->
			</div>
			<div class="footer-right-wrap" id="footer-right-wrap" style="margin: 34px 1px 2px 3px;">
				<p>CONNECT WITH US:</p>
				<ul>
					<li><a href="#"><img src="<%=request.getContextPath() %>/resources/images/a1.png" alt="" title="" /></a></li>
					<li><a href="#"><img src="<%=request.getContextPath() %>/resources/images/a2.png" alt="" title="" /></a></li>
					<li><a href="#"><img src="<%=request.getContextPath() %>/resources/images/a3.png" alt="" title="" /></a></li>
					<li><a href="#"><img src="<%=request.getContextPath() %>/resources/images/a4.png" alt="" title="" /></a></li>
					<li><a href="#"><img src="<%=request.getContextPath() %>/resources/images/a5.png" alt="" title="" /></a></li>
				</ul>
			</div>
			<!-- <div class="clearfix"></div> -->
		</div>
		<script src="<%=request.getContextPath() %>/resources/js/CreateHTML5Elements.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jqueryvalidation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jqueryvalidation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jqueryvalidation/additional-methods.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jqueryvalidation/additional-methods.min.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/jquery.tabs.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/jquery.bxslider.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/userregister.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/base64.js"></script>
	<script type="text/javascript">
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
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jssor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jssor.slider.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/marketaggregators.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/tradingapplicationvendor.js"></script>
	
	
	<script src="<%=request.getContextPath() %>/resources/js/loginmain.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/loginmodernizr.js"></script>
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
		 if (window.history && window.history.pushState) {

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
		 
			/// F5 disbled---:
			 function disableF5(e) { 
				 if ((e.which || e.keyCode) == 116 ) {
					 	e.preventDefault();
					 	window.location.href = "<%=request.getServletPath()%>/<%=RequestConstans.Login.HOME%>"; 
					 } 
			 }
		 
			// Ajax session time out------
				
				function ajaxSessionTimeout()
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
				}(window.jQuery);
		
	</script>
