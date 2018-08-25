package com.finvendor.serverwebapi.resources.metrics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.finvendor.server.metrics.service.MetricService;
import com.finvendor.server.metrics.service.MetricsType;

public class MyFilter implements Filter {
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
        HttpServletRequest httpRequest = ((HttpServletRequest) servletRequest);
        String req = httpRequest.getRequestURI();///system/api/researchReports
        String reqTag = req.substring(req.lastIndexOf("/") + 1);
        reqTag = reqTag.isEmpty() ? HOME_PAGE : reqTag;
        MetricsType metricsType = requestMap.get(reqTag);
        if (metricsType != null) {
            User loggedInUser = (User) httpRequest.getSession().getAttribute("loggedInUser");
            String userName;
            if (loggedInUser == null) {
                userName = "UNKNOWN";
            } else {
                userName = loggedInUser.getUsername();
            }
            metricService.increaseCount(userName, metricsType);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
