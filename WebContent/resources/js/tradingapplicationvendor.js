$(document).ready(function() {
	
		$("#searchsingletradingapplicationform").slideDown("slow");
		$("#searchmultitradingapplicationform").hide();
		$("#savedsearchtradingapplicationform").hide();
		$("#singletradingapplicationbutton").click(function() {
			$("#searchsingletradingapplicationform").slideDown("slow");
			$("#searchmultitradingapplicationform").hide();
			$("#savedsearchtradingapplicationform").hide();
		});
		$("#multitradingapplicationbutton").click(function() {
			$("#searchsingletradingapplicationform").hide();
			$("#searchmultitradingapplicationform").slideDown("slow");
			$("#savedsearchtradingapplicationform").hide();
		});
		$("#savetradingappsearchbutton").click(function() {
			$("#searchsingletradingapplicationform").hide();
			$("#searchmultitradingapplicationform").hide();
			$("#savedsearchtradingapplicationform").slideDown("slow");
		});
	});
	
$(document).ready(function() {
	$("#creditratemarket").hide();
	$("#etfmarket").hide();
	$("#derivativemarket").hide();
	$("#fxmarket").hide();
	$("#tradingmulticommonarea").hide();
	
	
	$("#CreditRateMarket").click(function() {
		if(document.getElementById('CreditRateMarket').value != '' && document.getElementById('CreditRateMarket').value.length > 0
				&&  document.getElementById('CreditRateMarket').value == 'CreditRateMarket' && document.getElementById('CreditRateMarket').checked == true){
			$("#tradingmulticommonarea").slideDown("slow");
			$("#creditratemarket").slideDown("slow");
		}else{
			$("#creditratemarket").hide();
		}
				
	});
		
	$("#ETFMarket").click(function() {
		if(document.getElementById('ETFMarket').value != '' && document.getElementById('ETFMarket').value.length > 0
				&&  document.getElementById('ETFMarket').value == 'ETFMarket' && document.getElementById('ETFMarket').checked == true){
			$("#etfmarket").slideDown("slow");
			$("#tradingmulticommonarea").slideDown("slow");
		}else{
			$("#etfmarket").hide();
		}
				
	});
	
	$("#DerivativeMarket").click(function() {
		if(document.getElementById('DerivativeMarket').value != '' && document.getElementById('DerivativeMarket').value.length > 0
				&&  document.getElementById('DerivativeMarket').value == 'DerivativeMarket' && document.getElementById('DerivativeMarket').checked == true){
			$("#derivativemarket").slideDown("slow");
			$("#tradingmulticommonarea").slideDown("slow");
		}else{
			$("#derivativemarket").hide();
		}
				
	});
	
	$("#FXMarket").click(function() {
		if(document.getElementById('FXMarket').value != '' && document.getElementById('FXMarket').value.length > 0
				&&  document.getElementById('FXMarket').value == 'FXMarket' && document.getElementById('FXMarket').checked == true){
			$("#fxmarket").slideDown("slow");
			$("#tradingmulticommonarea").slideDown("slow");
		}else{
			$("#fxmarket").hide();
		}
				
	});
	
	});
	


