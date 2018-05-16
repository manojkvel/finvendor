package com.finvendor.serverwebapi.startup;

import java.text.ParseException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.finvendor.server.scheduler.jobs.PriceUpdateJob;

public class FvStartupService implements ServletContextListener {

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
			trigger.setCronExpression("0 0/5 * * * ?");
			//schedule it
	    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//	    	scheduler.start();
//	    	scheduler.scheduleJob(job, trigger);
		} catch (ParseException | SchedulerException e) {
			e.printStackTrace();
		}
    	System.out.println("PriceAlert TimerTask started");
	}
}