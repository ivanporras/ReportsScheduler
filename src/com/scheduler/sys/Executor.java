package com.scheduler.sys;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.lib.email.Email;
import com.scheduler.vs.SchedulerFrame;

public class Executor implements Job {
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		String fromDate="";
		try{
			Freader freader=new Freader();
			fromDate=freader.reader();
			System.out.println(SchedulerFrame.getVmArgs());
			System.out.println(SchedulerFrame.getAppArgs());
			if(fromDate!="" && fromDate!=null){
				System.out.println("EXEC TASK");
				//Executes JAR with default the date as a default app argument
				Runtime.getRuntime().exec("java -Xms256m -Xmx512m "+SchedulerFrame.getVmArgs()+" -jar "+SchedulerFrame.getFile() +" "+fromDate+" "+SchedulerFrame.getAppArgs());
			}else{
				Email email=new Email();
				email.setRecipients(new String[]{"ivan.porras@sanmina.com,cristian.delgado@sanmina.com,luis.hurtado@sanmina.com,jose.fonseca@sanmina.com"});
				email.send(null, "The report wasn't created because the date couldn't be loaded.", "CREE XML ERROR");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
