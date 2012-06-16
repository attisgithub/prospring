package taskscheduling.quartz.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringQuartzIntegrationExample {

	public static void main(String[] args) {
//		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:taskscheduling/quartz/spring/quartz-simple.xml");
		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:taskscheduling/quartz/spring/quartz-cron.xml");
	}

}
