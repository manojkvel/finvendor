package com.finvendor.serverwebapi.startup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.finvendor.common.util.LogUtil;
import com.finvendor.server.companyprofile.pricealert.job.PriceUpdateJob;

public class FvStartupService implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			Properties prop = new Properties();
			String propFileName = "/WEB-INF/finvendor.properties";
 
			InputStream  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
    		//Get file from resources folder
			String enable_price_alert_scheduler = (String) prop.get("enable_price_alert_scheduler"); 
			String price_alert_cron = (String) prop.get("price_alert_cron");    	
			String price_update_uri = (String) prop.get("price_update_uri");
			String price_alert_uri = (String) prop.get("price_alert_uri");
			
			
			JobDetail jobDetails = new JobDetail();
			jobDetails.setName("PriceAlertJob");
			jobDetails.setJobClass(PriceUpdateJob.class);
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("priceUpdateUrl", price_update_uri);
			jobDataMap.put("price_alert_uri", price_alert_uri);
			jobDetails.setJobDataMap(jobDataMap);
			CronTrigger trigger = new CronTrigger();
			trigger.setName("PriceAlert Trigger");
			trigger.setCronExpression(price_alert_cron);
			
			// schedule it
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			if ("true".equals(enable_price_alert_scheduler)) {
				scheduler.start();
				scheduler.scheduleJob(jobDetails, trigger);
			} else {
				LogUtil.logInfo("Unable to start scheduler as it is desable where disable flag=" + enable_price_alert_scheduler);
			}

			LogUtil.logInfo("*** Scheulder Started with following properties...");
			LogUtil.logInfo("enable_price_alert_scheduler=" + enable_price_alert_scheduler);
			LogUtil.logInfo("price_alert_cron=" + price_alert_cron);
			LogUtil.logInfo("price_update_uri=" + price_update_uri);
			LogUtil.logInfo("price_alert_uri=" + price_alert_uri);
			
		} catch (ParseException | SchedulerException | IOException e) {
			LogUtil.logError("*** Error in FvStartupService (Startup Task) - ErrMsg=" + e.getMessage());
		}
	}
}