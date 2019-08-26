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
            this.userList = [];
            this.baseApiUrl = "/api/subscriptions";
            this.subscriptionStateActive = "ACTIVE";
            this.subscriptionStateTerminate = "TERMINATE";
            this.userId = $("input[name='loggedInUser']").val();
            this.subscriptionRefIds = [];
            this.filterJsonObj = {
                "from" : new Date().getTime(),
                "to" : new Date().getTime()
            };
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

                $("#manage_user_subscriptions #total_records_count").html(classRef.totalRecords + " Results");


                classRef.getAllSubscriptionApi(classRef.userId).then(function(serverResponse){
                    $("#manage_user_subscriptions .paging_container").remove();
                    classRef.populateSubscriptionsHtml(serverResponse);
                    isProgressLoader(false);
                }, function(error) {
                    var error = JSON.parse(error);
                    isProgressLoader(false);
                    $("tbody").html("<tr><td colspan='10'>No Records Found</td></tr>");
                });
            }, function(error) {
                isProgressLoader(false);
                $("tbody").html("<tr><td colspan='10'>We are not able to get the info, please try again later.</td></tr>");
            });
        },

        populateSubscriptionsHtml: function(response) {
            var classRef = this;

            var response = JSON.parse(response);
            response = response.data;
            var len = response.length;
            var htmlCode = '';
            var rowHtml =   "";

            if(len === 0) {
                $("tbody").html("<tr><td colspan='10'>No Records Found</td></tr>");
                $("#manage_user_subscriptions .deleteBtn").attr("disabled", "disabled");
                $('#manage_user_subscriptions #user_subscriptions_table input[name=selectAll]').prop('checked', false);
                return;
            }

            for(var i = 0; i < len; i++) {

                var statusClass = "label-warning";
                var styleClass = "visible";
                if(response[i].subscriptionState == "ACTIVE") {
                    styleClass = "hidden";
                    statusClass = "label-success";
                } else if(response[i].subscriptionState == "TERMINATE") {
                    styleClass = "hidden";
                    statusClass = "label-danger";
                } else {
                    styleClass = "visible";
                    statusClass = "label-warning";
                }


                htmlCode = htmlCode + "<tr data-id='" + response[i].subscriptionRefId + "' data-userid='" + response[i].userName + "' data-subscriptionstatus='" + response[i].subscriptionState + "'>" +
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
                "<div class='transactionDate'>" + classRef.timeStampToDate(Number(response[i].transactionDate)) + "</div>" + 
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
                "<div class='amtTransferred'>" + response[i].amtTransferred + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='subscriptionState " + statusClass + "'>" + response[i].subscriptionState + "</div>" + 
                "</td>" +
                "<td>" + 
                    "<input name='subscriptions" + i + "' class='submit-button " + styleClass + "' type='checkbox' />" + 
                "</td>" +
                "</tr>";
            }

            $("#user_subscriptions_table tbody").html(htmlCode);

            $("#manage_user_subscriptions .approveBtn").attr("disabled", "disabled");
            $("#manage_user_subscriptions .rejectBtn").attr("disabled", "disabled");

            $("#manage_user_subscriptions #user_subscriptions_table tbody input[type=checkbox]").on('click', {this: classRef}, classRef.enableButtons);

            var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

            $("#manage_user_subscriptions .manage_user_subscriptions_content").append(paginationHtml);

            $('#manage_user_subscriptions .manage_user_subscriptions_content .pager a').off().on('click', {this: classRef}, classRef.getPaginationIndex);

            $("#user_subscriptions_table thead th a").off().on('click', {this: classRef}, classRef.getSortedByValue);

            classRef.setRecordStats();

            $("#transactionDateFrom").val(classRef.getFormattedDate());
            $("#transactionDateTo").val(classRef.getFormattedDate());


            $('#manage_user_subscriptions .manage_user_subscriptions_content .applyBtn').off().on('click', {this: classRef}, classRef.getFilterData);
            $('#manage_user_subscriptions .manage_user_subscriptions_content .max_per_page select').off().on('change', {this: classRef}, classRef.getPerPageMaxRecords);
            $("#manage_user_subscriptions input[name='selectAll']").off().on('change', {this: classRef}, classRef.checkForAllData);
        },

        getFilterData: function(event) {
            var classRef = event.data.this;
            var transactionDateFrom = new Date($("#transactionDateFrom").val()).getTime();
            var transactionDateTo = new Date($("#transactionDateTo").val()).getTime();

            transactionDateFrom = (transactionDateFrom == "") ? new Date().getTime() : transactionDateFrom;
            transactionDateTo = (transactionDateTo == "") ? new Date().getTime() : transactionDateTo;

            classRef.filterJsonObj = {
                "from" : transactionDateFrom,
                "to" : transactionDateTo
            };
            classRef.getAllSubscriptionsDetails();
        },

        enableButtons: function(event) {
            var classRef = event.data.this;
            var rowId = $(this).parents('tr');
            var subscriptionRefId = rowId.attr('data-id');
            var userId = rowId.attr('data-userid');
            var status = rowId.attr('data-subscriptionstatus');
            var element = event.target;

            var itemJson = {
                "userId" : userId,
                "subscriptionRefId" : subscriptionRefId
            };
            
            classRef.addRemoveItemFromArray(classRef.subscriptionRefIds, subscriptionRefId);

            if(classRef.subscriptionRefIds.length == 0) {
                $("#manage_user_subscriptions .approveBtn").attr("disabled", "disabled");
                $("#manage_user_subscriptions .rejectBtn").attr("disabled", "disabled");
            } else {
                $("#manage_user_subscriptions .approveBtn").removeAttr("disabled");
                $("#manage_user_subscriptions .rejectBtn").removeAttr("disabled");
                $("#approveUserSubscription .submitBtn").on('click', {this: classRef, subscriptionState: classRef.subscriptionStateActive}, classRef.updateUserSubscription);
                $("#rejectUserSubscription .submitBtn").on('click', {this: classRef, subscriptionState: classRef.subscriptionStateTerminate}, classRef.updateUserSubscription);
            }


        },

        updateUserSubscription: function(event) {
            var classRef = event.data.this;
            var state = event.data.subscriptionState;
            var element = (state == classRef.subscriptionStateActive) ? "#approveUserSubscription" : "#rejectUserSubscription";

            classRef.updateUserSubscriptionApi(state).then(function(response) {
                $(element).modal('hide');
                var arr = $("table tbody input:visible");


                for(key in arr) {
                    if (!isNaN(key)) {
                        var rowId = $("table tbody input:visible").eq(key).parents('tr');
                        var subscriptionRefId = rowId.attr('data-id');
                        var userId = rowId.attr('data-userid');
                        var status = rowId.attr('data-subscriptionstatus');
                        if(subscriptionRefId == classRef.subscriptionRefIds[0]) {
                            classRef.subscriptionRefIds.splice(subscriptionRefId, 1);
                            rowId.find(".subscriptionState").text(state);
                            rowId.find("td:last input").addClass("hidden");
                            if(state == classRef.subscriptionStateActive) {
                                rowId.find(".subscriptionState").removeClass("label-warning");
                                rowId.find(".subscriptionState").addClass("label-success");
                            } else {
                                rowId.find(".subscriptionState").removeClass("label-warning");
                                rowId.find(".subscriptionState").addClass("label-danger");
                            }
                        }
                    }
                }
            }, function(error) {
                $(element).modal('hide');
            });
        },

        /**
        * Function to start async call to get filter data.
        */
        updateUserSubscriptionApi : function(state) {
            var classRef = this;

            var jsonBody = {
                "subscriptionState": state,
                "subscriptionIds" : classRef.subscriptionRefIds
            };

            var url = "/api/users/" + classRef.userId + "/subscriptions";
            return new Promise(function(resolve, reject) {
                var httpRequest = new XMLHttpRequest({
                    mozSystem: true
                });
                //httpRequest.timeout = API_TIMEOUT_SMALL;
                httpRequest.open('PUT', url, true);
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
                            reject(httpRequest.responseText);
                        }
                    } else {
                    }
                };

                httpRequest.send(JSON.stringify(jsonBody));
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
        * Function to start async call to get all user subscribed data.
        */
        getAllSubscriptionApi : function(userId) {
            var classRef = this;

            return new Promise(function(resolve, reject) {

                if(userId != '') {
                    var url = classRef.baseApiUrl + "?pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
                    var httpRequest = new XMLHttpRequest({
                        mozSystem: true
                    });
                    //httpRequest.timeout = API_TIMEOUT_SMALL;
                    httpRequest.open('POST', url, true);
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

                    httpRequest.send(JSON.stringify(classRef.filterJsonObj));
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

                httpRequest.send(JSON.stringify(classRef.filterJsonObj));
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
            classRef.getAllSubscriptionsDetails();
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
            classRef.getAllSubscriptionsDetails();
        },

        setRecordStats : function() {
            var classRef = this;

            if(classRef.currentIndex > classRef.lastPageNumber) {
                classRef.currentIndex = classRef.lastPageNumber;
            }
            $("#manage_user_subscriptions #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
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
                classRef.getAllSubscriptionsDetails();
            }
        },

        getLastPage : function() {
            var classRef = this;

            if(classRef.pageNumber != classRef.lastPageNumber) {
                classRef.pageNumber = classRef.lastPageNumber;
                classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
                classRef.getAllSubscriptionsDetails();
            }
        },

        getNextPage : function() {
            var classRef = this;

            if(classRef.pageNumber < classRef.lastPageNumber) {
                classRef.pageNumber = classRef.pageNumber + 1;
                classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
                classRef.getAllSubscriptionsDetails();
            }
        },

        getPreviousPage : function() {
            var classRef = this;

            if(classRef.pageNumber > 1) {
                classRef.pageNumber = classRef.pageNumber - 1;
                classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
                classRef.getAllSubscriptionsDetails();
            }
        },

        addRemoveItemFromArrayJsonObject : function(array, item) {
            if(array.length != 0) {
                for(key in array) {
                    if(array[key].subscriptionRefId == item.subscriptionRefId) {
                        array.splice(key, 1);
                    } else {
                        array.push(item);
                    }
                }
            } else {
                array.push(item);
            }
            return array;
        },

        // Find and remove item from an array
        addRemoveItemFromArray : function(array, item) {
            var i = array.indexOf(item);
            if(i != -1) {
                array.splice(i, 1);
            } else {
                array.push(item);
            }
            return array;
        },

        timeStampToDate : function (ts) {
            if (ts) {
                ts = new Date(ts).toString();
                ts = ts.split(' ').slice(0, 5);
                ts = /*ts[0] + " " + */ ts[1] + " " + ts[2] + ", " + ts[3]; //+ " " + ts[4];
                //console.log(ts);
                return ts;
            } else {
                return 'NA';
            }
        },

        checkForAllData : function(event) {
            var classRef = event.data.this;
            var arr = $(event.target).parents("table").find("tbody input:visible");
            

            for(key in arr) {
                if (!isNaN(key)) {
                    var rowId = $("tbody input:visible").eq(key).parents('tr');
                    var subscriptionRefId = rowId.attr('data-id');
                    var userId = rowId.attr('data-userid');
                    var status = rowId.attr('data-subscriptionstatus');
                    classRef.addRemoveItemFromArray(classRef.subscriptionRefIds, subscriptionRefId);
                }
            }
        },

        getFormattedDate: function() {
            var today = new Date();
            var dd = today.getDate(); 
            var mm = today.getMonth() + 1; 
      
            var yyyy = today.getFullYear(); 
            if (dd < 10) { 
                dd = '0' + dd; 
            } 
            if (mm < 10) { 
                mm = '0' + mm; 
            } 
            var today = mm + '/' + dd + '/' + yyyy; 
            return today;
        }
    };

    manageSubscriptionsObj.init();

});