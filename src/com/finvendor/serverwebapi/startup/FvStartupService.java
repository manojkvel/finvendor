package com.finvendor.serverwebapi.startup;

import java.text.ParseException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.finvendor.common.util.LogUtil;
import com.finvendor.server.companyprofile.pricealert.job.PriceUpdateJob;


public class FvStartupService implements ServletContextListener {

	private static final String EVERY_DAY_AT_8_PM = "0 0 20 * * ?";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	
	public void contextInitialized(ServletContextEvent arg0) {
		
		JobDetail job = new JobDetail();
    	job.setName("PriceAlertJob");
    	job.setJobClass(PriceUpdateJob.class);
    	
    	CronTrigger trigger = new CronTrigger();
    	trigger.setName("PriceAlert Trigger");
    	try {
    		trigger.setCronExpression(EVERY_DAY_AT_8_PM);
			//schedule it
	    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
	    	scheduler.start();
	    	scheduler.scheduleJob(job, trigger);
		} catch (ParseException | SchedulerException e) {
			LogUtil.logError("*** Error in FvStartupService (Startup Task) - ErrMsg="+e.getMessage());
		}
    	LogUtil.logInfo("*** PriceAlert TimerTask started ***");
	}
}