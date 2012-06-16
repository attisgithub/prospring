package taskscheduling.timer;

import java.util.Calendar;
import java.util.Timer;

/**
 * Fixed Rate. The delay time refers to the first execution time. Following tasks will not be delayed if the task before them delays.
 * 
 * @author Attis
 *
 */
public class FixedRateScheduling {

	public static void main(String[] args) {
		Timer t = new Timer();
		Calendar cal = Calendar.getInstance();
		cal.set(2012, Calendar.MAY, 19, 12, 37, 30);
		t.scheduleAtFixedRate(new HelloWorldTask(), cal.getTime(), 1000);
	}

}
