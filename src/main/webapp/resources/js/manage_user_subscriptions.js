jQuery(document).ready(function() {

    /*
    ** Pricing for the products
    */

    var manageSubscriptionsObj = {
        init: function() {
            var classRef = this;
            this.firstPageNumber = 1;
            this.pageNumber = 1;
            this.lastPageNumber = 1;
            this.totalRecords = 1;
            this.currentIndex = 1;
            this.perPageMaxRecords = 10;
            this.sortByValue = 'userName';
            this.orderBy = 'desc';
            this.baseApiUrl = "/api/subscriptions";
            this.getAllSubscriptionsDetails();
        },

        getAllSubscriptionsDetails: function() {
            var classRef = this;
            isProgressLoader(true);

            classRef.getRecordStats().then(function(stats) {
                stats = JSON.parse(stats);
                classRef.firstPageNumber = stats.firstPageNumber;
                classRef.lastPageNumber = stats.lastPageNumber;
                classRef.totalRecords = stats.totalRecords;

                $("#strategyModal #total_records_count").html(classRef.totalRecords + " Results");
            }, function(error) {
                classRef.isProgressLoader(false);
                $("tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
            });

            classRef.getAllSubscriptionApi(classRef.userId).then(function(response){
                classRef.populateSubscriptionsHtml(response);
            }, function(error) {
                var error = JSON.parse(error);
                $("tbody").html("<tr><td colspan='9'>No Records Found</td></tr>");
            });
        },

        populateSubscriptionsHtml: function(response) {
            var response = JSON.parse(response);
            response = response.data;
            var len = response.length;
            var htmlCode = '';
            var rowHtml =   "";

            if(len === 0) {
                $("tbody").html("<tr><td colspan='9'>No Records Found</td></tr>");
                $("#manage_user_subscriptions .deleteBtn").attr("disabled", "disabled");
                $('#manage_user_subscriptions #user_subscriptions_table input[name=selectAll]').prop('checked', false);
                return;
            }

            for(var i = 0; i < len; i++) {

                htmlCode = htmlCode + "<tr data-id='" + response[i].subscriptionId + "'>" +
                "<td>" + 
                "<div class='userName'>" + response[i].userName + "</div>" +
                "</td>" + 
                "<td>" + 
                "<div class='subscriptionType'>" + response[i].subscriptionType + "</div>" + 
                "</td>" + 
                "<td>" + 
                "<div class='paymentMode'>" + response[i].paymentMode + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='transactionDate'>" + response[i].transactionDate + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='transactionRefNumber'>" + response[i].transactionRefNumber + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='bankName'>" + response[i].bankName + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='bankHolderName'>" + response[i].bankHolderName + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='amountTransferred'>" + response[i].amountTransferred + "</div>" + 
                "</td>" +
                "<td>" + 
                    "<input name='subscriptions" + i + "' class='submit-button' type='checkbox' />" + 
                "</td>" +
                "</tr>";
            }

            $("#user_subscriptions_table tbody").html(htmlCode);

            $("#manage_user_subscriptions .deleteBtn").attr("disabled", "disabled");

            //$("#manage_user_subscriptions #user_subscriptions_table tbody input[type=checkbox]").on('click', updateUserSubscription);
        },

        updateUserSubscription: function() {

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
        * Function to start async call to get all user subscribed data.
        */
        getAllSubscriptionApi : function(userId) {
            var classRef = this;

            return new Promise(function(resolve, reject) {

                if(userId != '') {
                    var url = classRef.baseApiUrl + "?pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords ;
                    var httpRequest = new XMLHttpRequest({
                        mozSystem: true
                    });
                    //httpRequest.timeout = API_TIMEOUT_SMALL;
                    httpRequest.open('GET', url, true);
                    httpRequest.setRequestHeader('Content-Type',
                    'application/json');
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

        getRecordStats: function() {
            var classRef = this;

            var url = classRef.baseApiUrl + "/recordstat?perPageMaxRecords=" + classRef.perPageMaxRecords;
            return new Promise(function(resolve, reject) {
                var httpRequest = new XMLHttpRequest({
                    mozSystem: true
                });

                httpRequest.open('GET', url, true);
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

                httpRequest.send();
            });
        }, 

        getPerPageMaxRecords: function(event) {
            var classRef = event.data.this;

            if(classRef.perPageMaxRecords !== Number($(this).val())) {
                classRef.pageNumber = 1;
                classRef.firstPageNumber = 1;
                classRef.lastPageNumber = 1;
                classRef.currentIndex = 1;
            }

            classRef.perPageMaxRecords = Number($(this).val());
            console.log("perPageMaxRecords: " + classRef.perPageMaxRecords);
            kennithFisherStrategyObj.getCurrentStrategyData();
        },

        getSortedByValue: function(event) {
            var classRef = event.data.this;

            if($(this).attr('data-id') == undefined) {
                return;
            }
            
            if($(this).attr('data-id') == classRef.sortByValue) {
                
                if(classRef.orderBy == "desc") {
                    classRef.orderBy = "asc";
                } else if(classRef.orderBy == "asc") {
                    classRef.orderBy = "desc";
                }
            } else {
                classRef.orderBy = "desc";
            }

            classRef.sortByValue = $(this).attr('data-id');
            kennithFisherStrategyObj.getCurrentStrategyData();
        },

        setRecordStats : function() {
            var classRef = this;

            if(classRef.currentIndex > classRef.lastPageNumber) {
                classRef.currentIndex = classRef.lastPageNumber;
            }
            $("#strategyModal #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
        },

        getPaginationIndex : function(event) {
            var classRef = event.data.this;
            var currentNode = $(this).attr('id');

            if(currentNode == 'last') {
                classRef.getLastPage();
            } else if(currentNode == 'next') {
                classRef.getNextPage();
            } else if(currentNode == 'prev') {
                classRef.getPreviousPage();
            } else if(currentNode == 'first') {
                classRef.getFirstPage();
            }
        },

        getFirstPage : function() {
            var classRef = this;

            if(classRef.pageNumber != classRef.firstPageNumber) {
                classRef.pageNumber = classRef.firstPageNumber;
                classRef.currentIndex = classRef.firstPageNumber;
                classRef.getCurrentStrategyData();
            }
        },

        getLastPage : function() {
            var classRef = this;

            if(classRef.pageNumber != classRef.lastPageNumber) {
                classRef.pageNumber = classRef.lastPageNumber;
                classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
                classRef.getCurrentStrategyData();
            }
        },

        getNextPage : function() {
            var classRef = this;

            if(classRef.pageNumber < classRef.lastPageNumber) {
                classRef.pageNumber = classRef.pageNumber + 1;
                classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
                classRef.getCurrentStrategyData();
            }
        },

        getPreviousPage : function() {
            var classRef = this;

            if(classRef.pageNumber > 1) {
                classRef.pageNumber = classRef.pageNumber - 1;
                classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
                classRef.getCurrentStrategyData();
            }
        }

    };

    manageSubscriptionsObj.init();

});