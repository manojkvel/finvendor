package com.finvendor.serverwebapi.resources.metrics;

import com.finvendor.server.metrics.service.MetricService;
import com.finvendor.server.metrics.service.MetricsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class.getName());
    private static Map<String, MetricsType> requestMap;
    private static final String RESEARCH_REPORTS = "researchReports";
    private static final String DOWNLOAD_RESEARCH_REPORTS = "downloadResearchReports";
    private static final String HOME_PAGE = "homepage";

    FilterConfig filterConfig = null;
    private MetricService metricService;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        metricService = (MetricService) WebApplicationContextUtils
                .getRequiredWebApplicationContext(filterConfig.getServletContext()).getBean("metricService");
        requestMap = new HashMap<String, MetricsType>();
        requestMap.put(RESEARCH_REPORTS, MetricsType.EQTY_RESEARCH);
        requestMap.put(DOWNLOAD_RESEARCH_REPORTS, MetricsType.DOWNLOAD_EQTY_RESEARCH);
        requestMap.put(HOME_PAGE, MetricsType.HOME_PAGE);

    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = ((HttpServletRequest) servletRequest);
        final String req = httpRequest.getRequestURI();///system/api/researchReports
        String reqTag = req.substring(req.lastIndexOf("/") + 1);
        reqTag = reqTag.isEmpty() ? HOME_PAGE : reqTag;
        final MetricsType metricsType = requestMap.get(reqTag);
        if (metricsType != null) {
            final User loggedInUser = (User) httpRequest.getSession().getAttribute("loggedInUser");
            String userName;
            if (loggedInUser == null) {
                userName = "UNKNOWN";
            } else {
                userName = loggedInUser.getUsername();
            }
            try {
                String clientIp = getClientIp(httpRequest);
                metricService.increaseCount(userName, metricsType,clientIp);
            } catch (Exception e) {
                logger.error("MyFilter -> Metrics Count  - Error has occurred while inserting request count to db", e);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

}
