$(document).ready(function() {
		$("#searchsingleform").slideDown("slow");
		$("#searchmultiform").hide();
		$("#savedsearchform").hide();
		$("#singleform").click(function() {
			$("#searchsingleform").slideDown("slow");
			$("#searchmultiform").hide();
			$("#savedsearchform").hide();
			document.getElementsByClassName('inner')[0].style.backgroundColor = '#5CE5E5';
			document.getElementsByClassName('outer')[0].style.backgroundColor = '#5CE5E5';
		});
		$("#multiform").click(function() {
			$("#searchsingleform").hide();
			$("#searchmultiform").slideDown("slow");
			$("#savedsearchform").hide();
			document.getElementsByClassName('inner')[0].style.backgroundColor = '#5CE5E5';
			document.getElementsByClassName('outer')[0].style.backgroundColor = '#5CE5E5';
		});
		$("#savesearch").click(function() {
			$("#searchsingleform").hide();
			$("#searchmultiform").hide();
			$("#savedsearchform").slideDown("slow");
		});
	});
	
function getRegionValues(regionName) {
		if(regionName != '' && regionName.length > 0 && !regionName.match("-SELECT-")){
			regionName = encode64(regionName);
			$.ajax({
				type: 'GET',
				url:  "loadRegionNameTypes?SiMhRaYuL="+regionName,
				cache:false,
				success : function(output){
					document.getElementById("regionsNames").innerHTML = output;
				},
				error : function(data, textStatus, jqXHR){
					 alert('Error: '+data+':'+textStatus);
				}
			});
		}
	}
	
