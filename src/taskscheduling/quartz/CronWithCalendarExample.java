package taskscheduling.quartz;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

public class CronWithCalendarExample {

	public static void main(String[] args) throws SchedulerException, ParseException {
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		
		Calendar cal = Calendar.getInstance();
		cal.set(2012, Calendar.MAY, 19);
		
		HolidayCalendar calendar = new HolidayCalendar();
		calendar.addExcludedDate(cal.getTime());
		
		scheduler.addCalendar("xmasCalendar", calendar, true, false);
		
		JobDetail jobDetail = new JobDetail("messageJob", Scheduler.DEFAULT_GROUP, MessageJob.class);
		Map map = jobDetail.getJobDataMap();
		map.put("message", "This is a message from Quartz");
		
		String cronExpression = "3/5 * 14,15,16,17 * * ?";
		
		Trigger trigger = new CronTrigger("cronTrigger", Scheduler.DEFAULT_GROUP, cronExpression);
		trigger.setCalendarName("xmasCalendar");
		
		scheduler.scheduleJob(jobDetail, trigger);
		
	}

}
