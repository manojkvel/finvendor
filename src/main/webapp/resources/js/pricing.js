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
            $("#pricing_account_info").hide();
            $("#pricing_bank_form").show();
            $("#amountTransferred").val(pricingObj.selectedPlanAmount);
            $("#steps_update").text("Step 2 of 4");
            $("#steps_update").show();
        } else {
            inner_login('view/pricing.jsp');
        }
    }

};

pricingObj.init();
$("#pricing button").on('click', pricingObj.checkForPlan);
$("#pricing #pricing_account_info .pricing_form_btn").on('click', pricingObj.checkForBankForm);