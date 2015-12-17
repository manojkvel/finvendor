$(document).ready(function() {
	
		$("#searchsingleanalyticsapplicationform").slideDown("slow");
		$("#searchmultianaylticsapplicationform").hide();
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
		if(document.getElementById('AlternativeInvestmentSolu').value != '' && document.getElementById('AlternativeInvestmentSolu').value.length > 0
				&&  document.getElementById('AlternativeInvestmentSolu').value == 'AlternativeInvestmentSolu' && document.getElementById('AlternativeInvestmentSolu').checked == true){
			$("#financialanalyticsmulticommonarea").slideDown("slow");
			$("#alternativeinvestment").slideDown("slow");
		}else{
			$("#alternativeinvestment").hide();
		}
				
	});
		
	$("#BackofficeOperations").click(function() {
		if(document.getElementById('BackofficeOperations').value != '' && document.getElementById('BackofficeOperations').value.length > 0
				&&  document.getElementById('BackofficeOperations').value == 'BackofficeOperations' && document.getElementById('BackofficeOperations').checked == true){
			$("#backofficeoperations").slideDown("slow");
			$("#financialanalyticsmulticommonarea").slideDown("slow");
		}else{
			$("#backofficeoperations").hide();
		}
				
	});
	
	$("#ProductValuation").click(function() {
		if(document.getElementById('ProductValuation').value != '' && document.getElementById('ProductValuation').value.length > 0
				&&  document.getElementById('ProductValuation').value == 'ProductValuation' && document.getElementById('ProductValuation').checked == true){
			$("#productvaluation").slideDown("slow");
			$("#financialanalyticsmulticommonarea").slideDown("slow");
		}else{
			$("#productvaluation").hide();
		}
				
	});
	
	$("#BankingSolution").click(function() {
		if(document.getElementById('BankingSolution').value != '' && document.getElementById('BankingSolution').value.length > 0
				&&  document.getElementById('BankingSolution').value == 'BankingSolution' && document.getElementById('BankingSolution').checked == true){
			$("#bankingsolution").slideDown("slow");
			$("#financialanalyticsmulticommonarea").slideDown("slow");
		}else{
			$("#bankingsolution").hide();
		}
				
	});
	
	$("#RegulatoryComplianceRiskMGT").click(function() {
		if(document.getElementById('RegulatoryComplianceRiskMGT').value != '' && document.getElementById('RegulatoryComplianceRiskMGT').value.length > 0
				&&  document.getElementById('RegulatoryComplianceRiskMGT').value == 'RegulatoryComplianceRiskMGT' && document.getElementById('RegulatoryComplianceRiskMGT').checked == true){
			$("#regulatorycomplianceriskmgt").slideDown("slow");
			$("#financialanalyticsmulticommonareainformation").slideDown("slow");
		}else{
			$("#regulatorycomplianceriskmgt").hide();
		}
				
	});
	
	
	$("#MarginingSolutions").click(function() {
		if(document.getElementById('MarginingSolutions').value != '' && document.getElementById('MarginingSolutions').value.length > 0
				&&  document.getElementById('MarginingSolutions').value == 'MarginingSolutions' && document.getElementById('MarginingSolutions').checked == true){
			$("#marginingsolutions").slideDown("slow");
			$("#financialanalyticsmulticommonareainformation").slideDown("slow");
		}else{
			$("#marginingsolutions").hide();
		}
				
	});
	
	
	$("#PortfolioManagement").click(function() {
		if(document.getElementById('PortfolioManagement').value != '' && document.getElementById('PortfolioManagement').value.length > 0
				&&  document.getElementById('PortfolioManagement').value == 'PortfolioManagement' && document.getElementById('PortfolioManagement').checked == true){
			$("#portfoliomgtsolutions").slideDown("slow");
			$("#financialanalyticsmulticommonareainformation").slideDown("slow");
		}else{
			$("#portfoliomgtsolutions").hide();
		}
				
	});
	
	});
	


