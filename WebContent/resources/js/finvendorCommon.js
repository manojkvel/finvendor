function openPopupCenter(pageURL, title, w, h) {
    var left = (screen.width - w) / 2;
    var top = (screen.height - h) / 4;
    var targetWin = window.open(pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
} 

function updateAjax(element, ajaxUrl, errorLabelId) {
	var url = ajaxUrl + "?param="+ $.trim(element.value);
	$.ajax({
		type: 'POST',
		url: url,
		cache: false,
		success: function(output) {
			document.getElementById(errorLabelId).innerHTML = output;
		}
	});
}

function loadSecurityTypesForAssetClass(assetClassId, securityTypeElementId, errorLabelId, selectedValue) {
	var url = "loadSecurityTypesForAssetClass";
	$.ajax({
		type: 'POST',
		url:  url,
		data:{
			assetClassId: assetClassId
		},	
		cache:false,
		success : function(output){
			document.getElementById(securityTypeElementId).innerHTML = output;		
		},
		error : function(data, textStatus, jqXHR){
			document.getElementById(errorLabelId).innerHTML = 'Error loading Security Types';	
		}
	});
}