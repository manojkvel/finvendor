package com.finvendor.server.common.infra.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 
 * @author ayush on May 12, 2018
 */
public class SchedulerManager {
	private static volatile SchedulerManager instance;
	private static Scheduler scheduler;

	private SchedulerManager() {

	}

	public static SchedulerManager getInstance() throws SchedulerException {
		if (instance == null) {
			synchronized (SchedulerManager.class) {
				if (instance == null) {
					instance = new SchedulerManager();
					scheduler = new StdSchedulerFactory().getScheduler();
					scheduler.start();
				}
			}
		}
		return instance;
	}

	public void scheduleIt(JobDetail job, Trigger trigger) throws SchedulerException {
		scheduler.scheduleJob(job, trigger);
	}

	public void shutdown() throws SchedulerException {
		scheduler.shutdown();
	}
}
