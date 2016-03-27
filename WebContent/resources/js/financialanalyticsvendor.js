$(document).ready(function() {
	debugger;
		$("#searchsingleanalyticsapplicationform").hide();
		$("#searchmultianaylticsapplicationform").slideDown("slow");
		$("#savedsearchanalyticsapplicationform").hide();
		$("#singleanalytcsapplicationbutton").click(function() {
			$("#searchsingleanalyticsapplicationform").slideDown("slow");
			$("#searchmultianaylticsapplicationform").hide();
			$("#savedsearchanalyticsapplicationform").hide();
		});
		$("#multianalyticsapplicationbutton").click(function() {
			$("#searchsingleanalyticsapplicationform").hide();
			$("#searchmultianaylticsapplicationform").slideDown("slow");
			$("#savedsearchanalyticsapplicationform").hide();
		});
		$("#savefinancialanalyticsappsearchbutton").click(function() {
			$("#searchsingleanalyticsapplicationform").hide();
			$("#searchmultianaylticsapplicationform").hide();
			$("#savedsearchanalyticsapplicationform").slideDown("slow");
		});
	});
	
$(document).ready(function() {
	debugger;
	$("#alternativeinvestment").hide();
	$("#backofficeoperations").hide();
	$("#productvaluation").hide();
	$("#bankingsolution").hide();
	$("#regulatorycomplianceriskmgt").hide();
	$("#marginingsolutions").hide();
	$("#portfoliomgtsolutions").hide();
	
	
	$("#financialanalyticsmulticommonarea").hide();
	
	$("#financialanalyticsmulticommonareainformation").hide();
	
	$("#AlternativeInvestmentSolu").click(function() {
		if(document.getElementById('AlternativeInvestmentSolu').checked == true){
			$("#financialanalyticsmulticommonarea").slideDown("slow");
			$("#alternativeinvestment").slideDown("slow");
		}else{
			$("#alternativeinvestment").hide();
		}
				
	});
		
	$("#BackofficeOperations").click(function() {
		if(document.getElementById('BackofficeOperations').checked == true){
			$("#backofficeoperations").slideDown("slow");
			$("#financialanalyticsmulticommonarea").slideDown("slow");
		}else{
			$("#backofficeoperations").hide();
		}
				
	});
	
	$("#ProductValuation").click(function() {
		if(document.getElementById('ProductValuation').checked == true){
			$("#productvaluation").slideDown("slow");
			$("#financialanalyticsmulticommonarea").slideDown("slow");
		}else{
			$("#productvaluation").hide();
		}
				
	});
	
	$("#BankingSolution").click(function() {
		if(document.getElementById('BankingSolution').checked == true){
			$("#bankingsolution").slideDown("slow");
			$("#financialanalyticsmulticommonarea").slideDown("slow");
		}else{
			$("#bankingsolution").hide();
		}
				
	});
	
	$("#RegulatoryComplianceRiskMGT").click(function() {
		if(document.getElementById('RegulatoryComplianceRiskMGT').checked == true){
			$("#regulatorycomplianceriskmgt").slideDown("slow");
			$("#financialanalyticsmulticommonareainformation").slideDown("slow");
		}else{
			$("#regulatorycomplianceriskmgt").hide();
		}
				
	});
	
	
	$("#MarginingSolutions").click(function() {
		if(document.getElementById('MarginingSolutions').checked == true){
			$("#marginingsolutions").slideDown("slow");
			$("#financialanalyticsmulticommonareainformation").slideDown("slow");
		}else{
			$("#marginingsolutions").hide();
		}
				
	});
	
	
	$("#PortfolioManagement").click(function() {
		if(document.getElementById('PortfolioManagement').checked == true){
			$("#portfoliomgtsolutions").slideDown("slow");
			$("#financialanalyticsmulticommonareainformation").slideDown("slow");
		}else{
			$("#portfoliomgtsolutions").hide();
		}
				
	});
	
	});
	


