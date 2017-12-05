package com.scheduler.sys;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import com.scheduler.vs.SchedulerFrame;

public class TriggerTask {
	private JobDetail job;
	private Scheduler scheduler;

	public void trigger(){
		try{
			job = JobBuilder.newJob(Executor.class).withIdentity("reportsExec", "group1").build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("reportsTrigger", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule(SchedulerFrame.getTime()+" ? * "+SchedulerFrame.getDays())).build();
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void stopJob(){
		try{
			scheduler.deleteJob(job.getKey());
			scheduler.shutdown();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
