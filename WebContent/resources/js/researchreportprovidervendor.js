$(document).ready(function() {
	
		$("#searchsingleresearchreportprovidersform").slideDown("slow");
		$("#searchmultiresearchreportprovidersform").hide();
		$("#savedresearchreportproviderform").hide();
		
		$("#singlesearchreportprovidersbutton").click(function() {
			$("#searchsingleresearchreportprovidersform").slideDown("slow");
			$("#searchmultiresearchreportprovidersform").hide();
			$("#savedresearchreportproviderform").hide();
		});
		$("#multisearchreportprovidersbutton").click(function() {
			$("#searchsingleresearchreportprovidersform").hide();
			$("#searchmultiresearchreportprovidersform").slideDown("slow");
			$("#savedresearchreportproviderform").hide();
		});
		$("#savesearchreportprovidersbutton").click(function() {
			$("#searchsingleresearchreportprovidersform").hide();
			$("#searchmultiresearchreportprovidersform").hide();
			$("#savedresearchreportproviderform").slideDown("slow");
		});
		
	});
	
$(document).ready(function() {
	$("#macroeconomicanalysis").hide();
	$("#sectorindustryanalysis").hide();
	$("#commodityanalysis").hide();
	$("#exchangerateanalysis").hide();
	
	$("#interestrateanalysis").hide();
	$("#indexETFfundresearch").hide();
	$("#equitydebtresearch").hide();
	
	
	$("#researchreportprovidermulticommonarea").hide();
	
	$("#researchreportprovidermulticommonareainformation").hide();
	
	$("#MacroEconomicAnalysis").click(function() {
		if(document.getElementById('MacroEconomicAnalysis').value != '' && document.getElementById('MacroEconomicAnalysis').value.length > 0
				&&  document.getElementById('MacroEconomicAnalysis').value == 'MacroEconomicAnalysis' && document.getElementById('MacroEconomicAnalysis').checked == true){
			$("#researchreportprovidermulticommonarea").slideDown("slow");
			$("#macroeconomicanalysis").slideDown("slow");
		}else{
			$("#macroeconomicanalysis").hide();
		}
				
	});
		
	$("#SectorIndustryAnalysis").click(function() {
		if(document.getElementById('SectorIndustryAnalysis').value != '' && document.getElementById('SectorIndustryAnalysis').value.length > 0
				&&  document.getElementById('SectorIndustryAnalysis').value == 'SectorIndustryAnalysis' && document.getElementById('SectorIndustryAnalysis').checked == true){
			$("#sectorindustryanalysis").slideDown("slow");
			$("#researchreportprovidermulticommonarea").slideDown("slow");
		}else{
			$("#sectorindustryanalysis").hide();
		}
				
	});
	
	$("#CommodityAnalysis").click(function() {
		if(document.getElementById('CommodityAnalysis').value != '' && document.getElementById('CommodityAnalysis').value.length > 0
				&&  document.getElementById('CommodityAnalysis').value == 'CommodityAnalysis' && document.getElementById('CommodityAnalysis').checked == true){
			$("#commodityanalysis").slideDown("slow");
			$("#researchreportprovidermulticommonarea").slideDown("slow");
		}else{
			$("#commodityanalysis").hide();
		}
				
	});
	
	$("#ExchangeRateAnalysis").click(function() {
		if(document.getElementById('ExchangeRateAnalysis').value != '' && document.getElementById('ExchangeRateAnalysis').value.length > 0
				&&  document.getElementById('ExchangeRateAnalysis').value == 'ExchangeRateAnalysis' && document.getElementById('ExchangeRateAnalysis').checked == true){
			$("#exchangerateanalysis").slideDown("slow");
			$("#researchreportprovidermulticommonarea").slideDown("slow");
		}else{
			$("#exchangerateanalysis").hide();
		}
				
	});
	
	$("#InterestRateAnalysis").click(function() {
		if(document.getElementById('InterestRateAnalysis').value != '' && document.getElementById('InterestRateAnalysis').value.length > 0
				&&  document.getElementById('InterestRateAnalysis').value == 'InterestRateAnalysis' && document.getElementById('InterestRateAnalysis').checked == true){
			$("#interestrateanalysis").slideDown("slow");
			$("#researchreportprovidermulticommonareainformation").slideDown("slow");
		}else{
			$("#interestrateanalysis").hide();
		}
				
	});
	
	
	$("#IndexETFFundResearch").click(function() {
		if(document.getElementById('IndexETFFundResearch').value != '' && document.getElementById('IndexETFFundResearch').value.length > 0
				&&  document.getElementById('IndexETFFundResearch').value == 'IndexETFFundResearch' && document.getElementById('IndexETFFundResearch').checked == true){
			$("#indexETFfundresearch").slideDown("slow");
			$("#researchreportprovidermulticommonareainformation").slideDown("slow");
		}else{
			$("#indexETFfundresearch").hide();
		}
				
	});
	
	
	$("#EquityDebtResearch").click(function() {
		if(document.getElementById('EquityDebtResearch').value != '' && document.getElementById('EquityDebtResearch').value.length > 0
				&&  document.getElementById('EquityDebtResearch').value == 'EquityDebtResearch' && document.getElementById('EquityDebtResearch').checked == true){
			$("#equitydebtresearch").slideDown("slow");
			$("#researchreportprovidermulticommonareainformation").slideDown("slow");
		}else{
			$("#equitydebtresearch").hide();
		}
				
	});
	
	});
	


