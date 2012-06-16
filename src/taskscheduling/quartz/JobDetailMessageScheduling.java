package taskscheduling.quartz;

import java.util.Date;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class JobDetailMessageScheduling {

	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		
		JobDetail jobDetail = new JobDetail("messageJob", Scheduler.DEFAULT_GROUP, MessageJob.class);
		Map map = jobDetail.getJobDataMap();
		map.put("message", "This is a messsage from Quartz");
		map.put("jobDetailMessage", "A jobDetail message");
		
		Trigger trigger = new SimpleTrigger("simpleTrigger", Scheduler.DEFAULT_GROUP, new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY, 3000);
		scheduler.scheduleJob(jobDetail, trigger);
		
	}

}
