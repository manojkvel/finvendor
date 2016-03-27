$(document).ready(function() {
	debugger;
		$("#searchsingletradingapplicationform").hide("slow");
		$("#searchmultitradingapplicationform").slideDown("slow");
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
	debugger;
	$("#creditratemarket").hide();
	$("#etfmarket").hide();
	$("#derivativemarket").hide();
	$("#fxmarket").hide();
	$("#tradingmulticommonarea").hide();
	
	
	$("#CreditRateMarket").click(function() {
		if(document.getElementById('CreditRateMarket').checked == true){
			$("#tradingmulticommonarea").slideDown("slow");
			$("#creditratemarket").slideDown("slow");
		}else{
			$("#creditratemarket").hide();
		}
				
	});
		
	$("#ETFMarket").click(function() {
		if(document.getElementById('ETFMarket').checked == true){
			$("#etfmarket").slideDown("slow");
			$("#tradingmulticommonarea").slideDown("slow");
		}else{
			$("#etfmarket").hide();
		}
				
	});
	
	$("#DerivativeMarket").click(function() {
		if(document.getElementById('DerivativeMarket').checked == true){
			$("#derivativemarket").slideDown("slow");
			$("#tradingmulticommonarea").slideDown("slow");
		}else{
			$("#derivativemarket").hide();
		}
				
	});
	
	$("#FXMarket").click(function() {
		if(document.getElementById('FXMarket').checked == true){
			$("#fxmarket").slideDown("slow");
			$("#tradingmulticommonarea").slideDown("slow");
		}else{
			$("#fxmarket").hide();
		}
				
	});
	
});
	


