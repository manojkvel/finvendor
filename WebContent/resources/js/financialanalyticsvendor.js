/**
 * @author Maninder Singh Rajpal
 * @date Jul 01, 2016
 * @emailId wonderfulmani@gmail.com
 * Copyrights: All Rights Reserved FinVendor
 */
 $(document).ready(function() {

	var assetClassArray = [];
	var getAssetClassAggregators = function() {
		var costRange = $("select#acquisitioncostrange option").map(function() {return $(this).html();}).get();
		
		if($(this).prop('checked') == true) {
			assetClassArray.push($(this).attr('id').toLowerCase());
		} else {
			assetClassArray.splice($.inArray($(this).attr('id').toLowerCase(), assetClassArray), 1);
		}

		if(assetClassArray.length == 0) {
			$("#multipleAsset").slideUp('slow');
		}

		if(assetClassArray.length > 3) {
			alert("Sorry, You cannot select more than 3 asset class.");
			console.log(assetClassArray);
			assetClassArray.splice($.inArray($(this).attr('id').toLowerCase(), assetClassArray), 1);
			console.log(assetClassArray);
			$(this).prop('checked', false);
			return;
		} else {
			$(".error").hide();
		}

		console.log(assetClassArray + " : " + assetClassArray.length);

		if($("#" +$(this).attr('id') + '_search').html() != undefined) {
			$("#" +$(this).attr('id') + '_search').remove();
			return;
		}
		

		multipleAssetClass($(this).attr('id'));
		getAnalyticalSolutionSubTypeMapping($(this).attr('id'), $(this).attr('id') + '_analyticsSolutionSubType');
		/*if(assetClassArray.length == 1) {
			var assetType = assetClassArray[0];
			multipleAssetClass(assetType);
			$(".selectpicker").selectpicker('refresh');
			//$("#singleAsset").slideDown('slow');
			//$("#multipleAsset").slideDown('slow');
		}*/

	};

	

	$(".assetClass").click(getAssetClassAggregators);

	function multipleAssetClass(assetType) {
	console.log("assetType : " + assetType);
	multipleAssetData = '';
	multipleAssetData = multipleAssetData
	+ "<div id='" + assetType + "_search'>"
	+ "<h3>"
	+ $("#" + assetType).siblings().html()
	+ "</h3>"
	+ "<ul>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "_analyticsSolutionSubType' name='" + assetType + "_analyticsSolutionSubType' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Analytics Solutions Sub Type</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "tdssubsriptioncostUSDperannum' name='" + assetType + "tdssubsriptioncostUSDperannum'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Subscription Cost - Annual(USD)</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "tdslaunchyear' name='" + assetType + "tdslaunchyear'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Launch Year</label>"
	+ "</li>"
	+ "</ul>"
	+ "</div>"
	$('#multipleAssetFields').append(multipleAssetData);
	$("#multipleAsset").slideDown('slow');
	$(".selectpicker").selectpicker('refresh');
}
});