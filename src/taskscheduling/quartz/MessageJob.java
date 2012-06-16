package taskscheduling.quartz;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MessageJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Map<?, ?> properties = context.getMergedJobDataMap();
		System.out.println("Previous Fire Time: " + context.getPreviousFireTime());
		System.out.println("Current File Time: " + context.getFireTime());
		System.out.println("Next Fire Time: " + context.getNextFireTime());
		System.out.println(properties.get("message"));
		System.out.println(properties.get("jobDetailMessage"));
		System.out.println(properties.get("triggerMessage"));
		System.out.println("");
	}

}
