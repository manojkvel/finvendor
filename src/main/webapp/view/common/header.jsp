<%@page import="com.finvendor.util.RequestConstans" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
            <%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
                <%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen" %>
                    <jsp:include page="head.jsp"></jsp:include>
                    <script src="${pageContext.request.contextPath}/resources/js/finvendorCommon.js"></script>
                    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
                    <script>
                        $(document).ready(function() {
                            $(".account").click(function() {
                                var X = $(this).attr('id');
                                if (X == 1) {
                                    $(".profilepicsubmenu").hide();
                                    $(this).attr('id', '0');
                                } else {
                                    $(".profilepicsubmenu").show();
                                    $(this).attr('id', '1');
                                }
                            });

                            $(".profilepicsubmenu").mouseup(function() {
                                return false
                            });

                            $(".account").mouseup(function() {
                                return false
                            });

                            $(document).mouseup(function() {
                                $(".profilepicsubmenu").hide();
                                $(".account").attr('id', ''); 
                            });
                        });
                    </script>
                    <c:set var="username" value="${finVen:decrypt(param.RaYUnA)}"></c:set>
                    <c:set var="myusername" value="${myusername}"></c:set>
                    <div id='getLoggedInUser' style="display: none;">
                        <input type="hidden" name="isLoggedInUser" value=${sessionScope.loggedInUser} />
                    </div>
                    <header>
                        <div class="container-fluid" >
                            <div class="row">
                                    <div class="header">
                                        <a href="${pageContext.request.contextPath}/" onclick="homePage()" class="logo"> <img src="${pageContext.request.contextPath}/resources/images/company-logo-header.jpg" alt="FinVendor" title="FinVendor" onclick="homepage()" align="middle" />
                                                <span class="hide">Democratizing The World Of Financial Vendors.</span>
                                            </a>
                                            
                                    <nav>
                                        <div class="header-nav">
                                                <div class="pull-left">
                                                    <div class="nav-container">
                                                        <div class="nav-srch">
                                                            <div class="nav-srch-cnt ">

                                                                <ul class="sf-menu hidden-xs" id="example">
                                                                    <li>
                                                                        <a href="#">Research Reports</a>
                                                                        <ul>
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/equity_research_report_vendor.jsp?researchReportType=Equity/Company Research">
                                                                                    Equity/Company Research
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/sector-research.jsp">
                                                                                    Sector Research
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/macro-research.jsp">
                                                                                    Macro Research
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/debt-market-research.jsp">
                                                                                    Debt Mkt Research
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/fund-etf-research.jsp">
                                                                                    Fund/ETF Research
                                                                                </a>
                                                                            </li>
                                                                        </ul>
                                                                    </li>
                                                                    <li>
                                                                        <a href="${pageContext.request.contextPath}/view/brokers-analysts.jsp">Brokers/Analysts</a>
                                                                    </li>
                                                                    <li><a href="javascript:void(0);">More</a>
                                                                        <ul>
                                                                            <li><a href="${pageContext.request.contextPath}/view/brochures.jsp">Brochures</a>
                                                                            </li>
                                                                            <li><a href="${pageContext.request.contextPath}/view/white-papers.jsp">Whitepapers</a>
                                                                            </li>

                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/blogs.jsp">Blogs</a>
                                                                            </li>
                                                                            <li><a href="${pageContext.request.contextPath}/view/case-studies.jsp">Case
                                                                                Studies</a>
                                                                            </li>
                                                                            <li><a href="${pageContext.request.contextPath}/view/spotlights.jsp">Spotlights</a>
                                                                            </li>
                                                                        </ul>
                                                                    </li>

                                                                    <li class="cd-contact">
                                                                        <a href="${pageContext.request.contextPath}/view/contact-us.jsp">
                                                                            <i class="fa fa-address-book"></i> Contact
                                                                        </a>
                                                                    </li>

                                                                    <li class="user_activity">
                                                                        <c:choose>
                                                                        <c:when test="${sessionScope.loggedInUser != null }">
                                                                        <a href="${pageContext.request.contextPath}/adminUserSummaryProfile?userName=${sessionScope.loggedInUser.username}">
                                                                            <img src="${pageContext.request.contextPath}/displayCompanyLogo/${sessionScope.loggedInUser.username}" class="profile-circle" border="0" />
                                                                            <span>${sessionScope.loggedInUser.username}</span>
                                                                        </a>
                                                                        <ul>
                                                                            <!--<li><a href="${pageContext.request.contextPath}/welcometodashboards" id='my_account'>My Dashboard</a>
                                                                        </li>-->
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/welcometodashboards" id='my_profile'>My Profile</a>
                                                                        </li>
                                                                        <c:choose>
                                                                            <c:when test="${sessionScope.loggedInUser != null && (sessionScope.loggedInRole=='ROLE_CONSUMER' || sessionScope.loggedInRole=='ROLE_ADMIN')}">
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/my-watchlist.jsp" id='view_my_watchlist'>My Watchlist</a>
                                                                            </li>
                                                                            
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/my-price-alert.jsp" id='view_my_pricealert'>My Alerts</a>
                                                                            </li>
                                                                            </c:when>
                                                                        </c:choose>
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/view/subscribe.jsp" id='my_subscription'>Subscribe</a>
                                                                        </li>
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/view/my-portfolio.jsp" id='my_portfolio'>My Portfolio</a>
                                                                        </li>
                                                                        <li>
                                                                            <a class="settings" href="${pageContext.request.contextPath}/displayAccountSettings?userName=${sessionScope.loggedInUser.username}">Settings</a>
                                                                        </li>
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/logout" id="logout-confirm">Logout</a>
                                                                        </li>
                                                                        </ul>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                    <div class="hd-right">
                                                                        <a class="cd-signin" href="#">
                                                                            <i class="fa fa-user"></i> Login
                                                                        </a>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </li>
                                                    </ul>
                                                    <ul class="sf-menu visible-xs">
                                                        <li>
                                                            <a id="sidemenu" title="Menu" class="mobile_menu" href="javascript:void(0);">
                                                                <i class="fa fa-navicon"></i>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <script type="text/javascript">
                                                    function userCheck(checktype) {
                                                        if (checktype.match('logout') && checktype != '') {
                                                            window.location.href = "${pageContext.request.contextPath}/<%=RequestConstans.Login.LOGOUT%>";
                                                        } else {
                                                            window.location.href = "${pageContext.request.contextPath}/<%=RequestConstans.Login.FORGOT_PASSWORD%>";
                                                        }
                                                    }

                                                    function homePage() {
                                                        window.location.href = "${pageContext.request.contextPath}/<%=RequestConstans.Login.MY_HOME_PAGE%>?RaYUnA=${finVen:encrypt(myusername)}";
                                                    }
                                                </script>
                                            </div>
                                            </div>
                                                    </div>
                                        <div id="fv_sub_header">
                                           <form action="/view/company-profile.jsp" class="elastic-search desktop_variant" method="GET">
                                               <div class="input-group">
                                                   <input id="txtSearchBox" class="form-control" autocomplete="off" name="searchKeyword" type="text" placeholder="Search stocks & its quote">
                                                   <div class="input-group-addon" style="width:40px;"><span class="glyphicon glyphicon-search"><input disabled="disabled" name="txtSearchBox" class="submit-button" type="submit" /></span></div>
                                                   <div class="input-group-addon close-btn"><span class="glyphicon glyphicon-remove"><a href="javascript:;"></a></span></div>
                                               </div>
                                               <div class="suggestions" style="display:none" id="tblSuggestions">
                                                   <div id="tableBodyAutocomplete">
                                                   </div>
                                               </div>
                                           </form>
                                        </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                        </div>
                                    </nav>
                                    </div>
                            </div>
                    </header>


                    <c:if test="${sessionScope.loggedInUser != null && !param.hideTabsAfterLogIn == 'true'}"> 
                        <div class="tab-container">
                                <div class="tab-navigation">
                                    <ul>
                                    <c:choose>
                                        <c:when test="${sessionScope.loggedInRole == 'ROLE_CONSUMER' }">
                                            <li><a class="active" href="${pageContext.request.contextPath}/<%=RequestConstans.Consumer.CONSUMER_MY_PROFILE%>">
                                            <span>My Profile</span>
                                            </a>
                                            </li>
                                            <li><a class="#" href="#"><span>My Blog</span></a>
                                            </li>
                                            <li><a class="#" href="${pageContext.request.contextPath}/<%=RequestConstans.Consumer.CONSUMER_INVITE_AN_RFP%>"><span>Invite
                                                            an RFP</span></a>
                                            </li>
                                            <li><a class="#" href="#"><span>My History</span></a>
                                            </li>
                                            <li><a class="#" href="#"><span>My Statistics</span></a>
                                            </li>
                                            <li style="display: none;"><a class="#" href="#"><span>Invite Your Team</span></a>
                                            </li>
                                        </c:when>
                                    </c:choose>
                                    <ul>
                                </div>
                        </div>
                    </c:if>



                    <nav class="side-menu">
                        <ul class="side-menu-list">
                            <li class="user_activity">
                                <c:choose>
                                    <c:when test="${sessionScope.loggedInUser != null }">
                                        <a href="${pageContext.request.contextPath}/adminUserSummaryProfile?userName=${sessionScope.loggedInUser.username}" class="account">
                                            <img src="${pageContext.request.contextPath}/displayCompanyLogo/${sessionScope.loggedInUser.username}" class="profile-circle" border="0" />
                                            <span title="${sessionScope.loggedInUser.username}">${sessionScope.loggedInUser.username}</span>
                                        </a>

                                        <i class="fa fa-chevron-down"></i>
                                        <ul class="child-main-menu">
                                                <!--<li><a href="${pageContext.request.contextPath}/welcometodashboards" id='my_account'>My Dashboard</a>
                                                                        </li>-->
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/welcometodashboards" id='my_profile'>My Profile</a>
                                                                        </li>
                                                                        <c:choose>
                                                                            <c:when test="${sessionScope.loggedInUser != null && (sessionScope.loggedInRole=='ROLE_CONSUMER' || sessionScope.loggedInRole=='ROLE_ADMIN')}">
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/view/my-watchlist.jsp" id='view_my_watchlist'>My Watchlist</a>
                                                                        </li> 
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/view/my-price-alert.jsp" id='view_my_pricealert'>My Alerts</a>
                                                                        </li>
                                                                        </c:when>
                                                                        </c:choose>
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/view/subscribe.jsp" id='my_subscription'>Subscribe</a>
                                                                        </li>
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/view/my-portfolio.jsp" id='my_portfolio'>My Portfolio</a>
                                                                        </li>
                                                                        <li>
                                                                            <a class="settings" href="${pageContext.request.contextPath}/displayAccountSettings?userName=${sessionScope.loggedInUser.username}">Settings</a>
                                                                        </li>
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/logout" id="logout-confirm">Logout</a>
                                                                        </li>
                                            </li>
                                        </ul>
                                        </c:when>
                                        <c:otherwise>
                                        <div class="hd-right">
                                            <a class="cd-signin" href="#"> 
                                                Login
                                                <i class="fa fa-user"></i>
                                            </a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </li>

                            <li>
                                <a href="#">Research Reports</a>
                                <ul class="child-main-menu">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/view/equity_research_report_vendor.jsp?researchReportType=Equity/Company Research">
                                            Equity/Company Research
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/view/sector-research.jsp">
                                            Sector Research
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/view/macro-research.jsp">
                                            Macro Research
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/view/debt-market-research.jsp">
                                            Debt Mkt Research
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/view/fund-etf-research.jsp">
                                            Fund/ETF Research
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/view/brokers-analysts.jsp">Brokers/Analysts</a>
                            </li>
                            <li>
                                <a href="javascript:void(0);">More <i class="fa fa-chevron-down"></i></a>
                                <ul class="child-main-menu">
                                    <li><a href="${pageContext.request.contextPath}/view/brochures.jsp">Brochures</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/view/white-papers.jsp">Whitepapers</a>
                                    </li>

                                    <li>
                                        <a href="${pageContext.request.contextPath}/view/blogs.jsp">Blogs</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/view/case-studies.jsp">Case
                                        Studies</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/view/spotlights.jsp">Spotlights</a>
                                    </li>
                                </ul>
                            </li>


                            <li>
                                <a href="${pageContext.request.contextPath}/view/contact-us.jsp">
                                    <i class="fa fa-address-book"></i> Contact
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <div class="screen-block-hamburger"></div>
                    <script type="text/javascript">
                        $(document).ready(function() {
                            $('.screen-block-hamburger').on('click', function() {
                                $('.screen-block-hamburger').hide();
                                $('.side-menu').removeClass('side-menu-open');
                            });

                            $('#sidemenu').click(function() {
                                $('.screen-block-hamburger').show();
                                $('.side-menu').addClass('side-menu-open');
                            });

                            $('.side-menu-list li').on('click', function() {
                                if($(this).hasClass('active')) {
                                    $(this).removeClass('active');
                                } else {
                                    $(this).addClass('active');
                                }
                            });
                        });
                    </script>

    <script type="text/javascript">
        /*$('#fv_sub_header #txtSearchBox').on('focus', function() {
            $('#fv_sub_header form').addClass('open');
        });

        $('#fv_sub_header #txtSearchBox').on('blur', function() {
            $('#fv_sub_header form').removeClass('open');
        });*/
    </script>