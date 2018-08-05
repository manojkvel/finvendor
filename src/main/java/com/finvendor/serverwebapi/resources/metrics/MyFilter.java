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

	FilterConfig filterConfig = null;
	private MetricService metricService;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		metricService = (MetricService) WebApplicationContextUtils
				.getRequiredWebApplicationContext(filterConfig.getServletContext()).getBean("metricService");
		requestMap = new HashMap<String, MetricsType>();
		requestMap.put("researchReports", MetricsType.EQTY_RESEARCH);
		requestMap.put("downloadResearchReports", MetricsType.DOWNLOAD_EQTY_RESEARCH);
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = ((HttpServletRequest) servletRequest);
		String req = httpRequest.getRequestURI();
		String reqTag = req.substring(req.lastIndexOf("/") + 1);
		MetricsType metricsType = requestMap.get(reqTag);
		if (metricsType != null) {
			User loggedInUser = (User) httpRequest.getSession().getAttribute("loggedInUser");
			String userName =null;
			if (loggedInUser == null) {
				userName="UNKNOWN";
			}else {
				userName = loggedInUser.getUsername();
			}
			metricService.increaseCount(userName,metricsType);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

}
