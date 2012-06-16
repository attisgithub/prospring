package taskscheduling.quartz;

import java.text.ParseException;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerExample {

	public static void main(String[] args) throws SchedulerException, ParseException {
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		JobDetail jobDetail = new JobDetail("messageJob", Scheduler.DEFAULT_GROUP, MessageJob.class);
		Map map = jobDetail.getJobDataMap();
		map.put("message", "This is a message from Quartz");
		
		String cronExpression = "3/5 * 14,15,16,17 * * ?";
		
		Trigger trigger = new CronTrigger("cronTrigger", Scheduler.DEFAULT_GROUP, cronExpression);
	
		scheduler.scheduleJob(jobDetail, trigger);
		
	}

}
