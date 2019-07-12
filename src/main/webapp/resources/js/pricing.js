var baseApiUrl = "/system/api/cis";


/*
** Pricing for the products
*/

var pricingObj = {
    init: function() {
        this.selectedPlanName = 'General';
        this.selectedPlanAmount = 0;

        this.pricingLookUp().then(function(response) {
            $(".pricing_table").show();
        }, function(error) {
            $(".pricing_table").show();
        });
    },

    pricingLookUp: function() {
        return new Promise(function(resolve, reject) {
            if(!isLoggedInUser()) {
                $("#pricing .btnSubscribe a").text("Subscribe");
                $("#pricing #general_investors .btnSubscribe").hide();
                resolve(true);
            } else {
                reject(false);
            }
        });
    },

    checkForPlan : function() {
        var classRef = this;

        if(!isLoggedInUser()) {
            if(!$(this).find(".btnSubscribe").is(':visible')) {
                return;
            }

            window.scrollTo(0, 0);

            pricingObj.selectedPlanName = $(this).attr("data-value");
            pricingObj.selectedPlanAmount = Number($(this).find(".btnSubscribe").attr("data-value"));

            $("#pricing_account_info #selected_plan_name").text(pricingObj.selectedPlanName);
            $("#pricing_account_info #selected_plan_amount").text("INR " + pricingObj.selectedPlanAmount);

            $("#pricing_plan").hide();
            
            $("#pricing_account_info").show();
            $("#steps_update").show();
        } else {
            inner_login('view/pricing.jsp');
        }
    },

    checkForBankForm: function() {
        var classRef = this;

        if(!isLoggedInUser()) {
            window.scrollTo(0, 0);

            $("#pricing_account_info").hide();
            $("#pricing_bank_form").show();
            $("#amountTransferred").val(pricingObj.selectedPlanAmount);
            $("#steps_update").text("Step 2 of 4");
            $("#steps_update").show();
            $("#pricing_bank_form .pricing_bank_form_btn").on('click', classRef.validateBankForm);
        } else {
            inner_login('view/pricing.jsp');
        }
    },

    validateBankForm: function() {
        var classRef = this;

        var userId = $("#pricing_bank_form #userId").val().trim();
        var subscriptionType = $("#pricing_bank_form #subscriptionType").val().trim();
        var paymentMode = $("#pricing_bank_form #paymentMode").selectpicker('val');
        var transactionDate = $("#pricing_bank_form #transactionDate").val();
        var transactionRefNumber = $("#pricing_bank_form #transactionRefNumber").val().trim();
        var amountTransferred = $("#pricing_bank_form #amountTransferred").val().trim();
        var bankName = $("#pricing_bank_form #bankName").val().trim();
        var bankHolderName = $("#pricing_bank_form #bankHolderName").val().trim();

        if(userId != '') {
            $("#pricing_bank_form #userId").removeClass("error_field");
        } else {
            $("#pricing_bank_form #userId").addClass("error_field");
        }

        if(subscriptionType != '') {
            $("#pricing_bank_form #subscriptionType").removeClass("error_field");
        } else {
            $("#pricing_bank_form #subscriptionType").addClass("error_field");
        }

        if(paymentMode != '') {
            $("#pricing_bank_form #paymentMode").removeClass("error_field");
        } else {
            $("#pricing_bank_form #paymentMode").addClass("error_field");
        }

        if(transactionDate != '') {
            $("#pricing_bank_form #transactionDate").removeClass("error_field");
        } else {
            $("#pricing_bank_form #transactionDate").addClass("error_field");
        }

        if(transactionRefNumber != '') {
            $("#pricing_bank_form #transactionRefNumber").removeClass("error_field");
        } else {
            $("#pricing_bank_form #transactionRefNumber").addClass("error_field");
        }
    }

};

pricingObj.init();
$("#pricing button").on('click', pricingObj.checkForPlan);
$("#pricing #pricing_account_info .pricing_form_btn").on('click', pricingObj.checkForBankForm);