function loadSecurityTypes(assettypeId) {
	if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
		assettypeId = encode64(assettypeId);
		$.ajax({
			type: 'GET',
			url:  "loadSecurityTypes?RAyuL="+assettypeId,
			cache:false,
			success : function(output){
				document.getElementById("assetClassSecurityMaps").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}
}

function loadCountryList(regionId) {
	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryTypes?RAYVEM="+regionId,
			cache:false,
			success : function(output){
				document.getElementById("regionCountryMaps").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}
}

function loadCountryListInfo(regionId) {
	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryTypesInfo?RAYVEM="+regionId,
			cache:false,
			success : function(output){
				document.getElementById("regionCountryMapsinfo").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}
	
}
function loadCountryListMultiInfo(regionId) {
	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryTypesInfoMulti?VeMuRaYu="+regionId,
			cache:false,
			success : function(output){
				document.getElementById("regionCountryMapsinfoMulti").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}
	
}
function loadCountryListAssetInfo(regionId) {
	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryListAssetInfo?RaYuLuVeMuLa="+regionId,
			cache:false,
			success : function(output){
				document.getElementById("regionCountryMapsinfoAsset").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}
	
}
function loadCountryListAssetInfoFI(regionId) {
	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryListAssetInfoFI?RaYuLuVeMuLa="+regionId,
			cache:false,
			success : function(output){
				document.getElementById("regionCountryMapsinfoAssetFI").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}
	
}
function loadExchangeAssetListFI(countryId) {
	if(countryId != '' && countryId.length > 0 && !countryId.match("-SELECT-")){
		countryId = encode64(countryId);
		$.ajax({
			type: 'GET',
			url:  "loadExchangeAssetListFI?RaYuLU="+countryId,
			cache:false,
			success : function(output){
				document.getElementById("countryExchangeMapsAssetFI").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}
	
}
function loadExchangeList(countryId) {
	if(countryId != '' && countryId.length > 0 && !countryId.match("-SELECT-")){
		countryId = encode64(countryId);
		$.ajax({
			type: 'GET',
			url:  "loadExchangeTypes?RaYuLU="+countryId,
			cache:false,
			success : function(output){
				document.getElementById("countryExchangeMaps").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}
	
}

function loadExchangeAssetList(countryId) {
	if(countryId != '' && countryId.length > 0 && !countryId.match("-SELECT-")){
		countryId = encode64(countryId);
		$.ajax({
			type: 'GET',
			url:  "loadExchangeAssetList?VeMuLaRaYuL="+countryId,
			cache:false,
			success : function(output){
				document.getElementById("countryExchangeMapsAsset").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}
	
}

$(document).ready(function() {
	$("#equities").hide();
	$("#fi").hide();
	$("#indices").hide();
	$("#derivatives").hide();
	$("#fx").hide();
	$("#ai").hide();
	$("#misc").hide();
	$("#commonarea").hide();
	$("#commonareainfo").hide();
	
	$("#Equities").click(function() {
		if(document.getElementById('Equities').value != '' && document.getElementById('Equities').value.length > 0
				&&  document.getElementById('Equities').value == 'Equities' && document.getElementById('Equities').checked == true){
			var assettypeId = '1';
			if(assettypeId != '' && assettypeId.length > 0){
				assettypeId = encode64(assettypeId);
				$.ajax({
					type: 'GET',
					url:  "loadEquitiesSecurityTypes?RAyuL="+assettypeId,
					cache:false,
					success : function(output){
						document.getElementById("assetClassEquiSecurityMaps").innerHTML = output;		
					},
					error : function(data, textStatus, jqXHR){
						//alert('Error: '+data+':'+textStatus);
					}
				});
			}
			$("#commonarea").slideDown("slow");
			$("#equities").slideDown("slow");
		}else{
			$("#equities").hide();
		}
				
	});
		
	$("#FI").click(function() {
		if(document.getElementById('FI').value != '' && document.getElementById('FI').value.length > 0
				&&  document.getElementById('FI').value == 'FI' && document.getElementById('FI').checked == true){
			var assettypeId = '2';
			if(assettypeId != '' && assettypeId.length > 0){
				assettypeId = encode64(assettypeId);
				$.ajax({
					type: 'GET',
					url:  "loadFISecurityTypes?RAyuL="+assettypeId,
					cache:false,
					success : function(output){
						document.getElementById("assetClassFISecurityMaps").innerHTML = output;		
					},
					error : function(data, textStatus, jqXHR){
						//alert('Error: '+data+':'+textStatus);
					}
				});
			}
			$("#fi").slideDown("slow");
			$("#commonarea").slideDown("slow");
		}else{
			$("#fi").hide();
		}
				
	});
	
	$("#Indices").click(function() {
		if(document.getElementById('Indices').value != '' && document.getElementById('Indices').value.length > 0
				&&  document.getElementById('Indices').value == 'Indices' && document.getElementById('Indices').checked == true){
			var assettypeId = '3';
			if(assettypeId != '' && assettypeId.length > 0){
				assettypeId = encode64(assettypeId);
				$.ajax({
					type: 'GET',
					url:  "loadIndicesSecurityTypes?RAyuL="+assettypeId,
					cache:false,
					success : function(output){
						document.getElementById("assetClassIndicesSecurityMaps").innerHTML = output;		
					},
					error : function(data, textStatus, jqXHR){
						//alert('Error: '+data+':'+textStatus);
					}
				});
			}
			$("#indices").slideDown("slow");
			$("#commonarea").slideDown("slow");
		}else{
			$("#indices").hide();
		}
				
	});
	
	
	$("#Derivatives").click(function() {
		if(document.getElementById('Derivatives').value != '' && document.getElementById('Derivatives').value.length > 0
				&&  document.getElementById('Derivatives').value == 'Derivatives' && document.getElementById('Derivatives').checked == true){
			$("#derivatives").slideDown("slow");
			$("#commonarea").slideDown("slow");
		}else{
			$("#derivatives").hide();
		}
				
	});

	$("#FX").click(function() {
		if(document.getElementById('FX').value != '' && document.getElementById('FX').value.length > 0
				&&  document.getElementById('FX').value == 'FX' && document.getElementById('FX').checked == true){
			$("#fx").slideDown("slow");
			$("#commonareainfo").slideDown("slow");
		}else{
			$("#fx").hide();
		}
				
	});

	$("#AI").click(function() {
		if(document.getElementById('AI').value != '' && document.getElementById('AI').value.length > 0
				&&  document.getElementById('AI').value == 'AI' && document.getElementById('AI').checked == true){
			$("#ai").slideDown("slow");
			$("#commonareainfo").slideDown("slow");
		}else{
			$("#ai").hide();
		}
				
	});

	$("#MISC").click(function() {
		if(document.getElementById('MISC').value != '' && document.getElementById('MISC').value.length > 0
				&&  document.getElementById('MISC').value == 'MISC' && document.getElementById('MISC').checked == true){
			$("#misc").slideDown("slow");
			$("#commonareainfo").slideDown("slow");
		}else{
			$("#misc").hide();
		}
				
	});
	
	});
	

