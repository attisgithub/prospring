package taskscheduling.quartz;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class HelloWorldScheduling {

	public static void main(String[] args) throws SchedulerException, InterruptedException {
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		JobDetail jobDetail = new JobDetail("helloWorldJob", Scheduler.DEFAULT_GROUP, HelloWorldJob.class);
		Trigger trigger = new SimpleTrigger("simpleTrigger", Scheduler.DEFAULT_GROUP, new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY, 1000);
		
		scheduler.scheduleJob(jobDetail, trigger);
		Thread.sleep(5000);
		scheduler.pauseJob("helloWorldJob", Scheduler.DEFAULT_GROUP);
	}

}
