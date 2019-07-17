jQuery(document).ready(function() {
    var baseApiUrl = "/system/api/cis";


    /*
    ** Pricing for the products
    */

    var pricingObj = {
        init: function() {
            var classRef = this;

            this.selectedPlanName = 'General';
            this.selectedPlanAmount = 0;
            this.bankFormData = {};

            this.pricingLookUp().then(function(response) {
                $(".pricing_table").show();
                $("#pricing_bank_form .pricing_bank_form_btn a").on('click', {this: classRef}, classRef.validateBankForm);
            }, function(error) {
                $(".pricing_table").show();
            });

            $("#pricing #pricing_account_info .pricing_form_btn").on('click', classRef.checkForBankForm);
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
                $("#steps_update").text("Step 2 of 3");
                $("#steps_update").show();
            } else {
                inner_login('view/pricing.jsp');
            }
        },

        validateBankForm: function(event) {
            var classRef = event.data.this;

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

            if(bankName != '') {
                $("#pricing_bank_form #bankName").removeClass("error_field");
            } else {
                $("#pricing_bank_form #bankName").addClass("error_field");
            }

            if(bankHolderName != '') {
                $("#pricing_bank_form #bankHolderName").removeClass("error_field");
            } else {
                $("#pricing_bank_form #bankHolderName").addClass("error_field");
            }

            if(userId != '' && subscriptionType != '' && paymentMode != '' && transactionDate != ''
                && transactionRefNumber != '' && bankName != '' && bankHolderName != '') {
                classRef.bankFormData.userrid = userId;
            classRef.bankFormData.subscriptionType = subscriptionType;
            classRef.bankFormData.paymentMode = paymentMode;
            classRef.bankFormData.transactionDate = transactionDate;
            classRef.bankFormData.transactionRefNumber = transactionRefNumber;
            classRef.bankFormData.bankName = bankName;
            classRef.bankFormData.bankHolderName = bankHolderName;
            classRef.bankFormData.transactionVerified = false;

            classRef.postBankFormApi(userId).then(function(response) {
                console.log("Success in bank form");
            }, function(error) {
                console.log("Error in bank form");
            });
        }
    },

        /**
        * Function to start async call to get filter data.
        */
        postBankFormApi : function(userId) {
            var url = "/system/api/users/" + userId + "/subscription";
            return new Promise(function(resolve, reject) {
                var httpRequest = new XMLHttpRequest({
                    mozSystem: true
                });
                //httpRequest.timeout = API_TIMEOUT_SMALL;
                httpRequest.open('POST', url, true);
                httpRequest.setRequestHeader('Content-Type',
                    'application/json; charset=UTF-8');
                httpRequest.ontimeout = function () {
                    reject("" + httpRequest.responseText);
                };
                httpRequest.onreadystatechange = function () {
                    if (httpRequest.readyState === XMLHttpRequest.DONE) {
                        if (httpRequest.status === 200) {
                            resolve(httpRequest.response);
                        } else {
                            //console.log(httpRequest.status + httpRequest.responseText);
                            reject(httpRequest.responseText);
                        }
                    } else {
                    }
                };

                httpRequest.send(JSON.stringify(classRef.bankFormData));
            });
        }

    };

    pricingObj.init();
    $("#pricing button").on('click', pricingObj.checkForPlan);
});