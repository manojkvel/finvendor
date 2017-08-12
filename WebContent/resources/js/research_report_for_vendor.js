$(document).ready(function(){
	
	//Start of Research Report for Stocks in vendor offerings

	var operation = "A"; //"A"=Adding; "E"=Editing
	var selected_index = -1; //Index of the selected list item

	window.localStorage.removeItem('researh_report_for_summary_details');
	var researh_report_for_summary_details  = window.localStorage.removeItem('researh_report_for_summary_details');

	if(researh_report_for_summary_details == null) {
		researh_report_for_summary_details = [];
	}

	setTimeout(function() {
		$('#vo_rr_report_for').selectpicker('val', 'default');
		$('#vo_eqrrv_recommendation_type').selectpicker('val', 'default');
	}, 1000);



	var research_report_for = {
		add : function() {
			var vo_rr_report_for = $('#vo_rr_report_for').selectpicker('val');
			var vo_datepicker = $('#vo_datepicker').val();
			var vo_target_price = $('#vo_target_price').val();
			var vo_eqrrv_recommendation_type = $('#vo_eqrrv_recommendation_type').selectpicker('val');
			var vo_upload_report = $('#vo_upload_report').val();

			if(vo_rr_report_for == null || vo_datepicker == '' || vo_target_price == '' || 
				vo_eqrrv_recommendation_type == null) {
				return;
		}


		var researh_report_for_summary_details_json_obj = {
			'vo_rr_report_for' : vo_rr_report_for,
			'vo_datepicker' : vo_datepicker,
			'vo_target_price' : vo_target_price,
			'vo_eqrrv_recommendation_type' : vo_eqrrv_recommendation_type,
			'vo_upload_report' : vo_upload_report
		};

		researh_report_for_summary_details.push(researh_report_for_summary_details_json_obj);
		window.localStorage.setItem('researh_report_for_summary_details', JSON.stringify(researh_report_for_summary_details));

		research_report_for.list(researh_report_for_summary_details);
	},

	edit : function(e) {
		e.preventDefault();
		var researh_report_for_summary_details_json_obj = {
			'vo_rr_report_for' : $('#vo_rr_report_for').selectpicker('val'),
			'vo_datepicker' : $('#vo_datepicker').val(),
			'vo_target_price' : $('#vo_target_price').val(),
			'vo_eqrrv_recommendation_type' : $('#vo_eqrrv_recommendation_type').selectpicker('val'),
			'vo_upload_report' : $('#vo_upload_report').val()
		};

		researh_report_for_summary_details[selected_index] = researh_report_for_summary_details_json_obj;

		window.localStorage.setItem('researh_report_for_summary_details', JSON.stringify(researh_report_for_summary_details));
		console.log("The data was edited.");
	operation = "A"; //Return to default value return true;
	research_report_for.list(researh_report_for_summary_details);
	}, 

	delete : function(e) {
		e.preventDefault();
		selected_index = $(this).parents('tr').index();
		researh_report_for_summary_details.splice(selected_index, 1);
		window.localStorage.setItem("researh_report_for_summary_details", JSON.stringify(researh_report_for_summary_details));
		$(this).parents('tr').remove();
		console.log("Client deleted.");

		if(researh_report_for_summary_details.length === 0) {
			$('.summary_details table').html('');
			$('.research_report_for_info .summary_details').hide();
		}
	//research_report_for.list(JSON.parse(window.localStorage.getItem("researh_report_for_summary_details")));
	},

	list: function(researh_report_for_summary_details) {

		$('.summary_details table').html('');
		$('.summary_details table').html(
			"<thead>" +
			"<th>Company</th>" +
			"<th>Date</th>" +
			"<th>Price</th>" +
			"<th>Recommendation Type</th>" +
			"<th></th>" +
			"</thead>"
			);
		var total = researh_report_for_summary_details.length;
		var row = '';
		for(var i = 0; i < total; i++) {
			row = row + "<tr>" + 
			"<td>" +  researh_report_for_summary_details[i].vo_rr_report_for + "</td>" +
			"<td>" +  researh_report_for_summary_details[i].vo_datepicker + "</td>" +
			"<td>" +  researh_report_for_summary_details[i].vo_target_price + "</td>" +
			"<td>" +  researh_report_for_summary_details[i].vo_eqrrv_recommendation_type + "</td>" +
			"<td><a class='fa fa-edit edit_btn' href='#' tabindex='" + i + "'></a><a class='fa fa-trash delete_btn' href='#'></a></td>" +
			"</tr>";

		}

		$('.summary_details table').append(row);
		$('.research_report_for_info .summary_details').show();


		$('#vo_rr_report_for').selectpicker('val', 'default');
		$('#vo_datepicker').val('');
		$('#vo_target_price').val('');
		$('#vo_eqrrv_recommendation_type').selectpicker('val', 'default');
		$('#vo_upload_report').val('');

		$(".edit_btn").bind("click", function(e) {
			e.preventDefault();
			operation = 'E';
			selected_index = $(this).parents('tr').index();
			var data = researh_report_for_summary_details[selected_index];
			$('#vo_rr_report_for').selectpicker('val', data.vo_rr_report_for);
			$('#vo_datepicker').val(data.vo_datepicker);
			$('#vo_target_price').val(data.vo_target_price);
			$('#vo_eqrrv_recommendation_type').selectpicker('val', data.vo_eqrrv_recommendation_type);
			$('#vo_upload_report').val(data.vo_upload_report);
		});

		$('.delete_btn').bind("click", research_report_for.delete);
	}
	};

	$('#research_report_for_info_add_more_btn a').on('click', function(e) {
		e.preventDefault();

		if(window.localStorage.researh_report_for_summary_details == undefined) {
			var vo_rr_report_for = $("#research_application #vo_rr_report_for").selectpicker('val');
			var vo_datepicker = $("#research_application #vo_datepicker").val();
			var vo_target_price = $("#research_application #vo_target_price").val();
			var vo_eqrrv_recommendation_type = $("#research_application #vo_eqrrv_recommendation_type").selectpicker('val');
			if(vo_rr_report_for != null) {
				$("#research_application #vo_rr_report_for").parent().find("button").removeClass("error_field");
			} else {
				$("#research_application #vo_rr_report_for").parent().find("button").addClass("error_field");
			}

			if(vo_datepicker != '') {
				$("#research_application #vo_datepicker").removeClass("error_field");
			} else {
				$("#research_application #vo_datepicker").addClass("error_field");
			}

			if(vo_target_price != '') {
				$("#research_application #vo_target_price").removeClass("error_field");
			} else {
				$("#research_application #vo_target_price").addClass("error_field");
			}

			if(vo_eqrrv_recommendation_type != null) {
				$("#research_application #vo_eqrrv_recommendation_type").parent().find("button").removeClass("error_field");
			} else {
				$("#research_application #vo_eqrrv_recommendation_type").parent().find("button").addClass("error_field");
			}

			if(vo_rr_report_for == null || vo_datepicker == '' || vo_target_price == '' || vo_eqrrv_recommendation_type == null) {
				return;
			}

		}
		
		$("#research_application #vo_rr_report_for").parent().find("button").removeClass("error_field");
		$("#research_application #vo_datepicker").removeClass("error_field");
		$("#research_application #vo_target_price").removeClass("error_field");
		$("#research_application #vo_eqrrv_recommendation_type").parent().find("button").removeClass("error_field");

		if(operation == 'A') {
			return research_report_for.add();
		} else {
			return research_report_for.edit(e);
		}
	});
});