<div class="inner-sidebar-wrap">
    <div class="sidebar-ctn-wrap cnt-ctn-wrap">
        <div class="head">
            <h3>PRODUCTS</h3>
        </div> 
        <div class="content" id="sidelinks">
            <dl class="accordion">
                <dt>
                    <a href="#">
                        Screener
                        <span class="fa fa-angle-right"></span>
                    </a>
                </dt>
                <dd>
                    <ul>
                        <li>
                            <a href="${pageContext.request.contextPath}/view/equity_research_report_vendor.jsp?researchReportType=Equity/Company Research">
                                Research Analyst's Recommendations
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/view/celebrity-investors-strategies.jsp">
                                Celebrity Investor's Strategies
                            </a>
                        </li>
                        
                        <li>
                            <a href="${pageContext.request.contextPath}/view/do-it-yourself.jsp">
                                Do It Yourself
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/view/sector-research.jsp">
                                Sector Screener
                            </a>
                        </li>
                    </ul>
                </dd>
                <ul class="parent_link">
                    <li>
                        <a href="${pageContext.request.contextPath}/view/markets.jsp">
                            Markets
                        </a>
                    </li>
                </ul>

                <ul class="parent_link">
                    <li>
                        <a href="${pageContext.request.contextPath}/view/pricing.jsp">
                            Pricing
                        </a>
                    </li>
                </ul>
                
                <dt>
                    <a href="#">
                        More
                        <span class="fa fa-angle-right"></span>
                    </a>
                </dt>
                <dd>
                    <ul class="accordion">
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
                </dd>
            </dl>
        </div>
    </div>
    <div class="sidebar-ctn-wrap cnt-ctn-wrap">
        <div class="head">
            <h3>ABOUT</h3>
        </div>
        <div class="content" id="sidelinks">
            <ul>
                <li><a href="${pageContext.request.contextPath}/view/about-us.jsp">About Us</a></li>
                <li style="display:none;"><a href="${pageContext.request.contextPath}/view/advisory-team.jsp">Advisory Team</a></li>
                <li data-div-id="Management"><a href="${pageContext.request.contextPath}/view/management-team.jsp">Management Team</a></li>
                <li data-div-id="Vision"><a href="${pageContext.request.contextPath}/view/vision-statement.jsp">Vision Statement</a></li>
            </ul>
        </div>
    </div>
    <div class="sidebar-ctn-wrap cnt-ctn-wrap">
        <div class="head">
            <h3>CONTACT</h3>
        </div>
        <div class="content">
            <ul class="contact">
                <li class="phone"><span class="fa fa-phone"></span> +91-7519312003</li>
                <li class="message"><a href="mailto:enquiry@finvendor.com"><span class="fa fa-envelope"></span> enquiry@finvendor.com</a></li>
                <li class="location">
                    <span class="fa fa-map-marker"></span>
                    <span>101, F-Wing, Kailash Gardens, Gauripada, Kalyan(west), Thane, Maharashtra, India, 42130</span>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="clearfix"></div>
<script type="text/javascript">
    (function($) {

        var allPanels = $('.accordion > dd').hide();

      $('.accordion > dt > a').click(function() {
        allPanels.slideUp();

        if($(this).parent().next().is(':visible')) {
            $(this).parent().next().slideUp();

        } else {
            $(this).parent().next().slideDown();
        }
        return false;
    });

  })(jQuery);
</script>