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

            this.pricingLookUp();
        },

        pricingLookUp: function() {
            var classRef = this;
            isProgressLoader(true);

            classRef.getUserSubscriptionApi(classRef.userId).then(function(response){
                classRef.showDefaultPricingScreen();
                classRef.setUserSubscriptionDetails(response);
                $("#pricing_bank_form .pricing_bank_form_btn a").on('click', {this: classRef}, classRef.validateBankForm);
                $("#pricing #pricing_account_info .pricing_form_btn").on('click', {this: classRef}, classRef.checkForBankForm)
            }, function(error) {
                classRef.showDefaultPricingScreen();
            });

            $("#pricing button").on('click', {this: classRef}, classRef.checkForPlan);

            /*return new Promise(function(resolve, reject) {
                if(!isLoggedInUser()) {
                    var userDetails = JSON.parse(window.localStorage.getItem("userDetails"));
                    if(userDetails.subscriptionType == "SAGE") {
                        $("#pricing #sage_investors .btnSubscribe").hide(); 
                        $("#pricing #smart_investors .btnSubscribe").hide();
                    } else if(userDetails.subscriptionType == "SMART") { 
                        $("#pricing #smart_investors .btnSubscribe").hide();
                        $("#pricing #sage_investors .btnSubscribe").show();
                    } else {
                        $("#pricing #general_investors .btnSubscribe").hide();
                    }

                    $("#pricing .btnSubscribe a").text("Subscribe");
                    $("#pricing #general_investors .btnSubscribe").hide();
                    resolve(true);
                } else {
                    window.localStorage.removeItem("userDetails");
                    reject(false);
                }
            });*/
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
            }

        },

        checkForBankForm: function(event) {
            var classRef = event.data.this;

            if(!isLoggedInUser()) {
                window.scrollTo(0, 0);

                $("#pricing_account_info").hide();
                $("#pricing_bank_form").show();
                $("#bank_form").show();
                $("#pricing_bank_form #subscriptionType").val(pricingObj.selectedPlanName);
                $("#amountTransferred").val(pricingObj.selectedPlanAmount);
                $("#steps_update span").text("Step 2 of 3");
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
                classRef.bankFormData.subscriptionType = subscriptionType;
                classRef.bankFormData.paymentMode = paymentMode;

                classRef.bankFormData.transactionDate = moment(transactionDate).toDate().getTime();
                classRef.bankFormData.transactionRefNumber = transactionRefNumber;
                classRef.bankFormData.amountTransferred = Number(amountTransferred);
                classRef.bankFormData.bankName = bankName;
                classRef.bankFormData.bankHolderName = bankHolderName;
                classRef.bankFormData.transactionVerified = false;

                classRef.postBankFormApi(userId).then(function(response) {
                    var response = JSON.parse(response);

                    window.scrollTo(0, 0);

                    var userDetails = {
                        "data" :{
                            "subscriptionType": subscriptionType
                        }
                    }
                    classRef.setUserSubscriptionDetails(userDetails);

                    $("#pricing_bank_form").hide();
                    $("#steps_update span").text("Step 3 of 3");
                    $("#pricing_bank_form_result").show();
                    $("#steps_update a").hide();
                    $("#pricing_bank_form_result p").text(response.message);
                }, function(error) {
                    console.log("Error in bank form");
                });
            }
        },

        /**
        * Function to start async call to get filter data.
        */
        postBankFormApi : function(userId) {
            var classRef = this;

            var url = "/api/users/" + userId + "/subscriptions";
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
                                //console.log(httpRequest.status + httpRequest.responseText);
                                reject(httpRequest.responseText);
                            }
                        } else {
                        }
                    };

                    httpRequest.send();
                } else {
                    classRef.showDefaultPricingScreen();
                }
            });
        },

        showDefaultPricingScreen: function() {
            $(".pricing_table").show();
            isProgressLoader(false);
        },

        setUserSubscriptionDetails: function(response) {
            
            if(typeof response == 'string') {
                response = JSON.parse(response);
            }

            var userDetails = {
                "subscriptionType": response.data.subscriptionType
            }
            window.localStorage.setItem("userDetails", JSON.stringify(userDetails));

            if(userDetails.subscriptionType == "SAGE") {
                $("#pricing #sage_investors .btnSubscribe").hide(); 
                $("#pricing #smart_investors .btnSubscribe").hide();
            } else if(userDetails.subscriptionType == "SMART") { 
                $("#pricing #smart_investors .btnSubscribe").hide();
                $("#pricing #sage_investors .btnSubscribe").show();
            } else {
                $("#pricing #general_investors .btnSubscribe").hide();
            }

            $("#pricing .btnSubscribe a").text("Subscribe");
            $("#pricing #general_investors .btnSubscribe").hide();
        }

    };

    pricingObj.init();
});