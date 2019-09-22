jQuery(document).ready(function() {
    var baseApiUrl = "/system/api/cis";

    /*
    ** Pricing for the products
    */

    var pricingObj = {
        init: function() {
            var classRef = this;

            this.selectedPlanName = "FREE";
            this.selectedPlanAmount = 0;
            this.bankFormData = {};
            this.userId = $("input[name='loggedInUser']").val();
            this.isFormSuccess = false;
            this.pricingLookUp();
        },

        pricingLookUp: function() {
            var classRef = this;
            isProgressLoader(true);

            classRef.getUserSubscriptionApi(classRef.userId).then(function(response){
                classRef.setUserSubscriptionDetails(response);
                classRef.showDefaultPricingScreen();
                $("#pricing button").off().on('click', {this: classRef}, classRef.checkForPlan);
            }, function(error) {
                classRef.showDefaultPricingScreen();
                $("#pricing button").off().on('click', {this: classRef}, classRef.checkForPlan);
            });
        },

        checkForPlan : function(event) {
            var classRef = event.data.this;

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
                console.log($("#steps_update #prev").is(':visible'));
                $("#steps_update #prev").off().on('click', classRef.goBack);
                $("#pricing #pricing_account_info .pricing_form_btn").off().on('click', {this: classRef}, classRef.checkForBankForm);
            } else {
                inner_login('view/pricing.jsp');
            }
        },

        goBack: function(event) {

            if($("#pricing_account_info").is(':visible')) {
                $("#pricing_plan").show();
                $("#steps_update").hide();
                $("#pricing_account_info").hide();
            } else if($("#pricing_bank_form").is(':visible')) {
                $("#pricing_account_info").show();
                $("#steps_update span").text("Step 1 of 3");
                $("#pricing_bank_form").hide();
            } else if($("#pricing_bank_form_review").is(':visible')) {
                $("#pricing_bank_form").show();
                $("#steps_update span").text("Step 2 of 3");
                $("#pricing_bank_form_review").hide();
            }

        },

        checkForBankForm: function(event) {
            var classRef = event.data.this;

            if(!isLoggedInUser()) {
                window.scrollTo(0, 0);

                $("#pricing_account_info").hide();
                $("#pricing_bank_form").show();
                $("#bank_form").show();



                $("#bank_form").trigger("reset");
                $("#pricing_bank_form #bank_form input").removeClass("error_field");

                $("#pricing_bank_form #subscriptionType").val(pricingObj.selectedPlanName);
                $("#amountTransferred").val(pricingObj.selectedPlanAmount);
                $("#steps_update span").text("Step 2 of 3");
                $("#steps_update").show();
                $("#pricing_bank_form .pricing_bank_form_btn .pricing_cancel_btn").off().on('click', {this: classRef}, classRef.goBack);
                $("#pricing_bank_form .pricing_bank_form_btn .pricing_review_btn").off().on('click', {this: classRef}, classRef.validateBankForm);
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
                classRef.bankFormData.userId = userId;
                classRef.bankFormData.subscriptionType = subscriptionType;
                classRef.bankFormData.paymentMode = paymentMode;
                classRef.bankFormData.transactionDate = new Date(transactionDate).getTime();
                classRef.bankFormData.transactionRefNumber = transactionRefNumber;
                classRef.bankFormData.amountTransferred = Number(amountTransferred);
                classRef.bankFormData.bankName = bankName;
                classRef.bankFormData.bankHolderName = bankHolderName;
                classRef.bankFormData.transactionVerified = false;



                var userDetails = {
                    "data" : {
                        "userId" : userId,
                        "subscriptionType": subscriptionType,
                        "paymentMode" : paymentMode,
                        "transactionDate" : transactionDate,
                        "transactionRefNumber" : transactionRefNumber,
                        "amountTransferred" : amountTransferred,
                        "bankName" : bankName,
                        "bankHolderName" : bankHolderName
                    }
                }

                
                $("#review_user_id").text(userId);
                $("#review_subscriptionType").text(subscriptionType);
                $("#review_paymentMode").text(paymentMode);
                $("#review_transactionDate").text(transactionDate);
                $("#review_transactionRefNumber").text(transactionRefNumber);
                $("#review_amountTransferred").text(amountTransferred);
                $("#review_bankName").text(bankName);
                $("#review_bankHolderName").text(bankHolderName);

                $("#pricing_bank_form").hide();
                $("#steps_update span").text("Step 3 of 3");
                $("#pricing_bank_form_review").show();

                $("#pricing_bank_form_review .pricing_bank_form_btn .pricing_cancel_btn").off().on('click', {this: classRef}, classRef.goBack);
                $("#pricing_bank_form_review .pricing_bank_form_btn .pricing_submit_btn").off().on('click', {this: classRef, userDetails: userDetails}, classRef.reviewBankForm);
            }
        },

        reviewBankForm: function(args) {
            var classRef = args.data.this;
            var userDetails = args.data.userDetails;
            
            classRef.postBankFormApi(userId).then(function(response) {
                classRef.isFormSuccess = true;

                var response = JSON.parse(response);

                window.localStorage.setItem("userDetails", JSON.stringify(userDetails));

                window.scrollTo(0, 0);

                //$("#pricing_bank_form_review").hide();
                //$("#steps_update span").text("Step 4 of 4");
                //$("#pricing_bank_form_result").show();
                //$("#steps_update a").hide();
                $("#user_message_modal .modal-content p").html("<span>Thank you for subscribing, we will revert back to you soon!!</span>");
            }, function(error) {
                console.log("Error in bank form");
                classRef.isFormSuccess = false;

                var error = JSON.parse(error);
                if(error.message === undefined) {
                    error.message = "There is some error, please try again later";
                }
                $("#user_message_modal .modal-content p").html("<span class='danger'>" + error.message + "</span>");
            });
        },

        /**
        * Function to start async call to get filter data.
        */
        postBankFormApi : function() {
            var classRef = this;

            var url = "/api/users/" + classRef.bankFormData.userId + "/subscriptions";
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
                        if (httpRequest.status === 201) {
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
        },

        /**
        * Function to start async call to get user subscribed data.
        */
        getUserSubscriptionApi : function(userId) {
            var classRef = this;

            return new Promise(function(resolve, reject) {

                if(userId != '') {
                    var url = "/api/users/" + userId + "/subscriptions";
                    var httpRequest = new XMLHttpRequest({
                        mozSystem: true
                    });
                    //httpRequest.timeout = API_TIMEOUT_SMALL;
                    httpRequest.open('GET', url, true);
                    httpRequest.ontimeout = function () {
                        reject("" + httpRequest.responseText);
                    };
                    httpRequest.onreadystatechange = function () {
                        if (httpRequest.readyState === XMLHttpRequest.DONE) {
                            if (httpRequest.status === 200) {
                                resolve(httpRequest.response);
                            } else {
                                reject(false);
                            }
                        } else {
                            
                        }
                    };

                    httpRequest.send();
                } else {
                    reject(false)
                }
            });
        },

        showDefaultPricingScreen: function() {
            $(".pricing_table").show();
            if(!isLoggedInUser()) {
                var userDetails = JSON.parse(window.localStorage.getItem("userDetails"));
                if(userDetails != undefined) {

                    if((userDetails.data.subscriptionStatus == "PENDING" && userDetails.data.subscriptionStatus == "N/A") && userDetails.data.subscriptionType == undefined) {
                        
                    } else if(userDetails.data.subscriptionStatus == "ACTIVE") {
                        if(userDetails.data.subscriptionType == "SAGE") {
                            $("#pricing #sage_investors .btnSubscribe").hide();
                            $("#pricing #smart_investors .btnSubscribe").hide();
                        } else if(userDetails.data.subscriptionType == "SMART") { 
                            $("#pricing #smart_investors .btnSubscribe").hide();
                        }
                        $("#pricing #general_investors .btnSubscribe").hide();
                    } else {
                        if(userDetails.data.subscriptionType == "SAGE") {
                            $("#pricing button#smart_investors").prop("disabled", "disabled");
                            $("#pricing button#sage_investors").prop("disabled", "disabled");

                        } else if(userDetails.data.subscriptionType == "SMART") { 
                            $("#pricing #sage_investors .btnSubscribe").show();
                            $("#pricing button#smart_investors").prop("disabled", "disabled");

                        } else if(userDetails.data.subscriptionType == "FREE") {
                            $("#pricing #smart_investors .btnSubscribe").show();
                            $("#pricing #sage_investors .btnSubscribe").show();
                            $("#pricing button#smart_investors").prop("disabled", "");
                            $("#pricing button#sage_investors").prop("disabled", "");
                        } else {
                            $("#pricing #general_investors .btnSubscribe").hide();
                        }

                        $("#pricing .btnSubscribe a").text("Subscribe");
                        $("#pricing #general_investors .btnSubscribe").hide();
                    }
                }
            } else {
                window.localStorage.removeItem("userDetails");
            }

            isProgressLoader(false);
        },

        setUserSubscriptionDetails: function(response) {
            
            if(typeof response == 'string') {
                response = JSON.parse(response);
            }

            var userDetails = {
                "data" : {
                    "subscriptionStatus": response.data.subscriptionState,
                    "subscriptionType": response.data.subscriptionType
                }
            }
            window.localStorage.setItem("userDetails", JSON.stringify(userDetails));

        }

    };

    pricingObj.init();

    $(".modal").on("hidden.bs.modal", function () {
        if(pricingObj.isFormSuccess) {
            window.location = "/view/pricing.jsp";
        }
    });
});